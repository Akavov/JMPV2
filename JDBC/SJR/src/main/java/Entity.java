import java.io.Serializable;

/**
 * Created by Ars on 16.08.2017.
 */
public class Entity implements Serializable, Cloneable {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Entity(){
    }

    public Entity(int id){
        this.id=id;
    }


}
