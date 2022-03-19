package pawforyou.pawforyou.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private AuthService authService;
    @Autowired
    private CartService cartService;

    @GetMapping("/")
    public String greeting(Model model, @CookieValue(name = "token", defaultValue = "") String token) {
        Client client =  authService.getClient(token);
        model.addAttribute("client", client);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "home";
    }

    @GetMapping("/category/{id}")
    public String categoryAll(
        @PathVariable int id, Model model,
         HttpServletRequest request,
            @CookieValue(name = "token", defaultValue = "") String token,
             @RequestParam(name="sort", defaultValue = "insertionDate") String sortProperty,
             @RequestParam(name="diraction", defaultValue = "descending") String sortDiraction ) {
        
        List<Product> products = productService.getProductsByCategory(id, sortDiraction, sortProperty );
        Session session = authService.getSession(token);
        Client client = null;
        double sum = 0;

        if (session != null) {
            List<ProductInCart> cartProudcts = cartService.getProductList(session.getClient());
            model.addAttribute("cartProducts", cartProudcts);
            client = session.getClient();
            for (ProductInCart product : cartProudcts) {
                Product p = product.getProduct();
                if(product.getProduct().isInSale()){
                    sum += p.getPrice() > p.getSalePrice()? p.getSalePrice(): p.getPrice();
                }
            }
        }

        model.addAttribute("client", client)
                .addAttribute("cartSum",  Math.ceil(sum * 100) / 100)
                .addAttribute("category", id)
                .addAttribute("categories", categoryService.getAllCategories())
                .addAttribute("products", products);
        return "category";
    }

    @GetMapping("/in-sale")
    public String inSale(Model model) {
        List<Product> products = productService.getProductsInSale();
        model.addAttribute("categories", categoryService.getAllCategories())
                .addAttribute("products", products);
        return "category";
    }
}
