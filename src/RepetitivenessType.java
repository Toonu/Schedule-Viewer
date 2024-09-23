/**
 * Enum for types of repetition. Days, Weeks, Months, Years.
 */
public enum RepetitivenessType {
    DAY, WEEK, MONTH, YEAR;
    
    /**
     * String to Enum convertor.
     * @param typeString String input - d w m y
     * @return Enum Days, Weeks, Months, Years.
     * @throws IllegalArgumentException In case of unrecognized input.
     */
    public static RepetitivenessType fromString(String typeString) throws IllegalArgumentException {
        return switch (typeString.toLowerCase()) {
            case "d" -> DAY;
            case "w" -> WEEK;
            case "m" -> MONTH;
            case "y" -> YEAR;
            default -> throw new IllegalArgumentException("Invalid color: " + typeString);
        };
    }
}
