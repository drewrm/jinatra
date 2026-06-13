package au.id.andrewmyers.jinatra.http;

import au.id.andrewmyers.jinatra.annotations.*;
import java.lang.annotation.Annotation;

/**
 *
 * @author andrew
 */
public class HttpMethod<T extends Annotation> {

    public static final HttpMethod<Delete> DELETE = new HttpMethod<Delete>(Delete.class, "DELETE");
    public static final HttpMethod<Get> GET = new HttpMethod<Get>(Get.class, "GET");
    public static final HttpMethod<Options> OPTIONS = new HttpMethod<Options>(Options.class, "OPTIONS");
    public static final HttpMethod<Patch> PATCH = new HttpMethod<Patch>(Patch.class, "PATCH");
    public static final HttpMethod<Post> POST = new HttpMethod<Post>(Post.class, "POST");
    public static final HttpMethod<Put> PUT = new HttpMethod<Put>(Put.class, "PUT");

    private Class<T> type;
    private String name;
    private static final HttpMethod<?>[] values = new HttpMethod<?>[]{DELETE, GET, OPTIONS, PATCH, POST, PUT};

    private HttpMethod(final Class<T> type, final String name) {
        this.type = type;
        this.name = name;
    }

    public Class<T> getType() {
        return type;
    }

    public String getName() {
        return name;
    }
    
    public static HttpMethod<?>[] values() {
        return values;
    }
}
