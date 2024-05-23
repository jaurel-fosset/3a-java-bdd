import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class MySQLDatabase
{
    private final String host;
    private final int port;
    private final String databaseName;
    private final String user;
    private final String password;
    private Connection connection;
    
    static private boolean driverLoaded = false;

    MySQLDatabase(String host, int port, String databaseName, String user, String password)
    {
        this.host = host;
        this.port = port;
        this.databaseName = databaseName;
        this.user = user;
        this.password = password;

        this.connection = null;

        loadDriver();
    }

    static private void loadDriver()
    {
        if (driverLoaded == false)
        {
            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                driverLoaded = true;
            }
            catch(Exception e)
            {
                System.err.println("We cannot load the mysql driver :'(");
                System.exit(-1);
            }
        }
    }

    void connect() throws SQLException
    {
        this.connection = DriverManager.getConnection(
            "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.databaseName + "?allowMultiQuerie=true",
            this.user,
            this.password
        );
    }

    Statement creatStatement() throws SQLException
    {
        if (this.connection != null)
        {
            return this.connection.createStatement();
        }
        else
        {
            throw new SQLException();
        }
    }

    PreparedStatement preparedStatement(String request) throws SQLException
    {
        return connection.prepareStatement(request);
    }
}
