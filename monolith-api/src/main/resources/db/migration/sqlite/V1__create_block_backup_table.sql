CREATE TABLE block_backup
(
    id    BIGINT      NOT NULL PRIMARY KEY,
    world VARCHAR(32) NOT NULL,
    x     INTEGER     NOT NULL,
    y     INTEGER     NOT NULL,
    z     INTEGER     NOT NULL,
    data  TEXT        NOT NULL
);
