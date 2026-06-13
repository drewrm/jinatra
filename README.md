Jinatra - A (poor) Sinatra-esque web framework for Java
======================================================

[![Known Vulnerabilities](https://snyk.io/test/github/drewrm/jinatra/badge.svg?targetFile=pom.xml)](https://snyk.io/test/github/drewrm/jinatra?targetFile=pom.xml)

Don't use this, it's not meant to be useful.

Example:

```java
import java.io.IOException;

import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;
import org.eclipse.jetty.io.Content;
import au.id.andrewmyers.jinatra.annotations.*;

@Application(name="Hello World", port=8090)
public class TestApplication {

    @Get(route="/hello")
    public void hello(Request request, Response response, Callback callback) {
        response.setStatus(200);
        response.getHeaders().put(HttpHeader.CONTENT_TYPE, "text/html; charset=UTF-8");
        Content.Sink.write(response, true, "<html><head><title>Hello World</title></head><body><h1>Hello World</h1></body></html>", callback);
    }
}
```

Run with the following

```
mvn clean package 
javac -cp target/jinatra-1.0-SNAPSHOT-jar-with-dependencies.jar TestApplication.java
java -cp "$(pwd):target/jinatra-1.0-SNAPSHOT-jar-with-dependencies.jar" au.id.andrewmyers.jinatra.Jinatra

```
Then navigate to http://localhost:8090/hello to see the result.
