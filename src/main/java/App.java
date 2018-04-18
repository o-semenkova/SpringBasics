
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class App {
    private Client client;
    private CacheFileEventLogger cacheFileEventLogger;

    public App() {
    }

    public App(Client client, CacheFileEventLogger cacheFileEventLogger) {
        this.client = client;
        this.cacheFileEventLogger = cacheFileEventLogger;
    }

    private void logEvent(Event event) throws IOException {
        String message = event.getMsg().replaceAll(client.getId().toString(), client.getFullName());
        event.setMsg(message);
        cacheFileEventLogger.logEvent(event);
    }

    public static void main(String args[]) throws IOException {

        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
        Event event_1 = (Event)ctx.getBean("event");
        app.logEvent(event_1);
        Event event_2 = (Event)ctx.getBean("event");
        app.logEvent(event_2);
        ctx.close();

    }
}
