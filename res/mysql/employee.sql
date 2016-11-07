use s2emp;
drop table if exists Games;
create table Games (
  gameId int unsigned not null auto_increment,
  title varchar(24) not null,
  rating varchar(18) not null,
  genre varchar(14) not null,
  primary key(gameId)
);
