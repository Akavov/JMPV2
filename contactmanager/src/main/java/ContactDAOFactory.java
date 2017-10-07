/**
 * Created by Ars on 08.10.2017.
 */
public class ContactDAOFactory {
    public static ContactDAO getContactDAO(){
        return new ContactSimpleDAO();
    }
}
