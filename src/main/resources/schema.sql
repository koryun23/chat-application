   alter table chat_message
       drop constraint FK_CHAT_MESSAGE_CHAT_ID_CHAT_ID

    alter table chat_message
       drop constraint FK_CHAT_MESSAGE_MESSAGE_ID_MESSAGE_ID

    alter table message
       drop constraint FK_MESSAGE_USER_ID_USER_ID

    alter table user_app_role
       drop constraint USER_APP_ROLE_USER_ID_USER_ID

    alter table user_chat
       drop constraint USER_CHAT_CHAT_CHAT_ID

    alter table user_chat
       drop constraint USER_CHAT_USER_USER_ID

    alter table user_message
       drop constraint FK_USER_MESSAGE_MESSAGE_ID_MESSAGE_ID

    alter table user_message
       drop constraint FK_USER_MESSAGE_USER_ID_USER_ID

    drop table if exists chat_message cascade

    drop table if exists chats cascade

    drop table if exists message cascade

    drop table if exists user_app_role cascade

    drop table if exists user_chat cascade

    drop table if exists user_message cascade

    drop table if exists users cascade

    drop sequence if exists chat_message_sequence

    drop sequence if exists chat_sequence

    drop sequence if exists message_sequence

    drop sequence if exists user_app_role_sequence

    drop sequence if exists user_group_chat_sequence

    drop sequence if exists user_message_sequence

    drop sequence if exists user_sequence
create sequence chat_message_sequence start 1 increment 50
create sequence chat_sequence start 1 increment 50
create sequence message_sequence start 1 increment 50
create sequence user_app_role_sequence start 1 increment 50
create sequence user_group_chat_sequence start 1 increment 50
create sequence user_message_sequence start 1 increment 50
create sequence user_sequence start 1 increment 50

    create table chat_message (
       id int8 not null,
        chat_id int8 not null,
        message_id int8 not null,
        primary key (id)
    )

    create table chats (
       id int8 not null,
        chat_type int4 not null,
        created_at timestamp not null,
        chat_name varchar(255) not null,
        primary key (id)
    )

    create table message (
       id int8 not null,
        message_body varchar(600) not null,
        sent_at timestamp not null,
        user_id int8 not null,
        primary key (id)
    )

    create table user_app_role (
       id int8 not null,
        user_app_role_type varchar(20) not null,
        user_id int8 not null,
        primary key (id)
    )

    create table user_chat (
       id int8 not null,
        role varchar(40) not null,
        chat_id int8 not null,
        user_id int8 not null,
        primary key (id)
    )

    create table user_message (
       id int8 not null,
        message_status_type int4 not null,
        message_id int8 not null,
        user_id int8 not null,
        primary key (id)
    )

    create table users (
       id int8 not null,
        first_name varchar(255) not null,
        joined_at timestamp not null,
        password varchar(255) not null,
        second_name varchar(255) not null,
        username varchar(255) not null,
        primary key (id)
    )

    alter table user_app_role
       add constraint UK_kspiomjr2bpobqc8puej8xg65 unique (user_id)

    alter table users
       add constraint UK_r53o2ojjw4fikudfnsuuga336 unique (password)

    alter table users
       add constraint UK_r43af9ap4edm43mmtq01oddj6 unique (username)

    alter table chat_message
       add constraint FK_CHAT_MESSAGE_CHAT_ID_CHAT_ID
       foreign key (chat_id)
       references chats

    alter table chat_message
       add constraint FK_CHAT_MESSAGE_MESSAGE_ID_MESSAGE_ID
       foreign key (message_id)
       references message

    alter table message
       add constraint FK_MESSAGE_USER_ID_USER_ID
       foreign key (user_id)
       references users

    alter table user_app_role
       add constraint USER_APP_ROLE_USER_ID_USER_ID
       foreign key (user_id)
       references users

    alter table user_chat
       add constraint USER_CHAT_CHAT_CHAT_ID
       foreign key (chat_id)
       references chats

    alter table user_chat
       add constraint USER_CHAT_USER_USER_ID
       foreign key (user_id)
       references users


    alter table user_message
       add constraint FK_USER_MESSAGE_MESSAGE_ID_MESSAGE_ID
       foreign key (message_id)
       references message

    alter table user_message
       add constraint FK_USER_MESSAGE_USER_ID_USER_ID
       foreign key (user_id)
       references users