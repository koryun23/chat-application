create sequence user_sequence start 1
create sequence role_sequence start 1
create sequence app_role_sequence start 1
create sequence group_chat_role_sequence start 1
create sequence user_app_role_sequence start 1
create sequence user_group_chat_role_sequence start 1
create sequence message_sequence start 1
create sequence message_group_chat_sequence start 1
create sequence message_personal_chat_sequence start 1
create sequence message_user_status start 1
create sequence group_chat_sequence start 1
create sequence personal_chat_sequence start 1

create table users (
    created_at timestamp not null,
    first_name varchar(40) not null,
    password varchar(255) not null,
    second_name varchar(40) not null,
    username varchar(40) not null,
    serial primary key (id)
)

create table roles (
    role_type varchar(40) not null,
    serial primary key (id)
)

create table app_roles (
    role_id int4 not null,
    serial primary key (id)
)

create table group_chat_roles (
    role_id int4 not null,
    serial primary key (id)
)

create table user_app_roles (
    app_role_id int4 not null,
    user_id int8 not null,
    serial primary key (id)
)

create table user_group_chat_roles (
    group_chat_role_id int4 not null,
    user_id int8 not null,
    group_chat_id int8 not null,
    serial primary key (id)
)

create table messages (
    message varchar(600) not null,
    sent_at timestamp not null,
    serial primary key (id)
)

create table message_group_chat (
    message_id int8 not null,
    group_chat_id int8 not null,
    sender_id int8 not null,
    serial primary key (id)
)

create table message_personal_chat (
    message_id int8 not null,
    sender_id int8 not null,
    receiver_id int8 not null,
    serial primary key (id)
)

create table message_user_status (
    message_id int8 not null,
    user_id int8 not null,
    status varchar(10) not null,
    serial primary key (id)
)

create table group_chats (
    name varchar(40) not null,
    created_at timestamp not null,
    serial primary key (id)
)

create table personal_chats (
    first_user_id int8 not null,
    second_user_id int8 not null,
    serial primary key (id)
)

alter table if exists users add constraint UK_USERNAME unique (username)
alter table if exists roles add constraint UK_ROLE_TYPE unique (role_type)
alter table if exists app_roles add constraint FK_APP_ROLE_ID_ROLE_ID foreign key (role_id) references roles
alter table if exists group_chat_roles add constraint FK_GROUP_CHAT_ROLE_ID_ROLE_ID foreign key (role_id) references roles
alter table if exists user_app_roles add constraint FK_APP_ROLE_ID_APP_ROLE_ID foreign key (app_role_id) references app_roles
alter table if exists user_app_roles add constraint FK_USER_ID_USER_ID foreign key (user_id) references users
alter table if exists user_group_chat_roles add constraint FK_USER_GROUP_CHAT_USER_ID_USER_ID foreign key (user_id) references users
alter table if exists user_group_chat_roles add constraint FK_USER_GROUP_CHAT_GROUP_CHAT_ROLE_ID_ROLE_ID foreign key (group_chat_role_id) references roles
alter table if exists user_group_chat_roles add constraint FK_USER_GROUP_CHAT_ID_GROUP_CHAT_ID foreign key (group_chat_id) references group_chats
alter table if exists message_group_chat add constraint FK_MESSAGE_GROUP_CHAT_GROUP_CHAT_ID_GROUP_CHAT_ID foreign key (group_chat_id) references group_chats
alter table if exists message_group_chat add constraint FK_MESSAGE_GROUP_CHAT_MESSAGE_ID_MESSAGE_ID foreign key (message_id) references messages
alter table if exists message_group_chat add constraint FK_MESSAGE_GROUP_CHAT_SENDER_ID_USER_ID foreign key (sender_id) references users
alter table if exists message_personal_chat add constraint FK_MESSAGE_PERSONAL_CHAT_MESSAGE_ID_MESSAGE_ID foreign key (message_id) references messages
alter table if exists message_personal_chat add constraint FK_MESSAGE_PERSONAL_CHAT_SENDER_ID_USER_ID foreign key (sender_id) references users
alter table if exists message_personal_chat add constraint FK_MESSAGE_PERSONAL_CHAT_RECEIVER_ID_USER_ID foreign key (receiver_id) references users
alter table if exists message_user_status add constraint FK_MESSAGE_USER_STATUS_MESSAGE_ID_MESSAGE_ID foreign key (message_id) references messages
alter table if exists message_user_status add constraint FK_MESSAGE_USER_STATUS_USER_ID_USER_ID foreign key (user_id) references users
alter table if exists group_chats add constraint UK_GROUP_CHAT_NAME unique (name)
alter table if exists personal_chats add constraint FK_PERSONAL_CHATS_FIRST_USER_ID_USER_ID foreign key (first_user_id) references users
alter table if exists personal_chats add constraint FK_PERSONAL_CHATS_SECOND_USER_ID_USER_ID foreign key (second_user_id) references users
