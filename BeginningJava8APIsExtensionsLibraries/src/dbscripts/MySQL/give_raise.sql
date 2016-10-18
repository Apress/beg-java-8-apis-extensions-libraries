DELIMITER $$

DROP PROCEDURE IF EXISTS give_raise $$

CREATE PROCEDURE give_raise(in person_id_param int, in raise double, 
                          out old_income double, out new_income double)
BEGIN

set old_income = null, new_income = null;

if exists(select null from person where person_id=person_id_param) then
    select income into old_income
      from person
     where person_id = person_id_param;

    if old_income is null then
        select 20000.00 into new_income;
    else
        select old_income * (1 + raise/100) into new_income;
    end if;

    update person
       set income = new_income
     where person_id = person_id_param;
end if;

END $$

DELIMITER ;

