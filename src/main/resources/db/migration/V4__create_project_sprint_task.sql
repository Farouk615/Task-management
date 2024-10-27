CREATE TABLE project
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(512)
);

CREATE TABLE sprint
(
    id   BIGSERIAL PRIMARY KEY,
    startDate TIMESTAMP WITH TIME ZONE,
    endDate TIMESTAMP WITH TIME ZONE,
    project_id BIGINT,
    FOREIGN KEY (project_id) REFERENCES project (id)
);

CREATE TYPE status AS ENUM ('COMPLETED', 'PENDING');

CREATE TABLE task
(
    id BIGSERIAL PRIMARY KEY,
    description VARCHAR(255),
    status status NOT NULL,
    sprint_id BIGINT,
    FOREIGN KEY (sprint_id) REFERENCES sprint (id)
);

ALTER TABLE users
    ADD COLUMN task_id BIGINT REFERENCES task(id) ON DELETE CASCADE;

ALTER TABLE team
    ADD COLUMN project_id BIGINT REFERENCES project(id) ON DELETE CASCADE;

