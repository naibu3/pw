
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClassCamp extends ClassMonitor{
    private int idCamp;
    private LocalDate beginningDate;
    private LocalDate endingDate;//FORMAto =>año/mes/dia 
    private EnumLevel level_;
    private int maxAssistants;
    private List<ClassActivity> activity;
    private ClassMonitor[] monitors_;
    private int minMonitors;
    private ClassMonitor responsibleMonitor;
    private ClassMonitor responsiblespecialMonitor;

    public ClassCamp() {
        activity = new ArrayList<ClassActivity>() ;
    } 
    public ClassCamp(int idCamp, LocalDate beginningDate, LocalDate endingDate, EnumLevel level, int maxAssistants) {
        this.idCamp = idCamp;
        this.beginningDate = beginningDate;
        this.endingDate = endingDate;
        this.level_ = level;
        this.maxAssistants = maxAssistants;
        activity = new ArrayList<ClassActivity>();
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

    public List<ClassActivity> getClassactivity() {return activity;}

    public void setClassactivity(List<ClassActivity> activity) {this.activity = activity;}

    public ClassMonitor[] getMonitors() {return monitors_;}

    public int getminMonitors() {return minMonitors;}

    public void setminMonitors(int minMonitors) {this.minMonitors = minMonitors;}

    public ClassMonitor getresponsibleMonitor() {return responsibleMonitor;}

    public void setresponsibleMonitors(ClassMonitor responsibleMonitor) {this.responsibleMonitor = responsibleMonitor;}

    public ClassMonitor getresponsiblespecialMonitor() {return responsiblespecialMonitor;}

    public void setresponsiblespecialMonitor(ClassMonitor responsiblespecialMonitor) {this.responsiblespecialMonitor=responsiblespecialMonitor;}

    public void setMonitor(ClassMonitor[] monitor) {this.monitors_ = monitor;}

    public void associateActivity(ClassActivity activity) {
        if (activity.getLevel_().equals(this.level_)) {
            this.activity.add(activity);
        }
    }

    public void associateMonitor(ClassMonitor monitor) {
        
            responsibleMonitor=monitor;
        
    }

    public void associateSpecialMonitor(ClassMonitor monitor) {
        if (monitor.getSpecialNeedsEducator()) {
            responsiblespecialMonitor = monitor;
        }
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
