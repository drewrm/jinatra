/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package au.id.andrewmyers.jinatra.scanner;

import au.id.andrewmyers.jinatra.JinatraApplication;
import au.id.andrewmyers.jinatra.annotations.Application;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

/**
 *
 * @author andrew
 */
public class ApplicationScanner {

    public List<JinatraApplication> scanForApplications() {
        Reflections reflections = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forJavaClassPath()));
        Set<Class<?>> applications = reflections.getTypesAnnotatedWith(Application.class);
        List<JinatraApplication> apps = new ArrayList<JinatraApplication>();
        
        for (final Class clazz : applications) {
            try {
                apps.add(new JinatraApplication(clazz));
            } catch (InstantiationException ex) {
                Logger.getLogger(ApplicationScanner.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ApplicationScanner.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return apps;
    }
}
