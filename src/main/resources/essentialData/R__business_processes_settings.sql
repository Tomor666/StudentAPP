INSERT INTO PUBLIC.USERS(ID,CREATED_AT, PASSWORD,EMAIL,ENABLED,USERNAME)
VALUES (nextval('USERS_ID_SEQ'), now(), '$2a$12$iF5hLTZXvIs2fQG52v2KyudxTCEzz0J7WjWS.7NpAeH25.MyrYkXC' ,
        'admin@gmail.com', true,'master_admin');


INSERT INTO PUBLIC.AUTHORITIES(ID,authority)
VALUES (nextval('AUTHORITIES_ROLE_ID_SEQ'),'ROLE_ADMIN');
