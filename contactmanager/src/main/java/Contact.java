/**
 * Created by Ars on 07.10.2017.
 */
public class Contact {

    //Contact`s id
    private Long contactId;
    //Name
    private String firstName;
    //Lastname
    private String lastName;
    //telephone
    private String phone;
    //email
    private String email;

    public Contact(){}

    public Contact(String firstName, String lastName, String phone, String email) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.phone=phone;
        this.email=email;
    }

    public Contact (Long contactId,String firstName, String lastName, String phone, String email) {
        this.contactId=contactId;
        this.firstName=firstName;
        this.lastName=lastName;
        this.phone=phone;
        this.email=email;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactId=" + contactId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
