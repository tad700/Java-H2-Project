import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
static Connection conn = null;

static Connection getConnection(){
    try {
        Class.forName("org.h2.Driver");
        conn=DriverManager.getConnection("jdbc:h2:tcp://localhost/D:\\Projects\\IdeaProjects\\untitled","sa","1234");
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }
return conn;

}


}
