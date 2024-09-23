import jdk.jshell.spi.ExecutionControl;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class ScheduleTemplate {
    private String title;
    private LocalDate date_start;
    private LocalDate date_end;
    private int repetitiveness;
    private RepetitivenessType repetitivenessType;
    
    public ScheduleTemplate(String title, LocalDate date_start, LocalDate date_end, int repetitiveness, RepetitivenessType repetitivenessType) throws InvalidParameterException {
        this.title = title;
        this.date_start = date_start;
        this.date_end = date_end;
        
        if (date_end.isBefore(date_start)) {throw new InvalidParameterException("End date is before the start date!");}
        
        this.repetitiveness = repetitiveness;
        this.repetitivenessType = repetitivenessType;
    }
    
    public void ExportSchedules(Calendar calendar) {
        //ToDo logic
        
        ScheduleItem item = new ScheduleItem(title, date_start);
        
        calendar.AddTemplate(item);
    }
    
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
        ScheduleTemplate template = (ScheduleTemplate) o;
        return Objects.equals(title, template.title) && Objects.equals(date_start, template.date_start) && Objects.equals(date_end, template.date_end);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(title, date_start, date_end);
    }
}
