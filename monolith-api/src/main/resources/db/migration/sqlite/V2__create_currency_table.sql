CREATE TABLE currency
(
    player_id CHARACTER(32) NOT NULL,
    currency  VARCHAR(32)   NOT NULL,
    amount    INTEGER       NOT NULL,
    lifetime  INTEGER       NOT NULl,

    PRIMARY KEY (player_id, currency)
);
