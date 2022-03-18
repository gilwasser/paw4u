package pawforyou.pawforyou.models;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @NotNull
    private String token;

    @NotNull
    @ManyToOne
    private Client client;

    @NotNull
    private Date expirationDate;

    public Session(int id, String token, Client client, Date date) {
        this.id = id;
        this.token = token;
        this.client = client;
        this.expirationDate = date;
    }

    public Date getEpirationDate() {
        return this.expirationDate;
    }


    public void setExpirationDate(Date date) {
        this.expirationDate = date;
    }

    public Session expirationDate(Date date) {
        setExpirationDate(date);
        return this;
    }

    public Session() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Session id(int id) {
        setId(id);
        return this;
    }

    public Session token(String token) {
        setToken(token);
        return this;
    }

    public Session client(Client client) {
        setClient(client);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Session)) {
            return false;
        }
        Session session = (Session) o;
        return id == session.id && Objects.equals(token, session.token) && Objects.equals(client, session.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token, client);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", token='" + getToken() + "'" +
            ", client='" + getClient() + "'" +
            "}";
    }
    
}
