package business;

import java.time.LocalDate;

import data.dao.RegistrationDAO;
import data.dto.RegistrationDTO;

import data.shared.Time;
import data.shared.Type;

public class RegistrationManager {

    /*
     * Constructor
     */
    public RegistrationManager(){}


    public float createRegistration(int idP, int idC, LocalDate rDate, float price, Type type){
        RegistrationDAO myDao=new RegistrationDAO();
        Time time=Time.valueOf("Late");
        rDate=LocalDate.now();
        RegistrationDTO myDTO=new RegistrationDTO(idP, idC, rDate, price, type, time);                
        float prize=myDao.createRegistration(myDTO);
        return prize;
    }

    public void updateRegistration(int registrationId, int idPartcipant, int idCamp, LocalDate registrationDate,float price, Type type){
        RegistrationDAO myDao=new RegistrationDAO();              
        myDao.updateRegistration(idP, idC, rDate, price, type);
    }

    

    /*
     * Remove a registration given an dni and a idCamp
     */
    public Boolean removeRegistration(int dni, int idCamp) {
        RegistrationDAO deleteDao=new RegistrationDAO();
        deleteDao.deleteRegistration(dni,idCamp);
        return true;
    }

    /*
     * Esta funcion es para obtener los campamenos que aun no han empezado y aun tienen hueco
     * disponible. comparar si la fecha de inicio del campamento es menor que la fecha del ordenador
     * actual
     */
    public void getAvailableCamps() {

    }
}
