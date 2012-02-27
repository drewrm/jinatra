/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package au.id.andrewmyers.jinatra;

import au.id.andrewmyers.jinatra.annotations.*;
import au.id.andrewmyers.jinatra.http.HttpMethod;
import au.id.andrewmyers.jinatra.http.Route;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author andrew
 */
public class JinatraApplication {

    private int port;
    private String bind;
    private String name;
    private Object instance;
    private Map<String, Map<String, Route>> actions;

    public JinatraApplication(final Class clazz) throws InstantiationException, IllegalAccessException {
        Application app = (Application) clazz.getAnnotation(Application.class);
        this.bind = app.bind();
        this.port = app.port();
        this.name = app.name();

        this.instance = clazz.newInstance();

        actions = new HashMap<String, Map<String, Route>>();

        for (HttpMethod httpMethod : HttpMethod.values()) {
            actions.put(httpMethod.getName(), new HashMap<String, Route>());
        }

        for (Method m : clazz.getDeclaredMethods()) {
            for (HttpMethod httpMethod : HttpMethod.values()) {
                if (m.isAnnotationPresent(httpMethod.getType())) {
                    String route = null;
                    Annotation a = m.getAnnotation(httpMethod.getType());

                    if (a instanceof Delete) {
                        route = ((Delete) a).route();
                    }
                    if (a instanceof Get) {
                        route = ((Get) a).route();
                    }
                    if (a instanceof Options) {
                        route = ((Options) a).route();
                    }
                    if (a instanceof Patch) {
                        route = ((Patch) a).route();
                    }
                    if (a instanceof Post) {
                        route = ((Post) a).route();
                    }
                    if (a instanceof Put) {
                        route = ((Put) a).route();
                    }

                    actions.get(httpMethod.getName()).put(route, new Route(route, m));
                }
            }
        }
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @return the bind address
     */
    public String getBindAddress() {
        return bind;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    public Route getRoute(final String route, final String method) {
        for (Route r : actions.get(getRequestType(method)).values()) {
            if (r.matches(route)) {
                return r;
            }
        }

        return null;
    }

    public boolean dispatch(final String route, final HttpServletRequest request, final HttpServletResponse response) {
        try {
            Method m = getRoute(route, request.getMethod()).getMethod();
            m.invoke(instance, new Object[]{request, response});
            return true;
        } catch (IllegalAccessException ex) {
            Logger.getLogger(JinatraApplication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(JinatraApplication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(JinatraApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    private String getRequestType(final String method) {
        return method.equals("HEAD") ? "GET" : method;
    }
}
