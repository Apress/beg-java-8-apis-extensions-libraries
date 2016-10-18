create table person (
	person_id integer not null , 
	first_name varchar(20) not null , 
	last_name varchar(20) not null , 
	gender character (1)  not null , 
	dob date, 
	income double, 
	constraint pk_person_id primary key (person_id)  
);

