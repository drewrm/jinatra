package au.id.andrewmyers.jinatra;

import au.id.andrewmyers.jinatra.scanner.ApplicationScanner;
import au.id.andrewmyers.jinatra.server.JinatraServer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class Jinatra {

    public static void main(String[] args) {
        ApplicationScanner scanner = new ApplicationScanner();
        List<JinatraApplication> apps = scanner.scanForApplications();

        if (apps.isEmpty()) {
            System.err.println("No Jinatra applications found.");
            System.exit(1);
        }
        
        for (JinatraApplication app : apps) {
            Logger.getLogger(Jinatra.class.getName()).log(Level.INFO, null, "Starting application on port " + app.getPort());
            JinatraServer s = new JinatraServer(app);
            s.start();
        }
    }
}