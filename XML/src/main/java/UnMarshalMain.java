import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by Ars on 07.09.2017.
 */
public class UnMarshalMain {
    public static void main(String[] args) {
        try {
            JAXBContext jc = JAXBContext.newInstance(Students.class);
            Unmarshaller u = jc.createUnmarshaller();
            FileReader reader = new FileReader("src/main/resources/studs_marsh.xml");
            Students students = (Students) u.unmarshal(reader);
            System.out.println(students);
        } catch (JAXBException e) {

            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
