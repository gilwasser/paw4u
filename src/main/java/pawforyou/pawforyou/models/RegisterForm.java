package pawforyou.pawforyou.models;

import java.util.Objects;

public class RegisterForm {
    private String email;
    private String password;
    private String name;
    private String lastName;
    private String phone;
    private String address;

    public RegisterForm() {
    }

    public RegisterForm(String email, String password, String name, String lastName, String phone, String address) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
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

    public RegisterForm email(String email) {
        setEmail(email);
        return this;
    }

    public RegisterForm password(String password) {
        setPassword(password);
        return this;
    }

    public RegisterForm name(String name) {
        setName(name);
        return this;
    }

    public RegisterForm lastName(String lastName) {
        setLastName(lastName);
        return this;
    }

    public RegisterForm phone(String phone) {
        setPhone(phone);
        return this;
    }

    public RegisterForm address(String address) {
        setAddress(address);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RegisterForm)) {
            return false;
        }
        RegisterForm registerForm = (RegisterForm) o;
        return Objects.equals(email, registerForm.email) && Objects.equals(password, registerForm.password) && Objects.equals(name, registerForm.name) && Objects.equals(lastName, registerForm.lastName) && Objects.equals(phone, registerForm.phone) && Objects.equals(address, registerForm.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, name, lastName, phone, address);
    }

    @Override
    public String toString() {
        return "{" +
            " email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", name='" + getName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", phone='" + getPhone() + "'" +
            ", address='" + getAddress() + "'" +
            "}";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
