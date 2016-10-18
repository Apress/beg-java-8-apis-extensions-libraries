
-- Drop stored procedure if it already exists
IF EXISTS (
  SELECT * 
    FROM INFORMATION_SCHEMA.ROUTINES 
   WHERE SPECIFIC_SCHEMA = N'dbo'
     AND SPECIFIC_NAME = N'give_raise' 
)
   DROP PROCEDURE dbo.give_raise
GO

CREATE PROCEDURE dbo.give_raise
	@person_id int, 
	@raise decimal(5, 2),
    @old_income decimal(10, 2) OUTPUT, 
    @new_income decimal(10, 2) OUTPUT
AS
BEGIN
	SET NOCOUNT OFF

	SELECT @old_income = null,  @new_income = null;  

	IF EXISTS (SELECT null FROM person WHERE person_id = @person_id) 
	BEGIN
		SELECT @old_income = income 
         FROM person
        WHERE person_id = @person_id;

		IF @old_income is null 
			SELECT @new_income = 20000.00;   
		ELSE
			SELECT @new_income = @old_income * (1 + @raise/100);

	    update person       
           set income = @new_income    
         WHERE person_id = @person_id; 
     END; 
END;
GO
