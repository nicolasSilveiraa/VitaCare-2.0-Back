IF NOT EXISTS (SELECT * FROM sys.schemas WHERE name = 'paciente_comando_schema')
    EXEC('CREATE SCHEMA paciente_comando_schema');

IF NOT EXISTS (SELECT * FROM sys.tables t
               JOIN sys.schemas s ON t.schema_id = s.schema_id
               WHERE t.name = 'pacientes' AND s.name = 'paciente_comando_schema')
BEGIN
CREATE TABLE paciente_comando_schema.pacientes
(
    id              BIGINT IDENTITY(1,1) NOT NULL,
    nome            VARCHAR(255),
    data_nascimento DATE,
    sexo            VARCHAR(255),
    endereco        VARCHAR(255),
    possui_convenio BIT,
    plano_id        BIGINT,
    cpf             VARCHAR(255),
    CONSTRAINT pk_pacientes PRIMARY KEY (id)
);
END