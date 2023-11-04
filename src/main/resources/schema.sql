create table about(

  id bigint not null auto_increment,
  aboutmovie varchar(1024) not null,
  movie_id bigint,
  primary key (id)

);

create table movie(

    id bigint not null auto_increment,
    name varchar(255) unique not null,
    imdb double not null,
    releasedate bigint not null,
    director varchar(255) not null,
    writer varchar(255) not null,
    about_id bigint,
    img bytea,
    primary key (id)

);


create table moviecast(

   id bigint not null auto_increment,
   firstname varchar(255),
   lastname varchar(255),
   movie_id bigint,
   primary key (id)

);

create table genre(

   id bigint not null auto_increment,
   description varchar(255) not null,
   primary key (id)

);


create table movie_genre(

    movie_id bigint not null,
    genre_id bigint not null,
    primary key(movie_id, genre_id)

);


alter table about add constraint FKdbfsiv21ocsbt63sd6fg0t3c2s8 foreign key(movie_id) references movie(id);
alter table movie add constraint FKdbfsiv21ocsbt63sd6fg0t3c28 foreign key(about_id) references about(id);


alter table moviecast add constraint FKdbfsiv21ocsbt63sd6fsg0t3c28 foreign key(movie_id) references movie(id);

alter table movie_genre add constraint FK6iv5l89qmitedn5m2a71kta2t foreign key(movie_id) references movie(id);
alter table movie_genre add constraint FK6iv5l89qmitedn5m2a71kta7t foreign key(genre_id) references genre(id);


insert into genre (description) values ('Action');
insert into genre (description) values ('Drama');
insert into genre (description) values ('Crime');

