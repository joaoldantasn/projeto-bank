-- Inserir Endereços
INSERT INTO tb_endereco (cep, logradouro, cidade, bairro, numero) VALUES ('12345-678', 'Rua Exemplo', 'Cidade Exemplo', 'Bairro Exemplo', '123');
INSERT INTO tb_endereco (cep, logradouro, cidade, bairro, numero) VALUES ('23456-789', 'Avenida Teste', 'Cidade Teste', 'Bairro Teste', '456');
INSERT INTO tb_endereco (cep, logradouro, cidade, bairro, numero) VALUES ('34567-890', 'Travessa Demo', 'Cidade Demo', 'Bairro Demo', '789');
INSERT INTO tb_endereco (cep, logradouro, cidade, bairro, numero) VALUES ('45678-901', 'Rua Modelo', 'Cidade Modelo', 'Bairro Modelo', '101');
INSERT INTO tb_endereco (cep, logradouro, cidade, bairro, numero) VALUES ('56789-012', 'Avenida Exemplo 2', 'Cidade Exemplo 2', 'Bairro Exemplo 2', '202');
INSERT INTO tb_endereco (cep, logradouro, cidade, bairro, numero) VALUES ('67890-123', 'Travessa Teste 2', 'Cidade Teste 2', 'Bairro Teste 2', '303');
INSERT INTO tb_endereco (cep, logradouro, cidade, bairro, numero) VALUES ('78901-234', 'Rua Exemplar', 'Cidade Exemplar', 'Bairro Exemplar', '404');
INSERT INTO tb_endereco (cep, logradouro, cidade, bairro, numero) VALUES ('89012-345', 'Avenida Demo 2', 'Cidade Demo 2', 'Bairro Demo 2', '505');
INSERT INTO tb_endereco (cep, logradouro, cidade, bairro, numero) VALUES ('90123-456', 'Travessa Modelo 2', 'Cidade Modelo 2', 'Bairro Modelo 2', '606');
INSERT INTO tb_endereco (cep, logradouro, cidade, bairro, numero) VALUES ('01234-567', 'Rua Teste 3', 'Cidade Teste 3', 'Bairro Teste 3', '707');


-- Inserir Agências
INSERT INTO tb_agencia (cod_agencia, endereco_id, telefone) VALUES (1234, 1, '1234-5678');
INSERT INTO tb_agencia (cod_agencia, endereco_id, telefone) VALUES (1235, 2, '1234-5679');
INSERT INTO tb_agencia (cod_agencia, endereco_id, telefone) VALUES (1236, 3, '1234-5680');

-- Inserir Usuários
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id, role, endereco_id) VALUES ('123.456.789-00', 'João Silva', '1234-5678', '$2a$12$N4g0VLAUu75chvfKd5mhuuzdmJhW85HZ3holFKguUiYJvfirwYHhq', 1, 0, 4);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id, role, endereco_id) VALUES ('987.654.321-00', 'Maria Souza', '1234-5679', '$2a$12$N4g0VLAUu75chvfKd5mhuuzdmJhW85HZ3holFKguUiYJvfirwYHhq', 2, 1, 5);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id, role, endereco_id) VALUES ('456.789.123-00', 'Carlos Pereira', '1234-5680', '$2a$12$N4g0VLAUu75chvfKd5mhuuzdmJhW85HZ3holFKguUiYJvfirwYHhq', 3, 1, 7);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id, role, endereco_id) VALUES ('789.123.456-00', 'Ana Costa', '1234-5681', '$2a$12$N4g0VLAUu75chvfKd5mhuuzdmJhW85HZ3holFKguUiYJvfirwYHhq', 1, 1, 6);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id, role, endereco_id) VALUES ('321.654.987-00', 'José Santos', '1234-5682', '$2a$12$N4g0VLAUu75chvfKd5mhuuzdmJhW85HZ3holFKguUiYJvfirwYHhq', 2, 1, 8);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id, role, endereco_id) VALUES ('654.987.321-00', 'Fernanda Lima', '1234-5683', '$2a$12$N4g0VLAUu75chvfKd5mhuuzdmJhW85HZ3holFKguUiYJvfirwYHhq', 3, 1, 7);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id, role, endereco_id) VALUES ('111.222.333-44', 'Paulo Almeida', '1234-5684', '$2a$12$N4g0VLAUu75chvfKd5mhuuzdmJhW85HZ3holFKguUiYJvfirwYHhq', 1, 1, 10);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id, role, endereco_id) VALUES ('222.333.444-55', 'Juliana Mendes', '1234-5685', '$2a$12$N4g0VLAUu75chvfKd5mhuuzdmJhW85HZ3holFKguUiYJvfirwYHhq', 2, 1, 4);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id, role, endereco_id) VALUES ('333.444.555-66', 'Bruno Oliveira', '1234-5686', '$2a$12$N4g0VLAUu75chvfKd5mhuuzdmJhW85HZ3holFKguUiYJvfirwYHhq', 3, 1, 9);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id, role, endereco_id) VALUES ('444.555.666-77', 'Patrícia Moreira', '1234-5687', '$2a$12$N4g0VLAUu75chvfKd5mhuuzdmJhW85HZ3holFKguUiYJvfirwYHhq', 1, 1, 10);


-- Inserir Contas
INSERT INTO tb_conta (numero_conta, saldo, ativa, chave_pix, tipo_conta, usuario_id) VALUES (987654, 1000.00, TRUE, 'joao.silva@pix.com', 'POUPANCA', 1);
INSERT INTO tb_conta (numero_conta, saldo, ativa, chave_pix, tipo_conta, usuario_id) VALUES (876543, 1500.50, TRUE, 'maria.souza@pix.com', 'CORRENTE', 2);
INSERT INTO tb_conta (numero_conta, saldo, ativa, chave_pix, tipo_conta, usuario_id) VALUES (765432, 2000.75, TRUE, 'carlos.pereira@pix.com', 'POUPANCA', 3);
INSERT INTO tb_conta (numero_conta, saldo, ativa, chave_pix, tipo_conta, usuario_id) VALUES (654321, 2500.00, TRUE, 'ana.costa@pix.com', 'CORRENTE', 4);
INSERT INTO tb_conta (numero_conta, saldo, ativa, chave_pix, tipo_conta, usuario_id) VALUES (543210, 3000.25, TRUE, 'jose.santos@pix.com', 'POUPANCA', 5);
INSERT INTO tb_conta (numero_conta, saldo, ativa, chave_pix, tipo_conta, usuario_id) VALUES (432109, 3500.50, TRUE, 'fernanda.lima@pix.com', 'CORRENTE', 6);
INSERT INTO tb_conta (numero_conta, saldo, ativa, chave_pix, tipo_conta, usuario_id) VALUES (321098, 4000.75, TRUE, 'paulo.almeida@pix.com', 'POUPANCA', 7);
INSERT INTO tb_conta (numero_conta, saldo, ativa, chave_pix, tipo_conta, usuario_id) VALUES (210987, 4500.00, TRUE, 'juliana.mendes@pix.com', 'CORRENTE', 8);
INSERT INTO tb_conta (numero_conta, saldo, ativa, chave_pix, tipo_conta, usuario_id) VALUES (109876, 5000.25, TRUE, 'bruno.oliveira@pix.com', 'POUPANCA', 9);
INSERT INTO tb_conta (numero_conta, saldo, ativa, chave_pix, tipo_conta, usuario_id) VALUES (987654, 5500.50, TRUE, 'patricia.moreira@pix.com', 'CORRENTE', 10);

-- Inserir Extratos
INSERT INTO tb_extrato (data_hora_mov, operacao, conta_id) VALUES ('2023-08-01 10:00:00', 'DEPOSITO', 1);
INSERT INTO tb_extrato (data_hora_mov, operacao, conta_id) VALUES ('2023-08-02 11:00:00', 'SAQUE', 2);
INSERT INTO tb_extrato (data_hora_mov, operacao, conta_id) VALUES ('2023-08-03 12:00:00', 'TRANSFERENCIA', 3);
INSERT INTO tb_extrato (data_hora_mov, operacao, conta_id) VALUES ('2023-08-04 13:00:00', 'DEPOSITO', 4);
INSERT INTO tb_extrato (data_hora_mov, operacao, conta_id) VALUES ('2023-08-05 14:00:00', 'SAQUE', 5);
INSERT INTO tb_extrato (data_hora_mov, operacao, conta_id) VALUES ('2023-08-06 15:00:00', 'TRANSFERENCIA', 6);
INSERT INTO tb_extrato (data_hora_mov, operacao, conta_id) VALUES ('2023-08-07 16:00:00', 'DEPOSITO', 7);
INSERT INTO tb_extrato (data_hora_mov, operacao, conta_id) VALUES ('2023-08-08 17:00:00', 'SAQUE', 8);
INSERT INTO tb_extrato (data_hora_mov, operacao, conta_id) VALUES ('2023-08-09 18:00:00', 'TRANSFERENCIA', 9);
