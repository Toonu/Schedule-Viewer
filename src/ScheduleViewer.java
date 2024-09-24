import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScheduleViewer {
    private static final Map<String, ArgumentHandler> arguments = new HashMap<>();
    public static boolean doesAbortOnExc = false;
    
    /**
     * Main starting point.
     * @param args String[] args.
     */
    public static void main(String[] args) {
        if (args.length < 1) {System.out.println("Usage: java ScheduleViewer <filename> -args\nArguments:\n-d | -delimiter ;"); return;}
        if (args.length % 2 == 0) {System.out.println("Invalid number of arguments"); return;}
        
        //Potential for growth of arguments via the dictionary setup.
        arguments.put("-d", ScheduleViewer::changeDelimiter);
        arguments.put("-delimiter", ScheduleViewer::changeDelimiter);
        arguments.put("-a", ScheduleViewer::setDoesAbortOnExc);
        arguments.put("-abort", ScheduleViewer::setDoesAbortOnExc);
        
        String file = args[0]; //Arg 1 - File checking
        if (!file.endsWith(".txt")) {System.out.println("Invalid file extension. Should be in txt format."); return;}
        
        if (args.length > 1) { //Arg 1+ - Process additional arguments
            for (int i = 1; i < args.length; i=i+2) { //2 starts after filename value, 2 to always group arg + request
                for (Map.Entry<String, ArgumentHandler> argument : arguments.entrySet()) {
                    if (argument.getKey().equals(args[i])) {
                        argument.getValue().process(args[i + 1].replaceAll("[\"']", ""));
                    }
                }
            }
        }
        
        //Arg 1 - Check file & content. Error handling.
        try {
            FileLoader.loadFileByRow(file, new Calendar(new ArrayList<>()));
        } catch (Exception e) {
            System.out.println("Aborting operation: " + e.getMessage());
        }
    }
    
    interface ArgumentHandler {
        void process(String argument);
    }
    
    private static void changeDelimiter(String newDelimiter) {
        FileLoader.delimiter = newDelimiter.charAt(0);
    }
    
    private static void setDoesAbortOnExc(String condition) {
        if (condition.equalsIgnoreCase("true")) { doesAbortOnExc = true; } //ignoring false as its default state
    }
}