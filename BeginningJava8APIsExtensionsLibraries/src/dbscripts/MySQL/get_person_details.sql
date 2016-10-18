DELIMITER $$

DROP PROCEDURE IF EXISTS get_person_details $$

CREATE PROCEDURE get_person_details(in person_id_param int)
BEGIN
 select person_id, first_name, last_name, gender, dob, income
   from person
  where person_id = person_id_param;
END $$

DELIMITER ;

