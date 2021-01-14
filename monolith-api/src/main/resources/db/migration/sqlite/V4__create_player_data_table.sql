CREATE TABLE player_data
(
    id             CHARACTER(32) NOT NULL PRIMARY KEY,
    team           VARCHAR(32),
    time_connected VARCHAR(32)   NOT NULL,
    time_last_seen VARCHAR(32)   NOT NULL
);
