create table person (
	person_id integer not null default null, 
	first_name varchar(20) not null default null, 
	last_name varchar(20) not null default null, 
	gender char(1) not null default null, 
	dob date null default null, 
	income double null default null , 
	primary key (person_id)
);

