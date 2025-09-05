package com.pracasustentavel.praca_sustentavel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(dateTimeProviderRef = "dateTimeProvider")
@SpringBootApplication
public class PracaSustentavelApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracaSustentavelApplication.class, args);
    }

}
