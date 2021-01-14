CREATE TABLE statistic
(
    player_id CHARACTER(32) NOT NULL,
    statistic VARCHAR(32)   NOT NULL,
    value     INTEGER       NOT NULL,

    PRIMARY KEY (player_id, statistic)
);
