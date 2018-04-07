import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by tall on 14/03/2018.
 */
public class sqlExecuteUpdate {

    public static void main(String[] args) {
        // JDBC driver name and database URL
        final String DB_URL = "jdbc:jtds:sqlserver://localhost:1433/demo;instance=SQLEXPRESS";

        //  Database credentials
        final String USER = "java";
        final String PASS = "java123";
        String sql;

        Connection conn = null;
        Statement stmt = null;
        try {
            //Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            sql = "update Employees set age=102 WHERE id=1";
            int countLines = stmt.executeUpdate(sql);
            System.out.println(countLines);
            stmt.close();
            conn.close();
        } catch (Exception e) {
            //Handle errors for JDBC
            e.printStackTrace();
        }
    }
}

