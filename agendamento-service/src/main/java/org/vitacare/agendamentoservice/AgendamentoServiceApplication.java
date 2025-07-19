package org.vitacare.agendamentoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "org.vitacare")

public class AgendamentoServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AgendamentoServiceApplication.class, args);
    }
}
