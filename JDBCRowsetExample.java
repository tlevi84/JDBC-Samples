import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

public class JDBCRowsetExample {

    private static final String DB_URL = "jdbc:jtds:sqlserver://localhost:1433/demo;instance=SQLEXPRESS";
    private final static String USR = "java";
    private final static String PWD = "java123";
    private final static String AGE = "age";

    public static void main(String[] args) throws Exception {
        JdbcRowSet rowSet = RowSetProvider.newFactory().createJdbcRowSet();
        rowSet.setUrl(DB_URL);
        rowSet.setUsername(USR);
        rowSet.setPassword(PWD);

        rowSet.setCommand("SELECT id, first_name, last_name, age FROM EMPLOYEES");
        rowSet.execute();

        rowSet.addRowSetListener(new MyListener());
        while (rowSet.next()) {
            System.out.print("Id: " + rowSet.getString(1));
            System.out.print(", First Name: " + rowSet.getString(2));
            System.out.println(", last Name: " + rowSet.getString(3));
        }

    }

    static class MyListener implements RowSetListener {
        public void cursorMoved(RowSetEvent event) {
            System.out.println("Cursor Moved...");
        }

        public void rowChanged(RowSetEvent event) {
            System.out.println("Cursor Changed...");
        }

        public void rowSetChanged(RowSetEvent event) {
            System.out.println("RowSet changed...");
        }

    }
}



