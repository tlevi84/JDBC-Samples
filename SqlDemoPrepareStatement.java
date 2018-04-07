import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by tall on 14/03/2018.
 */
public class SqlDemoPrepareStatement {

    public static void main(String[] args) {
        // JDBC driver name and database URL
        final String DB_URL = "jdbc:jtds:sqlserver://localhost:1433/demo;instance=SQLEXPRESS";
        //  Database credentials
        final String USER = "java";
        final String PASS = "java123";
        String sql;

        Connection conn;
        PreparedStatement stmt;
        try {
            //Register JDBC driver
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            //Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //Execute a query
            System.out.println("Creating statement...");

            sql = "update Employees set age=? WHERE id=?";
            stmt = conn.prepareStatement(sql);

            //Bind values into the parameters.
            stmt.setInt(1, 102);  // This would set age
            stmt.setInt(2, 1); // This would set ID
            stmt.executeUpdate();

            // Let us select all the records and display them.
            sql  =  "SELECT id, first_name, last_name, age FROM Employees";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            //Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");

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

