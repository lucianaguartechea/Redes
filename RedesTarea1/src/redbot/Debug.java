package redbot;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author d
 */
public class Debug
{

    /**************************************************************************/
    /****************************** C O M M O N *******************************/
    /**************************************************************************/

    static void print(String txt)
    {
        if (Args.debug())
            System.out.print(txt);
    }

    static void println(){ println(""); }
    static void println(String txt)
    {
        if (Args.debug())
            System.out.println(txt);
    }

    static void print(String title, String txt)
    {
        println("___________");
        println(title.toUpperCase());
        println("___________");
        println(txt);
        println("_________________");
    }

    static void print(String title, List<String> txts)
    {
        println("----------------------");
        println(title.toUpperCase());
        println("----------------------");
        for (String txt : txts)
            println(txt);
        println("_________________");
        println();
    }

    private static String bool2string(boolean b)
    {
        return b ? "true" : "false";
    }

    /**************************************************************************/
    /******************************** A R G S *********************************/
    /**************************************************************************/

    static void printArgs()
    {
        List<String> args = new ArrayList();

        args.add("Reader: "         + Args.reader());
        args.add("Debug: "          + bool2string(Args.debug()));
        args.add("Seed URL: "       + Args.seed());
        args.add("Proxy: "          + Args.proxy());
        args.add("HTTP 1.1: "       + bool2string(Args.persistent()));
        args.add("MaxDepth: "       + Args.maxDepth());
        args.add("MaxThreads: "     + Args.maxThreads());
        args.add("Sinks file: "     + Args.sinksFile());
        args.add("Multilang file: " + Args.multilangFile());

        print("Configuración", args);
    }

}
