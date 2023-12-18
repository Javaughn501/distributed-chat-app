CREATE TABLE chat_room (
    id UUID PRIMARY KEY,
    user1 VARCHAR(255) NOT NULL,
    user2 VARCHAR(255) NOT NULL
);

CREATE TABLE message (
    id UUID PRIMARY KEY,
    chat_id UUID REFERENCES chat_room(id),
    sender_id VARCHAR(255) NOT NULL,
    recipient_id VARCHAR(255) NOT NULL,
    content TEXT,
    timestamp TIME
);

CREATE TABLE chat_room_messages (
    chat_room_id UUID REFERENCES chat_room(id),
    messages_id UUID REFERENCES message(id),
    PRIMARY KEY (chat_room_id, messages_id)
);