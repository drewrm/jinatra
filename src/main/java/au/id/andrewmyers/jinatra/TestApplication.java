/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package au.id.andrewmyers.jinatra;

import au.id.andrewmyers.jinatra.annotations.Application;
import au.id.andrewmyers.jinatra.annotations.Get;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author andrew
 */
@Application(port=8090)
public class TestApplication {
    
    @Get(route="/hello")
    public void hello(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.getWriter().println("<html><head><title>Hello World</title></head><body><h1>Hello World</h1></body></html>");
        } catch (IOException ex) {
            Logger.getLogger(TestApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
