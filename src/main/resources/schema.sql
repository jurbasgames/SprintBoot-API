CREATE TABLE SituacaoMatricula(
                                  id INT AUTO_INCREMENT PRIMARY KEY,
                                  codigo VARCHAR(5),
                                  nome VARCHAR(255)
);

CREATE TABLE Aluno (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       nome VARCHAR(255) NOT NULL,
                       matricula VARCHAR(9) NOT NULL,
                       hobbies VARCHAR(255),
                       situacaoMatriculaId INT NOT NULL,
                       FOREIGN KEY (situacaoMatriculaId) REFERENCES  SituacaoMatricula(id),
                       CONSTRAINT unique_matricula UNIQUE (matricula)

);

