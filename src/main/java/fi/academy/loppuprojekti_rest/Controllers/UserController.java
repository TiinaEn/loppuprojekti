package fi.academy.loppuprojekti_rest.Controllers;

import fi.academy.loppuprojekti_rest.Entities.User;
import fi.academy.loppuprojekti_rest.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserRepo userRepo;

    public BCryptPasswordEncoder pe;


    @RequestMapping("/login")
    public String login(org.springframework.security.core.Authentication authentication, Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("auth", authentication);
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "frontpage";
    }


    @GetMapping("/registration")
    public String registration(org.springframework.security.core.Authentication authentication, Model model) {
        model.addAttribute("auth", authentication);
        User u = new User();
        u.setActive(1);
        model.addAttribute("newUser", u);
        return "registration2";
    }

    @PostMapping("/registration2")
    public String rekisteroi(User u, org.springframework.security.core.Authentication authentication, Model model) {
        String pw = u.getPassword();
        u.setPassword(pe.encode(pw));
        userRepo.save(u);
        model.addAttribute("auth", authentication);
        return "login";
    }
}
