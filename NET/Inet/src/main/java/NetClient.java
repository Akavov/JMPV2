import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Ars on 19.08.2017.
 */
public class NetClient {
    public static void main(String[] args) {
        Socket socket=null;
        BufferedReader br=null;
        try {
            //set connection with server
            socket = new Socket(InetAddress.getLocalHost(), 8071);
            //or Socket socket=new Socket("ИМЯ_СЕРВЕРА",8071);
            PrintStream ps = new PrintStream(socket.getOutputStream());
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            for (int i = 1; i < 10; i++) {
                ps.println("PING");
                System.out.println(br.readLine());
                Thread.sleep(1_000);
            }
        } catch (UnknownHostException e) {
            //if can`t connect with server
            System.err.println("адрес недоступенм " + e);
        } catch (IOException e) {
            System.err.println("ошибка I/O потока" + e);
        } catch (InterruptedException e) {
            System.err.println("ошибка потока выполнения "+e);
        }finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
