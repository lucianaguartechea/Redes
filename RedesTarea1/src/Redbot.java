public class Redbot {
    public static void main(String[] args) {
        // INICIALIZACIÓN DE BANDERAS
        boolean debug = false;
        boolean infiniteDepth = true;
        int depth = 0;
        boolean persistent = false;
        boolean pozos = false;
        boolean reader = false;
        boolean multilang = false;
        int threads = 1;
        boolean proxy = false;
        String pozosFile = "";
        String multilangFile = "";
        String proxyURL = "";
        String semilla = "";
        // PROCESAMIENTO DE ARGUMENTOS
        if (args.length > 0) {
            int i = 0;
            while (i < args.length - 1) {
                switch (args[i].toUpperCase()) {
                    case "-D":
                        debug = true;
                        i++;
                        break;
                    case "-DEPTH":
                        i++;
                        if (i < args.length - 1) {
                            try {
                                depth = Integer.parseInt(args[i]);
                                infiniteDepth = false;
                                i++;
                            } catch (NumberFormatException exc) { }
                        }
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
                    case "-READER":
                        reader = true;
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
                        if (i < args.length - 1) {
                            try {
                                threads = Integer.parseInt(args[i]);
                                i++;
                            } catch (NumberFormatException exc) { }
                        }
                        break;
                    case "-PRX":
                        proxy = true;
                        i++;
                        proxyURL = args[i];
                        i++;
                        break;
                }
            }
            semilla = args[i];
        }
        // ECHO DE CONFIGURACIÓN:
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
        if (reader) {
            System.out.print("-reader\n");
        }
        if (multilang) {
            System.out.print("-multilang " + multilangFile + "\n");
        }
        System.out.print("-p " + threads + "\n");
        if (proxy) {
            System.out.print("-prx " + proxyURL + "\n");
        }
        System.out.print("\nSEMILLA:\n");
        System.out.print(semilla);
        System.out.print("\n\n");
    }
}
