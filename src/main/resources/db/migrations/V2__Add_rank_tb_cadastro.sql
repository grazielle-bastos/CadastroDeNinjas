-- V2: Migrations para adicionar a coluna de RANK na tabela tabela de cadastro

ALTER TABLE tb_cadastro
ADD COLUMN rank VARCHAR(255);