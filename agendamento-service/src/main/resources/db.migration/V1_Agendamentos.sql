-- Flyway Migration: Refatora a tabela de agendamentos para usar IDs e LocalDateTime

-- Renomeia a tabela para o plural
EXEC sp_rename 'agendamento', 'agendamentos';

-- Renomeia colunas existentes para o novo padrão
EXEC sp_rename 'agendamentos.medico', 'profissional_id', 'COLUMN';
EXEC sp_rename 'agendamentos.especialidade', 'especialidade_id', 'COLUMN';

-- Adiciona a nova coluna para data e hora combinadas
ALTER TABLE agendamentos ADD data_hora_agendamento DATETIME2;

-- Copia os dados das colunas antigas para a nova (se houver dados)
-- UPDATE agendamentos SET data_hora_agendamento = data_consulta + hora_consulta;

-- Remove as colunas antigas e denormalizadas
ALTER TABLE agendamentos DROP COLUMN data_consulta;
ALTER TABLE agendamentos DROP COLUMN hora_consulta;
ALTER TABLE agendamentos DROP COLUMN name_paciente;

-- Garante que a nova coluna não possa ser nula (faça isso depois de popular)
-- ALTER TABLE agendamentos ALTER COLUMN data_hora_agendamento DATETIME2 NOT NULL;