package au.id.andrewmyers.jinatra.server;

import au.id.andrewmyers.jinatra.JinatraApplication;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;

/**
 *
 * @author andrew
 */
public class RequestHandler extends Handler.Abstract {

    private JinatraApplication app;

    public RequestHandler(JinatraApplication app) {
        this.app = app;
    }

    @Override
    public boolean handle(Request request, Response response, Callback callback) throws Exception {
        String target = request.getHttpURI().getPath();
        String method = request.getMethod();
        if (app.getRoute(target, method) != null) {
            response.setStatus(HttpServletResponse.SC_OK);
            app.dispatch(target, request, response, callback);
            callback.succeeded();
            return true; 
        }

        return false;
    }
}
