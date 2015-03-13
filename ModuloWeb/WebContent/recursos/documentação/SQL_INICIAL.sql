
--PERFIL BÁSICO
INSERT INTO PERFIL (ID, DESCRICAO, NOME, UF) VALUES (1, 'Administrador do Sistema', 'admin', 'Todas');

--USUARIO ADMIN
INSERT INTO USUARIO (DTYPE, ID, LOGIN, SENHA, PERFIL_ID) VALUES ('br.com.sistxweb.model.Usuario', '1', 'admin', 'admin', 1);
INSERT INTO USUARIO (DTYPE, ID, LOGIN, SENHA, PERFIL_ID) VALUES ('br.com.sistxweb.model.Cliente', '2', 'cliente', 'cliente', 1);
INSERT INTO USUARIO (DTYPE, ID, LOGIN, SENHA, PERFIL_ID) VALUES ('br.com.sistxweb.model.Taxista', '3', 'taxista', 'taxista', 1);

--CORRIDAS EXEMPLO
INSERT INTO CORRIDA (ID, RUA, BAIRRO, CIDADE, HORASOLICITACAO, STATUS) VALUES ('1', 'Rua Um', 'Itapoã', 'Vila Velha', '07/11/2014 23:15', 'Pendente');
INSERT INTO CORRIDA (ID, RUA, BAIRRO, CIDADE, HORASOLICITACAO, STATUS) VALUES ('2', 'Rua Dois', 'Itapoã', 'Vila Velha', '07/11/2014 23:16', 'Pendente');
INSERT INTO CORRIDA (ID, RUA, BAIRRO, CIDADE, HORASOLICITACAO, STATUS) VALUES ('3', 'Rua Três', 'Itapoã', 'Vila Velha', '07/11/2014 23:17', 'Andamento');
INSERT INTO CORRIDA (ID, RUA, BAIRRO, CIDADE, HORASOLICITACAO, STATUS) VALUES ('4', 'Rua Quatro', 'Itapoã', 'Vila Velha', '07/11/2014 23:18', 'Andamento');
INSERT INTO CORRIDA (ID, RUA, BAIRRO, CIDADE, HORASOLICITACAO, STATUS) VALUES ('5', 'Rua Cinco', 'Itapoã', 'Vila Velha', '07/11/2014 23:19', 'Andamento');
