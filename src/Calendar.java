import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Calendar {
    private final List<ScheduleItem> scheduleItems;
    public static List<Calendar> calendars = new ArrayList<>();
    
    public void AddTemplate(ScheduleItem template) {
        scheduleItems.add(template);
    }
    public void RemoveTemplate(ScheduleItem template) {
        scheduleItems.remove(template);
    }
    public void RemoveTemplate(String title, LocalDate date) {
        for (ScheduleItem template : scheduleItems) {
            if (template.getTitle().equals(title) && template.getDate().equals(date)) {
                scheduleItems.remove(template);
            }
        }
    }
    
    public Calendar(List<ScheduleItem> scheduleItems) {
        this.scheduleItems = scheduleItems;
    }
    
    public void GenerateCalendar() {
        //Header
        System.out.println("Time " + "Title");
        for (ScheduleItem scheduleItem : scheduleItems) {
            scheduleItem.toString();
        }
    }
}
