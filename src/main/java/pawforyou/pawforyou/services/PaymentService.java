package pawforyou.pawforyou.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pawforyou.pawforyou.models.Client;
import pawforyou.pawforyou.models.ItemInPurchase;
import pawforyou.pawforyou.models.PaymentForm;
import pawforyou.pawforyou.models.Product;
import pawforyou.pawforyou.models.ProductInCart;
import pawforyou.pawforyou.models.Purchase;
import pawforyou.pawforyou.repositories.ItemInPurchaseRepository;
import pawforyou.pawforyou.repositories.PurchaseRepository;

@Service
public class PaymentService {
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private ItemInPurchaseRepository itemInPurchaseRepository;

    public List<ProductInCart> order(PaymentForm paymentForm, Client client) {
        List<ProductInCart> products = cartService.getProductList(client);
        List<Product> filteredProducts = new ArrayList<>();
        List<ProductInCart> notInStock = new ArrayList<>();

        Purchase purchase = new Purchase();
        purchase.date(new Date())
                .client(client)
                .address(paymentForm.getAddress())
                .name(paymentForm.getName())
                .lastName(paymentForm.getLastName())
                .country(paymentForm.getState())
                .zip(paymentForm.getZip());

        for (ProductInCart p : products) {
            Product product = p.getProduct();
            if (product.isInSale()) {
                int stock = product.getStock();
                if (stock == 0) {
                    notInStock.add(p);
                }
                product.setStock(stock - 1);
                filteredProducts.add(product);
            }
        }

        if (notInStock.size() > 0) {
            return notInStock;
        }
        purchase = purchaseRepository.save(purchase);
        for (Product p : filteredProducts) {
            productService.addProduct(p);
            ItemInPurchase item = new ItemInPurchase();
            item.product(p).purchase(purchase);
            itemInPurchaseRepository.save(item);
        }

        cartService.empty(client);
        return null;
    }


    public List<Purchase> getOrders(){
        List<Purchase> purchases = new ArrayList<>();
        this.purchaseRepository.findAll().forEach(purchases:: add);

        return purchases;
    }
}
