import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Utility support classes
 */
public class Utils {
    
    /**
     * Pads text from right side.
     * @param text String text to pad.
     * @param length int length of the final text.
     * @return String padded text.
     */
    public static String rPad(String text, int length) {
        if (text.length() >= length) {
            return text;
        }
        
        StringBuilder sb = new StringBuilder(text);
        while (sb.length() < length) {
            sb.append(' ');
        }
        return sb.toString();
    }
    
    
    /**
     * Pads text from left side.
     * @param text String text to pad.
     * @param length int length of the final text.
     * @return String padded text.
     */
    public static String lPad(String text, int length) {
        if (text.length() >= length) {
            return text;
        }
        
        StringBuilder sb = new StringBuilder(text);
        while (sb.length() < length - text.length()) {
            sb.append(' ');
        }
        // Append the original text
        sb.append(text);
        return sb.toString();
    }
    
    /**
     * Formats number into prettier format. yyyy-mm-dd > dth month year
     * @param date LocalDate input.
     * @return String formatted date.
     */
    public static String getFormattedDay(LocalDate date) {
        //Suffix
        int day = date.getDayOfMonth();
        String suffix;
        if (day >= 11 && day <= 13) {
            suffix = "th";
        } else {
            suffix = switch (day % 10) {
                case 1 -> "st";
                case 2 -> "nd";
                case 3 -> "rd";
                default -> "th";
            };
        }
        //Completion
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.UK);
        return day + suffix + " " + date.format(formatter);
    }
}
