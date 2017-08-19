import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;

/**
 * Created by Ars on 19.08.2017.
 */
public class UDPSender {
    public static void main(String[] args) {
        String fileName = "audio/THUNDERSTRUCK.mp3";
        try (FileInputStream fr = new FileInputStream(new File(fileName))) {
            byte[] data = new byte[1024];
            DatagramSocket dSocket = new DatagramSocket();
            InetAddress address = InetAddress.getLocalHost();
            DatagramPacket packet;
            while (fr.read(data) != -1) {
                //creating data package
                packet = new DatagramPacket(data, data.length, address, 8033);
                dSocket.send(packet);
            }
            System.out.println("Файл успешно отправлен");
        } catch (UnknownHostException e) {
            //incorrect address of receiver
            e.printStackTrace();
        } catch (SocketException e) {
            //while sending data some mistakes happened
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
