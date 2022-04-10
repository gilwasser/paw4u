package pawforyou.pawforyou.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import pawforyou.pawforyou.models.Category;
import pawforyou.pawforyou.models.Product;
import pawforyou.pawforyou.models.ProductDTO;
import pawforyou.pawforyou.models.Purchase;
import pawforyou.pawforyou.models.Session;
import pawforyou.pawforyou.services.AuthService;
import pawforyou.pawforyou.services.CategoryService;
import pawforyou.pawforyou.services.OrderService;
import pawforyou.pawforyou.services.ProductService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*  Endpoints for managing the shop
    adding updating and deleting products
    changing order status etc...
*/
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AuthService authService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private OrderService orderService;

    /**
     * Endpoint for getting the admin page
     */
    @GetMapping
    public String getAdminPage(@CookieValue(name = "token", defaultValue = "") String token, Model model) {
        if (!isAdmin(token)) {
            return "redirect:/";
        }
        model.addAttribute("products", productService.getProductsInSale());
        model.addAttribute("action", "in-sale");
        return "admin";
    }

    /**
     * Endpoint for getting deleted products page
     */
    @GetMapping("/deleted")
    public String getDeletePage(@CookieValue(name = "token", defaultValue = "") String token, Model model) {
        if (!isAdmin(token)) {
            return "redirect:/";
        }
        model.addAttribute("products", productService.getDeletedInSale());
        model.addAttribute("action", "deleted");

        return "admin";
    }

    /**
     * Endpoint for deleting product
     */
    @DeleteMapping("/product/{id}")
    public String deleteProduct(@CookieValue(name = "token", defaultValue = "") String token,
            @PathVariable("id") int id) {
        if (!isAdmin(token)) {
            return "redirect:/";
        }
        Optional<Product> product = productService.getProduct(id);
        if (!product.isPresent()) {
            return "redirect:/admin";
        }
        productService.addProduct(product.get().inSale(false));
        return "redirect:/admin";
    }

    /**
     * Endpoint for Taking product back to in Sale
     */
    @PostMapping("/product/in-sale/{id}")
    public String moveProductToIsnSale(@CookieValue(name = "token", defaultValue = "") String token,
            @PathVariable("id") int id) {
        if (!isAdmin(token)) {
            return "redirect:/";
        }
        Optional<Product> product = productService.getProduct(id);
        if (!product.isPresent()) {
            return "redirect:/admin";
        }
        productService.addProduct(product.get().inSale(true));
        return "redirect:/admin/deleted";
    }

    /*
     *  Endpoint for adding product
     */
    @PostMapping("/product")
    public String addProduct(@CookieValue(name = "token", defaultValue = "") String token,
            @ModelAttribute ProductDTO productForm, Model model) {
        model.addAttribute("productForm", productForm);
        if (!isAdmin(token)) {
            return "redirect:/";
        }
        Product product = new Product(productForm);
        extracted(product);

        Optional<Category> category = categoryService.getCategoryById(productForm.getCategory());
        if (!category.isPresent()) {
            return "redirect:/";
        }
        product.category(category.get());
        productService.addProduct(product);
        return "redirect:/admin";
    }

    /**
     * Endpint for updating product
     */
    @PutMapping("/product/{id}")
    public String updateProduct(
            @CookieValue(name = "token", defaultValue = "") String token,
            @PathVariable("id") int id, @ModelAttribute ProductDTO productForm,
            @RequestParam(name = "sell") String sell,
            Model model) {

        if (!isAdmin(token)) {
            return "redirect:/";
        }

        Product product = new Product(productForm);
        model.addAttribute("productForm", productForm);
        // if saleprice is not sale put real price
        
        resetSalePriceIfNeeded(product);
        setInSale(sell, product);

        Optional<Category> category = categoryService.getCategoryById(productForm.getCategory());
        if (!category.isPresent()) {
            return "redirect:/";
        }
        product.category(category.get());
        product.id(id);

        productService.addProduct(product);

        // return to last page
        if (sell.equals("true")) {
            return "redirect:/admin";
        } else {
            return "redirect:/admin/deleted";
        }

    }

    private void setInSale(String sell, Product product) {
        if (sell.equals("true")) {
            product.setInSale(true);
        } else {
            product.setInSale(false);
        }

        if(product.getStock() == 0){
            product.inSale(false);
        }
    }


    private void resetSalePriceIfNeeded(Product product) {
        if (product.getSalePrice() <= 0 || product.getSalePrice() > product.getPrice()) {
            product.setSalePrice(product.getPrice());
        }
    }

    /**
     * Get products managing page
     */
    @GetMapping("/product")
    public String getAddProductPage(@CookieValue(name = "token", defaultValue = "") String token,
            Model model) {
        if (!isAdmin(token)) {
            return "redirect:/";
        }
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("path", "");
        model.addAttribute("method", "post");
        model.addAttribute("productForm", new ProductDTO());
        return "productForm";
    }

    /**
     * get product edit form 
     */
    @GetMapping("/product/{id}")
    public String getEditProductPage(@CookieValue(name = "token", defaultValue = "") String token,
            @RequestParam(name = "sell", defaultValue = "true") String sell,
            @PathVariable("id") int id, Model model) {
        if (!isAdmin(token)) {
            return "redirect:/";
        }
        Optional<Product> product = productService.getProduct(id);
        if (!product.isPresent()) {
            return "redirect:/";
        }

        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("path", "/" + product.get().getId());
        model.addAttribute("method", "put");
        model.addAttribute("sell", sell);
        model.addAttribute("productForm", product.get());

        return "productForm";
    }

    /**
     * Method for checking if logged in client is the admin
     */
    private boolean isAdmin(String token) {
        Session session = this.authService.getSession(token);
        if (session == null) {
            return false;
        }
        if (session.getClient().getId() != authService.getAdmin().getId()) {
            return false;
        }
        return true;
    }


    /**
     * Endpint for getting purchases page here admin can manage the order status
     */
    @GetMapping("/purchases")
    public String getPurchasesPage(@CookieValue(name = "token", defaultValue = "") String token,
            Model model) {
        if (!isAdmin(token)) {
            return "redirect:/";
        }
        List<Purchase> orders = this.orderService.getOrders();
        model.addAttribute("orders", orders);
        System.out.println(orders);
        return "purchases-manager";
    }

    /*
     * Endpoint for updating order status
     */
    @PostMapping("/order/state/{id}")
    public String updateOrderState(
            @CookieValue(name = "token", defaultValue = "") String token,
            @PathVariable("id") int id, @RequestParam("state") String state) {
        if (!isAdmin(token)) {
            return "redirect:/";
        }
        this.orderService.updateOrderState(id, state);

        return "redirect:/admin/purchases";
    }

}
