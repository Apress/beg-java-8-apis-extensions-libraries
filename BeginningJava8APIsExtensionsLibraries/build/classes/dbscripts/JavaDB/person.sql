create table person (
	person_id integer not null, 
	first_name varchar(20) not null, 
	last_name varchar(20) not null, 
	gender char(1) not null, 
	dob date , 
	income double,
	primary key(person_id)
);
