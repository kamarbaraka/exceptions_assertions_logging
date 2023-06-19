package logging;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * a program to demonstrate advanced logging techniques in java
 * @author kamar baraka
 * @since 18/06/2023*/
public class LoggingTest {

    public static void main(String[] args) throws IOException{


        var logConfigFile = "src/main/resources/logging.properties";
        System.setProperty("java.util.logging.config.file", logConfigFile);
        LogManager.getLogManager().readConfiguration();
        var logHandler = new FileHandler("%h/Desktop/JAVA/logs/LoggingTest%u", true);
        var logger = Logger.getLogger("logging.LoggingTest");
        logger.addHandler(logHandler);
        logger.setUseParentHandlers(false);

        logger.entering("logging.LoggingTest", "main");

        JFrame frame = new JFrame("LoggingTest");
        var label = new JLabel("this is a test run", JLabel.CENTER);
        frame.add(label);

        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        logger.exiting("logging.LoggingTest", "main");
    }
}

class Loggerr{
    private Logger logger;
    private FileHandler logHandler;
    private String logConfigFile;

    public Loggerr(){
        try {
            this.logConfigFile = "src/main/resources/logging.properties";
            System.setProperty("java.util.logging.config.file", logConfigFile);
            LogManager.getLogManager().readConfiguration();

            this.logHandler = new FileHandler("%h/Desktop/JAVA/logs/LoggingTest%u", true);
            this.logger = Logger.getLogger("logging.LoggingTest");
            this.logger.addHandler(logHandler);
        } catch (IOException exception) {
            Objects.requireNonNull(logger).log(Level.SEVERE, "cant create a logger");
            System.err.println("ops!");
        }
    }

    public Logger getLogger(){
        return this.logger;
    }
}