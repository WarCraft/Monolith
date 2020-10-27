CREATE TABLE currency
(
    player_id UUID        NOT NULL,
    currency  VARCHAR(32) NOT NULL,
    amount    INTEGER     NOT NULL DEFAULT 0,
    lifetime  INTEGER     NOT NULL DEFAULT 0,

    PRIMARY KEY (player_id, currency)
);
