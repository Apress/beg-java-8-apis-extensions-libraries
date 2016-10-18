create procedure give_raise(IN person_id_param int,             
                            IN raise_param double,    
                            OUT old_income double,                       
                            OUT new_income double) 
language sql
begin  

 declare person_count int;

 set old_income = null; 
 set new_income = null;   

 select count(*) into person_count   
   from person   
 where person_id = person_id_param;  

 if person_count = 1 then     
      select income into old_income    
       from person         
      where person_id = person_id_param; 

     if old_income is null then         
        set new_income =  20000.00;  
     else           
        set new_income = old_income * (1 + raise_param/100) ;     
     end if;          

     update person 
        set income = new_income         
      where person_id = person_id_param; 
 end if;     
end
@
