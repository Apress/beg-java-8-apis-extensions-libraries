create table person (
	person_id integer not null primary key, 
	first_name varchar(20) not null, 
	last_name varchar(20) not null, 
	gender char(1) not null, 
	dob datetime null, 
	income double null
);

