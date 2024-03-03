drop table if exists USER;

/*==============================================================*/
/* Table: USER                                                  */
/*==============================================================*/
create table IF NOT EXISTS USER
(
    id               int not null AUTO_INCREMENT,
    name             varchar(255) NOT NULL,
    primary key (id)
);