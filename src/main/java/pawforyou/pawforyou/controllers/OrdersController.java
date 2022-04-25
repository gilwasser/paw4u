package pawforyou.pawforyou.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pawforyou.pawforyou.models.Client;
import pawforyou.pawforyou.models.Purchase;
import pawforyou.pawforyou.services.AuthService;
import pawforyou.pawforyou.services.CategoryService;
import pawforyou.pawforyou.services.OrderService;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private AuthService authService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CategoryService categoryService;

    /*
    * get orders of logged in client
    */
    @GetMapping()
    public String getOrders(@CookieValue(name = "token", defaultValue = "") String token,
            Model model) {
        Client client = authService.getClient(token);
        if (client == null) {
            return "redirect:/";
        }
        List<Purchase> orders = orderService.getOrderOfClient(client);
        model.addAttribute("orders", orders);
        model.addAttribute("client", client);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "orders";
    }

}
