CREATE TABLE cliente(
	id SERIAL PRIMARY KEY,
	matricula VARCHAR(20) UNIQUE NOT NULL,
	nome VARCHAR(50) NOT NULL,
	telefone VARCHAR(12) NOT NULL
);

CREATE TABLE livro(
	id SERIAL PRIMARY KEY,
	isbn VARCHAR(13) UNIQUE NOT NULL,
	nome VARCHAR(50) NOT NULL,
	autor VARCHAR(50) NOT NULL,
	editora VARCHAR(50) NOT NULL,
	ano INTEGER NOT NULL
);

CREATE TABLE itemLivro(
	id SERIAL PRIMARY KEY,
	codLivro INTEGER REFERENCES livro(id) ON DELETE CASCADE,
	quantidadeTotal INTEGER NOT NULL,
	quantidadeDisponivel INTEGER NOT NULL
);

CREATE TABLE emprestimo(
	id SERIAL PRIMARY KEY,
	codCliente INTEGER REFERENCES cliente(id) ON DELETE CASCADE,
	codItemLivro INTEGER REFERENCES itemLivro(id) ON DELETE CASCADE,
	dataEmprestimo DATE NOT NULL,
	dataDevolucao DATE NOT NULL,
	devolucaoEfetiva DATE,
	diasAtraso INTEGER NOT NULL,
        ativo BOOLEAN NOT NULL
);

INSERT INTO cliente(matricula,nome,telefone) VALUES ('01','Maria da Silva','0000');
INSERT INTO cliente(matricula,nome,telefone) VALUES ('02','João da Silva','0000');
INSERT INTO cliente(matricula,nome,telefone) VALUES ('03','Ciclano','0000');
INSERT INTO cliente(matricula,nome,telefone) VALUES ('04','Beltrano','0000');
INSERT INTO cliente(matricula,nome,telefone) VALUES ('05','José da Silva','0000');

INSERT INTO livro(isbn,nome,autor,editora,ano) VALUES ('0000000000001','Senhor dos Anéis - As Duas Torres','J. R. R. Tolkien','Martins Editora',2000);
INSERT INTO livro(isbn,nome,autor,editora,ano) VALUES ('0000000000002','Senhor dos Anéis - O Retorno do Rei','J. R. R. Tolkien','Martins Editora',2001);
INSERT INTO livro(isbn,nome,autor,editora,ano) VALUES ('0000000000003','Senhor dos Anéis - A Sociedade do Anel','J. R. R. Tolkien','Martins Editora',1999);
INSERT INTO livro(isbn,nome,autor,editora,ano) VALUES ('0000000000004','Game of Thrones - A Guerra dos Tronos','George R. R. Martin','Martins Editora',2000);
INSERT INTO livro(isbn,nome,autor,editora,ano) VALUES ('0000000000005','Game of Thrones - A Fúria dos Reis','George R. R. Martin','Martins Editora',2001);
INSERT INTO livro(isbn,nome,autor,editora,ano) VALUES ('0000000000006','Game of Thrones - A Tormenta de Espadas','George R. R. Martin','Martins Editora',2002);
INSERT INTO livro(isbn,nome,autor,editora,ano) VALUES ('0000000000007','Game of Thrones - O Festim dos Corvos','George R. R. Martin','Martins Editora',2003);
INSERT INTO livro(isbn,nome,autor,editora,ano) VALUES ('0000000000008','Game of Thrones - A Dança dos Dragões','George R. R. Martin','Martins Editora',2004);
INSERT INTO livro(isbn,nome,autor,editora,ano) VALUES ('0000000000009','Harry Potter e a Pedra Filosofal','J. K. Rowling','Martins Editora',2010);
INSERT INTO livro(isbn,nome,autor,editora,ano) VALUES ('0000000000010','Java - Como Programar','James Gosling','Martins Editora',2015);

INSERT INTO itemLivro(codLivro,quantidadeTotal,quantidadeDisponivel) VALUES (1,10,8);
INSERT INTO itemLivro(codLivro,quantidadeTotal,quantidadeDisponivel) VALUES (2,5,5);
INSERT INTO itemLivro(codLivro,quantidadeTotal,quantidadeDisponivel) VALUES (3,3,2);
INSERT INTO itemLivro(codLivro,quantidadeTotal,quantidadeDisponivel) VALUES (4,1,0);
INSERT INTO itemLivro(codLivro,quantidadeTotal,quantidadeDisponivel) VALUES (5,2,0);
INSERT INTO itemLivro(codLivro,quantidadeTotal,quantidadeDisponivel) VALUES (6,2,2);
INSERT INTO itemLivro(codLivro,quantidadeTotal,quantidadeDisponivel) VALUES (7,1,0);
INSERT INTO itemLivro(codLivro,quantidadeTotal,quantidadeDisponivel) VALUES (8,1,0);
INSERT INTO itemLivro(codLivro,quantidadeTotal,quantidadeDisponivel) VALUES (9,11,10);
INSERT INTO itemLivro(codLivro,quantidadeTotal,quantidadeDisponivel) VALUES (10,10,9);

INSERT INTO emprestimo(codCliente,codItemLivro,dataEmprestimo,dataDevolucao,devolucaoEfetiva,diasAtraso,ativo) VALUES (1,1,'2015-11-18','2015-11-25','2015-11-26',1,false);
INSERT INTO emprestimo(codCliente,codItemLivro,dataEmprestimo,dataDevolucao,devolucaoEfetiva,diasAtraso,ativo) VALUES (2,1,'2015-11-19','2015-11-26','2015-11-26',0,false);
INSERT INTO emprestimo(codCliente,codItemLivro,dataEmprestimo,dataDevolucao,devolucaoEfetiva,diasAtraso,ativo) VALUES (3,5,'2015-11-20','2015-11-27','2015-11-22',0,false);
INSERT INTO emprestimo(codCliente,codItemLivro,dataEmprestimo,dataDevolucao,devolucaoEfetiva,diasAtraso,ativo) VALUES (1,5,'2015-10-10','2015-10-17','2015-11-17',30,false);
INSERT INTO emprestimo(codCliente,codItemLivro,dataEmprestimo,dataDevolucao,devolucaoEfetiva,diasAtraso,ativo) VALUES (4,7,'2015-10-10','2015-10-17','2015-10-16',0,false);
INSERT INTO emprestimo(codCliente,codItemLivro,dataEmprestimo,dataDevolucao,devolucaoEfetiva,diasAtraso,ativo) VALUES (4,10,'2015-11-15','2015-11-22','2015-11-22',0,false);
INSERT INTO emprestimo(codCliente,codItemLivro,dataEmprestimo,dataDevolucao,devolucaoEfetiva,diasAtraso,ativo) VALUES (5,8,'2015-11-01','2015-11-08','2015-11-09',1,false);
INSERT INTO emprestimo(codCliente,codItemLivro,dataEmprestimo,dataDevolucao,devolucaoEfetiva,diasAtraso,ativo) VALUES (1,3,'2015-11-01','2015-11-08',null,0,true);
INSERT INTO emprestimo(codCliente,codItemLivro,dataEmprestimo,dataDevolucao,devolucaoEfetiva,diasAtraso,ativo) VALUES (1,4,'2015-11-01','2015-11-08',null,0,true);
INSERT INTO emprestimo(codCliente,codItemLivro,dataEmprestimo,dataDevolucao,devolucaoEfetiva,diasAtraso,ativo) VALUES (1,9,'2015-11-01','2015-11-08',null,0,true);