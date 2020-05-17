CREATE TABLE server_data
(
    data            TEXT        PRIMARY KEY,
    int_value       INTEGER     NOT NULL        DEFAULT 0,
    float_value     REAL        NOT NULL        DEFAULT 0,
    string_value    TEXT        NOT NULL        DEFAULT ''
);
