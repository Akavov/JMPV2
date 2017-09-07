import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created by Ars on 07.09.2017.
 */
@XmlRootElement
public class Students {
    @XmlElement(name = "student")
    private ArrayList<Student> list = new ArrayList<Student>();

    public Students() {
        super();
    }

    public boolean addList(Student st) {
        return list.add(st);
    }

    public void setList(ArrayList<Student> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Students{" +
                "list=" + list +
                '}';
    }
}
