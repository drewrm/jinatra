/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package au.id.andrewmyers.jinatra.server;

import au.id.andrewmyers.jinatra.JinatraApplication;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jetty.server.Server;

/**
 *
 * @author andrew
 */
public class JinatraServer {

    private Server server;

    public JinatraServer(final JinatraApplication app) {
        server = new Server(app.getPort());
        server.setHandler(new RequestHandler(app));
        server.setStopAtShutdown(true);
    }

    public void start() {
        try {
            server.start();
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(JinatraServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
