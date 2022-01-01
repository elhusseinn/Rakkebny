import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {

    static private Connection c = null;
    public static Connection openConnection(){
        if (c == null){
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
                c.setAutoCommit(false);
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        }

        return c;
    }


}
