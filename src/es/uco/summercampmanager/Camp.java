package es.uco.summercampmanager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Camp extends Monitor{
    private int idCamp;
    private Date beginningDate;
    private Date endingDate;
    private String educativeLevel;
    private int maxAssistants;
    private List<activity> activity;
    private Monitor Monitor;
    private Monitor specialMonitor;

    
    public Camp() {
        activity = new ArrayList<>();
    } 
    public Camp(int idCamp, Date beginningDate, Date endingDate, String educativeLevel, int maxAssistants) {
        this.idCamp = idCamp;
        this.beginningDate = beginningDate;
        this.endingDate = endingDate;
        this.educativeLevel = educativeLevel;
        this.maxAssistants = maxAssistants;
        activity = new ArrayList<>();
    }

    
    public int getIdCamp() {return idCamp;
    }

    public void setIdCamp(int idCamp) {this.idCamp = idCamp;}

    public Date getbeginningDate() {return beginningDate;}

    public void setbeginningDate(Date beginningDate) {this.beginningDate = beginningDate;}

    public Date getendingDate() {return endingDate;}

    public void setendingDate(Date endingDate) {this.endingDate = endingDate;    }

    public String geteducativeLevel() {return educativeLevel; }

    public void seteducativeLevel(String educativeLevel) {this.educativeLevel = educativeLevel;}

    public int getmaxAssistants() {return maxAssistants;}

    public void setmaxAssistants(int maxAssistants) {this.maxAssistants = maxAssistants;}

    public List<activity> getactivityes() {return activity;}

    public void setactivityes(List<activity> activity) {this.activity = activity;}

    public Monitor getMonitor() {return Monitor;}

    public void setMonitor(Monitor Monitor) {this.Monitor = Monitor;}

    public void associateActivity(Activity activity) {
        if (activity.geteducativeLevel().equals(this.educativeLevel)) {
            activity.add(activity);
        }
    }

    public void associateSpecial(Monitor monitor) {
        if (activity.contains(monitor.getactivity())) {
            Monitor = monitor;
        }
    }

    public void associateSpecialMonitor(Monitor monitor) {
        if (!activity.contains(monitor.getactivity())) {
            specialMonitor = monitor;
        }
    }

    @Override
    public String toString() {
        return "Camp ID: " + idCamp +
                "\nBeginning on: " + beginningDate +
                "\nEnding on: " + endingDate +
                "\nEducative level: " + educativeLevel +
                "\nMax number of assistants: " + maxAssistants +
                "\nActivites: " + activity +
                "\nResponsible monitor: " + Monitor +
                "\nSpecial monitor: " + specialMonitor;
    }
}
