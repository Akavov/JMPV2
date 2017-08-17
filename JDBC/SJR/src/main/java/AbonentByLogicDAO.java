import java.sql.Connection;
import java.util.List;

/**
 * Created by Ars on 17.08.2017.
 */
public class AbonentByLogicDAO extends AbstractByLogicDAO<Abonent> {

    public AbonentByLogicDAO(Connection connection) {
        super(connection);
    }

    //down here need to be method`s implementation
    public List<Abonent> findAll() {
        return null;
    }

    public Abonent findEntityById(int id) {
        return null;
    }

    public boolean delete(int id) {
        return false;
    }

    public boolean delete(Abonent entity) {
        return false;
    }

    public boolean create(Abonent entity) {
        return false;
    }

    public Abonent update(Abonent entity) {
        return null;
    }
}
