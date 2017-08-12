import com.mysql.jdbc.Driver;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by Ars on 12.08.2017.
 */
public class SimpleJDBCRunner {
    public static void main(String[] args) throws SQLException {

        String url="jdbc:mysql://localhost:3306/testphones";
        Properties prop=new Properties();
        prop.put("user","root");
        prop.put("password","root");
        prop.put("autoReconnect","true");
        prop.put("characterEncoding","UTF-8");
        prop.put("useUnicode","true");

        Connection connection=null;
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());

        try {// 1 block
            connection=DriverManager.getConnection(url,prop);
            Statement st=null;
            try{ //2 block
                st= (Statement) connection.createStatement();
                ResultSet rs=null;
                try{//3 block
                    rs=st.executeQuery("SELECT * FROM phonebook");
                    ArrayList<Abonent> lst=new ArrayList<Abonent>();
                    while(rs.next()){
                        int id=rs.getInt(1);
                        int phone=rs.getInt(3);
                        String name=rs.getString(2);
                        lst.add(new Abonent(id,phone,name));
                    }
                    if(lst.size()>0){
                        System.out.println(lst);
                    } else {
                        System.out.println("Not found");
                    }
                } finally { //for 3 block
                        /*
                        *close ResultSet if it was open
                        * or error was while reading data from him
                         */
                    if(rs!=null){//if ResultSet was created
                        rs.close();
                    } else{
                        System.err.println("Mistake while reading DB");
                    }
                }

            } finally { // for 2 block
                    /*
                    *close statement, if it was opened
                    * or
                    * there was mistake when Statement was creating
                     */
                if(st!=null){
                    st.close();
                } else {
                    System.err.println("Statement was not created");
                }
            }

        } catch(SQLException e) {//for 1 block
            System.err.println("DB connection error: " + e);
                                /*
                                *Every SQLException error`s output
                                 */
        } finally {
                /*
                *Close connection, if it was opened
                 */
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e){
                    System.err.println("Connection close error: "+e);
                }
            }
        }
    }
}
