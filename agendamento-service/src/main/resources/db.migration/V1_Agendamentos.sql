-- Flyway Migration: Refatora a tabela de agendamentos para usar IDs e LocalDateTime

-- Renomeia a tabela para o plural
ALTER TABLE agendamento RENAME TO agendamentos;

-- Renomeia colunas existentes para o novo padrão
ALTER TABLE agendamentos RENAME COLUMN id TO id; -- Já está ok, mas por consistência
ALTER TABLE agendamentos RENAME COLUMN medico TO profissional_id;
ALTER TABLE agendamentos RENAME COLUMN especialidade TO especialidade_id;

-- Adiciona a nova coluna para data e hora combinadas
ALTER TABLE agendamentos ADD COLUMN data_hora_agendamento TIMESTAMP WITHOUT TIME ZONE;

-- Copia os dados das colunas antigas para a nova (se houver dados)
-- UPDATE agendamentos SET data_hora_agendamento = data_consulta + hora_consulta;

-- Remove as colunas antigas e denormalizadas
ALTER TABLE agendamentos DROP COLUMN data_consulta;
ALTER TABLE agendamentos DROP COLUMN hora_consulta;
ALTER TABLE agendamentos DROP COLUMN name_paciente;

-- Garante que a nova coluna não possa ser nula (faça isso depois de popular)
-- ALTER TABLE agendamentos ALTER COLUMN data_hora_agendamento SET NOT NULL;