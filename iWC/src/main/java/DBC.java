import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ars on 16.10.2017.
 */
public class DBC {


    Connection connection;
    Statement statement;
    ResultSet rs;
    List<String> list;

    public DBC() {

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "panamera15");
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM goods");
            list = new ArrayList<String>();
            while (rs.next()) {
                list.add(rs.getString(1)+" "+rs.getString(2)+" " + rs.getString(3)+" "+rs.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
