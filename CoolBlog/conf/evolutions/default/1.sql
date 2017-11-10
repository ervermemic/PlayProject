# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table category (
  id                            bigint auto_increment not null,
  category_name                 varchar(50),
  constraint uq_category_category_name unique (category_name),
  constraint pk_category primary key (id)
);

create table comment (
  id                            bigint auto_increment not null,
  user_id                       bigint,
  create_date                   varchar(255),
  post_id                       bigint,
  content                       varchar(1000),
  constraint pk_comment primary key (id)
);

create table post (
  id                            bigint auto_increment not null,
  title                         varchar(255),
  category_id                   bigint,
  user_id                       bigint,
  create_date                   varchar(255),
  story                         varchar(8000),
  visible                       integer,
  constraint pk_post primary key (id)
);

create table post_user (
  post_id                       bigint not null,
  user_id                       bigint not null,
  constraint pk_post_user primary key (post_id,user_id)
);

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

alter table comment add constraint fk_comment_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_comment_user_id on comment (user_id);

alter table comment add constraint fk_comment_post_id foreign key (post_id) references post (id) on delete restrict on update restrict;
create index ix_comment_post_id on comment (post_id);

alter table post add constraint fk_post_category_id foreign key (category_id) references category (id) on delete restrict on update restrict;
create index ix_post_category_id on post (category_id);

alter table post add constraint fk_post_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_post_user_id on post (user_id);

alter table post_user add constraint fk_post_user_post foreign key (post_id) references post (id) on delete restrict on update restrict;
create index ix_post_user_post on post_user (post_id);

alter table post_user add constraint fk_post_user_user foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_post_user_user on post_user (user_id);

alter table user add constraint fk_user_role_id foreign key (role_id) references role (id) on delete restrict on update restrict;
create index ix_user_role_id on user (role_id);


# --- !Downs

alter table comment drop foreign key fk_comment_user_id;
drop index ix_comment_user_id on comment;

alter table comment drop foreign key fk_comment_post_id;
drop index ix_comment_post_id on comment;

alter table post drop foreign key fk_post_category_id;
drop index ix_post_category_id on post;

alter table post drop foreign key fk_post_user_id;
drop index ix_post_user_id on post;

alter table post_user drop foreign key fk_post_user_post;
drop index ix_post_user_post on post_user;

alter table post_user drop foreign key fk_post_user_user;
drop index ix_post_user_user on post_user;

alter table user drop foreign key fk_user_role_id;
drop index ix_user_role_id on user;

drop table if exists category;

drop table if exists comment;

drop table if exists post;

drop table if exists post_user;

drop table if exists role;

drop table if exists user;

