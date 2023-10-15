import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClassCampsManager extends ClassCamp, ClassActivity{
    private String campsFile = "camps.txt";
    private String activitiesFile = "activities.txt";
    private String monitorsFile = "monitors.txt";

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

    public List<ClassCamp> loadCamps() {
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

    public List<ClassActivity> loadActivities() {
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

    public List<ClassMonitor> loadMonitors() {
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

    public void addMonitor(ClassMonitor monitor) {
        if (monitors_.size() < getminMonitors()) {
            monitors_[monitors_n_] = monitor;
            monitors_n_++;
        } else {
            System.out.println("Se ha alcanzado el mínimo requerido de monitores para esta actividad.");
        }
    }
}
    
    

