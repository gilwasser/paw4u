package pawforyou.pawforyou.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pawforyou.pawforyou.models.Client;
import pawforyou.pawforyou.models.Product;
import pawforyou.pawforyou.models.ProductInCart;
import pawforyou.pawforyou.repositories.ProductInCartRepository;

@Service
public class CartService {
    @Autowired ProductService productService;
    @Autowired ProductInCartRepository productInCartRepository;

    public ProductInCart getProduct(int id){
        return productInCartRepository.findById(id).get();
    }

    public void addProduct(int productId, Client client){
        Optional<Product> product = productService.getProduct(productId);
        if(product.isPresent()) {
            ProductInCart productInCart = new ProductInCart();
            productInCart.client(client).product(product.get());
            productInCartRepository.save(productInCart);
        }
    }

    public List<ProductInCart> getProductList(Client client) {
        List<ProductInCart> products = productInCartRepository.findByClient(client);

        return products;
    }

    public void deleteById(int id){
        productInCartRepository.deleteById(id);
    }
}
