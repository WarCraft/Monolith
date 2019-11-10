CREATE TABLE block_backup (
    id CHAR(36) PRIMARY KEY,
    data VARCHAR(1024) NOT NULL,
    world VARCHAR(10) NOT NULL,
    x INT NOT NULL,
    y INT NOT NULL,
    z INT NOT NULL
);
