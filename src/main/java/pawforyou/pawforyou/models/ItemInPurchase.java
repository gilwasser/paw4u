package pawforyou.pawforyou.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class ItemInPurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @ManyToOne
    private Product product;

    @NotNull
    @ManyToOne
    private Purchase purchase;

    public ItemInPurchase() {
    }

    public ItemInPurchase(int id, Product product, Purchase purchase) {
        this.id = id;
        this.product = product;
        this.purchase = purchase;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ItemInPurchase product(Product product) {
        setProduct(product);
        return this;
    }

    public ItemInPurchase(int id, Purchase purchase) {
        this.id = id;
        this.purchase = purchase;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Purchase getPurchase() {
        return this.purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public ItemInPurchase id(int id) {
        setId(id);
        return this;
    }

    public ItemInPurchase purchase(Purchase purchase) {
        setPurchase(purchase);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ItemInPurchase)) {
            return false;
        }
        ItemInPurchase itemInPurchase = (ItemInPurchase) o;
        return id == itemInPurchase.id && Objects.equals(purchase, itemInPurchase.purchase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, purchase);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", purchase='" + getPurchase() + "'" +
            "}";
    }
    
}
