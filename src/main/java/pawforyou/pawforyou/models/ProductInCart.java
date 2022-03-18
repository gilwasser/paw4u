package pawforyou.pawforyou.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class ProductInCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @ManyToOne
    private Product product;

    @NotNull
    @ManyToOne
    private Client client;


    public ProductInCart() {
    }

    public ProductInCart(int id, Product product, Client client) {
        this.id = id;
        this.product = product;
        this.client = client;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ProductInCart id(int id) {
        setId(id);
        return this;
    }

    public ProductInCart product(Product product) {
        setProduct(product);
        return this;
    }

    public ProductInCart client(Client client) {
        setClient(client);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ProductInCart)) {
            return false;
        }
        ProductInCart productInCart = (ProductInCart) o;
        return id == productInCart.id && Objects.equals(product, productInCart.product) && Objects.equals(client, productInCart.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, client);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", product='" + getProduct() + "'" +
            ", client='" + getClient() + "'" +
            "}";
    }

}
