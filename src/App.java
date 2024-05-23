import java.util.Scanner;

public class App
{
    public static void main(String[] args) throws Exception
    {
        PolySportDatabase database = PolySportDatabase.getInstance();
        SportsDAO dao = new SportsDAO(database);

        System.out.println("Enter a name to search : ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        for(Sport sport : dao.findByName(name))
        {
            System.out.println("name : " + sport.getName());
            System.out.println("participants : " + sport.getRequiredParticipants());
        }

        scanner.close();
    }
}
