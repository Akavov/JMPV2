import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by Ars on 17.08.2017.
 */
public class WrapperConnector {
    private Connection connection;
    public WrapperConnector(){
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.database");
            String url = resourceBundle.getString("url");
            String user = resourceBundle.getString("user");
            String pass = resourceBundle.getString("password");
            Properties prop = new Properties();
            prop.put("user", user);
            prop.put("password", pass);
            connection = DriverManager.getConnection(url, prop);
        } catch (MissingResourceException e) {
            System.err.println("properties file is missing" + e);
        } catch (SQLException e) {
            System.err.println("not obtained connection "+e );
        }
    }
    public Statement getStatement() throws SQLException {
        if (connection != null) {
            Statement statement=connection.createStatement();
            if(statement!=null){
                return statement;
            }
        }
        throw new SQLException("connection or statement is null");
    }
    public void closeStatement(Statement statement){
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.err.println("statement is null" +e);
            }
        }
    }
    public void closeConnection(){
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("wrong connection"+e);
            }
        }
    }
    //other methods of Connection interface
}
