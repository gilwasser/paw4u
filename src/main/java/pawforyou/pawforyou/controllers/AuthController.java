package pawforyou.pawforyou.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pawforyou.pawforyou.models.Client;
import pawforyou.pawforyou.models.LoginForm;
import pawforyou.pawforyou.models.RegisterForm;
import pawforyou.pawforyou.models.Session;
import pawforyou.pawforyou.services.AuthService;

/*
 * Controller for login logout register
 */
@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    /*
     * get login page
     */
    @GetMapping
    public String getLoginPage(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    /*
     * Make login and create session
     */
    @PostMapping("/login")
    public String login(@ModelAttribute LoginForm loginForm, Model model, HttpServletResponse response) {
        model.addAttribute("loginForm", loginForm);
        Session session = authService.login(loginForm);
        if (session == null) {
            return "redirect:/auth";
        }
        Cookie cookie = new Cookie("token", session.getToken().toString());
        cookie.setMaxAge(60 * 60 * 24 * 14);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }

    /*
     * will create client
     */
    @PostMapping("/register")
    public String register(@ModelAttribute RegisterForm registerForm, Model model) {
        model.addAttribute("register", registerForm);
        Client client = authService.register(registerForm);
        if (client == null) {
            return "redirect:/auth/register";
        }
        return "redirect:/auth";
    }

    /*
    * logut the client
    */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("token")) {
                authService.logout(cookie.getValue());
                cookie = new Cookie("token", "");
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
        return "redirect:/";
    }
}
