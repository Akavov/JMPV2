import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Ars on 07.09.2017.
 */
public class MarshalMain {
    public static void main(String[] args) {
        try {
            JAXBContext context = JAXBContext.newInstance(Students.class);
            Marshaller m=context.createMarshaller();
            Students st=new Students() {//anonymous class
                {
                    //add first student
                    Student.Address address=new Student.Address("BLR","Minsk","Skoriny 4");
                    Student s=new Student("gochette","Klimenko","mmf",2095306,address);
                    this.addList(s);
                    //add second student
                    address = new Student.Address("BLR", "Polotesk", "Simeona P.25");
                    s = new Student("ivette", "Teran", "mmf", 345386, address);
                    this.addList(s);
                }
            };
            m.marshal(st, new FileOutputStream("src/main/resources/studs_marsh.xml"));
            m.marshal(st,System.out);//copy to console
            System.out.println("XML-file has been created");
        } catch (FileNotFoundException e) {
            System.out.println("XML can`t be created: "+e);;
        } catch (JAXBException e) {
            System.out.println("JAXB-context - mistake "+e);
        }
    }
}
