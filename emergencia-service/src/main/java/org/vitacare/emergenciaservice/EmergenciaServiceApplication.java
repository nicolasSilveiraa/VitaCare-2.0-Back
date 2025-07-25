package org.vitacare.emergenciaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {"org.vitacare.emergenciaservice", "org.vitacare.security", "org.vitacare.dtos"}
)
@EnableFeignClients(basePackages = "org.vitacare")
public class EmergenciaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmergenciaServiceApplication.class, args);
    }
}