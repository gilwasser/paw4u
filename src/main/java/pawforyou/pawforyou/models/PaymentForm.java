package pawforyou.pawforyou.models;

import java.sql.Date;
import java.util.Objects;

public class PaymentForm {
    private String name;
    private String lastName;
    private String address;
    private String state;
    private String zip;
    private String cardOwner;
    private String cardNumber;
    private Date expirationDate;
    private String cvv;


    public PaymentForm() {
    }

    public PaymentForm(String name, String lastName, String address, String state, String zip, String cardOwner, String cardNumber, Date expirationDate, String cvv) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.state = state;
        this.zip = zip;
        this.cardOwner = cardOwner;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
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

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return this.zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCardOwner() {
        return this.cardOwner;
    }

    public void setCardOwner(String cardOwner) {
        this.cardOwner = cardOwner;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return this.cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public PaymentForm name(String name) {
        setName(name);
        return this;
    }

    public PaymentForm lastName(String lastName) {
        setLastName(lastName);
        return this;
    }

    public PaymentForm address(String address) {
        setAddress(address);
        return this;
    }

    public PaymentForm state(String state) {
        setState(state);
        return this;
    }

    public PaymentForm zip(String zip) {
        setZip(zip);
        return this;
    }

    public PaymentForm cardOwner(String cardOwner) {
        setCardOwner(cardOwner);
        return this;
    }

    public PaymentForm cardNumber(String cardNumber) {
        setCardNumber(cardNumber);
        return this;
    }

    public PaymentForm expirationDate(Date expirationDate) {
        setExpirationDate(expirationDate);
        return this;
    }

    public PaymentForm cvv(String cvv) {
        setCvv(cvv);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PaymentForm)) {
            return false;
        }
        PaymentForm paymentForm = (PaymentForm) o;
        return Objects.equals(name, paymentForm.name) && Objects.equals(lastName, paymentForm.lastName) && Objects.equals(address, paymentForm.address) && Objects.equals(state, paymentForm.state) && Objects.equals(zip, paymentForm.zip) && Objects.equals(cardOwner, paymentForm.cardOwner) && Objects.equals(cardNumber, paymentForm.cardNumber) && Objects.equals(expirationDate, paymentForm.expirationDate) && Objects.equals(cvv, paymentForm.cvv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, address, state, zip, cardOwner, cardNumber, expirationDate, cvv);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", address='" + getAddress() + "'" +
            ", state='" + getState() + "'" +
            ", zip='" + getZip() + "'" +
            ", cardOwner='" + getCardOwner() + "'" +
            ", cardNumber='" + getCardNumber() + "'" +
            ", expirationDate='" + getExpirationDate() + "'" +
            ", cvv='" + getCvv() + "'" +
            "}";
    }

}
