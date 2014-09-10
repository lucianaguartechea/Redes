import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class NetworkAdmin {
    public static void main(String[] args) throws Exception {
        // SOLICITUD: Parámetros
        String HTTPversion = "HTTP/1.1";
        String route = "/";
        String domain = "whatismyipaddress.com";
        String userAgent = "User-Agent: Mozilla/5.0";
        String language = "Accept-Language: es";
        int port = 80;
        String proxyDomain;
        int proxyPort;
        boolean behindProxy = false;                                            // Sólo proxy público (sin autenticación)
        String connectionType;
        if (behindProxy) {
            connectionType = "Proxy-Connection: keep-alive";
            proxyDomain = "95.128.246.10";
            proxyPort = 80;
        } else {
            connectionType = "keep-alive";
            proxyDomain = domain;
            proxyPort = port;
        }
        
        // SOLICITUD: Confección
        String humanRequest =
            "GET " + route + " " + HTTPversion + "\r\n" +
            "Host: " + domain + "\r\n" +
            "Connection: " + connectionType + "\r\n" +
            userAgent + "\r\n" +
            language + "\r\n" +
            "\r\n";
        byte[] machineRequest = humanRequest.getBytes();
        
        // SOCKET: Creación y envío
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(proxyDomain, proxyPort));
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        out.write(machineRequest);
        
        // SOCKET: Recepción
        BufferedReader inBuff = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = inBuff.readLine()) != null) {
                response.append(inputLine);
        }
        inBuff.close();
        
        // RESPUESTA: Interpretación
        System.out.println("DATA RECEIVED:\n\n" + response);

        // SOCKET: Cerrar
        socket.close();
    }
}
