import java.time.LocalDate;
import java.util.*;

/**
 * Calendar class for storing events.
 */
public class Calendar {
    private final List<CalendarEvent> calendarEvents;
    private int autoIteration = 0;

    /**
     * Constructor.
     * @param calendarEvents List<CalendarEvent> of events in the calendar.
     */
    public Calendar(List<CalendarEvent> calendarEvents) {
        this.calendarEvents = calendarEvents;
    }
    
    /**
     * Method to print the calendar into console.
     */
    public void printCalendar() {
        LocalDate date = null;
        System.out.println("\n\n\n| Generating Calendar...\n\n\n| Time      " + " | Title                                                                            |"); //Header
        for (CalendarEvent calendarEvent : calendarEvents) {
            //Printing each day
            if (!calendarEvent.getDate().equals(date)) {
                date = calendarEvent.getDate();
                System.out.println("_________________________________________________________________________________________________\n| "
                        + Utils.rPad(Utils.getFormattedDay(date), 20)
                        + "                                                                          |\n_________________________________________________________________________________________________");
            }
            System.out.println("| "
                    + calendarEvent.getDate().toString() + " | "
                    + Utils.rPad(calendarEvent.getTitle(), 80) + " |");
        }
        System.out.println("_________________________________________________________________________________________________"); //Footer
        
    }
    
    /**
     * Method for adding new CalendarEvent events to the calendar.
     * @param title String name of the event
     * @param eventDate LocalDate of the event
     */
    public void addTemplate(String title, LocalDate eventDate) {
        calendarEvents.add(new CalendarEvent(autoIteration, title, eventDate));
        autoIteration++;
        Collections.sort(calendarEvents);
    }
    
    /**
     * Method for removal of events from calendar.
     * @param template CalendarEvent event to remove.
     */
    public void removeTemplate(CalendarEvent template) {
        calendarEvents.remove(template);
    }
    
    /**
     * Method for removal of events from calendar.
     * @param title String name of the event.
     * @param date LocalDate of the event.
     */
    public void removeTemplate(String title, LocalDate date) {
        calendarEvents.removeIf(template -> template.getTitle().equals(title) && template.getDate().equals(date));
    }
    
    /**
     * Method for removal of events from calendar.
     * @param id int of the event.
     */
    public void removeTemplate(int id) {
        calendarEvents.removeIf(template -> template.getId() == id);
    }
}
