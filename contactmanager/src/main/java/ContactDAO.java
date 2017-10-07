import java.util.List;

/**
 * Created by Ars on 07.10.2017.
 */
public interface ContactDAO {

    //add new contact
     Long addContact(Contact contact);

    //edit contact
    void updateContact(Contact contact);

    //delete contact by id
    void deleteContact(Long contactId);

    //get contact by id
    Contact getContact(Long contactId);

    //get list of contacts
    List<Contact> findContacts();
}
