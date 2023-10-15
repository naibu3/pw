import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClassCampsManager extends ClassCamp{
    private String campsFile = "camps.txt";
    private String activitiesFile = "activities.txt";
    private String monitorsFile = "monitors.txt";
    private String registeredFile = "registered.txt";

    public ClassCampsManager() {
    }

    public void createCamp(ClassCamp camp) {
        try (FileWriter archivo = new FileWriter(campsFile,true)) {
            archivo.write(camp.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createActivity(ClassActivity activity) {
        try (FileWriter archivo = new FileWriter(activitiesFile,true)) {
            archivo.write(activity.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createMonitor(ClassMonitor monitor) {
        try (FileWriter outputStream = new FileWriter(monitorsFile,true)) {
            outputStream.write(monitor.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<ClassParticipant> listParticipants() { //este es el ejemplo, modificar los otros en base a este
        List<ClassParticipant> listado = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(registeredFile))) {
            String linea;
            ClassParticipant aux = new ClassParticipant(); // Mover la declaración de ClassParticipant dentro del bucle
    
            while (((linea = br.readLine()) != null)) {
                if(linea.startsWith("Id:")){

                   aux.setId(Integer.parseInt(linea.substring(3).trim()));
                   listado.add(aux);                
                }
                    
                
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        
        
        
        return listado;
    }

    public List<ClassCamp> listCamps() {
        List<ClassCamp> camps = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(campsFile))) {
            while (true) {
                try {
                    ClassCamp camp = (ClassCamp) inputStream.readObject();
                    camps.add(camp);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return camps;
    }

    public List<ClassActivity> listActivities() {
        List<ClassActivity> activities = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(activitiesFile))) {
            while (true) {
                try {
                    ClassActivity activity = (ClassActivity) inputStream.readObject();
                    activities.add(activity);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return activities;
    }

    public List<ClassMonitor> listMonitors() {
        List<ClassMonitor> monitors = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(monitorsFile))) {
            while (true) {
                try {
                    ClassMonitor monitor = (ClassMonitor) inputStream.readObject();
                    monitors.add(monitor);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return monitors;
    }

            

    public static void searchCampStartDate(int campId, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean foundCamp = false;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Camp ID:") && !foundCamp) {
                    int id = Integer.parseInt(line.substring(9).trim());
                    if (id == campId) {
                        foundCamp = true;
                    }
                } else if (line.startsWith("Beginning on:") && foundCamp) {
                    System.out.println(line); // Imprime la línea que contiene la fecha de inicio
                    break; // Detiene la búsqueda después de encontrar la información
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }

    public void addMonitor(ClassMonitor monitor, ClassActivity activity) {
        ClassMonitor aux[];
        if (activity.getMonitors_().length<activity.getMonitors_n_() ) {
            aux=activity.getMonitors_();
            activity.setMonitors_(aux);
        } else {
            System.out.println("Se ha alcanzado el mínimo requerido de monitores para esta actividad.");
        }
    }
}
    
    

