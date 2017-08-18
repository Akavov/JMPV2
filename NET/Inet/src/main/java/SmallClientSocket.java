import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Ars on 18.08.2017.
 */
public class SmallClientSocket {
    public static void main(String[] args) {
        Socket socket=null;
        BufferedReader br=null;
        try {//receiving string by client
            socket = new Socket("ИМЯ_СЕРВЕРА", 8030); /*ИМЯ_СЕРВЕРА - computer, where socket is stand */
             br= new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = br.readLine();
            System.out.println(message);

        } catch (IOException e) {
            System.err.println("ошибка: "+e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket!= null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
