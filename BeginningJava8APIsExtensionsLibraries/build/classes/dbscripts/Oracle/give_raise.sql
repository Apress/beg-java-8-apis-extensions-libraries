create or replace procedure give_raise(person_id_param number, 
                                       raise_param number, 
                                       old_income out number,
                                       new_income out number)
is
 person_count number; 
begin  
 old_income := null;
 new_income := null;  

 select count(*) 
   into person_count
   from person 
  where person_id = person_id_param;
  
 if person_count = 1 then 
    select income into old_income 
      from person    
     where person_id = person_id_param; 
    
     if old_income is null then  
        new_income := 20000.00;
     else    
        new_income := old_income * (1 + raise_param/100) ; 
     end if;      
     update person      
        set income = new_income    
      where person_id = person_id_param; 
 end if; 
    
end give_raise;

