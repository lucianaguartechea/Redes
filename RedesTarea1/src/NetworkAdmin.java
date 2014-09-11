import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class NetworkAdmin {
    public static void main(String[] args) throws Exception {
        // SOLICITUD: Encabezado HTTP
        String HTTPversion = "HTTP/1.1";
        String route = "/";
        String domain = "whatismyipaddress.com";
        String userAgent = "User-Agent: Mozilla/5.0";
        String language = "Accept-Language: es";
        
        // SOLICITUD: Conectividad
        int port = 80;
        boolean behindProxy = false;                                            // Sólo proxy público (sin autenticación)
        int proxyPort;
        String proxyDomain;
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
        BufferedReader inBuff = new BufferedReader(new InputStreamReader(in));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = inBuff.readLine()) != null) {
                response.append(inputLine + "\n");
        }
        inBuff.close();
        
        // RESPUESTA: Interpretación
        System.out.println("DATA RECEIVED (CHUNKED):\n\n" + response);

        // RESPUESTA: Unificación de chunks                                     // Hay que detectar los chunks cuando existen
                                                                                // (leyendo el encabezado "Transfer-Encoding: chunked")
                                                                                // y reunificar los trozos por si alguna numeración de bytes
                                                                                // corta algún dato que nos interese.
                                                                                //
                                                                                // La dinámica para unificar sería leer el primer dato
                                                                                // hexadecimal que indica la cantidad de caracteres a leer
                                                                                // (hay que ver si cuenta los \n y verificar con un ejemplo
                                                                                // lo que afirmo) y volver a leer el hexadecimal siguiente para
                                                                                // repetir el procedimiento.
        
        // SOCKET: Cerrar
        socket.close();
    }
}
