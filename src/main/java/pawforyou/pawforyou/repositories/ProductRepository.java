package pawforyou.pawforyou.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import pawforyou.pawforyou.models.Category;
import pawforyou.pawforyou.models.Product;

import java.util.List;

@Service
public interface ProductRepository extends CrudRepository<Product, Integer> {
    @Query("SELECT p FROM Product p")
    List<Product> getProductsPage(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.category = ?1 AND p.inSale = TRUE")
    List<Product> findByCategory(Category category, Sort sort);

    @Query("SELECT p FROM Product p WHERE p.inSale = true")
    List<Product> getInSaleProducts();

    @Query("SELECT p FROM Product p WHERE p.inSale != true")
    List<Product> getDeletedProducts();


    @Query("SELECT p FROM Product p WHERE p.inSale = true AND p.salePrice < p.price AND p.salePrice > 0")
    List<Product> getDiscountedProducts(Sort sort);
}
