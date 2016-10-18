create procedure get_person_details(@person_id integer) 
as
begin
  select person_id, first_name, last_name, gender, dob, income 
    from person
   where person_id = @person_id
end

