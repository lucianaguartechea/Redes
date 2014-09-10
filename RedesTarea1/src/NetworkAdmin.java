import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class NetworkAdmin {
    public static void main(String[] args) throws Exception {
        // SOLICITUD: Parámetros
        String HTTPversion = "HTTP/1.1";
        String route = "/principal";
        String domain = "www.bps.gub.uy";
        String connectionType = "keep-alive";
        String userAgent = "User-Agent: Mozilla/5.0";
        String language = "Accept-Language: es";
        int port = 80;

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
        Socket socket = new Socket(domain, port);                               // Se está creando y conectando al socket de una. No se si es legal o quieren que hagamos el Socket primero y lo conectemos después.
        InputStream in = socket.getInputStream();
        
        OutputStream out = socket.getOutputStream();
        out.write(machineRequest);
        
        // SOCKET: Recepción
        int buff = socket.getReceiveBufferSize();
        byte[] machineAnswer = new byte[buff];
        String humanAnswer = "";

        int cumulativeRead = 0;
        int instanceRead = 0;
        while (instanceRead != -1) {
            instanceRead = in.read(machineAnswer, cumulativeRead,
                                   buff - 1 - cumulativeRead);                  // El problema de la demora radica en el tamaño del buffer, al menos eso creo (aunque ahora lo retesteo y me anda rápido... fíjense qué tal les anda a ustedes).
            cumulativeRead += 1 + instanceRead;
        }
        
        // RESPUESTA: Interpretación
        humanAnswer = new String(machineAnswer);
        System.out.println("DATA RECEIVED:\n\n" + humanAnswer);

        // SOCKET: Cerrar
        socket.close();
    }
}
