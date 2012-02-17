/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package au.id.andrewmyers.jinatra.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author andrew
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Application {
    String name();
    String bind() default "0.0.0.0";
    int port() default 8080;
}
