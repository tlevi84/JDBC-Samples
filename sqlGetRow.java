import java.sql.*;

/**
 * Created by tall on 14/03/2018.
 */
public class sqlGetRow {

    public static void main(String[] args) throws SQLException {
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
            rs.next();
            System.out.println("getString method: " + rs.getString("first_name"));
            System.out.println("getInt with index: " + rs.getInt("age"));
            System.out.println("getRow method: " + rs.getRow());
            //Extract data from result set
            //Clean-up environment
            stmt.close();
            conn.close();
        } catch (Exception e) {
            //Handle errors for JDBC
            e.printStackTrace();
        }
    }
}

