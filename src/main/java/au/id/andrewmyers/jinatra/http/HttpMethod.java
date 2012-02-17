package au.id.andrewmyers.jinatra.http;

import au.id.andrewmyers.jinatra.annotations.*;
import java.lang.annotation.Annotation;

/**
 *
 * @author andrew
 */
public class HttpMethod<T extends Annotation> {

    public static final HttpMethod DELETE = new HttpMethod(Delete.class, "DELETE");
    public static final HttpMethod GET = new HttpMethod(Get.class, "GET");
    public static final HttpMethod OPTIONS = new HttpMethod(Options.class, "OPTIONS");
    public static final HttpMethod PATCH = new HttpMethod(Patch.class, "PATCH");
    public static final HttpMethod POST = new HttpMethod(Post.class, "POST");
    public static final HttpMethod PUT = new HttpMethod(Put.class, "PUT");

    private Class<T> type;
    private String name;
    private static final HttpMethod[] values = new HttpMethod[]{DELETE, GET, OPTIONS, PATCH, POST, PUT};

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
    
    public static HttpMethod[] values() {
        return values;
    }
}
