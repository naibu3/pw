import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClassParticipantManager {
    private String registeredFile="registered.txt";

    public void enrollParticipant(ClassParticipant participant) {
        try (FileWriter archivo = new FileWriter(registeredFile, true)) {
            archivo.write(participant.toString());
         //   System.out.println("Se ha escrito '" + participant.toString() + "' en el archivo '" + registeredFile + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void modifyParticipant(int id, LocalDate newBirthday, boolean newSpecialAttention) {
        String idToFind=Integer.toString(id);
           
        // Leer el archivo y realizar la modificación
        List<String> lineas = new ArrayList<>();
        boolean modificar = false;

        try (BufferedReader br = new BufferedReader(new FileReader(registeredFile))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Id:" + idToFind)) {
                    lineas.add(linea);
                    modificar = true;
                } else if (modificar && linea.startsWith("Special attention:")) {
                    lineas.add("Special attention:"+newSpecialAttention);
                    lineas.add("Birth:"+newBirthday.toString());
                    modificar = false;
                } else {
                    lineas.add(linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(registeredFile))) {
            for (int i=0; i<lineas.size(); i++) {
                bw.write(lineas.get(i));
                if (i < lineas.size() - 1) {
                    bw.newLine(); // Agregar una nueva línea si no es la última línea
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void modifyParticipant(int id, boolean newSpecialAttention) {
        String idToFind=Integer.toString(id);
           
        // Leer el archivo y realizar la modificación
        List<String> lineas = new ArrayList<>();
        boolean modificar = false;

        try (BufferedReader br = new BufferedReader(new FileReader(registeredFile))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Id:" + idToFind)) {
                    lineas.add(linea);
                    modificar = true;
                } else if (modificar && linea.startsWith("Special attention:")) {
                    lineas.add("Special attention:"+newSpecialAttention);
                    modificar = false;
                } else {
                    lineas.add(linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(registeredFile))) {
            for (int i=0; i<lineas.size(); i++) {
                bw.write(lineas.get(i));
                if (i < lineas.size() - 1) {
                    bw.newLine(); // Agregar una nueva línea si no es la última línea
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void modifyParticipant(int id, LocalDate newBirthday) {
        String idToFind=Integer.toString(id);
           
        // Leer el archivo y realizar la modificación
        List<String> lineas = new ArrayList<>();
        boolean modificar = false;

        try (BufferedReader br = new BufferedReader(new FileReader(registeredFile))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Id:" + idToFind)) {
                    lineas.add(linea);
                    modificar = true;
                } else if (modificar && linea.startsWith("Special attention:")) {
                    lineas.add("Birth:"+newBirthday.toString());
                    modificar = false;
                } else {
                    lineas.add(linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(registeredFile))) {
            for (int i=0; i<lineas.size(); i++) {
                bw.write(lineas.get(i));
                if (i < lineas.size() - 1) {
                    bw.newLine(); // Agregar una nueva línea si no es la última línea
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public List<ClassParticipant> listParticipants() {
        List<ClassParticipant> listado = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(registeredFile))) {
            String linea;
            ClassParticipant aux = new ClassParticipant(); // Mover la declaración de ClassParticipant dentro del bucle
            while (((linea = br.readLine()) != null)) {
                if(linea.startsWith("Id:")){
                   aux.setId(Integer.parseInt(linea.substring(3).trim()));
                   linea=br.readLine();
                   aux.setNombre(linea.substring(4));
                    linea=br.readLine();
                    aux.setApellidos(linea.substring(8));
                    linea=br.readLine();                    
                    aux.set_birthday(LocalDate.of(Integer.parseInt(linea.substring(6,10)), Integer.parseInt(linea.substring(11,13)), Integer.parseInt(linea.substring(14,16))));
                    linea=br.readLine();
                    aux.set_special_attention(Boolean.parseBoolean(linea.substring(18)));
                    System.out.println(aux.toString());                    
                   listado.add(aux);        
                }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return listado;
    }
    
    public void writeParticipants(){
        List<ClassParticipant> listado = listParticipants(); 

        for (int i=0; i<listado.size(); i++) {
            System.out.println(listado.get(i).toString());
        }
    }
}