CREATE TABLE livro (
id bigint NOT NULL AUTO_INCREMENT,
nome varchar(75) DEFAULT NULL,
data_publicacao date,
editora varchar(70) NOT NULL,
resumo varchar(512) NOT NULL,
autor_id bigint NOT NULL,
PRIMARY KEY (id),
KEY key_livro_autor (autor_id),
CONSTRAINT fk_livro_autor FOREIGN KEY (autor_id) REFERENCES autor (id)
ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;