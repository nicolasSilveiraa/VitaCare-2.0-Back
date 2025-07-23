package org.vitacare.authservice.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.vitacare.authservice.dto.*;
import org.vitacare.authservice.exception.InvalidTokenException;
import org.vitacare.authservice.exception.UserAlreadyExistsException;
import org.vitacare.authservice.model.Role;
import org.vitacare.authservice.model.User;
import org.vitacare.authservice.repository.RoleRepository;
import org.vitacare.authservice.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final long refreshTokenExpirationMs;
    private final EmailService emailService;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder, JwtService jwtService,
                       AuthenticationManager authenticationManager,
                       @Value("${jwt.refresh-token.expiration.ms}") long refreshTokenExpirationMs, EmailService emailService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.refreshTokenExpirationMs = refreshTokenExpirationMs;
        this.emailService = emailService;
    }


    public void register(RegisterRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Ja existe um usuario com este email.");

        }

        Role userRole = roleRepository.findByName(request.getRoleName())
                .orElseThrow(() -> new IllegalStateException("O perfil " + request.getRoleName() + " não é valido"));



        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .enabled(true)
                .roles(Set.of(userRole))
                .build();

        userRepository.save(user);
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado após autenticar"));

        var userDetails = new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList())
        );

        Map<String, Object> extraClaims = Map.of(
                "roles", userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())
        );

        String accessToken = jwtService.generateToken(extraClaims, userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        user.setRefreshToken(refreshToken);
        LocalDateTime expiryDate = LocalDateTime.now().plusNanos(refreshTokenExpirationMs * 1000000);
        user.setRefreshTokenExpiry(expiryDate);
        userRepository.save(user);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthResponse refreshToken(RefreshTokenRequest request) {
        String refreshTokenFromRequest = request.getRefreshToken();

        String userEmail = jwtService.extractUsername(refreshTokenFromRequest);

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado a partir do refresh token"));

        if (!refreshTokenFromRequest.equals(user.getRefreshToken()) ||
                user.getRefreshTokenExpiry().isBefore(LocalDateTime.now())) {

            user.setRefreshToken(null);
            user.setRefreshTokenExpiry(null);
            userRepository.save(user);

            throw new InvalidTokenException("Refresh token inválido ou expirado. Por favor, faça login novamente."); // Use sua exceção customizada
        }

        var userDetails = new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList())
        );

        String newAccessToken = jwtService.generateToken(userDetails);

        return AuthResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(refreshTokenFromRequest)
                .build();
    }

    public void logout(RefreshTokenRequest request) {
        String refreshTokenFromRequest = request.getRefreshToken();

        if(refreshTokenFromRequest == null || refreshTokenFromRequest.isEmpty()) {
            return;
        }

        userRepository.findByRefreshToken(refreshTokenFromRequest)
                .ifPresent(user -> {
                    user.setRefreshToken(null);
                    user.setRefreshTokenExpiry(null);
                    userRepository.save(user);
                });
    }

    public void forgotPassword(ForgotPasswordRequest request) {
        userRepository.findByEmail(request.getEmail()).ifPresent(user -> {
            String token = UUID.randomUUID().toString();
            user.setPasswordResetToken(token);
            user.setPasswordResetTokenExpiry(LocalDateTime.now().plusHours(10));
            userRepository.save(user);

            String resetUrl = "http://localhost:4200/reset-password?token=" + token;
            logger.info("Forgot Password Reset URL: " + resetUrl);

            emailService.sendPasswordResetEmail(user.getEmail(), token);
        });
    }

    public void resetPassword(ResetPasswordRequest request) {
        User user = userRepository.findByPasswordResetToken(request.getToken())
                .orElseThrow(() -> new InvalidTokenException("Token de reset inválido ou não encontrado."));

        if (user.getPasswordResetTokenExpiry().isBefore(LocalDateTime.now())) {
            throw new InvalidTokenException("Token de reset expirado. Por favor, solicite um novo.");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        user.setPasswordResetToken(null);
        user.setPasswordResetTokenExpiry(null);

        userRepository.save(user);
    }
}