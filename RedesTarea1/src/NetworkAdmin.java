import java.io.InputStream;
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
        int buff = socket.getReceiveBufferSize();
        byte[] machineAnswer = new byte[buff];
        String humanAnswer = "";

        int cumulativeRead = 0;
        int instanceRead = 0;
        while (instanceRead != -1 &&
               instanceRead + 1 != cumulativeRead) {
            instanceRead = in.read(machineAnswer, cumulativeRead,
                                   buff - 1 - cumulativeRead);                  // El problema de la demora radica en el tamaño del buffer, al menos eso creo (aunque ahora lo retesteo y me anda rápido... fíjense qué tal les anda a ustedes).
            cumulativeRead += 1 + instanceRead;
        }
        
        // RESPUESTA: Interpretación
        humanAnswer = new String(machineAnswer);                                // Podría no haber problemas con los chunks, a no ser que justo parta una tira que nos interese al medio (como un href o un mail). Hay que ver cómo solucionarlo.
        System.out.println("DATA RECEIVED:\n\n" + humanAnswer);

        // SOCKET: Cerrar
        socket.close();
    }
}
