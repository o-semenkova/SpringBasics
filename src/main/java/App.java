import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private Client client;
    private ConsoleEventLogger consoleEventLogger;

    public App() {
    }

    public App(Client client, ConsoleEventLogger consoleEventLogger) {
        this.client = client;
        this.consoleEventLogger = consoleEventLogger;
    }

    public void logEvent(String msg){
        String message = msg.replaceAll(client.getId().toString(), client.getFullName());
        consoleEventLogger.logEvent(message);
    }

    public static void main(String args[]){

        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
        app.logEvent("Some event for 1");
        app.logEvent("Some event for 2");
    }
}
