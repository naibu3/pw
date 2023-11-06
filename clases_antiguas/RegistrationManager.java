import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegistrationManager {
    private List<AbstractRegistration> registrations = new ArrayList<AbstractRegistration>();
    private String registrationFile = "registrations.txt";

    /** Description
    */
    public RegistrationManager() {
        try {
            loadFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Description
    */
    private void writeFile() {
        try (FileWriter archivo = new FileWriter(registrationFile, false)) {
            for (AbstractRegistration registration : registrations) {
                archivo.write(registration.toString());
            }
            archivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Description
    */
    private void loadFile() throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(registrationFile)))) {
            String line;
            int id = 0, id_camp = 0;
            float price = 0;
            EnumRegistrationType type = null;
            EnumRegistrtationTime time = null;
            LocalDate registrationDate = null;
            boolean specialAttention = false;

            while ((line = br.readLine()) != null) {
                String[] partes = line.split(":");
                if (partes.length == 2) {
                    String parametro = partes[0].trim();
                    String valor = partes[1].trim();

                    switch (parametro) {
                        case "IdParticipant":
                            id = Integer.parseInt(valor);
                            break;
                        case "IdCamp":
                            id_camp = Integer.parseInt(valor);
                            break;
                        case "RegistrationDate":
                            registrationDate = LocalDate.parse(valor);
                            break;
                        case "Price":
                            price = Float.parseFloat(valor);
                            break;
                        case "Type":
                            type = EnumRegistrationType.valueOf(valor);
                            break;
                        case "Time":
                            time = EnumRegistrtationTime.valueOf(valor);
                            break;
                        case "SpecialNeeds":
                            specialAttention = Boolean.parseBoolean(valor);
                            break;
                        default:
                            // Parámetro desconocido, puedes manejarlo como desees
                            break;
                    }
                } else {
                    // Línea incorrectamente formateada, puedes manejarla como desees
                }

                if (id != 0 && id_camp != 0 && registrationDate != null && price != 0 && type != null && time != null) {
                    if (time == EnumRegistrtationTime.Early) {
                        registrations.add(new PartialRegistration(id, id_camp, registrationDate, price, type, time, specialAttention));
                    } else {
                        registrations.add(new FullRegistration(id, id_camp, registrationDate, price, type, time, specialAttention));
                    }
                    // Reinicia las variables para la próxima entrada
                    id = 0;
                    id_camp = 0;
                    registrationDate = null;
                    price = 0;
                    type = null;
                    time = null;
                    specialAttention = false;
                }
            }
        }
    }


    /** Description
     * @param id
     * @param camp
    */
    private boolean isUserRegistered(int id, int camp) {
        for (AbstractRegistration registration : registrations) {
            if (registration.getIdParticipant() == id && registration.getIdCamp() == camp) {
                return true;
            }
        }
        return false;
    }

    /** Description
     * @param id
     * @param id_camp
     * @param special
     * @param type
     * @param campsManager
     * @param participantManager
    */
    public boolean createEarlyRegistration(int id, int id_camp, boolean special, EnumRegistrationType type, CampsManager campsManager, ParticipantManager participantManager) {
        if (! isUserRegistered(id, id_camp) && participantManager.participantExists(id)) {
            PartialRegistration registration = new PartialRegistration();
            registration.setIdParticipant_(id);
            registration.setIdCamp_(id_camp);
            registration.setType(type);
            registration.setSpecialNeeds(special);
            if (type == EnumRegistrationType.Full) registration.setPrice(300); 
            else registration.setPrice(100);
            registrations.add(registration);
            writeFile();
            return true;
        }
        
        return false;
    }

    /** Description
     * @param id
     * @param id_camp
     * @param special
     * @param type
     * @param campsManager
     * @param participantManager
    */
    public boolean createLateRegistration(int id, int id_camp, boolean special, EnumRegistrationType type, ParticipantManager participantManager) {
        if (! isUserRegistered(id, id_camp) && participantManager.participantExists(id)) {
            FullRegistration registration = new FullRegistration();
            registration.setIdParticipant_(id);
            registration.setIdCamp_(id_camp);
            registration.setType(type);
            registration.setSpecialNeeds(special);
            if (type == EnumRegistrationType.Full) registration.setPrice(300); 
            else registration.setPrice(100);
            registrations.add(registration);
            writeFile();
            return true;
        }
        
        return true;
    }
}
