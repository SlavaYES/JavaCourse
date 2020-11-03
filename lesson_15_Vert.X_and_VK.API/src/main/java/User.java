public class User {
    public Integer id;
    public boolean alreadyWas;

    public User(Integer id, boolean alreadyWas) {
        this.id = id;
        this.alreadyWas = alreadyWas;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setAlreadyWas(boolean alreadyWas) {
        this.alreadyWas = alreadyWas;
    }

    public Integer getId() {
        return this.id;
    }

    public boolean getAlreadyWas() {
        return this.alreadyWas;
    }
}
