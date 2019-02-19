/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package au.id.andrewmyers.jinatra.http;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author andrew
 */
public class RouteParser {

    private static final String REGEX_PREFIX = "%r{";
    private static final String REGEX_SUFFIX = "}";
    private static final String PREFIX = ":";
    private static final String SEPARATOR = "/";
    private static final String SPLAT = "*";

    Pattern getPatternMatcher(final String path) {
        Pattern pattern = null;

        if (path.startsWith(REGEX_PREFIX) && path.endsWith(REGEX_SUFFIX)) {
            pattern = Pattern.compile(path.substring(REGEX_PREFIX.length(), path.length() - 1));
        } else if (path.contains(PREFIX) || path.contains(SPLAT)) {
            String[] components = path.split("(?=" + SEPARATOR + ")");

            for (int i = 0; i < components.length; i++) {
                if (components[i].startsWith(SEPARATOR + PREFIX)) {
                    components[i] = "/([^/|^$].*)";
                } else if (components[i].contains(SPLAT)) {
                    components[i] = components[i].replace(SPLAT, "(.*)");
                }
            }
            pattern = Pattern.compile(StringUtils.join(components));
        } else {
            pattern = Pattern.compile(path);
        }

        return pattern;
    }
}
