INSERT INTO tb_agencia (cod_agencia, endereco, telefone) VALUES (1234, 'Rua Principal, 123', '1234-5678');
INSERT INTO tb_agencia (cod_agencia, endereco, telefone) VALUES (1235, 'Avenida Secundária, 456', '1234-5679');
INSERT INTO tb_agencia (cod_agencia, endereco, telefone) VALUES (1236, 'Rua Terciária, 789', '1234-5680');
INSERT INTO tb_agencia (cod_agencia, endereco, telefone) VALUES (1237, 'Praça Central, 10', '1234-5681');
INSERT INTO tb_agencia (cod_agencia, endereco, telefone) VALUES (1238, 'Alameda das Flores, 20', '1234-5682');
INSERT INTO tb_agencia (cod_agencia, endereco, telefone) VALUES (1239, 'Rua das Palmeiras, 30', '1234-5683');
INSERT INTO tb_agencia (cod_agencia, endereco, telefone) VALUES (1240, 'Avenida dos Pássaros, 40', '1234-5684');
INSERT INTO tb_agencia (cod_agencia, endereco, telefone) VALUES (1241, 'Rua das Acácias, 50', '1234-5685');
INSERT INTO tb_agencia (cod_agencia, endereco, telefone) VALUES (1242, 'Rua das Orquídeas, 60', '1234-5686');
INSERT INTO tb_agencia (cod_agencia, endereco, telefone) VALUES (1243, 'Rua das Rosas, 70', '1234-5687');

INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id) VALUES ('123.456.789-00', 'João Silva', '1234-5678', 'senha123', 1);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id) VALUES ('987.654.321-00', 'Maria Souza', '1234-5679', 'senha456', 2);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id) VALUES ('456.789.123-00', 'Carlos Pereira', '1234-5680', 'senha789', 3);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id) VALUES ('789.123.456-00', 'Ana Costa', '1234-5681', 'senha012', 4);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id) VALUES ('321.654.987-00', 'José Santos', '1234-5682', 'senha345', 5);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id) VALUES ('654.987.321-00', 'Fernanda Lima', '1234-5683', 'senha678', 6);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id) VALUES ('111.222.333-44', 'Paulo Almeida', '1234-5684', 'senha901', 7);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id) VALUES ('222.333.444-55', 'Juliana Mendes', '1234-5685', 'senha234', 8);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id) VALUES ('333.444.555-66', 'Bruno Oliveira', '1234-5686', 'senha567', 9);
INSERT INTO tb_usuario (cpf, nome_usuario, telefone, senha, agencia_id) VALUES ('444.555.666-77', 'Patrícia Moreira', '1234-5687', 'senha890', 10);

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

INSERT INTO tb_extrato (data_hora_mov, operacao, conta_id) VALUES ('2023-08-01 10:00:00', 'DEPOSITO', 1); -- DEPOSITO
INSERT INTO tb_extrato (data_hora_mov, operacao, conta_id) VALUES ('2023-08-02 11:00:00','SAQUE', 2); -- SAQUE
INSERT INTO tb_extrato (data_hora_mov, operacao, conta_id) VALUES ('2023-08-03 12:00:00', 'TRANSFERENCIA', 3); -- TRANSFERENCIA
INSERT INTO tb_extrato (data_hora_mov, operacao, conta_id) VALUES ('2023-08-04 13:00:00', 'DEPOSITO', 4); -- DEPOSITO
INSERT INTO tb_extrato (data_hora_mov, operacao, conta_id) VALUES ('2023-08-05 14:00:00', 'SAQUE', 5); -- SAQUE
INSERT INTO tb_extrato (data_hora_mov, operacao, conta_id) VALUES ('2023-08-06 15:00:00', 'TRANSFERENCIA', 6); -- TRANSFERENCIA
INSERT INTO tb_extrato (data_hora_mov, operacao, conta_id) VALUES ('2023-08-07 16:00:00', 'DEPOSITO', 7); -- DEPOSITO
INSERT INTO tb_extrato (data_hora_mov, operacao, conta_id) VALUES ('2023-08-08 17:00:00', 'SAQUE', 8); -- SAQUE
INSERT INTO tb_extrato (data_hora_mov, operacao, conta_id) VALUES ('2023-08-09 18:00:00', 'TRANSFERENCIA', 9); -- TRANSFERENCIA








