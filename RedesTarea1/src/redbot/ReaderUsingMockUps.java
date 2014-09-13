package redbot;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author d
 */
public class ReaderUsingMockUps implements Reader
{

    @Override
    public String get(String url) throws Exception
    {
        Map<String, String> pages = new HashMap();
        
        String page;

        /**
         * Mails: 2 (one with "mailto:", one lose)
         * URLs: 2
         * Hiding: no
         * Others: single and double quotes
         */
        page = "Hola\n"
             + "esto es una página con un par de mails y algún link\n"
             + "por ejemplo <a href='link1'>link1</a>\n"
             + "o <a href=\"link2\">link2</a>\n"
             + "y pa mails no sé... uno con mailto <a href='mailto:cuisdy@gmail.com'>mi mail</a>\n"
             + "y otro suelto cuisdy@gmail.com'\n"
             + "(en el próximo capítulo, mails medianamente escondidos)'\n";
        pages.put("page1", page);

        /**
         * Mails: 
         * URLs: 
         * Hiding: 
         * Others: 
         */
        page = "";
        pages.put("page2", page);
        
        page = "La página que pediste no la conozco, te devuelvo"
             + " cualquier cosa, sin mails ni urls ni nada, ta?";
        pages.put("", page); // default

        return pages.containsKey(url) ? pages.get(url) : pages.get("");
    }

    @Override
    public String post(String url) throws Exception
    {
        return get(url);
    }

    @Override
    public String head(String url) throws Exception
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Map<String, String> headers()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}