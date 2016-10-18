create procedure give_raise(IN @person_id integer, IN @raise double, 
                            OUT @old_income double, OUT @new_income double)
begin
 select @old_income = null, @new_income = null;
 
 if exists(select null from person where person_id = @person_id) then          
   select income into @old_income
    from person 
   where person_id = @person_id;
    
   if @old_income is null then
     select 20000.00 into @new_income;
   else
     select @old_income * (1 + @raise/100) into @new_income;
   end if;
    
   update person 
      set income = @new_income
    where person_id = @person_id;
 end if;  
end;

