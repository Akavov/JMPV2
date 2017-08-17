import com.mysql.jdbc.Statement;

/**
 * Created by Ars on 17.08.2017.
 */
public abstract class AbstractByClassDAO {
    protected WrapperConnector connector;
    //here adding,deleting,searching,replacing methods
    //here closing connect & statement
    public void close(){
        connector.closeConnection();
    }
    protected void closeStatement(Statement statement){
        connector.closeStatement(statement);
    }
}
