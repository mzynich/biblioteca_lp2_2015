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

INSERT INTO livro(isbn,nome,autor,editora,ano) VALUES ('100','Senhor dos Anéis - As Duas Torres','J. R. R. Tolkien','Martins Editora',2000);
INSERT INTO livro(isbn,nome,autor,editora,ano) VALUES ('101','Senhor dos Anéis - O Retorno do Rei','J. R. R. Tolkien','Martins Editora',2001);
INSERT INTO livro(isbn,nome,autor,editora,ano) VALUES ('102','Senhor dos Anéis - A Sociedade do Anel','J. R. R. Tolkien','Martins Editora',1999);
INSERT INTO livro(isbn,nome,autor,editora,ano) VALUES ('103','Game of Thrones - A Guerra dos Tronos','George R. R. Martin','Martins Editora',2000);
INSERT INTO livro(isbn,nome,autor,editora,ano) VALUES ('104','Game of Thrones - A Fúria dos Reis','George R. R. Martin','Martins Editora',2001);
INSERT INTO livro(isbn,nome,autor,editora,ano) VALUES ('105','Game of Thrones - A Tormenta de Espadas','George R. R. Martin','Martins Editora',2002);
INSERT INTO livro(isbn,nome,autor,editora,ano) VALUES ('106','Game of Thrones - O Festim dos Corvos','George R. R. Martin','Martins Editora',2003);
INSERT INTO livro(isbn,nome,autor,editora,ano) VALUES ('107','Game of Thrones - A Dança dos Dragões','George R. R. Martin','Martins Editora',2004);
INSERT INTO livro(isbn,nome,autor,editora,ano) VALUES ('108','Harry Potter e a Pedra Filosofal','J. K. Rowling','Martins Editora',2010);
INSERT INTO livro(isbn,nome,autor,editora,ano) VALUES ('109','Java - Como Programar','James Gosling','Martins Editora',2015);

INSERT INTO itemLivro(codLivro,quantidadeTotal,quantidadeDisponivel) VALUES (1,10,10);
INSERT INTO itemLivro(codLivro,quantidadeTotal,quantidadeDisponivel) VALUES (2,5,5);
INSERT INTO itemLivro(codLivro,quantidadeTotal,quantidadeDisponivel) VALUES (3,3,3);
INSERT INTO itemLivro(codLivro,quantidadeTotal,quantidadeDisponivel) VALUES (4,1,1);
INSERT INTO itemLivro(codLivro,quantidadeTotal,quantidadeDisponivel) VALUES (5,2,2);
INSERT INTO itemLivro(codLivro,quantidadeTotal,quantidadeDisponivel) VALUES (6,2,2);
INSERT INTO itemLivro(codLivro,quantidadeTotal,quantidadeDisponivel) VALUES (7,1,1);
INSERT INTO itemLivro(codLivro,quantidadeTotal,quantidadeDisponivel) VALUES (8,1,1);
INSERT INTO itemLivro(codLivro,quantidadeTotal,quantidadeDisponivel) VALUES (9,11,11);
INSERT INTO itemLivro(codLivro,quantidadeTotal,quantidadeDisponivel) VALUES (10,10,10);