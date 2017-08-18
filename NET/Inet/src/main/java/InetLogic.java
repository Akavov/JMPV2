import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Ars on 18.08.2017.
 */
public class InetLogic {
    public static void main(String[] args) {
        InetAddress currentIP=null;
        InetAddress bsuIP=null;
        try {
            currentIP = InetAddress.getLocalHost();
            //output of local computer`s ip
            System.out.println("Мой IP->" + currentIP.getHostAddress());
            bsuIP = InetAddress.getByName("www.bsu.by");
            //output bsu`s ip
            System.out.println("BSU->" + bsuIP.getHostAddress());
        } catch (UnknownHostException e) {
            //if can`t find IP
            System.err.println("адрес недоступен "+e);
        }
    }
}
