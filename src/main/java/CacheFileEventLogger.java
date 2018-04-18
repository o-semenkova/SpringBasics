import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger{
    private int cacheSize;
    List<Event> cache = new ArrayList<>();

    public CacheFileEventLogger(int cacheSize){
        this.cacheSize = cacheSize;
    }

    @Override
    public void logEvent(Event event) throws IOException {
        cache.add(event);

        if(cache.size() == cacheSize){
            writeEventsFromCache();
            cache.clear();
        }
    }

    private void writeEventsFromCache() throws IOException {
        for(Event e: cache){
            FileUtils.writeStringToFile(this.getFile(), e.toString(), "UTF-8");
        }
    }

    public void destroy() throws IOException {
        if (!cache.isEmpty()){
            writeEventsFromCache();
        }
    }
}
