/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package au.id.andrewmyers.jinatra.server;

import au.id.andrewmyers.jinatra.JinatraApplication;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

/**
 *
 * @author andrew
 */
public class RequestHandler extends AbstractHandler {

    private JinatraApplication app;

    public RequestHandler(JinatraApplication app) {
        this.app = app;
    }

    @Override
    public void handle(final String target, final Request baseRequest, final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        if (app.getRoute(target, request.getMethod()) != null) {
            baseRequest.setHandled(true);
            response.setStatus(HttpServletResponse.SC_OK);
            app.dispatch(target, request, response);
        }
    }
}
