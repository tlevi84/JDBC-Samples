import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by tall on 14/03/2018.
 */
public class SqlDemo {

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
            sql = "SELECT id, first_name, last_name, age FROM Employees";
            ResultSet rs = stmt.executeQuery(sql);
            //Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                rs.updateInt("age", 17);
                rs.updateRow();
                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", First name: " + first_name);
                System.out.println(", Last name: " + last_name);
            }
            //Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            //Handle errors for JDBC
            e.printStackTrace();
        }
    }
}

