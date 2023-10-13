public class ClassRegistrationFactory {

    public ClassRegistration createEarlyRegistration(){

        /*The registration is made at least 15 days in advance, and allows 
        the possibility to cancel it, regardless of whether it is full or partial.*/
        
        ClassRegistration registration = new ClassRegistration(EnumRegistrtationTime.Early);
		return registration;
    }
    
    public ClassRegistration createLateRegistration(){

        /*Registration is made less than 15 days in advance and at least 48 
        hours  before  the  start  of  the  camp.  It  does  not  allow  for  cancellation,  and  is 
        applicable to both full and partial registrations.*/

        ClassRegistration registration = new ClassRegistration(EnumRegistrtationTime.Late);
		return registration;
    }
}
