
IF NOT EXISTS (SELECT * FROM sys.schemas WHERE name = 'auth_schema')
BEGIN
EXEC('CREATE SCHEMA auth_schema');
END
GO


IF NOT EXISTS (SELECT * FROM sys.tables t JOIN sys.schemas s ON t.schema_id = s.schema_id WHERE t.name = 'roles' AND s.name = 'auth_schema')
BEGIN
CREATE TABLE auth_schema.roles (
                                   id   INT IDENTITY(1,1) PRIMARY KEY,
                                   name VARCHAR(255) NOT NULL UNIQUE
);
END
GO


IF NOT EXISTS (SELECT * FROM sys.tables t JOIN sys.schemas s ON t.schema_id = s.schema_id WHERE t.name = 'users' AND s.name = 'auth_schema')
BEGIN
CREATE TABLE auth_schema.users (
                                   id                              BIGINT IDENTITY(1,1) PRIMARY KEY,
                                   email                           VARCHAR(255) NOT NULL UNIQUE,
                                   password                        VARCHAR(255) NOT NULL,
                                   enabled                         BIT,
                                   refresh_token                   VARCHAR(512),
                                   refresh_token_expiry            DATETIME2,
                                   password_reset_token            VARCHAR(255),
                                   password_reset_token_expiry     DATETIME2,
                                   created_at                      DATETIME2
);
END
GO


IF NOT EXISTS (SELECT * FROM sys.tables t JOIN sys.schemas s ON t.schema_id = s.schema_id WHERE t.name = 'users_roles' AND s.name = 'auth_schema')
BEGIN
CREATE TABLE auth_schema.users_roles (
                                         user_id BIGINT  NOT NULL,
                                         role_id INT     NOT NULL,
                                         PRIMARY KEY (user_id, role_id),
                                         CONSTRAINT fk_users_roles_to_users FOREIGN KEY (user_id) REFERENCES auth_schema.users (id) ON DELETE CASCADE,
                                         CONSTRAINT fk_users_roles_to_roles FOREIGN KEY (role_id) REFERENCES auth_schema.roles (id) ON DELETE CASCADE
);
END
GO


IF NOT EXISTS (SELECT 1 FROM auth_schema.roles WHERE id = 1)
BEGIN
    SET IDENTITY_INSERT auth_schema.roles ON;
INSERT INTO auth_schema.roles (id, name) VALUES (1, 'ROLE_ADMIN');
SET IDENTITY_INSERT auth_schema.roles OFF;
END
GO

IF NOT EXISTS (SELECT 1 FROM auth_schema.roles WHERE id = 2)
BEGIN
    SET IDENTITY_INSERT auth_schema.roles ON;
INSERT INTO auth_schema.roles (id, name) VALUES (2, 'ROLE_MEDICO');
SET IDENTITY_INSERT auth_schema.roles OFF;
END
GO

IF NOT EXISTS (SELECT 1 FROM auth_schema.roles WHERE id = 3)
BEGIN
    SET IDENTITY_INSERT auth_schema.roles ON;
INSERT INTO auth_schema.roles (id, name) VALUES (3, 'ROLE_ENFERMEIRA');
SET IDENTITY_INSERT auth_schema.roles OFF;
END
GO

IF NOT EXISTS (SELECT 1 FROM auth_schema.roles WHERE id = 4)
BEGIN
    SET IDENTITY_INSERT auth_schema.roles ON;
INSERT INTO auth_schema.roles (id, name) VALUES (4, 'ROLE_RECEPCIONISTA');
SET IDENTITY_INSERT auth_schema.roles OFF;
END
GO


IF NOT EXISTS (SELECT 1 FROM auth_schema.users WHERE id = 1)
BEGIN
    SET IDENTITY_INSERT auth_schema.users ON;
INSERT INTO auth_schema.users (id, email, password, enabled)
VALUES (1, 'matheos.kskb@gmail.com', '$2a$10$VYPwHokSJnwyC3Oh4fqade8qRpAfPZimgej2bms36xqWbykJjqyjW', 1);
SET IDENTITY_INSERT auth_schema.users OFF;
END
GO