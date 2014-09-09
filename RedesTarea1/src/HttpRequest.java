import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*
    Dada la url el metodo sendGet retorna la respuesta del servidor
    La cual se imprime en el main

*/


public class HttpRequest {
    
    private final String USER_AGENT = "Mozilla/5.0";
 
	public static void main(String[] args) throws Exception {
 
		HttpRequest http = new HttpRequest();              
                
                //imprime respuesta de servidor
		System.out.println(http.sendGet("http://www.fing.edu.uy/"));                
 
	}
        
        
	private String sendGet(String url) throws Exception {
		
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
    
}
