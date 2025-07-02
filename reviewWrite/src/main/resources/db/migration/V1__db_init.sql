CREATE TABLE IF NOT EXISTS review_event_store (
    event_id VARCHAR PRIMARY KEY,
    event_name VARCHAR NOT NULL,
    data JSONB NOT NULL,
    entity_id VARCHAR NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    processed BOOLEAN NOT NULL
);

