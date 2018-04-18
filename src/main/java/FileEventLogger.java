import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {

    private File file;
    private String filename = "/home/osemenkova/sources/comyetspringcore/my_logs.txt";

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void logEvent(Event event) throws IOException {
        FileUtils.writeStringToFile(new File(filename), event.toString(), "UTF-8");
    }
    public void init() throws IOException{
        File file = new File(filename);
        if(!file.canWrite()){
            throw new IOException("Permission for writing to this file " + filename + " are absent");
        }
        this.file = file;
    }
}
