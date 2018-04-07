import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by tall on 14/03/2018.
 */
public class SqlDataSourceDemo {

    public static void main(String[] args) {
        Connection conn;
        Statement stmt;
        String sql;
        try {
            //Open a connection
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setUser("java");
            ds.setPassword("java123");
            ds.setServerName("localhost");
            ds.setPortNumber(1433);
            ds.setDatabaseName("Demo");
            conn = ds.getConnection();

            //Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            sql = "SELECT id, first_name, last_name, age FROM Employees";
            ResultSet rs = stmt.executeQuery(sql);

            //Extract data from result set
            while (rs.next()) {
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

