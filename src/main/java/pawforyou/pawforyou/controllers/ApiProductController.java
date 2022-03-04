package pawforyou.pawforyou.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pawforyou.pawforyou.models.Category;
import pawforyou.pawforyou.models.Product;
import pawforyou.pawforyou.models.ProductDTO;
import pawforyou.pawforyou.services.CategoryService;
import pawforyou.pawforyou.services.ProductService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ApiProductController {
    @Autowired private ProductService productService;
    @Autowired private CategoryService categoryService;


    @PostMapping
    public ResponseEntity<Product> addProduct(@Valid @RequestBody ProductDTO req) {
        Product product = new Product(req);
        Optional<Category> category = categoryService.getCategoryById(req.getCategory());
        if (category.isPresent()) {
            product.setCategory(category.get());
            productService.addProduct(product);
            return ResponseEntity.status(200).body(product);
        }

        return ResponseEntity.status(404).body(null);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                        @RequestParam(name = "size", required = false, defaultValue = "10") int size,
                                        @RequestParam(name = "sortBy", required = false, defaultValue = "insertionDate") String sortBy,
                                        @RequestParam(name = "ascending", required = false, defaultValue = "false") Boolean ascending) {
        if (sortBy.equals("price")  || sortBy.equals("insertionDate")) {
            return ResponseEntity.status(200).body(productService.getAllProducts(page, size, sortBy, ascending));
        }

        return ResponseEntity.status(404).body(null);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }
}
