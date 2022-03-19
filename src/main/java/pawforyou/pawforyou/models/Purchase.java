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
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @ManyToOne
    private Client client;
    
    @NotNull
    private String name;

    @NotNull
    private String lastName;

    @NotNull

    public Purchase(int id, Client client, String name, String lastName, String country, String zip, String address, Date date, State state) {
        this.id = id;
        this.client = client;
        this.name = name;
        this.lastName = lastName;
        this.country = country;
        this.zip = zip;
        this.address = address;
        this.date = date;
        this.state = state;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return this.zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Purchase name(String name) {
        setName(name);
        return this;
    }

    public Purchase lastName(String lastName) {
        setLastName(lastName);
        return this;
    }

    public Purchase country(String country) {
        setCountry(country);
        return this;
    }

    public Purchase zip(String zip) {
        setZip(zip);
        return this;
    }
    private String country;

    @NotNull
    private String zip;

    @NotNull
    private String address;

    @NotNull
    private Date date;

    @NotNull
    private State state = State.PROCCESS;


    public Purchase() {
    }

    public Purchase(int id, Client client, String address, Date date, State state) {
        this.id = id;
        this.client = client;
        this.address = address;
        this.date = date;
        this.state = state;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Purchase id(int id) {
        setId(id);
        return this;
    }

    public Purchase client(Client client) {
        setClient(client);
        return this;
    }

    public Purchase address(String address) {
        setAddress(address);
        return this;
    }

    public Purchase date(Date date) {
        setDate(date);
        return this;
    }

    public Purchase state(State state) {
        setState(state);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Purchase)) {
            return false;
        }
        Purchase purchase = (Purchase) o;
        return id == purchase.id && Objects.equals(client, purchase.client) && Objects.equals(address, purchase.address) && Objects.equals(date, purchase.date) && Objects.equals(state, purchase.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, address, date, state);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", client='" + getClient() + "'" +
            ", address='" + getAddress() + "'" +
            ", date='" + getDate() + "'" +
            ", state='" + getState() + "'" +
            "}";
    }

}
