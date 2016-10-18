-- Drop stored procedure if it already exists
IF EXISTS (
  SELECT * 
    FROM INFORMATION_SCHEMA.ROUTINES 
   WHERE SPECIFIC_SCHEMA = N'dbo'
     AND SPECIFIC_NAME = N'get_person_details' 
)
   DROP PROCEDURE dbo.get_person_details
GO

CREATE PROCEDURE dbo.get_person_details	
       @person_id int 
AS
BEGIN
    SELECT person_id, first_name, last_name, gender, dob, income
      FROM person
     WHERE person_id = @person_id;
END;
GO

