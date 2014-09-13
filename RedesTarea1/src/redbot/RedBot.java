package redbot;


/**
 *
 * @author d
 */
public class RedBot
{
    
    private static String readerClass;


    static void main (String[] args) throws ClassNotFoundException
    {
        /**
         * Parse, store and validate arguments.
         * On failure, abort execution.
         */
        try
        {
            Args.load(args);
        }
        catch (NumberFormatException nf)
        {
            System.out.println("Invalid argument, number expected");
            System.out.println(nf.getMessage());
            System.exit(1);
        }
        catch (IllegalArgumentException ia)
        {
            System.out.println("Invalid arguments format, cannot parse");
            System.out.println(ia.getMessage());
            System.exit(1);
        }
        catch (Exception e)
        {
            System.out.println("Unexpected exception thrown");
            System.out.println(e.getMessage());
            System.exit(1);
        }


        /**
         * Validate and instantiate first Reader object
         * On failure, abort execution.
         */
        try
        {
            switch (Args.reader())
            {
                case "sockets": readerClass = "ReaderUsingSockets"; break;
                case "httpreq": readerClass = "ReaderUsingMockUps"; break;
                case "mockups": readerClass = "ReaderUsingHttpReq"; break;
                default:
                    throw new ClassNotFoundException();
            }

            // And... there we go
            start((Reader)Class.forName(readerClass).newInstance());
        }
        catch (IllegalAccessException | InstantiationException e)
        {
            System.out.println("Failed to instantiate Reader class");
            System.out.println(e.getMessage());
            System.exit(1);
        }
        catch (ClassNotFoundException cnf)
        {
            System.out.println("No matching Reader class for " + Args.reader());
            System.exit(1);
        }
    }

    private static void start(Reader reader)
    {
        // TODO : here's where it all starts!
    }

}
