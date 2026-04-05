IF NOT EXISTS (SELECT * FROM sys.schemas WHERE name = 'convenio_service')
    EXEC('CREATE SCHEMA convenio_service');

IF NOT EXISTS (SELECT * FROM sys.tables t
               JOIN sys.schemas s ON t.schema_id = s.schema_id
               WHERE t.name = 'convenios' AND s.name = 'convenio_service')
BEGIN
    CREATE TABLE convenio_service.convenios (
        id    BIGINT IDENTITY(1,1) NOT NULL,
        nome  VARCHAR(255),
        cnpj  VARCHAR(255),
        CONSTRAINT pk_convenios PRIMARY KEY (id)
    );
END