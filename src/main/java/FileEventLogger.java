import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {
    private String filename = "/home/osem/IdeaProjects/SpringBasics/my_logs.txt";

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void logEvent(Event event) throws IOException {
        FileUtils.writeStringToFile(new File(filename), event.toString());
    }
}
