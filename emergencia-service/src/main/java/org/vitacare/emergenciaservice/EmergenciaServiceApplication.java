package org.vitacare.emergenciaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "org.vitacare")
public class EmergenciaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmergenciaServiceApplication.class, args);
    }
}