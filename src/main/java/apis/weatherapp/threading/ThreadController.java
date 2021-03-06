package apis.weatherapp.threading;

import apis.weatherapp.metaweather.rest.controllers.WeatherInfoController;
import apis.weatherapp.threading.models.ThreadControl;
import apis.weatherapp.threading.services.ThreadControlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ThreadController implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherInfoController.class);
    @Autowired
    private PeriodicSaverThread periodicSaverThread;
    @Value("${refresher.port}")
    private int port;
    @Value("${refresher.start-regex}")
    private String startRegex;
    @Value("${refresher.end-regex}")
    private String endRegex;
    @Value("${refresher.receiving-mail}")
    private String receivingMail;
    @Value("${refresher.send-notifications}")
    private Boolean sendNotificationsFlag;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private ThreadControlService threadControlService;

    private Thread runner;
    private Pattern startPatter;
    private Pattern endPatter;

    @PostConstruct
    public void init() {
        this.startPatter = Pattern.compile(startRegex);
        this.endPatter = Pattern.compile(endRegex);
        this.runner = new Thread(this);
        this.runner.start();
    }

    @Override
    public void run() {
        LOGGER.info("Starting Socket server on port " + port);
        while (true) {
            try (
                    ServerSocket serverSocket = new ServerSocket(this.port);
                    Socket clientSocket = serverSocket.accept();
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
            ) {

                String inputLine, outputLine = null;

                // Initiate conversation with client
                while ((inputLine = in.readLine()) != null) {
                    Matcher startMatcher = startPatter.matcher(inputLine);
                    Matcher endMatcher = endPatter.matcher(inputLine);
                    if (startMatcher.matches()) {
                        LOGGER.info("Starting saver thread, received string:'{}'.", inputLine);
                        int minutes = Integer.parseInt(startMatcher.group(2));
                        periodicSaverThread.unpause();
                        outputLine = "Server thread started.";
                        threadControlService.save(new ThreadControl(true, inputLine));
                    } else if (endMatcher.matches()) {
                        LOGGER.info("Stoping saver thread, received string:'{}'.", inputLine);
                        int minutes = Integer.parseInt(endMatcher.group(2));
                        periodicSaverThread.pause();
                        outputLine = "Server thread paused.";
                        threadControlService.save(new ThreadControl(false, inputLine));
                    } else {
                        LOGGER.info("Invalid input, received:'{}'.", inputLine);
                        outputLine = "Invalid input!\nTry " + this.startRegex + " for starting the thread or " + this.endRegex + " for" +
                                "stopping it";
                    }
                    LOGGER.info("Writing '" + outputLine + "' to socket.");
                    out.println(outputLine);
                    if (!outputLine.startsWith("Invalid input!") && sendNotificationsFlag) {
                        notificationService.sendThreadNotification(this.receivingMail, outputLine);
                    }
                }
            } catch (IOException e) {
                LOGGER.error("Exception caught when trying to listen on port " + port + " or listening for a connection");
                LOGGER.error(e.getMessage());
            }
        }
    }
}
