INSERT INTO client (cpf, email, first_name, last_name, secret) VALUES ('123.456.789-00', 'tita@gmail.com', 'Thiago', 'Araujo', '123456');
INSERT INTO client (cpf, email, first_name, last_name, secret) VALUES ('123.456.789-00', 'caio@gmail.com', 'Caio', 'Barbosa', '123456');
INSERT INTO client (cpf, email, first_name, last_name, secret) VALUES ('123.456.789-00', 'lucas@gmail.com', 'Lucas', 'Almeida', '123456');
INSERT INTO client (cpf, email, first_name, last_name, secret) VALUES ('000.000.000-00', 'felipe@gmail.com', 'Felipe', 'Vasconcelos', '123456');

INSERT INTO BANK(name) VALUES ('Banco do Brasil');
INSERT INTO BANK(name) VALUES ('Caixa Economica');
INSERT INTO BANK(name) VALUES ('Nubank');


INSERT INTO AGENCY(number, name, banco_id) VALUES ('0001-1', 'Banco do Brasil CG', 1);
INSERT INTO AGENCY(number, name, banco_id) VALUES ('0002-1', 'Banco do Brasil JP', 1);

INSERT INTO AGENCY(number, name, banco_id) VALUES ('0001-1', 'Caixa Economica CG', 2);
INSERT INTO AGENCY(number, name, banco_id) VALUES ('0002-1', 'Caixa Economica JP', 2);

INSERT INTO AGENCY(number, name, banco_id) VALUES ('0001-1', 'Nubank', 3);
INSERT INTO AGENCY(number, name, banco_id) VALUES ('0002-1', 'Nubank Vip', 3);


INSERT INTO CONTA(number, saldo, agency_id,client_id) VALUES ('00001-0', 10000, 1,1);
INSERT INTO CONTA(number, saldo, agency_id,client_id) VALUES ('00001-1', 0.0, 1,2);