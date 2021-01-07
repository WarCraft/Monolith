CREATE TABLE player_data
(
    id             UUID PRIMARY KEY,
    team           VARCHAR(32),
    time_connected TIMESTAMP WITH TIME ZONE NOT NULL,
    time_last_seen TIMESTAMP WITH TIME ZONE NOT NULL
);
