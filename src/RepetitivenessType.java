public enum RepetitivenessType {
    DAY, WEEK, MONTH, YEAR;
    
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
