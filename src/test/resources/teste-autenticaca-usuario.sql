INSERT INTO USUARIO(nome, email, senha) VALUES('Aluno', 'aluno@email.com', '$2a$10$Hy/GyFLnG5OK.G7sANUG5eljvEBtw0JDkD.mP8ttMleB39Ap8gAui');

INSERT INTO PERFIL(id,nome) VALUES(1, 'ROLE_ALUNO');

INSERT INTO USUARIO_PERFIS(usuario_id, perfis_id) VALUES(1, 1);