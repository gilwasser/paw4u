package pawforyou.pawforyou.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


import java.util.Date;
import java.util.Objects;

/**
 * This is the entity model of product
 */
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String name;
    private Date insertionDate;

    @ManyToOne
    private Category category;

    @NotNull
    private double price;
    @NotNull
    private String picture;
    private boolean inSale = true;

    private double salePrice;
    @NotNull
    private int stock;


    public Product() {
    }

    public Product(int id, String name, Date insertionDate, Category category, double price, String picture, boolean inSale, double salePrice, int stock) {
        this.id = id;
        this.name = name;
        this.insertionDate = insertionDate;
        this.category = category;
        this.price = price;
        this.picture = picture;
        this.inSale = inSale;
        this.salePrice = salePrice;
        this.stock = stock;
    }

    public boolean getInSale() {
        return this.inSale;
    }


    public double getSalePrice() {
        return this.salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Product id(int id) {
        setId(id);
        return this;
    }

    public Product name(String name) {
        setName(name);
        return this;
    }

    public Product insertionDate(Date insertionDate) {
        setInsertionDate(insertionDate);
        return this;
    }

    public Product category(Category category) {
        setCategory(category);
        return this;
    }

    public Product price(double price) {
        setPrice(price);
        return this;
    }

    public Product picture(String picture) {
        setPicture(picture);
        return this;
    }

    public Product inSale(boolean inSale) {
        setInSale(inSale);
        return this;
    }

    public Product salePrice(double salePrice) {
        setSalePrice(salePrice);
        return this;
    }

    public Product stock(int stock) {
        setStock(stock);
        return this;
    }

    public Product(ProductDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.insertionDate = dto.getInsertionDate();
        this.price = dto.getPrice();
        this.picture = dto.getPicture();
        this.inSale = dto.isInSale();
        this.stock = dto.getStock();
        this.salePrice = dto.getSalePrice();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Product)) {
            return false;
        }
        Product product = (Product) o;
        return id == product.id && Objects.equals(name, product.name) && Objects.equals(insertionDate, product.insertionDate) && Objects.equals(category, product.category) && price == product.price && Objects.equals(picture, product.picture) && inSale == product.inSale && salePrice == product.salePrice && stock == product.stock;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, insertionDate, category, price, picture, inSale, salePrice, stock);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", insertionDate='" + getInsertionDate() + "'" +
            ", category='" + getCategory() + "'" +
            ", price='" + getPrice() + "'" +
            ", picture='" + getPicture() + "'" +
            ", inSale='" + isInSale() + "'" +
            ", salePrice='" + getSalePrice() + "'" +
            ", stock='" + getStock() + "'" +
            "}";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isInSale() {
        return inSale;
    }

    public void setInSale(boolean inSale) {
        this.inSale = inSale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getInsertionDate() {
        return insertionDate;
    }

    public void setInsertionDate(Date insertionDate) {
        this.insertionDate = insertionDate;
    }
}
