/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package au.id.andrewmyers.jinatra.http;

import static org.junit.Assert.assertEquals;
import org.junit.*;

/**
 *
 * @author andrew
 */
public class RouteParserTest {

    private RouteParser parser;

    public RouteParserTest() {
        parser = new RouteParser();
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetPatternMatcherWithSplat() {
        String route = "/test/*";
        assertEquals("/test/(.*)", parser.getPatternMatcher(route).pattern());
    }
    
    @Test
    public void testGetPatternMatcherWithNamedParameter() {
        String route = "/test/:me";
        assertEquals("/test/([^/|^$].*)", parser.getPatternMatcher(route).pattern());
    }
    
    @Test
    public void testGetPatternMatcherWithNamedParameters() {
        String route = "/test/:me/:too";
        assertEquals("/test/([^/|^$].*)/([^/|^$].*)", parser.getPatternMatcher(route).pattern());
    }       
    
    @Test
    public void testGetPatternMatcherWithSimpleRoute() {
        String route = "/test/route";
        assertEquals(route, parser.getPatternMatcher(route).pattern());
    }

    @Test
    public void testGetPatternMatcherWithRegexRoute() {
        String regexRoute = "%r{/test/(.*)}";
        assertEquals("/test/(.*)", parser.getPatternMatcher(regexRoute).pattern());
    }
}
