

public class ClassActivity {
    private String name_;   //como hacer unico?
    private int level_;
    private String timetable_;
    private int max_;
    private int monitors_n_;
    private String[] monitors_;

    public ClassActivity(){}
    public ClassActivity(String name, int level, String timetable, int max, int monitors_n){
        name_=name;
        level_=level;
        timetable_=timetable;
        max_=max;
        monitors_n_=monitors_n;
        monitors_=null;
    }
//getters
    public String getName_() {
        return name_;
    }
    public int getLevel_() {
        return level_;
    }
    public String getTimetable_() {
        return timetable_;
    }
    public int getMax_() {
        return max_;
    }
    public int getMonitors_n_() {
        return monitors_n_;
    }
    public String[] getMonitors_() {
        return monitors_;
    }
//setters
    public void setName_(String name_) {
        this.name_ = name_;
    }
    public void setLevel_(int level_) {
        this.level_ = level_;
    }
    public void setTimetable_(String timetable_) {
        this.timetable_ = timetable_;
    }
    public void setMax_(int max_) {
        this.max_ = max_;
    }
    public void setMonitors_n_(int monitors_n_) {
        this.monitors_n_ = monitors_n_;
    }
    public void setMonitors_(String[] monitors_) {
        this.monitors_ = monitors_;
    }

    public String toString(){
        return "Name:"+getName_()+"\nLevel:"+getLevel_()+"\nTimetable:"+getTimetable_()+"\nMax:"+getMax_()+"\nNumero de monitores:"+getMonitors_n_()+"\nMonitores:"+getMonitors_()+"\n";
    }
}
