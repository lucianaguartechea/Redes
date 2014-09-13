package redbot;

import java.io.*;
import java.net.*;
import java.util.Map;

/**
 *
 * @author d
 */
public class ReaderUsingHttpReq implements Reader
{

    private final String USER_AGENT = "Mozilla/5.0";


    @Override
    public String get(String url) throws Exception
    {
        URL obj = new URL(url);
        HttpURLConnection conecction = (HttpURLConnection) obj.openConnection();

        conecction.setRequestMethod("GET");
        conecction.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = conecction.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(conecction.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

       return response.toString();
    }

    @Override
    public String post(String url) throws Exception
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String head(String url) throws Exception
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Map<String, String> headers()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
