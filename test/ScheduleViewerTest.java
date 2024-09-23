import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScheduleViewerTest {
    //File tests
    @Test
    public void fileNoSuffix() {
        String[] args = {"test", "-d", ";", "-test", "t"};
        assertEquals("Invalid file extension. Should be in txt format.", getSysOutput(args).trim());
    }
    
    @Test
    public void fileEmpty() {
        String[] args = {"empty.txt", "-d", "|"};
        assertEquals("| Generating Calendar...\n" +
                "\n" +
                "\n" +
                "| Time       | Title                                                                            |\n" +
                "_________________________________________________________________________________________________", getSysOutput(args).trim().replace("\r\n", "\n"));
    }
    
    @Test
    public void fileErrors() {
        String[] args = {"fileErrors.txt", "-d", "|"};
        assertEquals("Warning: Line 18   is commented out: # Název činnosti | Počáteční datum | Perioda | Jednotka | Koncové datum\n" +
                "Warning: Line 19   is invalid: ||||\n" +
                "Warning: Line 20   is invalid due to wrongly formatted time. Proper usage is [yyyy-mm-dd]: x|x|1|Y|2025-01-01\n" +
                "Warning: Line 21   is invalid due to wrongly inputted number of repetition: x|2023-01-01|x|Y|2025-01-01\n" +
                "Warning: Line 22   is invalid due to wrong repetition type. Proper usage is either of [D W M Y]: x|2023-01-01|1|f|2025-01-01\n" +
                "Warning: Line 23   is invalid due to wrongly formatted time. Proper usage is [yyyy-mm-dd]: x|2023-01-01|1|Y|x\n" +
                "Warning: Line 24   is invalid due to wrongly formatted time. Proper usage is [yyyy-mm-dd]: x|x|x|x|x\n" +
                "Warning: Line 25   is invalid due to wrongly formatted time. End date is before the start date!\n" +
                "\n" +
                "\n" +
                "\n" +
                "| Generating Calendar...\n" +
                "\n" +
                "\n" +
                "| Time       | Title                                                                            |\n" +
                "_________________________________________________________________________________________________", getSysOutput(args).trim().replace("\r\n", "\n"));
    }
    
    //Args tests
    @Test
    public void argsMissingPrefix() {
        String[] args = {"test.txt", "d", ";", "-test", "t"};
        assertThrows(FileNotFoundException.class, () -> ScheduleViewer.main(args));
    }
    
    @Test
    public void argsTooMany() {
        String[] args = {"test.txt", "-d", ";", "-test", "t", "f"};
        assertEquals("Invalid number of arguments", getSysOutput(args).trim());
    }
    
    @Test
    public void argsTooFew() {
        String[] args = {"test.txt", "-d"};
        assertEquals("Invalid number of arguments", getSysOutput(args).trim());
    }
    
    @Test
    public void argsUnrecognized() throws Exception {
        String[] args = {"program_udrzby.txt", "-d", ";", "-test", "t"};
        ScheduleViewer.main(args);
    }
    
    @Test
    public void argsAbort() {
        String[] args = {"fileErrors.txt", "-d", "|", "-a", "true"};
        RuntimeException e = assertThrows(RuntimeException.class, () -> ScheduleViewer.main(args));
        
        assertEquals("Warning: Line 3    is invalid due to wrongly formatted time. Proper usage is [yyyy-mm-dd]: x|x|1|Y|2025-01-01", e.getMessage());
    }
    
    private String getSysOutput(String[] args) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        
        PrintStream originalOut = System.out; //Redirect
        System.setOut(printStream);
        
        try {
            ScheduleViewer.main(args); //Test
        } catch (Exception e) {
            //Ignore as we are testing output.
        }
       
        
        System.setOut(originalOut); //Return
        return outputStream.toString(); //Capture
    }
}

