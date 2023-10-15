import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.management.monitor.Monitor;

public class ClassCampsManager {
    private List<ClassCamp> camps = new ArrayList<ClassCamp>();
    private List<ClassActivity> activities = new ArrayList<ClassActivity>();
    private List<ClassMonitor> monitors = new ArrayList<ClassMonitor>();
    private String campsFile = "camps.txt";
    private String activitiesFile = "activities.txt";
    private String monitorsFile = "monitors.txt";

    public ClassCampsManager() {
        
        try {
            loadCampsFile();
            loadMonitorsFile();
            loadActivitiesFile();
           
        } catch (FileNotFoundException e) {
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    private void writeFileC() {
        try (FileWriter archivo = new FileWriter(campsFile, false)) {
            for (ClassCamp camp : camps) {
                archivo.write(camp.toString());
            }
            archivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFileA() {
        try (FileWriter archivo = new FileWriter(activitiesFile, false)) {
            for (ClassActivity act : activities) {
                archivo.write(act.toString());
            }
            archivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFileM() {
        try (FileWriter archivo = new FileWriter(monitorsFile, false)) {
            for (ClassMonitor mon: monitors) {
                archivo.write(mon.toString());
            }
            archivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCampsFile() throws FileNotFoundException, IOException {
        ClassCamp auxCamp=new ClassCamp();
        try (BufferedReader br = new BufferedReader(new FileReader(new File(campsFile)))) {
            String line;
            int id = 0, nMax=0;
            LocalDate begin = null, end=null;
            EnumLevel level=null;
            int monitorR=0, monitorS=0;
            boolean read=false;
            List<String> acts = new ArrayList<String>();
            List<Integer> monitors = new ArrayList<Integer>();

            while ((line = br.readLine()) != null) {
                String[] partes = line.split(":");
                if (partes.length == 2) {
                    String parametro = partes[0].trim();
                    String valor = partes[1].trim();

                    switch (parametro) {
                        case "Camp ID":
                            id = Integer.parseInt(valor);
                            break;
                        case "Beginning on":
                            begin = LocalDate.parse(valor);
                            
                            break;
                        case "Ending on":
                            end = LocalDate.parse(valor);
                            
                            break;
                        case "Educative level":
                            level = EnumLevel.valueOf(valor);
                            
                            break;
                        case "Max number of assistants":
                            nMax = Integer.parseInt(valor);
                            
                            break;
                        case "Activities":
                            String contenidoArray = valor.substring(valor.indexOf("[") + 1, valor.lastIndexOf("]"));

                            String[] valores = contenidoArray.split(",\\s*");
                        
                            List<String> listaDeActividades = new ArrayList<>();
                            
                            for (String actividad : valores) {
                                listaDeActividades.add(actividad);
                            }
                        
                            acts=listaDeActividades;                        
                            break;
                        case "Responsible monitor":
                            monitorR=Integer.parseInt(valor);
                            
                            break;
                        case "Special monitor":
                            monitorS=Integer.parseInt(valor);
                            read=true;
                            break;
                        default:
                            // Parámetro desconocido, puedes manejarlo como desees
                            break;
                    }
                } else {
                    System.out.println("Error mal formato");
                    break;
                    // Línea incorrectamente formateada, puedes manejarla como desees
                }

               
                if (id != 0 && nMax != 0 && begin != null && end != null && level!= null && read) {
                    
                    auxCamp=(new ClassCamp(id, begin, end, level, nMax));
                    
                    if(monitorR!=0){
                        auxCamp.setresponsibleMonitors(monitorR);
                        
                    }
                    if(monitorS!=0){
                        auxCamp.setresponsiblespecialMonitor(monitorS);    
                    }
                    if(acts!=null){
                        auxCamp.setClassactivity(acts);
                    }
                    if(monitors!=null){
                        auxCamp.setMonitors(monitors);;
                    }
                    camps.add(auxCamp);
                    // Reinicia las variables para la próxima entrada
                    id = 0;
                    nMax = 0;
                    begin = null;
                    end = null;
                    level = null;
                    monitorR =0;
                    monitorS=0;
                    monitors=null;
                    acts=null;
                    read=false;
                }
            }
        }
    }

    private void loadMonitorsFile() throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(monitorsFile)))) {
            String line;
            int id = 0;
            String name = null, lastName = null;
            boolean specialNeedsEducator = false, controlador=false;


            while ((line = br.readLine()) != null) {
                String[] partes = line.split(":");
                if (partes.length == 2) {
                    String parametro = partes[0].trim();
                    String valor = partes[1].trim();

                    switch (parametro) {
                        case "ID":
                            id = Integer.parseInt(valor);
                            break;
                        case "Name":
                            name = valor;
                            break;
                        case "Lastname":
                            lastName = valor;
                            break;
                        case "Special educator":
                            specialNeedsEducator = Boolean.parseBoolean(valor);
                            controlador=true;
                            break;
                        default:
                            // Parámetro desconocido, puedes manejarlo como desees
                            break;
                    }
                } else {
                    // Línea incorrectamente formateada, puedes manejarla como desees
                }

                if (id != 0 && name != null && lastName != null && controlador) {
                    monitors.add(new ClassMonitor(id, name, lastName, specialNeedsEducator));
                    // Reinicia las variables para la próxima entrada
                    id = 0;
                    name = null;
                    lastName = null;
                    specialNeedsEducator = false;
                    controlador=false;
                }
            }
        }
    }

    private void loadActivitiesFile() throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(activitiesFile)))) {
            String line;
            String name = null;
            boolean controlador=false;
            EnumLevel level=null;
            String timetable=null;
            int max=0, n_mon=0;
            List<Integer> mon=new ArrayList<Integer>();
            ClassActivity auxAct =new ClassActivity();


            while ((line = br.readLine()) != null) {
                String[] partes = line.split(":");
                if (partes.length == 2) {
                    String parametro = partes[0].trim();
                    String valor = partes[1].trim();

                    switch (parametro) {
                        case "Name":
                            name = valor;
                            
                            break;
                        case "Level":
                            level = EnumLevel.valueOf(valor);
                            
                            break;
                        case "Timetable":
                            timetable=valor;
                            
                            break;
                        case "Max":
                            max=Integer.parseInt(valor);
                            
                            break;
                        case "Numero de monitores":
                            n_mon=Integer.parseInt(valor);
                            
                            break;
                        case "Monitores":
                            
                            String contenidoLista = valor.substring(valor.indexOf("[") + 1, valor.lastIndexOf("]"));
        
                        
                            String[] valoresStr = contenidoLista.split(",\\s*");
                
                            List<Integer> listaDeEnteros = new ArrayList<>();
                        
                            for (String valorStr : valoresStr) {
                                // Convierte el valor de cadena a entero y agrega a la lista
                                int valorEntero = Integer.parseInt(valorStr);
                                listaDeEnteros.add(valorEntero);
                            }
                            
                            mon=listaDeEnteros;
                            
                            controlador=true;
                            break;                
                        default:
                            // Parámetro desconocido, puedes manejarlo como desees
                            break;
                    }
                } else {
                    // Línea incorrectamente formateada, puedes manejarla como desees
                }

                if (name != null && level != null && timetable!=null && max!=0 && n_mon!=0 && controlador) {
                    // Reinicia las variables para la próxima entrada
                    
                    auxAct=(new ClassActivity(name,level,timetable,max, n_mon));
                    if(mon!=null){
                        auxAct.setMonitors_(mon);
                    }
                   
                    activities.add(auxAct);
                    
                    name = null;
                    level = null;
                    timetable = null;
                    max=0;
                    n_mon=0;
                    mon=null;
                    controlador=false;
                }
            }
        }
    }

    public void createCamp(ClassCamp camp) {
        camps.add(camp);
        writeFileC();
    }

    public void createActivity(ClassActivity activity) {
        activities.add(activity);
        writeFileA();
    }

    public void createMonitor(ClassMonitor monitor) {
        monitors.add(monitor);
        writeFileM();
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

    public void associateActivity(String nameAct, int idCamp ) {
        List<String>auxActs= new ArrayList<String>();
        List<ClassCamp> auxcamps = new ArrayList<ClassCamp>();
        
        for(ClassCamp auxCamp: camps){
            if(auxCamp.getId()==idCamp){
                auxActs=auxCamp.getClassactivity();
                auxActs.add(nameAct);
                auxCamp.setClassactivity(auxActs);
            }
            auxcamps.add(auxCamp);
        }

        camps=auxcamps;

        writeFileC();               

    }

    public void associateMonitor(int id, int idCamp ) {   
        List<Integer>auxIdMon = new ArrayList<Integer>();
        List<ClassCamp> auxcamps = new ArrayList<ClassCamp>();
        
        for(ClassCamp auxCamp: camps){
            if(auxCamp.getId()==idCamp){
                auxIdMon=auxCamp.getMonitors();
                auxIdMon.add(id);
                auxCamp.setMonitors(auxIdMon);
            }
            auxcamps.add(auxCamp);
        }
    
        camps=auxcamps;
    
        writeFileM(); 
    
    }

    
}
    
    
