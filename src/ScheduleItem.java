import java.time.LocalDate;
import java.util.Objects;

public class ScheduleItem implements Comparable<ScheduleItem> {
    private int id;
    private String title;
    private LocalDate date;
    
    public ScheduleItem(String title, LocalDate date) {
        this.title = title;
        this.date = date;
    }
    
    public String getTitle() {
        return title;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public int getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return title + " | " + Utils.LPad(date.toString(), 5);
    }
    
    @Override
    public int compareTo(ScheduleItem other) {
        return this.date.compareTo(other.date);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleItem that = (ScheduleItem) o;
        return id == that.id;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
