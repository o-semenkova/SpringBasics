import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class App {
    private Client client;
    private ConsoleEventLogger consoleEventLogger;
    private FileEventLogger fileEventLogger;

    public App() {
    }

    public App(Client client, ConsoleEventLogger consoleEventLogger, FileEventLogger fileEventLogger) {
        this.client = client;
        this.consoleEventLogger = consoleEventLogger;
        this.fileEventLogger = fileEventLogger;
    }

    private void logEvent(Event event) throws IOException {
        String message = event.getMsg().replaceAll(client.getId().toString(), client.getFullName());
        event.setMsg(message);
        consoleEventLogger.logEvent(event);
        fileEventLogger.logEvent(event);

    }

    public static void main(String args[]) throws IOException {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
        Event event = (Event)ctx.getBean("event");
        app.logEvent(event);

    }
}
