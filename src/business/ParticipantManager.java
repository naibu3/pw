package business;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import data.dto.Participant;


/** Description.
 * @version 1.0
*/
public class ParticipantManager {
    private List<Participant> registeredParticipants = new ArrayList<Participant>();
    private String participantsFile = "registered.txt";

    public ParticipantManager() {
        try {
            loadFile();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFile() {
        try (FileWriter archivo = new FileWriter(participantsFile, false)) {
            for (Participant participant : registeredParticipants) {
                archivo.write(participant.toString());
            }
            archivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFile() throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(participantsFile)))) {
            String line;
            int id = 0;
            String name = null, lastName = null;
            LocalDate birth = null;
            boolean specialAttention = false;

            while ((line = br.readLine()) != null) {
                String[] partes = line.split(":");
                if (partes.length == 2) {
                    String parametro = partes[0].trim();
                    String valor = partes[1].trim();

                    switch (parametro) {
                        case "Id":
                            id = Integer.parseInt(valor);
                            break;
                        case "Name":
                            name = valor;
                            break;
                        case "Lastname":
                            lastName = valor;
                            break;
                        case "Birth":
                            birth = LocalDate.parse(valor);
                            break;
                        case "Special attention":
                            specialAttention = Boolean.parseBoolean(valor);
                            break;
                        default:
                            // Parámetro desconocido, puedes manejarlo como desees
                            break;
                    }
                } else {
                    // Línea incorrectamente formateada, puedes manejarla como desees
                }

                if (id != 0 && name != null && lastName != null && birth != null) {
                    registeredParticipants.add(new Participant(id, name, lastName, birth, specialAttention));
                    // Reinicia las variables para la próxima entrada
                    id = 0;
                    name = null;
                    lastName = null;
                    birth = null;
                    specialAttention = false;
                }
            }
        }
    }

    public void enrollParticipant(Participant participant) {
        registeredParticipants.add(participant);
        writeFile();
    }

    public void modifyParticipant(int id, LocalDate newBirthday, Boolean specialNeeds) {
        for (Participant participant : registeredParticipants) {
            if (participant.getId() == id) {
                System.out.println("Modifying participant with ID " + id);
                participant.set_birthday(newBirthday);
                participant.set_special_attention(specialNeeds);
                break;
            }
        }

        writeFile();
    }

    public void modifyParticipant(int id, LocalDate newBirthday) {
        for (Participant participant : registeredParticipants) {
            if (participant.getId() == id) {
                System.out.println("Modifying birthday for participant with ID " + id);
                participant.set_birthday(newBirthday);
                break;
            }
        }

        writeFile();
    }

    public void modifyParticipant(int id, Boolean specialNeeds) {
        for (Participant participant : registeredParticipants) {
            if (participant.getId() == id) {
                System.out.println("Modifying birthday for participant with ID " + id);
                participant.set_special_attention(specialNeeds);
                break;
            }
        }

        writeFile();
    }

    public List<Participant> listParticipants() {
        return registeredParticipants;
    }

    public void writeParticipants() {
        for (Participant participant : registeredParticipants) {
            System.out.println(participant.toString());
        }
    }

    public boolean participantExists(int id) {
        for (Participant p : registeredParticipants) {
            if (p.getId() == id) {
                return true;
            }
        }

        return false;
     }
}
