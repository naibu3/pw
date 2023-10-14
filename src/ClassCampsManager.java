import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClassCampsManager {
    private String campsFile = "camps.txt";
    private String activitiesFile = "activities.txt";
    private String monitorsFile = "monitors.txt";

    public ClassCampsManager() {
    }

    public void createCamp(ClassCamp camp) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(campsFile))) {
            outputStream.writeObject(camp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createActivity(ClassActivity activity) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(activitiesFile))) {
            outputStream.writeObject(activity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createMonitor(ClassMonitor monitor) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(monitorsFile))) {
            outputStream.writeObject(monitor);
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
}
