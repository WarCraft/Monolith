CREATE TABLE statistic
(
    player_id TEXT    NOT NULL,
    statistic TEXT    NOT NULL,
    value     INTEGER NOT NULL DEFAULT 0,
    PRIMARY KEY (player_id, statistic)
);
