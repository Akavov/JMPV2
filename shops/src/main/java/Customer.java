import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Ars on 30.10.2017.
 */
@Entity
@Table(name = "customers")

public class Customer {

    private long id;
    private String login;
    private String password;
    public Customer(){}

}
