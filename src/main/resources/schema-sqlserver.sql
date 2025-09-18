IF OBJECT_ID('Carro', 'U') IS NULL
BEGIN
    CREATE TABLE Carro (
        id BIGINT IDENTITY(1,1) PRIMARY KEY,
        marca VARCHAR(255) NOT NULL,
        ano INT NOT NULL,
        disponivel BIT NOT NULL
    );
    PRINT 'Tabela Carro criada com sucesso.';
END
GO