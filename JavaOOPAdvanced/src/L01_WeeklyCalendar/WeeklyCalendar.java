package L01_WeeklyCalendar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WeeklyCalendar {
    private List<WeeklyEntry> entries;

    public WeeklyCalendar() {
        this.entries = new ArrayList<>();
    }

    public void addEntry (String weekday, String notes){
        this.entries.add(new WeeklyEntry(weekday,notes));
    }

    public Iterable<WeeklyEntry> getWeeklySchedule(){
       this.entries.sort(Comparator.naturalOrder());
       return this.entries;
    }
}
