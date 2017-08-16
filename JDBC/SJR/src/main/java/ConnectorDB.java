import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by Ars on 16.08.2017.
 */
public class ConnectorDB {
    public static Connection getConnection() throws SQLException {
        ResourceBundle resourceBundle=ResourceBundle.getBundle("database");
        String url=resourceBundle.getString("db.url");
        String user=resourceBundle.getString("db.user");
        String pass=resourceBundle.getString("db.password");
        return DriverManager.getConnection(url,user,pass);
    }
}
