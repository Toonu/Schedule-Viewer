import java.time.LocalDate;
import java.util.*;

/**
 * Calendar class for storing events.
 */
public class Calendar {
    private final List<ScheduleItem> scheduleItems;
    private int autoIteration = 0;

    /**
     * Constructor.
     * @param scheduleItems List<ScheduleItem> of events in the calendar.
     */
    public Calendar(List<ScheduleItem> scheduleItems) {
        this.scheduleItems = scheduleItems;
    }
    
    /**
     * Method to print the calendar into console.
     */
    public void printCalendar() {
        LocalDate date = null;
        System.out.println("\n\n\n| Generating Calendar...\n\n\n| Time      " + " | Title                                                                            |"); //Header
        for (ScheduleItem scheduleItem : scheduleItems) {
            //Printing each day
            if (!scheduleItem.getDate().equals(date)) {
                date = scheduleItem.getDate();
                System.out.println("_________________________________________________________________________________________________\n| "
                        + Utils.rPad(Utils.getFormattedDay(date), 20)
                        + "                                                                          |\n_________________________________________________________________________________________________");
            }
            System.out.println("| "
                    + scheduleItem.getDate().toString() + " | "
                    + Utils.rPad(scheduleItem.getTitle(), 80) + " |");
        }
        System.out.println("_________________________________________________________________________________________________"); //Footer
        
    }
    
    /**
     * Method for adding new ScheduleItem events to the calendar.
     * @param title String name of the event
     * @param eventDate LocalDate of the event
     */
    public void addTemplate(String title, LocalDate eventDate) {
        scheduleItems.add(new ScheduleItem(autoIteration, title, eventDate));
        autoIteration++;
        Collections.sort(scheduleItems);
    }
    
    /**
     * Method for removal of events from calendar.
     * @param template ScheduleItem event to remove.
     */
    public void removeTemplate(ScheduleItem template) {
        scheduleItems.remove(template);
    }
    
    /**
     * Method for removal of events from calendar.
     * @param title String name of the event.
     * @param date LocalDate of the event.
     */
    public void removeTemplate(String title, LocalDate date) {
        scheduleItems.removeIf(template -> template.getTitle().equals(title) && template.getDate().equals(date));
    }
    
    /**
     * Method for removal of events from calendar.
     * @param id int of the event.
     */
    public void removeTemplate(int id) {
        scheduleItems.removeIf(template -> template.getId() == id);
    }
}
