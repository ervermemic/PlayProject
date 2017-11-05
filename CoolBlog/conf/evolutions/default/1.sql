# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table role (
  id                            bigint auto_increment not null,
  name                          varchar(10),
  constraint pk_role primary key (id)
);

create table user (
  id                            bigint auto_increment not null,
  username                      varchar(50),
  email                         varchar(100),
  password                      varchar(100),
  birth_date                    varchar(255),
  create_date                   varchar(255),
  role_id                       bigint,
  constraint uq_user_email unique (email),
  constraint pk_user primary key (id)
);

alter table user add constraint fk_user_role_id foreign key (role_id) references role (id) on delete restrict on update restrict;
create index ix_user_role_id on user (role_id);


# --- !Downs

alter table user drop foreign key fk_user_role_id;
drop index ix_user_role_id on user;

drop table if exists role;

drop table if exists user;

