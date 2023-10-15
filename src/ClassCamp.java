
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** Represents a camp.
 * @version 1.0
*/
public class ClassCamp extends ClassMonitor{
    private int idCamp;
    private LocalDate beginningDate;
    private LocalDate endingDate;//FORMATO =>año/mes/dia 
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

    /** Gets the camp id.
     * @return camp id
    */
    public int getIdCamp() {return idCamp;}

    /** Sets the camp id.
     * @param idCamp camp id
    */
    public void setIdCamp(int idCamp) {this.idCamp = idCamp;}

    /** Gets the beginning date.
     * @return beginning date
    */
    public LocalDate getbeginningDate() {return beginningDate;}

    /** Sets the beginning date.
     * @param beginningDate beginning date
    */
    public void setbeginningDate(LocalDate beginningDate) {this.beginningDate = beginningDate;}

    /** Gets the ending date.
     * @return ending date
    */
    public LocalDate getendingDate() {return endingDate;}

    /** Sets the ending date.
     * @param endingDate ending date
    */
    public void setendingDate(LocalDate endingDate) {this.endingDate = endingDate;    }

    /** Gets the educative level.
     * @return educative level
    */
    public EnumLevel geteducativeLevel() {return level_;}

    /** Sets the educative level.
     * @param educationLevel educative level
    */
    public void seteducativeLevel(EnumLevel educativeLevel) {this.level_ = educativeLevel;}

    /** Gets the max. number of assistants.
     * @return max. number of assistants
    */
    public int getmaxAssistants() {return maxAssistants;}

    /** Sets the max. number of assistants.
     * @param maxAssistants max. number of assistants
    */
    public void setmaxAssistants(int maxAssistants) {this.maxAssistants = maxAssistants;}

    /** Gets the activity list.
     * @return activity list
    */
    public List<ClassActivity> getClassactivity() {return activity;}

    /** Sets the activity list.
     * @param activity activity list
    */
    public void setClassactivity(List<ClassActivity> activity) {this.activity = activity;}

    /** Gets the camp monitors.
     * @return camp monitors
    */
    public ClassMonitor[] getMonitors() {return monitors_;}

    /** Gets the min. number of monitors.
     * @return min. number of monitors
    */
    public int getminMonitors() {return minMonitors;}

    /** Sets the min. number of monitors.
     * @param minMonitors min. number of monitors
    */
    public void setminMonitors(int minMonitors) {this.minMonitors = minMonitors;}

    /** Gets the responsible monitor.
     * @return responsible monitor
    */
    public ClassMonitor getresponsibleMonitor() {return responsibleMonitor;}

    /** Sets the responsible monitor.
     * @param responsibleMonitor responsible monitor
    */
    public void setresponsibleMonitors(ClassMonitor responsibleMonitor) {this.responsibleMonitor = responsibleMonitor;}

    /** Gets the responsible special monitor.
     * @return responsible special monitor
    */
    public ClassMonitor getresponsiblespecialMonitor() {return responsiblespecialMonitor;}

    /** Sets the responsiblespecial monitor.
     * @param responsiblespecialMonitor responsiblespecial monitor
    */
    public void setresponsiblespecialMonitor(ClassMonitor responsiblespecialMonitor) {this.responsiblespecialMonitor=responsiblespecialMonitor;}

    /** Sets the monitor list.
     * @param monitor monitor list
    */
    public void setMonitor(ClassMonitor[] monitor) {this.monitors_ = monitor;}

    /** Adds an activity.
     * @param activity activity
    */
    public void associateActivity(ClassActivity activity) {
        if (activity.getLevel_().equals(this.level_)) {
            this.activity.add(activity);
        }
    }

    /** Adds an monitor.
     * @param monitor monitor
    */
    public void associateMonitor(ClassMonitor monitor) {
        
            responsibleMonitor=monitor;
        
    }

    /** Adds an special monitor.
     * @param monitor special monitor
    */
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
