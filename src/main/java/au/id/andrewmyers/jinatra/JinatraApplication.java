/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package au.id.andrewmyers.jinatra;

import au.id.andrewmyers.jinatra.annotations.Get;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
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
    private HashMap<String, Method> getActions;
    
    public JinatraApplication(final String bind, final int port, Class clazz) throws InstantiationException, IllegalAccessException{ 
        this.bind = bind;
        this.port = port;
        this.clazz = clazz;
        this.instance = clazz.newInstance();
        
        getActions = new HashMap<String, Method>(); 
        for (Method m : clazz.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Get.class)) {
                getActions.put(m.getAnnotation(Get.class).route(), m);
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
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * @return the bind
     */
    public String getBind() {
        return bind;
    }

    /**
     * @param bind the bind to set
     */
    public void setBind(String bind) {
        this.bind = bind;
    }
    
    public boolean hasRoute(String route) {
        return getActions.containsKey(route);
    }
    
    public void dispatch(String route, HttpServletRequest request, HttpServletResponse response) {
        try {
            Method m = getActions.get(route);
            m.invoke(instance, new Object[]{request,response});
        } catch (IllegalAccessException ex) {
            Logger.getLogger(JinatraApplication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(JinatraApplication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(JinatraApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
