package pawforyou.pawforyou.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Credentials {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String email;

    @NotNull
    private String password;


    @NotNull
    @OneToOne
    private Client client;


    public Credentials(int id, String email, String password, Client client) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.client = client;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Credentials client(Client client) {
        setClient(client);
        return this;
    }

    public Credentials() {
    }

    public Credentials(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Credentials id(int id) {
        setId(id);
        return this;
    }

    public Credentials email(String email) {
        setEmail(email);
        return this;
    }

    public Credentials password(String password) {
        setPassword(password);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Credentials)) {
            return false;
        }
        Credentials credencials = (Credentials) o;
        return id == credencials.id && Objects.equals(email, credencials.email) && Objects.equals(password, credencials.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }

}
