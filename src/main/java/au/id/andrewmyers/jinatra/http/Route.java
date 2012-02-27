/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package au.id.andrewmyers.jinatra.http;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.regex.Pattern;

/**
 *
 * @author andrew
 */
public class Route {
    
    private String route;
    private Pattern matcher;
    private Method method;
    
    public Route(final String route, final Method method) {
        RouteParser parser = new RouteParser();
        this.route = route;
        this.matcher = parser.getPatternMatcher(route);
        this.method = method;
    }
    
    public boolean matches(String request) {
        return matcher.matcher(request).matches();
    }

    /**
     * @return the route
     */
    public String getRoute() {
        return route;
    }

    /**
     * @return the method
     */
    public Method getMethod() {
        return method;
    }
}
