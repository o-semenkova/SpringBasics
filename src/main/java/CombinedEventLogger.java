import java.io.IOException;
import java.util.Map;

public class CombinedEventLogger implements EventLogger {

    private Map<EventType,EventLogger> loggers;

    public CombinedEventLogger(Map<EventType,EventLogger> loggers){
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        loggers.forEach((k,v) -> {
            try {
                v.logEvent(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
