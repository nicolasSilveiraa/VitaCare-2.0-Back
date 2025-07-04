-- Este script insere dados de teste na tabela 'templates'
-- O schema é 'templates', então o nome completo é 'templates.templates'

-- Apaga dados antigos para garantir um início limpo a cada execução
DELETE FROM templates.templates;

-- Insere os novos templates de teste
INSERT INTO templates.templates (name, content) VALUES ('Email de Boas-Vindas', 'Olá {{userName}}, seja bem-vindo!');
INSERT INTO templates.templates (name, content) VALUES ('Notificação de Consulta', 'Sua consulta está agendada para {{appointmentDate}}.');