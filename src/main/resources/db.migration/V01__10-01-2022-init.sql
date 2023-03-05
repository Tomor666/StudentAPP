
create table if not exists USERS(
                      ID                INT          NOT NULL PRIMARY KEY,
                      USERNAME          VARCHAR(50) not null,
                      PASSWORD          VARCHAR(255) not null,
                      ENABLED           boolean not null,
                      EMAIL             VARCHAR(70)  NOT NULL,
                      CREATED_AT        TIMESTAMP    NOT NULL DEFAULT NOW()
);


create table AUTHORITIES (
                             id                INT          NOT NULL PRIMARY KEY,
                             authority              VARCHAR(50) not null,
                             constraint fk_authorities_users foreign key(id) references users(ID)
);



