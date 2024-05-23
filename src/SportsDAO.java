import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class SportsDAO
{
    private MySQLDatabase database;

    SportsDAO(MySQLDatabase database)
    {
        this.database = database;
    }

    ArrayList<Sport> findAll() throws SQLException
    {
        Statement myStatement = this.database.creatStatement();
        ResultSet results = myStatement.executeQuery("SELECT * FROM sport;");

        ArrayList<Sport> sports = new ArrayList<>();

        while(results.next())
        {
            Sport sport = new Sport(results.getInt("id"), results.getString("name"), results.getInt("required_participants"));
            sports.add(sport);
        }

        return sports;
    }

    Sport findbyId(int id) throws SQLException
    {
        PreparedStatement statement = this.database.preparedStatement("SELECT * FROM sport WHERE id = ?;");
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();

        result.next();
        if (result.wasNull())
        {
            return null;
        }

        return new Sport(result.getInt("id"), result.getString("name"), result.getInt("required_participants"));
    }

    ArrayList<Sport> findByName(String name) throws SQLException
    {
        PreparedStatement statement = this.database.preparedStatement("SELECT * FROM sport WHERE name LIKE ?;");
        statement.setString(1, name);
        ResultSet results = statement.executeQuery();

        ArrayList<Sport> sports = new ArrayList<>();

        while(results.next())
        {
            Sport sport = new Sport(results.getInt("id"), results.getString("name"), results.getInt("required_participants"));
            sports.add(sport);
        }

        return sports;
    }

    boolean insert(Sport sport)
    {
        try
        {
            PreparedStatement statement = this.database.preparedStatement("INSERT INTO sport (name, required_participants) VALUES (?, ?);");

            statement.setString(1, sport.getName());
            statement.setInt(2, sport.getRequiredParticipants());

            return true;
        }
        catch(SQLException e)
        {
            return false;
        }
    }
}
