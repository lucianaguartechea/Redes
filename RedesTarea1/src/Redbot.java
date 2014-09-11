public class Redbot {
    public static void main(String[] args) {
        // Inicialización de banderas
        boolean debug = false;
        boolean infiniteDepth = true;
        int depth = 0;
        boolean persistent = false;
        boolean pozos = false;
        String pozosFile = "";
        boolean multilang = false;
        String multilangFile = "";
        int threads = 1;
        boolean proxy = false;
        String proxyURL = "";
        boolean reader = false;
        String semilla = "";
        
        // Procesamiento de argumentos                                          // Se asume que todos los switchs están bien escritos:
        if (args.length > 0) {                                                  // Si se ingresa un switch que requiere un argumento,
            int i = 0;                                                          // éste es especificado.
            while (i < args.length - 1) {
                switch (args[i].toUpperCase()) {
                    case "-D":
                        debug = true;
                        i++;
                        break;
                    case "-DEPTH":
                        i++;
                        depth = Integer.parseInt(args[i]);
                        infiniteDepth = false;
                        i++;
                        break;
                    case "-PERSISTENT":
                        persistent = true;
                        i++;
                        break;
                    case "-POZOS":
                        pozos = true;
                        i++;
                        pozosFile = args[i];
                        i++;
                        break;
                    case "-MULTILANG":
                        multilang = true;
                        i++;
                        multilangFile = args[i];
                        i++;
                        break;
                    case "-P":
                        i++;
                        threads = Integer.parseInt(args[i]);
                        i++;
                        break;
                    case "-PRX":
                        proxy = true;
                        i++;
                        proxyURL = args[i];
                        i++;
                        break;
                    case "-READER":
                        reader = true;
                        i++;
                        break;
                }
            }
            semilla = args[i];
        }
        
        // Echo de la configuración (ejemplo de uso de banderas)
        System.out.print("CONFIGURACIÓN:\n");
        if (debug) {
            System.out.print("-d\n");
        }
        if (infiniteDepth) {
            System.out.print("-depth infinito (por omisión)\n");
        } else {
            System.out.print("-depth " + depth + "\n");
        }
        if (persistent) {
            System.out.print("-persistent\n");
        }
        if (pozos) {
            System.out.print("-pozos " + pozosFile + "\n");
        }
        if (multilang) {
            System.out.print("-multilang " + multilangFile + "\n");
        }
        System.out.print("-p " + threads + "\n");
        if (proxy) {
            System.out.print("-prx " + proxyURL + "\n");
        }
        if (reader) {
            System.out.print("-reader\n");
        }
        System.out.print("\nSEMILLA:\n");
        System.out.print(semilla);
        System.out.print("\n\n");
    }
}
