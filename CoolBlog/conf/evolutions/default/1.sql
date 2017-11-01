# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table user (
  id                            bigint auto_increment not null,
  username                      varchar(50),
  email                         varchar(100),
  birth_date                    varchar(255),
  create_date                   varchar(255),
  constraint uq_user_email unique (email),
  constraint pk_user primary key (id)
);


# --- !Downs

drop table if exists user;

