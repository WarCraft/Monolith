CREATE TABLE player_data
(
    id             CHARACTER(32) PRIMARY KEY NOT NULL,
    team           VARCHAR(32)               NOT NULL,
    time_connected VARCHAR(64)               NOT NULL,
    time_last_seen VARCHAR(64)               NOT NULL
);
