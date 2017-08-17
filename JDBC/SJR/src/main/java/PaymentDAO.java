import java.sql.Connection;
import java.util.List;

/**
 * Created by Ars on 17.08.2017.
 */
public class PaymentDAO extends AbstractByLogicDAO<Payment> {

    public PaymentDAO(Connection connection) {
        super(connection);
    }
    public List<Payment> findAll() {
        return null;
    }

    public Payment findEntityById(int id) {
        return null;
    }

    public boolean delete(int id) {
        return false;
    }

    public boolean delete(Payment entity) {
        return false;
    }

    public boolean create(Payment entity) {
        return false;
    }

    public Payment update(Payment entity) {
        return null;
    }
}
