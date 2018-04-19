
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class App {
    private ConsoleEventLogger consoleEventLogger;
    private CombinedEventLogger combinedEventLogger;
    private Client client;
    private CacheFileEventLogger cacheFileEventLogger;

    public App() {
    }

    public App(Client client, CacheFileEventLogger cacheFileEventLogger,
               CombinedEventLogger combinedEventLogger,
               ConsoleEventLogger consoleEventLogger) {
        this.client = client;
        this.cacheFileEventLogger = cacheFileEventLogger;
        this.combinedEventLogger = combinedEventLogger;
        this.consoleEventLogger = consoleEventLogger;
    }

    private void logEvent(Event event, EventType eventType) throws IOException {
        String message = event.getMsg().replaceAll(client.getId().toString(), client.getFullName());
        event.setMsg(message);

        if(eventType == null){
            cacheFileEventLogger.logEvent(event);
        }else if(eventType.equals(EventType.INFO)){
            consoleEventLogger.logEvent(event);
        }else if(eventType.equals(EventType.ERROR)){
            combinedEventLogger.logEvent(event);
        }
    }

    public static void main(String args[]) throws IOException {

        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
        Event event_1 = (Event)ctx.getBean("event");
        app.logEvent(event_1, EventType.ERROR);
        Event event_2 = (Event)ctx.getBean("event");
        app.logEvent(event_2, EventType.INFO);
        Event event_3 = (Event)ctx.getBean("event");
        app.logEvent(event_3, null);
        ctx.close();

    }
}
