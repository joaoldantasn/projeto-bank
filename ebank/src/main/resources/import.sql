-- Inserir Endereços
INSERT INTO tb_endereco (cep, logradouro, cidade, bairro, numero) VALUES ('12345-678', 'Rua Exemplo', 'Cidade Exemplo', 'Bairro Exemplo', '123');
INSERT INTO tb_endereco (cep, logradouro, cidade, bairro, numero) VALUES ('23456-789', 'Avenida Teste', 'Cidade Teste', 'Bairro Teste', '456');
INSERT INTO tb_endereco (cep, logradouro, cidade, bairro, numero) VALUES ('34567-890', 'Travessa Demo', 'Cidade Demo', 'Bairro Demo', '789');

-- Inserir Agências
INSERT INTO tb_agencia (cod_agencia, endereco_id, telefone) VALUES (1234, 1, '1234-5678');
INSERT INTO tb_agencia (cod_agencia, endereco_id, telefone) VALUES (1235, 2, '1234-5679');
INSERT INTO tb_agencia (cod_agencia, endereco_id, telefone) VALUES (1236, 3, '1234-5680');

-- Inserir Usuários
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id) VALUES ('123.456.789-00', 'João Silva', '1234-5678', 'senha123', 1);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id) VALUES ('987.654.321-00', 'Maria Souza', '1234-5679', 'senha456', 2);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id) VALUES ('456.789.123-00', 'Carlos Pereira', '1234-5680', 'senha789', 3);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id) VALUES ('789.123.456-00', 'Ana Costa', '1234-5681', 'senha012', 1);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id) VALUES ('321.654.987-00', 'José Santos', '1234-5682', 'senha345', 2);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id) VALUES ('654.987.321-00', 'Fernanda Lima', '1234-5683', 'senha678', 3);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id) VALUES ('111.222.333-44', 'Paulo Almeida', '1234-5684', 'senha901', 1);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id) VALUES ('222.333.444-55', 'Juliana Mendes', '1234-5685', 'senha234', 2);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id) VALUES ('333.444.555-66', 'Bruno Oliveira', '1234-5686', 'senha567', 3);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id) VALUES ('444.555.666-77', 'Patrícia Moreira', '1234-5687', 'senha890', 1);

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
