package org.vitacare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ConvenioApi {

    public static void main(String[] args) {
        SpringApplication.run(ConvenioApi.class, args);
    }
}
