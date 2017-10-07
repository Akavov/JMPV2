import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Ars on 08.10.2017.
 */
public class ContactSimpleDAO implements ContactDAO {

    private final List<Contact> contacts = new ArrayList<Contact>();

    public Long addContact(Contact contact) {
        Long id=generateContactId();
        contact.setContactId(id);
        contacts.add(contact);
        return id;
    }

    public void updateContact(Contact contact) {
        Contact oldContact = getContact(contact.getContactId());
        if (oldContact != null) {
            oldContact.setFirstName(contact.getFirstName());
            oldContact.setLastName(contact.getLastName());
            oldContact.setPhone(contact.getPhone());
            oldContact.setEmail(contact.getEmail());

        }
    }

    public void deleteContact(Long contactId) {
        for(Iterator<Contact> it=contacts.iterator(); it.hasNext();) {
            Contact cnt=it.next();

        if (cnt.getContactId().equals(contactId)) {
            it.remove();
        }
        }
    }

    public Contact getContact(Long contactId) {
        for (Contact contact : contacts) {
            if (contact.getContactId().equals(contactId)) {
                return contact;
            }
        }
        return null;
    }

    public List<Contact> findContacts() {
        return contacts;
    }

    private Long generateContactId(){
        Long contactId = Math.round(Math.random() * 1000 + System.currentTimeMillis());
        while (getContact(contactId) != null) {
            contactId = Math.round(Math.random() * 1000 + System.currentTimeMillis());
        }
        return  contactId;
    }

}
