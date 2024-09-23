public class Utils {
    public static String RPad(String text, int length) {
        if (text.length() >= length) {
            return text;
        }
        
        StringBuilder sb = new StringBuilder(text);
        while (sb.length() < length) {
            sb.append(' ');
        }
        return sb.toString();
    }
    
    public static String LPad(String text, int length) {
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
}
