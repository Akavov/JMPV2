import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by Ars on 08.10.2017.
 */
public class ContactModel extends AbstractTableModel {
    //list of headers for columns in the table
    private static final String[] headers = {"ID", "ИМЯ", "ФАМИЛИЯ", "EMAIL", "ТЕЛЕФОН"};
    //list of contacts, that will be map in the table
    private final List<Contact> contacts;

    public ContactModel(List<Contact> contacts) {
        this.contacts=contacts;
    }


    public int getRowCount() {
        return contacts.size();
    }

    public int getColumnCount() {
        return headers.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Contact contact = contacts.get(rowIndex);
        switch (columnIndex) {
            case 0: return contact.getContactId().toString();
            case 1:return contact.getFirstName();
            case 2: return  contact.getLastName();
            case 3: return contact.getEmail();
            default: return  contact.getPhone();
        }
    }
}
