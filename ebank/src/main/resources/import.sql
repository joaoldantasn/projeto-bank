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
INSERT INTO tb_agencia (cod_agencia, endereco_id, telefone) VALUES (1234, 1, '8312345678');
INSERT INTO tb_agencia (cod_agencia, endereco_id, telefone) VALUES (1235, 2, '8512345679');
INSERT INTO tb_agencia (cod_agencia, endereco_id, telefone) VALUES (1236, 3, '8312345680');

-- Inserir Usuários
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id, role, endereco_id) VALUES ('123.456.789-00', 'João Silva', '1234445678', '$2a$12$N4g0VLAUu75chvfKd5mhuuzdmJhW85HZ3holFKguUiYJvfirwYHhq', 1, 0, 4);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id, role, endereco_id) VALUES ('987.654.321-00', 'Maria Souza', '1234885679', '$2a$12$N4g0VLAUu75chvfKd5mhuuzdmJhW85HZ3holFKguUiYJvfirwYHhq', 2, 1, 5);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id, role, endereco_id) VALUES ('456.789.123-00', 'Carlos Pereira', '123885680', '$2a$12$N4g0VLAUu75chvfKd5mhuuzdmJhW85HZ3holFKguUiYJvfirwYHhq', 3, 1, 7);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id, role, endereco_id) VALUES ('789.123.456-00', 'Ana Costa', '1234775681', '$2a$12$N4g0VLAUu75chvfKd5mhuuzdmJhW85HZ3holFKguUiYJvfirwYHhq', 1, 1, 6);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id, role, endereco_id) VALUES ('321.654.987-00', 'José Santos', '1234665682', '$2a$12$N4g0VLAUu75chvfKd5mhuuzdmJhW85HZ3holFKguUiYJvfirwYHhq', 2, 1, 8);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id, role, endereco_id) VALUES ('654.987.321-00', 'Fernanda Lima', '1234995683', '$2a$12$N4g0VLAUu75chvfKd5mhuuzdmJhW85HZ3holFKguUiYJvfirwYHhq', 3, 1, 7);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id, role, endereco_id) VALUES ('111.222.333-44', 'Paulo Almeida', '1234785684', '$2a$12$N4g0VLAUu75chvfKd5mhuuzdmJhW85HZ3holFKguUiYJvfirwYHhq', 1, 1, 10);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id, role, endereco_id) VALUES ('222.333.444-55', 'Juliana Mendes', '1234145685', '$2a$12$N4g0VLAUu75chvfKd5mhuuzdmJhW85HZ3holFKguUiYJvfirwYHhq', 2, 1, 4);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id, role, endereco_id) VALUES ('333.444.555-66', 'Bruno Oliveira', '1234265686', '$2a$12$N4g0VLAUu75chvfKd5mhuuzdmJhW85HZ3holFKguUiYJvfirwYHhq', 3, 1, 9);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id, role, endereco_id) VALUES ('444.555.666-77', 'Patrícia Moreira', '1234655687', '$2a$12$N4g0VLAUu75chvfKd5mhuuzdmJhW85HZ3holFKguUiYJvfirwYHhq', 1, 1, 10);


-- Inserir Contas
INSERT INTO tb_conta (numero_conta, saldo, ativa, chave_pix, tipo_conta, usuario_id) VALUES (98765444, 1000.00, TRUE, 'joao.silva@pix.com', 'POUPANCA', 1);
INSERT INTO tb_conta (numero_conta, saldo, ativa, chave_pix, tipo_conta, usuario_id) VALUES (87654355, 1500.50, TRUE, 'maria.souza@pix.com', 'CORRENTE', 2);
INSERT INTO tb_conta (numero_conta, saldo, ativa, chave_pix, tipo_conta, usuario_id) VALUES (76543266, 2000.75, TRUE, 'carlos.pereira@pix.com', 'POUPANCA', 3);
INSERT INTO tb_conta (numero_conta, saldo, ativa, chave_pix, tipo_conta, usuario_id) VALUES (65432177, 2500.00, TRUE, 'ana.costa@pix.com', 'CORRENTE', 4);
INSERT INTO tb_conta (numero_conta, saldo, ativa, chave_pix, tipo_conta, usuario_id) VALUES (54321099, 3000.25, TRUE, 'jose.santos@pix.com', 'POUPANCA', 5);
INSERT INTO tb_conta (numero_conta, saldo, ativa, chave_pix, tipo_conta, usuario_id) VALUES (43210943, 3500.50, TRUE, 'fernanda.lima@pix.com', 'CORRENTE', 6);
INSERT INTO tb_conta (numero_conta, saldo, ativa, chave_pix, tipo_conta, usuario_id) VALUES (32109865, 4000.75, TRUE, 'paulo.almeida@pix.com', 'POUPANCA', 7);
INSERT INTO tb_conta (numero_conta, saldo, ativa, chave_pix, tipo_conta, usuario_id) VALUES (21098785, 4500.00, TRUE, 'juliana.mendes@pix.com', 'CORRENTE', 8);
INSERT INTO tb_conta (numero_conta, saldo, ativa, chave_pix, tipo_conta, usuario_id) VALUES (10987667, 5000.25, TRUE, 'bruno.oliveira@pix.com', 'POUPANCA', 9);
INSERT INTO tb_conta (numero_conta, saldo, ativa, chave_pix, tipo_conta, usuario_id) VALUES (98765469, 5500.50, TRUE, 'patricia.moreira@pix.com', 'CORRENTE', 10);

-- Inserir Transações
INSERT INTO tb_transacao (conta_origem_id, conta_destino_id, valor, tipo, data_hora) VALUES (1, 2, 150.00, 'TRANSFERENCIA', '2023-08-01T10:00:00Z');
INSERT INTO tb_transacao (conta_origem_id, conta_destino_id, valor, tipo, data_hora) VALUES (2, 3, 200.00, 'TRANSFERENCIA', '2023-08-02T11:00:00Z');
INSERT INTO tb_transacao (conta_origem_id, conta_destino_id, valor, tipo, data_hora) VALUES (3, 4, 250.00, 'TRANSFERENCIA', '2023-08-03T12:00:00Z');
INSERT INTO tb_transacao (conta_origem_id, conta_destino_id, valor, tipo, data_hora) VALUES (4, 5, 300.00, 'TRANSFERENCIA', '2023-08-04T13:00:00Z');
INSERT INTO tb_transacao (conta_origem_id, conta_destino_id, valor, tipo, data_hora) VALUES (5, 6, 350.00, 'TRANSFERENCIA', '2023-08-05T14:00:00Z');
INSERT INTO tb_transacao (conta_origem_id, conta_destino_id, valor, tipo, data_hora) VALUES (6, 7, 400.00, 'TRANSFERENCIA', '2023-08-06T15:00:00Z');
INSERT INTO tb_transacao (conta_origem_id, conta_destino_id, valor, tipo, data_hora) VALUES (7, 8, 450.00, 'TRANSFERENCIA', '2023-08-07T16:00:00Z');
INSERT INTO tb_transacao (conta_origem_id, conta_destino_id, valor, tipo, data_hora) VALUES (8, 9, 500.00, 'TRANSFERENCIA', '2023-08-08T17:00:00Z');
