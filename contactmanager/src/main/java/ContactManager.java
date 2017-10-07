import java.util.List;

/**
 * Created by Ars on 07.10.2017.
 */
public class ContactManager {

    private ContactDAO dao;

    public ContactManager(){
        dao=ContactDAOFactory.getContactDAO();
    }

    //add new contact
    public Long addContact(Contact contact) {
        return dao.addContact(contact);
    }

    //edit contact
    public void updateContact(Contact contact) {
        dao.updateContact(contact);
    }

    //delete contact by id
    public void deleteContact(Long contactId) {
        dao.deleteContact(contactId);
    }

    //get 1 contact
    public Contact getContact(Long contactId) {
        return dao.getContact(contactId);
    }

    //get list of contacts
    public List<Contact> findContacts(){
        return dao.findContacts();
    }

}
