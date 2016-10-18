create table person (
	person_id number(8,0) not null, 
	first_name varchar2(20) not null,
	last_name varchar2(20) not null, 
	gender char(1) not null, 
	dob date, 
	income number(10,2),
	constraint pk_person primary key(person_id)
);

