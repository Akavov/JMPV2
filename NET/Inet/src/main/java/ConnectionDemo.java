import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Ars on 18.08.2017.
 */
public class ConnectionDemo {
    public static void main(String[] args) {
        String urlName = "http://www.oracle.com";
        int timeout=10_000_000;
        URL url;
        try {
            url = new URL(urlName);
            final URLConnection connection = url.openConnection();
            //set timeout for connection
            connection.setConnectTimeout(timeout);
            System.out.println(urlName + " content type: " + connection.getContentType());
            //continiue taking information from connection
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
