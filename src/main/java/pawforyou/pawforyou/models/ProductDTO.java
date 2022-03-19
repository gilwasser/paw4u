package pawforyou.pawforyou.models;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;


public class ProductDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String name;
    private Date insertionDate;

    private int category;

    @NotNull
    private double price = 0;
    @NotNull
    private String picture;
    private boolean inSale = false;
    @NotNull
    private int stock = 0;

    @NotNull
    private double salePrice = 0;

    public ProductDTO(int id, String name, Date insertionDate, int categoryId, double price, String picture, boolean inSale, int stock, int salePrice) {
        this.id = id;
        this.name = name;
        this.insertionDate = insertionDate;
        this.category = categoryId;
        this.price = price;
        this.picture = picture;
        this.inSale = inSale;
        this.stock = stock;
        this.salePrice = salePrice;
    }

    public double getSalePrice() {
        return this.salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public ProductDTO salePrice(double salePrice) {
        setSalePrice(salePrice);
        return this;
    }


    public ProductDTO() {
    }

    public ProductDTO(int id, String name, Date insertionDate, int categoryId, double price, String picture, boolean inSale, int stock) {
        this.id = id;
        this.name = name;
        this.insertionDate = insertionDate;
        this.category = categoryId;
        this.price = price;
        this.picture = picture;
        this.inSale = inSale;
        this.stock = stock;
    }

    public int getCategoryId() {
        return this.category;
    }

    public void setCategoryId(int categoryId) {
        this.category = categoryId;
    }

    public boolean getInSale() {
        return this.inSale;
    }


    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public ProductDTO id(int id) {
        setId(id);
        return this;
    }

    public ProductDTO name(String name) {
        setName(name);
        return this;
    }

    public ProductDTO insertionDate(Date insertionDate) {
        setInsertionDate(insertionDate);
        return this;
    }

    public ProductDTO categoryId(int categoryId) {
        setCategoryId(categoryId);
        return this;
    }

    public ProductDTO price(double price) {
        setPrice(price);
        return this;
    }

    public ProductDTO picture(String picture) {
        setPicture(picture);
        return this;
    }

    public ProductDTO inSale(boolean inSale) {
        setInSale(inSale);
        return this;
    }

    public ProductDTO stock(int stock) {
        setStock(stock);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ProductDTO)) {
            return false;
        }
        ProductDTO productDTO = (ProductDTO) o;
        return id == productDTO.id && Objects.equals(name, productDTO.name) && Objects.equals(insertionDate, productDTO.insertionDate) && category == productDTO.category && price == productDTO.price && Objects.equals(picture, productDTO.picture) && inSale == productDTO.inSale && stock == productDTO.stock;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, insertionDate, category, price, picture, inSale, stock);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", insertionDate='" + getInsertionDate() + "'" +
            ", categoryId='" + getCategoryId() + "'" +
            ", price='" + getPrice() + "'" +
            ", picture='" + getPicture() + "'" +
            ", inSale='" + isInSale() + "'" +
            ", stock='" + getStock() + "'" +
            "}";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
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
