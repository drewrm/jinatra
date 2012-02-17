/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package au.id.andrewmyers.jinatra;

import au.id.andrewmyers.jinatra.annotations.*;
import au.id.andrewmyers.jinatra.http.HttpMethod;
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
    private Class clazz;
    private Object instance;
    private Map<String, Map> actions;

    public JinatraApplication(final Class clazz) throws InstantiationException, IllegalAccessException {
        Application app = (Application) clazz.getAnnotation(Application.class);
        this.bind = app.bind();
        this.port = app.port();
        this.name = app.name();

        this.clazz = clazz;
        this.instance = clazz.newInstance();

        actions = new HashMap<String, Map>();

        for (HttpMethod httpMethod : HttpMethod.values()) {
            actions.put(httpMethod.getName(), new HashMap<String, Method>());
        }

        for (Method m : clazz.getDeclaredMethods()) {
            for (HttpMethod httpMethod : HttpMethod.values()) {
                System.out.println("Has anotation " + httpMethod.getType().getCanonicalName() + " on method " + m.getName() + " " + m.isAnnotationPresent(httpMethod.getType()));
                if (m.isAnnotationPresent(httpMethod.getType())) {
                    String route = null;
                    Annotation a = m.getAnnotation(httpMethod.getType());
                    
                    if (a instanceof Delete) route = ((Delete)a).route();
                    if (a instanceof Get) route = ((Get)a).route();
                    if (a instanceof Options) route = ((Options)a).route();
                    if (a instanceof Patch) route = ((Patch)a).route();
                    if (a instanceof Post) route = ((Post)a).route();
                    if (a instanceof Put) route = ((Put)a).route();       
                   
                    actions.get(httpMethod.getName()).put(route, m);
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

    public boolean hasRoute(final String route, final String method) {
        return actions.get(getRequestType(method)).containsKey(route);
    }

    public void dispatch(final String route, final HttpServletRequest request, final HttpServletResponse response) {
        try {
            Map<String, Method> methods = actions.get(getRequestType(request.getMethod()));
            Method m = methods.get(route);
            m.invoke(instance, new Object[]{request, response});
        } catch (IllegalAccessException ex) {
            Logger.getLogger(JinatraApplication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(JinatraApplication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(JinatraApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getRequestType(final String method) {
        return method.equals("HEAD") ? "GET" : method;
    }
}
