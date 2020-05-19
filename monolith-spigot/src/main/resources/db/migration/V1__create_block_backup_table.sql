CREATE TABLE block_backup
(
    id    TEXT    NOT NULL PRIMARY KEY,
    data  TEXT    NOT NULL,
    world TEXT    NOT NULL,
    x     INTEGER NOT NULL,
    y     INTEGER NOT NULL,
    z     INTEGER NOT NULL
);
