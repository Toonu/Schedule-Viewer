import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleViewerTest {
    
    @org.junit.jupiter.api.Test
    public void main() throws IOException {
        String[] tooMany = {"test.txt", "-d", ";", "-test", "t", "f"};
        String[] unrecognizedArgs = {"test.txt", "-d", ";", "-test", "t"};
        String[] missingPrefix = {"test.txt", "d", ";", "-test", "t", "f"};
        String[] noFileSuffix = {"test", "-d", ";", "-test", "t", "f"};
        
        Exception tooManyException = assertThrows(UnsupportedOperationException.class, () -> {
            ScheduleViewer.main(tooMany);
        });
        assertEquals("Too many arguments provided", tooManyException.getMessage());
        
        Exception unrecognizedArgsException = assertThrows(UnsupportedOperationException.class, () -> {
            ScheduleViewer.main(unrecognizedArgs);
        });
        assertEquals("Unrecognized arguments provided", unrecognizedArgsException.getMessage());
        
        Exception missingPrefixException = assertThrows(UnsupportedOperationException.class, () -> {
            ScheduleViewer.main(missingPrefix);
        });
        assertEquals("Missing required prefix", missingPrefixException.getMessage());
        
        Exception noFileSuffixException = assertThrows(UnsupportedOperationException.class, () -> {
            ScheduleViewer.main(noFileSuffix);
        });
        assertEquals("No file suffix provided", noFileSuffixException.getMessage());
    }
}