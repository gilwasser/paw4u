package pawforyou.pawforyou.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pawforyou.pawforyou.models.Client;
import pawforyou.pawforyou.models.Session;
import pawforyou.pawforyou.services.AuthService;
import pawforyou.pawforyou.services.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private AuthService authService;

    @Autowired CartService cartService;
    @GetMapping("/{productId}")
    public String addProductToCart(@PathVariable("productId") int productId,
     @RequestParam(name = "category") String category,
     @CookieValue(name = "token", defaultValue = "") String token ) {
        Session session = authService.getSession(token);
        if(session == null) {
            return "redirect:/auth";
        }
        Client client = session.getClient();
        cartService.addProduct(productId, client);
        return "redirect:/category/" + category.toString();
    }


    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") int id, @CookieValue(name = "token", defaultValue = "") String token,
    @RequestParam(name = "category") String category) {
        Session session = authService.getSession(token);
        if(session == null) {
            return "redirect:/auth";
        }
        cartService.deleteById(id);
        return "redirect:/category/" + category.toString();
    }
}
