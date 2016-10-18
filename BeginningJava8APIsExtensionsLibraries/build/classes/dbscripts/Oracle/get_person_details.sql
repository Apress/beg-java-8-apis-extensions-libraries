CREATE OR REPLACE
PACKAGE JDBC_TEST_PKG 
AS
 type person_cursor_type is ref cursor;
END JDBC_TEST_PKG;


create or replace PROCEDURE GET_PERSON_DETAILS
( person_id_param IN NUMBER, 
  person_cursor OUT jdbc_test_pkg.person_cursor_type
) 
AS
BEGIN
  open person_cursor for 
  select person_id, first_name, last_name, gender, dob, income
    from person 
  where person_id = person_id_param;  
END GET_PERSON_DETAILS;

