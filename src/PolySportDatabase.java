public class PolySportDatabase extends MySQLDatabase
{
    private static PolySportDatabase instance; 

    private PolySportDatabase()
    {
        super("localhost", 3306, "polysport", "root", "");
        try
        {
            this.connect();
        }
        catch (Exception e)
        {
            System.err.println("Unable to connect to the polysport database");
            System.exit(-1);
        }
        
    }

    static PolySportDatabase getInstance()
    {
        if (instance == null)
        {
            instance = new PolySportDatabase();
        }

        return instance;
    }
}
