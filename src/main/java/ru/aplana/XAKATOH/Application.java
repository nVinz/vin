package ru.aplana.XAKATOH;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Properties;

@SpringBootApplication()
public class Application {
    private static ConfigurableApplicationContext context = null;

    public static void exit() {
        new Thread(() -> {
            int exitCode = SpringApplication.exit(context, () -> 0);
            delay(1000);
            System.exit(exitCode);
        }).start();
    }

    public static void main(String[] args) {
        AppProperties.getInstance().addApplicationArgs(args);

        SpringApplication application = new SpringApplication(Application.class);

        Properties properties = new Properties();
        properties.put("server.port", AppProperties.getInstance().getPort());
        properties.put("server.address", AppProperties.getInstance().getHost());
        application.setDefaultProperties(properties);


        context = application.run(args);
    }


    public static void delay(int timeoutMs) {
        try {
            Thread.sleep(timeoutMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
