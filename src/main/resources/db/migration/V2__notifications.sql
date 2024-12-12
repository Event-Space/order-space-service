CREATE TABLE order_unit.booking
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    slot_id      BIGINT                                  NOT NULL,
    user_email   VARCHAR(255)                            NOT NULL,
    booking_time TIMESTAMP WITHOUT TIME ZONE             NOT NULL,
    status       VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_booking PRIMARY KEY (id)
);

CREATE TABLE order_unit.comment
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    space_id    BIGINT,
    user_email  VARCHAR(255),
    content     VARCHAR(255),
    create_time TIMESTAMP WITHOUT TIME ZONE,
    update_time TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_comment PRIMARY KEY (id)
);

CREATE TABLE order_unit.event
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name       VARCHAR(255)                            NOT NULL,
    date       TIMESTAMP WITHOUT TIME ZONE             NOT NULL,
    location   VARCHAR(255)                            NOT NULL,
    created_by VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_event PRIMARY KEY (id)
);

CREATE TABLE order_unit.notifications
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    is_read    BOOLEAN NOT NULL,
    content    VARCHAR(255),
    for_who_id BIGINT,
    CONSTRAINT pk_notifications PRIMARY KEY (id)
);

ALTER TABLE order_unit.booking
    ADD CONSTRAINT FK_BOOKING_ON_SLOT FOREIGN KEY (slot_id) REFERENCES order_unit.slot (id);

