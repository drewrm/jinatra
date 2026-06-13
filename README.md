Jinatra - A (poor) Sinatra-esque web framework for Java
======================================================

[![Known Vulnerabilities](https://snyk.io/test/github/drewrm/jinatra/badge.svg?targetFile=pom.xml)](https://snyk.io/test/github/drewrm/jinatra?targetFile=pom.xml)

My own implementation of an annotation driven, sinatra-esque web framework in java. 
Don't use this, it's not meant to be useful, just doing it because I can.

Example:

```java
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import au.id.andrewmyers.jinatra.annotations.*;

@Application(port=8090)
public class TestApplication {
    
    @Get(route="/hello")
    public void hello(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.getWriter().println("<html><head><title>Hello World</title></head><body><h1>Hello World</h1></body></html>");
        } catch (IOException ex) {
            System.err.println("Derp! " + ex.toString());
        }
    }
}
```

Run with:
```
javac -cp target/jinatra-1.0-SNAPSHOT-jar-with-dependencies.jar TestApplication.java
java -cp "$(pwd):target/jinatra-1.0-SNAPSHOT-jar-with-dependencies.jar" au.id.andrewmyers.jinatra.Jinatra

```
Then navigate to http://localhost:8090/hello to see the result.
