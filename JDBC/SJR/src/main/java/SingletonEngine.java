import javax.swing.plaf.nimbus.State;
import java.sql.*;

/**
 * Created by Ars on 17.08.2017.
 */
public class SingletonEngine {

    private Connection connectionTo;
    private  Connection connectionFrom;
    private static SingletonEngine instance=null;

    public synchronized static SingletonEngine getInstance(){
        if(instance==null){
            instance=new SingletonEngine();
            instance.getConnectionTo();
            instance.getConnectionFrom();
        }
        return instance;
    }

    private Connection getConnectionFrom(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connectionFrom= DriverManager.getConnection("jdbc:mysql://localhost:3306/testFrom","root","pass");
            connectionFrom.setAutoCommit(false);

        } catch (SQLException e){
            System.err.println("SQLException: "+e.getMessage()
                    + "SQLState: "+e.getSQLState());
        } catch (ClassNotFoundException ex){
            System.out.println("Driver not found");
        }
        return connectionFrom;
    }

    private Connection getConnectionTo(){
        final String connectToAdress="jdbc:mysql://10.162.4.151:3306/testTo";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connectionTo=DriverManager.getConnection(connectToAdress,"root","pass");
            connectionTo.setAutoCommit(false);

        } catch (SQLException e){
            System.err.println("SQLException: "+e.getMessage()+
            "SQLState: "+e.getSQLState());
        } catch (ClassNotFoundException e){

            System.err.println("Driver not found");

        }
        return connectionTo;
    }

    public void transfer(String summa) throws SQLException {

        Statement stFrom = null;
        Statement stTo = null;

        try {
            int sum = Integer.parseInt(summa);
            if (sum <= 0) {
                throw new NumberFormatException("less or equals zero");
            }
            stFrom = connectionFrom.createStatement();
            stTo = connectionTo.createStatement();
            //transaction of 4 requests
            ResultSet rsFrom = stFrom.executeQuery("SELECT balanc FROM table_from");
            ResultSet rsTo = stTo.executeQuery("SELECT balance FROM table_to");
            int accountFrom = 0;
            while (rsFrom.next()) {
                accountFrom = rsFrom.getInt(1);

            }
            int resultFrom = 0;
            if (accountFrom >= sum) {
                resultFrom = accountFrom - sum;
            } else {
                throw new SQLException("Invalid balance");


            }
            int accountTo = 0;
            while (rsTo.next()) {
                accountTo = rsTo.getInt(1);
            }
            int resultTo = accountTo + sum;
            stFrom.executeUpdate("UPDATE table_to SET balance=" + resultFrom);
            stTo.executeUpdate("UPDATE table_to SET balance=" + resultTo);
            //end of transaction
            connectionFrom.commit();
            connectionTo.commit();
            System.out.println("remaining on :" + resultFrom +
                    " rub");
        } catch (SQLException e) {
            System.err.println("SQLState: " + e.getSQLState() +
                    "Error Message:" + e.getMessage());
            //rollback of transaction if mistake
            connectionFrom.rollback();
            connectionTo.rollback();
        } catch (NumberFormatException e) {
            System.err.println("Invalid summa"+summa);
        } finally {
            if (stFrom != null) {
                try {
                    stFrom.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stTo != null) {
                try {
                    stTo.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

}
