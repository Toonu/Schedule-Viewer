import java.util.HashMap;
import java.util.Map;

public class ScheduleViewer {
    private static final Map<String, ArgumentHandler> arguments = new HashMap<>();

    public static void main(String[] args) {
        if (args.length < 1) {System.out.println("Usage: java ScheduleViewer <filename> -args\nArguments:\n-d | -delimiter ;"); return;}
        if (args.length % 2 == 0) {System.out.println("Invalid number of arguments"); return;}
        
        //Potential for growth of arguments via the dictionary setup.
        arguments.put("-d", ScheduleViewer::ChangeDelimiter);
        arguments.put("-delimiter", ScheduleViewer::ChangeDelimiter);
        
        //Arg 1 - File checking
        String file = args[0];
        if (!file.endsWith(".txt")) {System.out.println("Invalid file extension. Should be in txt format."); return;}
        
        //Arg 1+ - Process additional arguments
        if (args.length > 1) {
            for (int i = 1; i < args.length; i=i+2) { //2 starts after filename value, 2 to always group arg + request
                for (Map.Entry<String, ArgumentHandler> argument : arguments.entrySet()) {
                    if (argument.getKey().equals(args[i])) {
                        argument.getValue().process(args[i + 1].replaceAll("[\"']", ""));
                    }
                }
            }
        }
        
        //Arg 1 - Check file & content.
        FileLoader.loadFileAsList(file);
    }
    
    interface ArgumentHandler {
        void process(String argument);
    }
    
    private static void ChangeDelimiter(String newDelimiter) {
        FileLoader.delimiter = newDelimiter.charAt(0);
    }
}