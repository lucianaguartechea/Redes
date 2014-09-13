package redbot;

public class Args
{

    private static String reader = "sockets";    // Class in charge of reading pages
    private static boolean debug = false;        // Debug mode

    private static String seed = null;           // Starting URL

    private static String proxy = null;          // Proxy URL
    private static boolean persistent = false;   // Persistent connections
    private static int depth = -1;               // Max recursion depth (-1 for no limit)
    private static int threads = 1;              // Max number of threads to spawn

    private static String sinksFile = null;      // List of pages without links, by URL
    private static String multilangFile = null;  // List of multilang pages, by URL


    public static void load(String[] args) throws Exception
    {
        int i = 0;

        while (i < args.length - 1)
        {
            switch (args[i].toUpperCase())
            {
                case "-RDR":
                    reader = args[++i].toLowerCase();
                    break;
                case "-D":
                    debug = true;
                    break;

                case "-PRX":
                    proxy = args[++i];
                    // TODO : validate proxy argument value
                    break;
                case "-PERSISTENT":
                    persistent = true;
                    break;
                case "-DEPTH":
                    depth = Integer.parseInt(args[++i]);

                    if (depth < -1)
                    {
                        String msg = "Tip: use \"-d -1\" for unlimited depth";
                        throw new IllegalArgumentException(msg);
                    }
                    break;
                case "-P":
                    threads = Integer.parseInt(args[++i]);

                    if (threads < 1)
                    {
                        String msg = "Parallel threads -p should be non-negative";
                        throw new IllegalArgumentException(msg);
                    }
                    break;

                case "-POZOS":
                    sinksFile = args[++i];
                    break;
                case "-MULTILANG":
                    multilangFile = args[++i];
                    break;

                default:
                    String msg = "Unrecognized parameter " + args[i];
                    throw new IllegalArgumentException(msg);
            }

            i++;
        }

        if (i == args.length || args[i].charAt(0) == '-')
        {
            throw new IllegalArgumentException("Missing seed (starting URL)");
        }

        seed = args[i];

        // Debug (ignored if @@debug is false)
        Debug.printArgs();
    }


    public static String reader()
    {
        return reader;
    }

    public static boolean debug()
    {
        return debug;
    }

    public static String seed()
    {
        return seed;
    }

    public static String proxy()
    {
        return proxy;
    }

    public static boolean persistent()
    {
        return persistent;
    }

    public static int maxDepth()
    {
        return depth >= -1 ? depth : -1;
    }

    public static int maxThreads()
    {
        return threads;
    }

    public static String sinksFile()
    {
        return sinksFile;
    }

    public static String multilangFile()
    {
        return multilangFile;
    }

}
