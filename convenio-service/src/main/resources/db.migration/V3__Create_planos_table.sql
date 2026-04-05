IF NOT EXISTS (SELECT * FROM sys.tables t
               JOIN sys.schemas s ON t.schema_id = s.schema_id
               WHERE t.name = 'planos' AND s.name = 'convenio_service')
BEGIN
CREATE TABLE convenio_service.planos (
                                         id          BIGINT IDENTITY(1,1) NOT NULL,
                                         nome        VARCHAR(255),
                                         id_convenio BIGINT,
                                         CONSTRAINT pk_planos PRIMARY KEY (id),
                                         CONSTRAINT fk_planos_to_convenios FOREIGN KEY (id_convenio)
                                             REFERENCES convenio_service.convenios (id) ON DELETE CASCADE
);
END

IF NOT EXISTS (SELECT * FROM sys.tables t
               JOIN sys.schemas s ON t.schema_id = s.schema_id
               WHERE t.name = 'plano_especialidade' AND s.name = 'convenio_service')
BEGIN
CREATE TABLE convenio_service.plano_especialidade (
                                                      plano_id         BIGINT NOT NULL,
                                                      especialidade_id BIGINT NOT NULL,
                                                      CONSTRAINT pk_plano_especialidade PRIMARY KEY (plano_id, especialidade_id),
                                                      CONSTRAINT fk_plano_esp_to_planos FOREIGN KEY (plano_id)
                                                          REFERENCES convenio_service.planos (id) ON DELETE CASCADE,
                                                      CONSTRAINT fk_plano_esp_to_especialidade FOREIGN KEY (especialidade_id)
                                                          REFERENCES convenio_service.especialidade (id)
);
END