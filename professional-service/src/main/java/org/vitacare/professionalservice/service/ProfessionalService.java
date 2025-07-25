package org.vitacare.professionalservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.vitacare.dtos.professional.ProfessionalRequestDTO;
import org.vitacare.dtos.professional.ProfessionalDetailDTO;
import org.vitacare.dtos.professional.ProfessionalSummaryDTO;
import org.vitacare.dtos.professional.SpecialtySummaryDTO;
import org.vitacare.professionalservice.exception.ResourceAlreadyExistsException;
import org.vitacare.professionalservice.exception.ResourceNotFoundException;
import org.vitacare.professionalservice.model.Professional;
import org.vitacare.professionalservice.model.Specialty;
import org.vitacare.professionalservice.repository.ProfessionalRepository;
import org.vitacare.professionalservice.repository.SpecialtyRepository;
import org.vitacare.professionalservice.specification.ProfessionalSpecification;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfessionalService {

    private final ProfessionalRepository professionalRepository;
    private final SpecialtyRepository specialtyRepository;

    public List<ProfessionalDetailDTO> findProfessionals(String name, Integer specialtyId) {

        Specification<Professional> spec = ProfessionalSpecification.hasName(name);

        if (specialtyId != null) {
            spec = spec.and(ProfessionalSpecification.hasSpecialty(specialtyId));
        }
        return professionalRepository.findAll(spec).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProfessionalDetailDTO findProfessionalById(Long id) {
        Professional professional = professionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado com o ID: " + id));

        return convertToDTO(professional);
    }

    public ProfessionalSummaryDTO findProfessionalSummaryById(Long id) {
        ProfessionalDetailDTO professionalDetail = this.findProfessionalById(id);

        List<String> specialtyNames = professionalDetail.specialties().stream()
                .map(SpecialtySummaryDTO::name)
                .collect(Collectors.toList());

        return new ProfessionalSummaryDTO(
                professionalDetail.id(),
                professionalDetail.fullName(),
                specialtyNames
        );
    }

    public ProfessionalDetailDTO createProfessional(ProfessionalRequestDTO requestDTO) {

        if (professionalRepository.findByProfessionalLicense(requestDTO.professionalLicense()).isPresent()) {
            throw new ResourceAlreadyExistsException("Já existe um profissional com a licença: " + requestDTO.professionalLicense());
        }

        List<Specialty> foundSpecialties = specialtyRepository.findAllById(requestDTO.specialtyIds());

        if(foundSpecialties.size() != requestDTO.specialtyIds().size()) {
            throw new ResourceNotFoundException("Uma ou mais especialidades fornecidas não foram encontradas.");

        }
        Professional newProfessional = new Professional();
        newProfessional.setFullName(requestDTO.fullName());
        newProfessional.setProfessionalLicense(requestDTO.professionalLicense());
        newProfessional.setSpecialties(new HashSet<>(foundSpecialties));

        Professional savedProfessional = professionalRepository.save(newProfessional);

        return convertToDTO(savedProfessional);
    }

    public ProfessionalDetailDTO patchProfessional(Long id, Map<String, Object> updates) {
        Professional professionalToUpdate = professionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado com o ID: " + id));

        updates.forEach((key, value) -> {
            switch (key) {
                case "nomePacientefullName":
                    professionalToUpdate.setFullName((String) value);
                    break;
                case "professionalLicense":
                    professionalToUpdate.setProfessionalLicense((String) value);
                    break;
                case "specialtyIds":
                    @SuppressWarnings("unchecked")
                    List<Integer> specialtyIds = (List<Integer>) value;
                    List<Specialty> specialties = specialtyRepository.findAllById(specialtyIds);
                    if (specialties.size() != specialtyIds.size()) {
                        throw new ResourceNotFoundException("Uma ou mais especialidades fornecidas na atualização não foram encontradas.");
                    }
                    professionalToUpdate.setSpecialties(new HashSet<>(specialties));
                    break;
                default:
                     throw new IllegalArgumentException("Campo inválido para atualização: " + key);
            }
        });

        Professional updatedProfessional = professionalRepository.save(professionalToUpdate);
        return convertToDTO(updatedProfessional);
    }


    public void deleteProfessional(Long id) {
        if (!professionalRepository.existsById(id)) {
            throw new ResourceNotFoundException("Profissional não encontrado com o ID: " + id);
        }
        professionalRepository.deleteById(id);
    }

    private ProfessionalDetailDTO convertToDTO(Professional professional) {
        List<SpecialtySummaryDTO> specialtySummaries = professional.getSpecialties().stream()
                .map(specialty -> new SpecialtySummaryDTO(
                    specialty.getId(),
                    specialty.getName()))
                .toList();


        return new ProfessionalDetailDTO(
                professional.getId(),
                professional.getFullName(),
                professional.getProfessionalLicense(),
                specialtySummaries
        );
    }
}
