package redbot;

import java.util.Map;


/**
 *
 * @author d
 */
public interface Reader
{

    /**
     * Main HTTP modes used in the bot
     * 
     * @param url
     * @return content of page returned by the corresponding request (GET/POST)
     * @throws java.lang.Exception 
     */
    public String get(String url) throws Exception;
    public String post(String url) throws Exception;

    /**
     * Main HTTP modes used in the bot
     * 
     * @param url
     * @return headers returned by a HEAD request, as a non-parsed string
     * @throws java.lang.Exception 
     */
    public String head(String url) throws Exception;

    /**
     * 
     * @return headers from last read operation, as a map {header → value}
     */
    public Map<String, String> headers();

}
