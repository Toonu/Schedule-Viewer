import java.io.*;
import java.security.InvalidParameterException;
import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Class for loading input file to the memory and validating its input.
 */
public class FileLoader {
    private static int i = 0;
    public static char delimiter = '|';
    
    /**
     * Loads input file into calendar list.
     * @param fileName String filename to input.
     */
    public static void loadFileByRow(String fileName, Calendar calendar) throws Exception {
        InputStream inputStream = FileLoader.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new FileNotFoundException("Could not load file " + fileName + ". File not found");
        }
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                i++;
                parseScheduleTemplate(line, calendar);
            }
        } catch (IOException e) {
            throw new FileNotFoundException("There was problem loading " + fileName + " file. Make sure it is in the root folder and in correct format. \n" + e.getMessage());
        } catch (Exception e) {
            if (ScheduleViewer.doesAbortOnExc) { //When onAbort = true, aborts on any line being invalid.
                throw e;
            }
            System.out.println(e.getMessage());
        }
        
        calendar.printCalendar();
    }
    
    /**
     * Parse one line of the file if valid, sends it to the calendar.
     * @param line String input. 5 arguments long.
     */
    private static void parseScheduleTemplate(String line, Calendar calendar) throws RuntimeException {
        //Line check
        String[] split = line.split("[/"+delimiter+"]");
        if (split.length != 5 || split[0].startsWith("#")) {System.out.println("Warning: Line " + Utils.rPad(String.valueOf(i), 4) + " is invalid: " + line); return; }
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
            template.exportSchedules(calendar);
            //ToDo -args for cancelling loading on any error, instead of ignoring errors
        } catch (InvalidParameterException e) {
            System.out.println("Warning: Line " + Utils.rPad(String.valueOf(i), 4) + " is invalid due to wrongly formatted time. " + e.getMessage());
        } catch (DateTimeException e) {
            System.out.println("Warning: Line " + Utils.rPad(String.valueOf(i), 4) + " is invalid due to wrongly formatted time. Proper usage is [yyyy-mm-dd]: " + line);
        } catch (NumberFormatException e) {
            System.out.println("Warning: Line " + Utils.rPad(String.valueOf(i), 4) + " is invalid due to wrongly inputted number of repetition: " + line);
        } catch (IllegalArgumentException e) {
            System.out.println("Warning: Line " + Utils.rPad(String.valueOf(i), 4) + " is invalid due to wrong repetition type. Proper usage is either of [D W M Y]: " + line);
        }
    }
}