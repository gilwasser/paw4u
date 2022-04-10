package pawforyou.pawforyou.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pawforyou.pawforyou.models.Client;
import pawforyou.pawforyou.models.Product;
import pawforyou.pawforyou.models.ProductInCart;
import pawforyou.pawforyou.services.AuthService;
import pawforyou.pawforyou.services.CartService;
import pawforyou.pawforyou.services.CategoryService;
import pawforyou.pawforyou.services.ProductService;

@Controller
@RequestMapping("/discount")
public class DiscountController {
    @Autowired
    private AuthService authService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    /*
    * get discout page
    */
    @GetMapping
    public String getDiscountPage(@CookieValue(name = "token", defaultValue = "") String token,
            @RequestParam(name = "sort", defaultValue = "salePrice") String prop,
            @RequestParam(name = "diraction", defaultValue = "ascending") String direction,
            Model model) {
        Client client = authService.getClient(token);
        double sum = 0;

        List<ProductInCart> products = cartService.getProductList(client);
        List<Product> discountedProducts = productService.getDiscountedProducts(direction, prop);

        
        for (ProductInCart product : products) {
            Product p = product.getProduct();
            if (p.isInSale()) {
                sum += p.getPrice() > p.getSalePrice() ? p.getSalePrice() : p.getPrice();
            }
        }

        model
                .addAttribute("cartProducts", products)
                .addAttribute("client", client)
                .addAttribute("sum", Math.ceil(sum * 100) / 100)
                .addAttribute("category", "discount")
                .addAttribute("categories", categoryService.getAllCategories())
                .addAttribute("products", discountedProducts);

        return "discount";
    }
}
