--Command to create a stored procedure
CREATE PROCEDURE give_raise(IN person_id integer, IN raise double, OUT old_income Double, OUT new_income Double)
PARAMETER STYLE JAVA
LANGUAGE JAVA
MODIFIES SQL DATA
EXTERNAL NAME 'com.jdojo.jdbc.JavaDBGiveRaiseSp.giveRaise';

