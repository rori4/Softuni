package L01_WeeklyCalendar;

public class Main {
    public static void main(String[] args) {
        WeeklyCalendar wc = new WeeklyCalendar();
        wc.addEntry("MONDAY", "sport");
        wc.addEntry("SUNDAY", "sport");
        wc.addEntry("SATURDAY", "hangover");
        wc.addEntry("FRIDAY", "party");
        Iterable<WeeklyEntry> schedule = wc.getWeeklySchedule();
        for (WeeklyEntry weeklyEntry : schedule) {
            System.out.println(weeklyEntry);
        }
    }
}
