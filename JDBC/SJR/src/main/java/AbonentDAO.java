import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ars on 17.08.2017.
 */
public class AbonentDAO extends AbstractDAO<Integer,Abonent> {

    public static final String SQL_SELECT_ALL_ABONENTS = "SELECT * FROM phonebook";
    public static final String SQL_SELECT_ABONENT_BY_LASTNAME = "SELECT idphonebook, phone FROM phonebook WHERE lastname=?";

    public List<Abonent> findAll() {
        List<Abonent> abonents = new ArrayList<Abonent>();
        Connection cn=null;
        Statement st=null;
        try {
            cn = ConnectionPool.getConnection();
            st = cn.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_ABONENTS);
            while (resultSet.next()) {
                Abonent abonent = new Abonent();
                abonent.setPhone(resultSet.getInt("idphonebook"));
                abonent.setPhone(resultSet.getInt("phone"));
                abonent.setLastname(resultSet.getString("lastname"));
                abonents.add(abonent);
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): "+e);
        } finally {
            close(st);
        }
            return abonents;
    }

    public Abonent findEntityById(Integer id) {
        throw new UnsupportedOperationException();
    }

    public boolean delete(Integer id) {
        throw new UnsupportedOperationException();
    }

    public boolean delete(Abonent entity) {
        throw new UnsupportedOperationException();
    }

    public boolean create(Abonent entity) {
        throw  new UnsupportedOperationException();
    }

    public Abonent update(Abonent entity) {
        throw new UnsupportedOperationException();
    }

    //DAO`s own method
    public Abonent findAbonentByLastName(String name){
        Abonent abonent = new Abonent();
        Connection cn=null;
        PreparedStatement st=null;
        try {
            cn = ConnectionPool.getConnection();
            st = cn.prepareStatement(SQL_SELECT_ABONENT_BY_LASTNAME);
            st.setString(1, name);
            ResultSet resultSet = st.executeQuery();
            resultSet.next();
            abonent.setId(resultSet.getInt("idphonebook"));
            abonent.setPhone(resultSet.getInt("phone"));
            abonent.setLastname(name);
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): "+e);
        } finally {
            close(st);
        }
        return abonent;
    }
}
