import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Ars on 16.10.2017.
 */
public class Serv extends HttpServlet {
    PrintWriter pw;
    List<String> ls;
    DBC dbc;
    public void doGet(HttpServletRequest req, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
       dbc=new DBC();
        try {
            pw= response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw.println("<B>NRFFFFF</B>");
        pw.println("**********");
        ls=dbc.list;
       for(int i=0;i<ls.size();i++) {
           pw.println(ls.get(i));
       }
    }

}


