package org.vitacare;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class cadastroApiService {
    public static void main(String[] args) {
        SpringApplication.run(cadastroApiService.class, args);
    }
}
