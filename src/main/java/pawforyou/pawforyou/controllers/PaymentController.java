package pawforyou.pawforyou.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pawforyou.pawforyou.models.Client;
import pawforyou.pawforyou.models.PaymentForm;
import pawforyou.pawforyou.models.ProductInCart;
import pawforyou.pawforyou.models.Session;
import pawforyou.pawforyou.services.AuthService;
import pawforyou.pawforyou.services.CartService;
import pawforyou.pawforyou.services.CategoryService;
import pawforyou.pawforyou.services.PaymentService;

@Controller()
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private AuthService authService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CartService cartService;
    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public String getPaymentPage(@CookieValue(name = "token", defaultValue = "") String token, Model model) {
        Session session = authService.getSession(token);
        double sum = 0;
        if (session == null) {
            return "redirect:/";
        }

        List<ProductInCart> cartProudcts = cartService.getProductList(session.getClient());
        for (ProductInCart product : cartProudcts) {
            sum += product.getProduct().getPrice();
        }

        model.addAttribute("payment", new PaymentForm())
                .addAttribute("sum", sum)
                .addAttribute("client", authService.getClient(token))
                .addAttribute("categories", categoryService.getAllCategories());

        return "payment";
    }

    @PostMapping
    public String pay(@CookieValue(name = "token", defaultValue = "") String token,
            @ModelAttribute PaymentForm paymentForm, Model model) {
        Session session = authService.getSession(token);
        if (session == null) {
            return "redirect:/";
        }

        Client client = session.getClient();
        paymentService.order(paymentForm, client);
        model.addAttribute("payment", paymentForm);
        return "payed";
    }
}
