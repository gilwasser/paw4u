package pawforyou.pawforyou.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pawforyou.pawforyou.models.Client;
import pawforyou.pawforyou.models.ProductInCart;

public interface ProductInCartRepository extends CrudRepository<ProductInCart, Integer> {
    List<ProductInCart> findByClient(Client client);
}
