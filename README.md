Jinatra - A (poor) Sinatra-eque web framework for Java
======================================================

Example:

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
