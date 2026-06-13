import java.io.IOException;

import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;
import org.eclipse.jetty.io.Content;
import au.id.andrewmyers.jinatra.annotations.*;

@Application(name="Hello World", port=8090)
public class TestApplication {

    @Get(route = "/")
    public void index(Request request, Response response, Callback callback) {
        response.setStatus(200);
        response.getHeaders().put(HttpHeader.CONTENT_TYPE, "text/html; charset=UTF-8");
        Content.Sink.write(response, true, """
                <html>
                    <head>
                        <title>Available Endpoints</title>
                    </head>
                    <body>
                        <h1>Available Endpoints</h1>
                        <ul>
                            <li><a href="/">Index</a></li>
                            <li><a href="/hello">Hello</a></li>
                            <li><a href="/teapot">Tea Pot</a></li>
                        </ul>
                    </body>
                </html>""", callback);
    }

    @Get(route="/teapot")
    public void teapot(Request request, Response response, Callback callback) {
        response.setStatus(418);
        response.getHeaders().put(HttpHeader.CONTENT_TYPE, "text/html; charset=UTF-8");
        Content.Sink.write(response, true, "<html><head><title>HTTP 418 - I am a teapot</title></head><body><h1>This is a tea pot</h1></body></html>", callback);
    }

    @Get(route="/hello")
    public void hello(Request request, Response response, Callback callback) {
        response.setStatus(200);
        response.getHeaders().put(HttpHeader.CONTENT_TYPE, "text/html; charset=UTF-8");
        Content.Sink.write(response, true, "<html><head><title>Hello World</title></head><body><h1>Hello World</h1></body></html>", callback);
    }
}
