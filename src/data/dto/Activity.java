package data.dto;

import java.util.List;

/** Represents an activity.
 * @version 1.0
*/
public class Activity {

    private String name_;   //como hacer unico?
    private EnumLevel level_;
    private String timetable_;
    private int max_;
    private int monitors_n_;
    private List<Integer> monitors_;

    /** Description.
	*/
    public Activity(){}

    /** Description.
	*/
    public Activity(String name, EnumLevel level, String timetable, int max, int monitors_n){
        name_=name;
        level_=level;
        timetable_=timetable;
        max_=max;
        monitors_n_=monitors_n;
        monitors_=null;
    }
//getters
    /** Gets the activity name.
     * @return activity name
    */
    public String getName_() {
        return name_;
    }

    /** Gets the activity level.
     * @return activity level
    */
    public EnumLevel getLevel_() {
        return level_;
    }

    /** Gets the activity time table.
     * @return activity time table
    */
    public String getTimetable_() {
        return timetable_;
    }

    /** Gets the activity's max. number of participants.
     * @return activity's max. number of participants
    */
    public int getMax_() {
        return max_;
    }

    /** Gets the activity's number of monitors.
     * @return activity's number of monitors
    */
    public int getMonitors_n_() {
        return monitors_n_;
    }

    /** Gets the activity monitors.
     * @return activity monitors (array)
    */
    public List<Integer> getMonitors_() {
        return monitors_;
    }


//setters

    /** Sets the activity name.
     * @param name activity name
    */
    public void setName_(String name_) {
        this.name_ = name_;
    }

    /** Sets the activity level.
     * @param level activity level
    */
    public void setLevel_(EnumLevel level_) {
        this.level_ = level_;
    }

    /** Sets the activity timetable.
     * @param timetable activity timetable
    */
    public void setTimetable_(String timetable_) {
        this.timetable_ = timetable_;
    }

    /** Sets the activity max. number of participants
     * @param max activity max number of participants
    */
    public void setMax_(int max_) {
        this.max_ = max_;
    }

    /** Sets the activity number of monitors.
     * @param monitors_n activity number of monitors
    */
    public void setMonitors_n_(int monitors_n_) {
        this.monitors_n_ = monitors_n_;
    }

    /** Sets the activity monitors.
     * @param monitors activity monitors array
    */
    public void setMonitors_(List<Integer> monitors_) {
        this.monitors_ = monitors_;
    }


    /** Dumps all class info into a string
     * @return activity info
    */
    public String toString(){
        String string = "Name:"+getName_()+"\nLevel:"+getLevel_()+"\nTimetable:"+getTimetable_()+"\nMax:"+getMax_()+"\nNumero de monitores:"+getMonitors_n_()+"\nMonitores:"+getMonitors_();
        
        string += "\n";
        return string;
    }

    public static void main(String[] args) {
        
    }
}
