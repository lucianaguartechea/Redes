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
     * @return content of page 
     * @throws java.lang.Exception 
     */
    public String get(String url) throws Exception;
    public String post(String url) throws Exception;
    public String head(String url) throws Exception;

    /**
     * 
     * @return headers from last read operation
     */
    public Map<String, String> headers();

}
