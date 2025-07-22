package org.vitacare.convenioservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.vitacare.convenioservice.model.Especialidade;
import org.vitacare.convenioservice.repository.EspecialidadeRepository;
import org.vitacare.dtos.healthplan.EspecialidadeEnum;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final EspecialidadeRepository especialidadeRepository;

    @Override
    public void run(String... args) throws Exception {
        // Verifica se já existem especialidades cadastradas
        if (especialidadeRepository.count() == 0) {
            // Cadastra todas as especialidades do enum
            for (EspecialidadeEnum especialidadeEnum : EspecialidadeEnum.values()) {
                Especialidade especialidade = new Especialidade();
                especialidade.setNome(especialidadeEnum);
                especialidadeRepository.save(especialidade);
            }
        }
    }
}