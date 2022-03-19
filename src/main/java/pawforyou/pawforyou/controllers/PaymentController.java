package pawforyou.pawforyou.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pawforyou.pawforyou.models.ProductInCart;
import pawforyou.pawforyou.models.Session;
import pawforyou.pawforyou.services.AuthService;
import pawforyou.pawforyou.services.CartService;
import pawforyou.pawforyou.services.CategoryService;

@Controller()
@RequestMapping("/payment")
public class PaymentController {
    @Autowired private AuthService authService;
    @Autowired private CategoryService categoryService;
    @Autowired private CartService cartService;


    @GetMapping
    public String getPaymentPage(@CookieValue(name = "token", defaultValue = "") String token, Model model) {
        Session session = authService.getSession(token);
        double sum = 0;

        if(session != null) {
            List<ProductInCart> cartProudcts = cartService.getProductList(session.getClient());
            for(ProductInCart product : cartProudcts) {
                sum += product.getProduct().getPrice();
            }
        }

        model.addAttribute("sum", sum);
        model.addAttribute("name",   authService.getName(token));
        model.addAttribute("categories", categoryService.getAllCategories());

        return "payment";
    }
}
