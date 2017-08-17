import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Ars on 17.08.2017.
 */
public class DataBaseHelper {

    private final static String SQL_INSERT=
            "INSERT INTO phonebook(idphonebook,lastname,phone) VALUES (?,?,?)";
    private Connection connection;
            public DataBaseHelper() throws SQLException {
                connection=ConnectorDB.getConnection();
            }
    public PreparedStatement getPreparedStatement(){
            PreparedStatement ps=null;
            try{
                    ps=connection.prepareStatement(SQL_INSERT);
            } catch (SQLException e){
                e.printStackTrace();
            }
            return ps;
    }
    public boolean insertAbonent(PreparedStatement ps, Abonent ab){
        boolean flag=false;
        try {
            ps.setInt(1,ab.getId());
            ps.setString(2,ab.getLastname());
            ps.setInt(3,ab.getPhone());
            ps.executeUpdate();
            flag=true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }
    public void closeStatement (PreparedStatement ps){
        if(ps!=null){
            try{
                ps.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
