CREATE TABLE currency
(
    player_id   TEXT        NOT NULL,
    currency    TEXT        NOT NULL,
    amount      INTEGER     NOT NULL    DEFAULT 0,
    lifetime    INTEGER     NOT NULL    DEFAULT 0,
    PRIMARY KEY (player_id, currency)
);
