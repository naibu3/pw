package business;

import java.time.LocalDate;
import java.util.Hashtable;
import java.util.ArrayList;

import data.common.DBConnection;
import data.dto.RegistrationDTO;

public class RegistrationManager {
    /*
     * Al crear una registration tengo que especificar si es mayor o menor de 15 dias y asignar time_
     */
    private RegistrationDTO createEarlyOrLate(LocalDate registrationDate) {
        /*
         * Aqui vamos a comprobar que si la fecha de registro es mayor o menor de 15 dias
         * si es menor de 15 dias vamos a hacer un late registration, en el caso contrario
         * devolvemos un early registration.
         */
        return new RegistrationDTO();
    }

    public Boolean createPartialRegistration() {
        /*
         * Esto es para crear un registro parcial. Las parciales tienen un costo de 100 euros base, 
         * y cada actividad son 20 euros extra.
         */
        return true;
    }

    public Boolean createFullRegistration() {
        /*
         * Registro a tiempo completo son 300 euros.
         */
        return true;
    }

    /*
     * Esto tengo que mirarlo, no se si el registration manager te permite borrar registros
     */
    public Boolean removeRegistration() {

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
