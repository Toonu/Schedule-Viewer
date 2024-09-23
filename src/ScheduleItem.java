import java.time.LocalDate;
import java.util.Objects;

public class ScheduleItem implements Comparable<ScheduleItem> {
    private final int id;
    private String title;
    private LocalDate date;
    
    public ScheduleItem(int id, String title, LocalDate date) {
        this.id = id;
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
    
    //Setters in case some person would want to shift or rebook schedule event.
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    @Override
    public String toString() {
        return title + " | " + Utils.lPad(date.toString(), 5);
    }
    
    @Override
    public int compareTo(ScheduleItem other) {
        int dateComparison = this.date.compareTo(other.date);
        if (dateComparison != 0) { return dateComparison; }
        return this.title.compareTo(other.title);
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
