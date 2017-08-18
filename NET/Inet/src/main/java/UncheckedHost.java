import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Ars on 18.08.2017.
 */
public class UncheckedHost {
    public static void main(String[] args) {
        //setting ip adresses in massive
        byte[] ip={(byte)217, (byte)21, (byte)43, (byte)3};
        try {
            InetAddress addr = InetAddress.getByAddress("University", ip);
            System.out.println(addr.getHostName() + "-> соединение:" + addr.isReachable(1000));
        } catch (UnknownHostException e) {
            System.err.println("адрес недоступен "+e);
        }catch (IOException e){
            System.err.println("ошибка потока "+e);
        }

    }
}
