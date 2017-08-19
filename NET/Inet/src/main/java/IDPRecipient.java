import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;

/**
 * Created by Ars on 19.08.2017.
 */
public class IDPRecipient {
    public static void main(String[] args) {
        File file = new File("AC/DC.mp3");
        System.out.println("Прием данных...");
        //receiving file
        acceptFile(file, 8033, 1024);
    }

    private static void acceptFile(File file, int port, int pacSize) {
        byte data[] = new byte[pacSize];
        DatagramPacket pac=new DatagramPacket(data,data.length);
        try (FileOutputStream os = new FileOutputStream(file)) {
            DatagramSocket s = new DatagramSocket(port);
            /*
            set time of waiting: if in 60 sec there are no one package, receiving will end
             */
            s.setSoTimeout(60_000);
            while (true) {
                s.receive(pac);
                os.write(data);
                os.flush();
            }
        } catch (SocketTimeoutException e) {
            //if waiting time is out
            System.err.println("Истекло время ожидания, прием данных закончен");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

