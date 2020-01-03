#mybatis文章
#https://www.cnblogs.com/rollenholt/p/3365866.html

create table admin(
 	 id              varchar(255)   primary key ,
 	 name            varchar(255),
 	 email           varchar(255),
 	 loginName       varchar(255),
 	 password 		 varchar(255),
     identity        varchar(255),
 	 superAccount 	 varchar(255)
 );

create table musics(
                      id              varchar(255)   primary key ,
                      name            varchar(255),
                      size           varchar(255),
                      duration       varchar(255),
                      addDateTime    varchar(255),
                      coverImgId             varchar(255),
                      fileName          varchar(255),
                      newFileName       varchar(255),
                      status           varchar(255),
                      info 	 text
);
create table users (
                           id           varchar(255)        primary key ,
                           userName             varchar(255)         null,
                           password             varchar(255)         null,
                           email                varchar(255)         null,
                           nickName             varchar(255)         null,
                           gender               varchar(255)         null,
                           coccupation          varchar(255)         null,
                           nativePlace          varchar(255)         null,
                           mobilePhone          varchar(255)         null,
                           intro                text                 null,
                           interest               text                 null,
                           headPortrait         varchar(255)         null,
                           registrationTime     datetime             null,
                           birthday             datetime             null
);

create  table  musics_type(

                              id              varchar(255)   primary key ,
                              name            varchar(255)
);


create table music_picture(
                       id              varchar(255)   primary key ,
                       musicsId         varchar(255),
                       name            varchar(255)
);