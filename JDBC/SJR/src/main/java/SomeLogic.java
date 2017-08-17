import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Ars on 17.08.2017.
 */
public class SomeLogic {
    public void doLogic(int id) throws SQLException {
        //1.creating-taking connection
        Connection conn=ConnectionPool.getConnection();
        //2.open transaction
        conn.setAutoCommit(false);
        //3.inizialization of necessary DAO objects
        AbonentByLogicDAO abonentByLogicDAO = new AbonentByLogicDAO(conn);
        PaymentDAO paymentDAO = new PaymentDAO(conn);
        //4.doing requests
        abonentByLogicDAO.findAll();
        paymentDAO.findEntityById(id);
        //5.closing transaction
        conn.commit();
        //6.closing-returning of connection
        ConnectionPool.close(conn);
    }
}
