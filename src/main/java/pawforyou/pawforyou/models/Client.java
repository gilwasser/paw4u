package pawforyou.pawforyou.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @NotNull
    private String name;
    
    @NotNull
    private String lastName;

    @NotNull
    private String phone;

    @NotNull
    private String address;


    public Client() {
    }

    public Client(int id, String name, String lastName, String phone, String address) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Client id(int id) {
        setId(id);
        return this;
    }

    public Client name(String name) {
        setName(name);
        return this;
    }

    public Client lastName(String lastName) {
        setLastName(lastName);
        return this;
    }

    public Client phone(String phone) {
        setPhone(phone);
        return this;
    }

    public Client address(String address) {
        setAddress(address);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Client)) {
            return false;
        }
        Client client = (Client) o;
        return id == client.id && Objects.equals(name, client.name) && Objects.equals(lastName, client.lastName) && Objects.equals(phone, client.phone) && Objects.equals(address, client.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, phone, address);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", phone='" + getPhone() + "'" +
            ", address='" + getAddress() + "'" +
            "}";
    }

}
