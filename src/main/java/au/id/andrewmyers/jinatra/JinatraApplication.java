/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package au.id.andrewmyers.jinatra;

import au.id.andrewmyers.jinatra.annotations.Application;
import au.id.andrewmyers.jinatra.annotations.Get;
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
    private Class clazz;
    private Object instance;
    private Map<String, Map> actions;

    public JinatraApplication(final Class clazz) throws InstantiationException, IllegalAccessException {
        Application app = (Application) clazz.getAnnotation(Application.class);
        this.bind = app.bind();
        this.port = app.port();
        this.clazz = clazz;
        this.instance = clazz.newInstance();

        actions = new HashMap<String, Map>();
        actions.put("GET", new HashMap<String, Method>());
        for (Method m : clazz.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Get.class)) {
                actions.get("GET").put(m.getAnnotation(Get.class).route(), m);
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
