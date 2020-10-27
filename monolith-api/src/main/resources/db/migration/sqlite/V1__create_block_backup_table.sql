CREATE TABLE block_backup
(
    id    BIGINT PRIMARY KEY AUTOINCREMENT,
    world VARCHAR(32) NOT NULL,
    x     INTEGER     NOT NULL,
    y     INTEGER     NOT NULL,
    z     INTEGER     NOT NULL,
    data  TEXT        NOT NULL
);
