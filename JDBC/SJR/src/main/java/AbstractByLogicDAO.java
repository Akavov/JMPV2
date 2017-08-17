import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Ars on 17.08.2017.
 */
public abstract class AbstractByLogicDAO <T extends Entity> {

    protected Connection connection;
    public AbstractByLogicDAO(Connection connection){
        this.connection=connection;
    }
    public abstract List<T> findAll();

    public abstract T findEntityById(int id);

    public abstract boolean delete(int id);

    public abstract boolean delete(T entity);

    public abstract boolean create(T entity);

    public abstract T update(T entity);

    public void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            //log about impossibility of closing the Statement
        }
    }
}
