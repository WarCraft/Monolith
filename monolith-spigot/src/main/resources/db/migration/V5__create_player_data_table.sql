CREATE TABLE player_data
(
    id             TEXT    NOT NULL PRIMARY KEY,
    team           TEXT,
    time_connected INTEGER NOT NULL DEFAULT 0,
    time_last_seen INTEGER NOT NULL DEFAULT 0
);
