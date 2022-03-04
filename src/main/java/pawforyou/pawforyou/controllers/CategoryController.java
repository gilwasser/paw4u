package pawforyou.pawforyou.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pawforyou.pawforyou.models.Category;
import pawforyou.pawforyou.models.Product;
import pawforyou.pawforyou.services.CategoryService;
import pawforyou.pawforyou.services.ProductService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class CategoryController {
    @Autowired private CategoryService categoryService;
    @Autowired private ProductService productService;
    @GetMapping("/")
    public String greeting(@RequestParam(name="sort", required=false, defaultValue="date") String name, Model model,
                           HttpServletResponse response) {
        model.addAttribute("name", name);
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "home";
    }

    @GetMapping("/category/{id}")
    public String categoryAll(@PathVariable int id, Model model) {
        List<Product> products = productService.getProductsByCategory(id);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("products", products);
        return "category";
    }

    @GetMapping("/in-sale")
    public String inSale(Model model){
        List<Product> products = productService.getProductsInSale();
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("products", products);
        return "category";
    }
}
