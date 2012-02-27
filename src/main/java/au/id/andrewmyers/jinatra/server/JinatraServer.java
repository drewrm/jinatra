/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package au.id.andrewmyers.jinatra.server;

import au.id.andrewmyers.jinatra.JinatraApplication;
import java.net.InetSocketAddress;
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
        InetSocketAddress bindAddress = new InetSocketAddress(app.getBindAddress(), app.getPort());
        server = new Server(bindAddress);
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
