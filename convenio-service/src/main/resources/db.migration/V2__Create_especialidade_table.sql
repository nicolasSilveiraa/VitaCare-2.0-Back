IF NOT EXISTS (SELECT * FROM sys.tables t
               JOIN sys.schemas s ON t.schema_id = s.schema_id
               WHERE t.name = 'especialidade' AND s.name = 'convenio_service')
BEGIN
CREATE TABLE convenio_service.especialidade (
                                                id   BIGINT IDENTITY(1,1) NOT NULL,
                                                nome VARCHAR(255) NOT NULL UNIQUE,
                                                CONSTRAINT pk_especialidade PRIMARY KEY (id)
);
END