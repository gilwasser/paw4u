package pawforyou.pawforyou.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pawforyou.pawforyou.models.Category;
import pawforyou.pawforyou.models.Product;
import pawforyou.pawforyou.repositories.ProductRepository;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired private ProductRepository productRepository;
    @Autowired private CategoryService categoryService;

    public List<Product> getAllProducts(int page, int size, String sortBy, Boolean ascending) {
        Sort sort = ascending?  Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return productRepository.getProductsPage(pageable);
    }

    public List<Product> getProductsByCategory(int id){
        Optional<Category> category = categoryService.getCategoryById(id);
        if(category.isPresent()){
            return  productRepository.findByCategory(category.get());
        }
        return new ArrayList<>();
    }

    public List<Product> getProductsInSale() {
        return productRepository.getInSaleProducts();
    }



    public Optional<Product> getProduct(int id){
        return productRepository.findById(id);
    }

    public Product addProduct(Product product) {
        product.setInsertionDate(new Date());
        return productRepository.save(product);
    }


    public void deleteProduct(int id){
        productRepository.deleteById(id);
    }
}
