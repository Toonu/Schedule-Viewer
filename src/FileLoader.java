import java.io.*;
import java.security.InvalidParameterException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

public class FileLoader {
    private static int i = 0;
    private static Calendar calendar = new Calendar(new ArrayList<>());
    public static char delimiter = '|';

    public static void loadFileAsList(String fileName) {
        InputStream inputStream = FileLoader.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            System.out.println("Could not load file " + fileName + ". File not found");
            return;
        }

        Calendar.calendars.add(calendar);
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                i++;
                ParseScheduleTemplate(line);
            }
        } catch (IOException e) {
            System.out.println("Could not load file " + fileName + ". Make sure it is in the root folder. " + e.getMessage());
        }
    }
    
    private static void ParseScheduleTemplate(String line) {
        //Line check
        String[] split = line.split("[/"+delimiter+"]");
        if (split.length != 5 || split[0].startsWith("#")) {System.out.println("Warning: Line " + Utils.RPad(String.valueOf(i), 4) + " is invalid: " + line); return; }
        //Assuming # is meant as comment in the file and would not be used for job names.
        
        String title = split[0];
        String stringDateStart = split[1].trim();
        String stringDateEnd = split[4].trim();
        String stringIteration = split[2].trim();
        String stringType = split[3].trim();

        LocalDate dateStart;
        LocalDate dateEnd;
        int iteration;
        RepetitivenessType repetitivenessType;

        //Parsing & object creation. Ignoring faulty ones but informing user.
        try {
            dateStart = LocalDate.parse(stringDateStart);
            dateEnd = LocalDate.parse(stringDateEnd);
            iteration = Integer.parseInt(stringIteration);
            repetitivenessType = RepetitivenessType.fromString(stringType);
            
            ScheduleTemplate template = new ScheduleTemplate(title, dateStart, dateEnd, iteration, repetitivenessType);
            template.ExportSchedules(calendar);
            
        } catch (InvalidParameterException e) {
            System.out.println("Warning: Line " + Utils.RPad(String.valueOf(i), 4) + " is invalid due to wrongly formatted time. " + e.getMessage());
        } catch (DateTimeException e) {
            System.out.println("Warning: Line " + Utils.RPad(String.valueOf(i), 4) + " is invalid due to wrongly formatted time. Proper usage is [yyyy-mm-dd]: " + line);
        } catch (NumberFormatException e) {
            System.out.println("Warning: Line " + Utils.RPad(String.valueOf(i), 4) + " is invalid due to wrongly inputted number of repetition: " + line);
        } catch (IllegalArgumentException e) {
            System.out.println("Warning: Line " + Utils.RPad(String.valueOf(i), 4) + " is invalid due to wrong repetition type. Proper usage is either of [D W M Y]: " + line);
        }
    }
}