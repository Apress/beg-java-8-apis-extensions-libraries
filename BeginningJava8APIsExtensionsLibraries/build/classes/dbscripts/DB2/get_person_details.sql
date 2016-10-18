create procedure get_person_details(in person_id_param int)
result sets 1
language sql
begin
   declare c1 cursor with return for
    select person_id, first_name, last_name, gender, dob, income
      from person
     where person_id = person_id_param;
   open c1;
end 
@

