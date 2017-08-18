import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Ars on 18.08.2017.
 */
public class SmallServerSocket {
    public static void main(String[] args) {
        Socket s=null;
        PrintStream ps=null;
        try { //sends string to client
            ServerSocket server = new ServerSocket(8030);
            s = server.accept();//waiting for connection
            ps = new PrintStream(s.getOutputStream());
            //put string "privet" into buffer
            ps.println("привет!");
            //send bufer`s contain to client
            ps.flush();
        } catch (IOException e) {
            System.err.println("Ошибка соединения потока: "+e);
        }finally {
            if (ps != null) {
                ps.close();
            }
            if (s != null) {
                try { //end of connection
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
