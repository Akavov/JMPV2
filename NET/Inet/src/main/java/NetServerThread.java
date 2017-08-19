import sun.plugin.viewer.IExplorerPluginObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Ars on 19.08.2017.
 */
public class NetServerThread {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8071);
            System.out.println("initialized");
            while(true){
                //waiting for client
                Socket socket=server.accept();
                System.out.println(socket.getInetAddress().getHostName()+" connected");
                /*
                creating thread for changing data with connected client
                 */
                ServerThread thread = new ServerThread(socket);
                //start thread
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class ServerThread extends Thread {
    private PrintStream os; //transfer
    private BufferedReader is; //admission
    private InetAddress addr;//client`s adress
    public ServerThread (Socket s) throws IOException {
        os = new PrintStream(s.getOutputStream());
        is = new BufferedReader(new InputStreamReader(s.getInputStream()));
        addr=s.getInetAddress();
    }
    public void run(){
        int i=0;
        String str;
        try {
            while ((str = is.readLine()) != null) {
                if ("PING".equals(str)) {
                    os.println("PONG" + ++i);
                }
                System.out.println("PING-PONG" + i + " with " + addr.getHostName());
            }
        } catch (IOException e) {
            //if client is not answering, connection will be interrupted
            System.err.println("Disconnet");
        } finally {
            disconnect();
        }
        }

    public void disconnect(){
        try {
            if (os != null) {
                os.close();
            }
            if (is != null) {
                is.close();
            }
            System.out.println(addr.getHostName() + " disconnecting");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.interrupt();
        }
    }
}
