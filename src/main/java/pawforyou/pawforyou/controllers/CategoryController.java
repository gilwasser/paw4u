package pawforyou.pawforyou.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pawforyou.pawforyou.models.Category;
import pawforyou.pawforyou.models.Client;
import pawforyou.pawforyou.models.Product;
import pawforyou.pawforyou.models.ProductInCart;
import pawforyou.pawforyou.models.Session;
import pawforyou.pawforyou.services.AuthService;
import pawforyou.pawforyou.services.CartService;
import pawforyou.pawforyou.services.CategoryService;
import pawforyou.pawforyou.services.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class CategoryController {
    @Autowired private CategoryService categoryService;
    @Autowired private ProductService productService;
    @Autowired private AuthService authService;
    @Autowired private CartService cartService;
    @GetMapping("/")
    public String greeting(Model model,
                           HttpServletResponse response ,@CookieValue(name = "token", defaultValue = "") String token) {
        model.addAttribute("name", getName(token));
        
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "home";
    }

    private String getName(String token) {
        Session session = authService.getSession(token);
        if(session == null) {
            return "";
        }
        Client client = session.getClient();
        return client.getName() + ' ' + client.getLastName();
    }

    @GetMapping("/category/{id}")
    public String categoryAll(@PathVariable int id, Model model,HttpServletRequest request,
    @CookieValue(name = "token", defaultValue = "") String token) {
        List<Product> products = productService.getProductsByCategory(id);
        Session session = authService.getSession(token);
        double sum = 0;

        if(session != null) {
            List<ProductInCart> cartProudcts = cartService.getProductList(session.getClient());
            model.addAttribute("cartProducts", cartProudcts);
            for(ProductInCart product : cartProudcts) {
                sum += product.getProduct().getPrice();
            }
        }
        model.addAttribute("cartSum", sum);

        model.addAttribute("category",   id);
        model.addAttribute("name",   getName(token));
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
