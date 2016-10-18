create table person (
	person_id int NOT NULL , 
	first_name varchar(20) NOT NULL , 
	last_name varchar(20) NOT NULL , 
	gender char(1) NOT NULL , 
	dob datetime NULL , 
	income decimal(10,2) NULL , 
	constraint pk_person primary key (person_id)
);

