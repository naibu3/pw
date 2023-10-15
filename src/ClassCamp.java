
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClassCamp extends ClassMonitor{
    private int idCamp;
    private LocalDate beginningDate;
    private LocalDate endingDate;//FORMATO =>año/mes/dia 
    private EnumLevel level_;
    private int maxAssistants;
    private List<String> activity;
    private List<Integer> monitors_;
    private int minMonitors;
    private int responsibleMonitor;
    private int responsiblespecialMonitor;

    public ClassCamp() {
        activity = new ArrayList<String>() ;
        monitors_ = new ArrayList<Integer>();
    } 
    public ClassCamp(int idCamp, LocalDate beginningDate, LocalDate endingDate, EnumLevel level, int maxAssistants) {
        this.idCamp = idCamp;
        this.beginningDate = beginningDate;
        this.endingDate = endingDate;
        this.level_ = level;
        this.maxAssistants = maxAssistants;
        activity = new ArrayList<String>();
        monitors_ = new ArrayList<Integer>();
    }

    
    public int getIdCamp() {return idCamp;}

    public void setIdCamp(int idCamp) {this.idCamp = idCamp;}

    public LocalDate getbeginningDate() {return beginningDate;}

    public void setbeginningDate(LocalDate beginningDate) {this.beginningDate = beginningDate;}

    public LocalDate getendingDate() {return endingDate;}

    public void setendingDate(LocalDate endingDate) {this.endingDate = endingDate;    }

    public EnumLevel geteducativeLevel() {return level_;}

    public void seteducativeLevel(EnumLevel educativeLevel) {this.level_ = educativeLevel;}

    public int getmaxAssistants() {return maxAssistants;}

    public void setmaxAssistants(int maxAssistants) {this.maxAssistants = maxAssistants;}

    public List<String> getClassactivity() {return activity;}

    public void setClassactivity(List<String> activity) {this.activity = activity;}

    public List<Integer> getMonitors() {return monitors_;}

    public int getminMonitors() {return minMonitors;}

    public void setminMonitors(int minMonitors) {this.minMonitors = minMonitors;}

    public int getresponsibleMonitor() {return responsibleMonitor;}

    public void setresponsibleMonitors(int responsibleMonitor) {this.responsibleMonitor = responsibleMonitor;}

    public int getresponsiblespecialMonitor() {return responsiblespecialMonitor;}

    public void setresponsiblespecialMonitor(int responsiblespecialMonitor) {this.responsiblespecialMonitor=responsiblespecialMonitor;}

    public void setMonitors(List<Integer> monitor) {this.monitors_ = monitor;}

    public void associateActivity(ClassActivity activity) {
        if (activity.getLevel_().equals(this.level_)) {
            this.activity.add(activity.getName_());
        }
    }

    public void associateMonitor(int monitor) {
        
            responsibleMonitor=monitor;
        
    }

    public void associateSpecialMonitor(int monitor) {
        
            responsiblespecialMonitor = monitor;
        
    }

    @Override
    public String toString() {
        return "Camp ID: " + idCamp +
                "\nBeginning on: " + beginningDate +
                "\nEnding on: " + endingDate +
                "\nEducative level: " + level_ +
                "\nMax number of assistants: " + maxAssistants +
                "\nActivites: " + activity +
                "\nResponsible monitor: " + responsibleMonitor +
                "\nSpecial monitor: " + responsiblespecialMonitor+"\n";
    }
}
