import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Template for Schedule calendar events.
 */
public class EventTemplate {
    private final String title;
    private final LocalDate date_start;
    private final LocalDate date_end;
    private final int repetitiveness;
    private final RepetitivenessType repetitivenessType;
    
    /**
     * Constructor.
     */
    public EventTemplate(String title, LocalDate date_start, LocalDate date_end, int repetitiveness, RepetitivenessType repetitivenessType) throws InvalidParameterException {
        this.title = title;
        this.date_start = date_start;
        this.date_end = date_end;
        
        if (date_end.isBefore(date_start)) {throw new InvalidParameterException("End date is before the start date!");}
        if (repetitiveness < 1) {throw new InvalidParameterException("Repetitiveness must be greater than zero!");}
        this.repetitiveness = repetitiveness;
        this.repetitivenessType = repetitivenessType;
    }
    
    /**
     * Creates CalendarEvent events for a calendar.
     * @param calendar Calendar.
     */
    public void exportSchedules(Calendar calendar) {
        switch (repetitivenessType) {
            case DAY -> {
                for (LocalDate tempDate = date_start; tempDate.isBefore(date_end.plusDays(1)); tempDate = tempDate.plusDays(repetitiveness)) {
                    calendar.addTemplate(title, tempDate);
                }
            }
            case WEEK -> {
                for (LocalDate tempDate = date_start; tempDate.isBefore(date_end.plusDays(1)); tempDate = tempDate.plusWeeks(repetitiveness)) {
                    calendar.addTemplate(title, tempDate);
                }
            }
            case MONTH -> {
                for (LocalDate tempDate = date_start; tempDate.isBefore(date_end.plusDays(1)); tempDate = tempDate.plusMonths(repetitiveness)) {
                    calendar.addTemplate(title, tempDate);
                }
            }
            case YEAR -> {
                for (LocalDate tempDate = date_start; tempDate.isBefore(date_end.plusDays(1)); tempDate = tempDate.plusYears(repetitiveness)) {
                    calendar.addTemplate(title, tempDate);
                }
            }
        }
    }
    
    //Getters & Logic
    
    public String getTitle() {
        return title;
    }
    
    public LocalDate getDate_start() {
        return date_start;
    }
    
    public LocalDate getDate_end() {
        return date_end;
    }
    
    //Potential private key methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventTemplate template = (EventTemplate) o;
        return Objects.equals(title, template.title) && Objects.equals(date_start, template.date_start) && Objects.equals(date_end, template.date_end);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(title, date_start, date_end);
    }
}
