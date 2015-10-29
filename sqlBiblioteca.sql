CREATE TABLE cliente(
	id SERIAL PRIMARY KEY,
	matricula VARCHAR(20) UNIQUE NOT NULL,
	nome VARCHAR(30) NOT NULL,
	telefone VARCHAR(10) NOT NULL
);

CREATE TABLE livro(
	id SERIAL PRIMARY KEY,
	isbn VARCHAR(13) UNIQUE NOT NULL,
	nome VARCHAR(30) NOT NULL,
	autor VARCHAR(30) NOT NULL,
	editora VARCHAR(30) NOT NULL,
	ano INTEGER NOT NULL
);

CREATE TABLE itemLivro(
	id SERIAL PRIMARY KEY,
	codLivro INTEGER REFERENCES livro(id),
	quantidadeTotal INTEGER NOT NULL,
	quantidadeDisponivel INTEGER NOT NULL
);

CREATE TABLE emprestimo(
	id SERIAL PRIMARY KEY,
	codCliente INTEGER REFERENCES cliente(id),
	codItemLivro INTEGER REFERENCES itemLivro(id),
	dataEmprestimo DATE NOT NULL,
	dataDevolucao DATE NOT NULL,
	devolucaoEfetiva DATE,
	diasAtraso INTEGER NOT NULL,
        ativo BOOLEAN not null
);



