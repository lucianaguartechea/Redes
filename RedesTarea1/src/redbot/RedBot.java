package redbot;


/**
 *
 * @author d
 */
public class RedBot
{

    /**
     *
     * @param args
     */
    public static void main(String[] args)
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
         * On failure abort execution, on success start the program.
         */
        try
        {
            Reader testReader = createReader();
            start();
        }
        catch (ClassNotFoundException cnf)
        {
            System.out.println("No matching Reader class for " + Args.reader());
            System.exit(1);
        }
        catch (Exception e)
        {
            System.out.println("Unexpected exception thrown");
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    private static Reader createReader() throws ClassNotFoundException
    {
        switch (Args.reader())
        {
            case "sockets":
                return new ReaderUsingSockets();
            case "httpreq":
                return new ReaderUsingMockUps();
            case "mockups":
                return new ReaderUsingHttpReq();

            default:
                throw new ClassNotFoundException();
        }
    }

    private static void start() throws Exception // Most exceptions handled in main, not here
    {
        Reader reader = createReader();

	System.out.println(reader.get("http://www.fing.edu.uy/"));
    }

}