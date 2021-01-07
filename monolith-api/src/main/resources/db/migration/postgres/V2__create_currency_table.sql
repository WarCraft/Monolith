CREATE TABLE currency
(
    player_id UUID        NOT NULL,
    currency  VARCHAR(32) NOT NULL,
    amount    INTEGER     NOT NULL,
    lifetime  INTEGER     NOT NULL,

    PRIMARY KEY (player_id, currency)
);
