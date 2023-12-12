package es.javaschool.train.Controller;

import es.javaschool.train.Service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import es.javaschool.train.Entity.dto.AdminRegi;
import es.javaschool.train.Entity.Admin;
import org.springframework.ui.Model;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/register")
public class RegisterAdminControl {

    private AdminService adminService;
    @GetMapping("/login")
    public String login(Model model, HttpSession session) {
        if (session.getAttribute("registrationSuccess") != null) {
            model.addAttribute("registrationSuccess", true);
            session.removeAttribute("registrationSuccess");
        }
        return "login";
    }

    public RegisterAdminControl(AdminService adminService) {
        this.adminService = adminService;
    }

    @ModelAttribute("admin")
    public AdminRegi adminRegi() {
        return new AdminRegi();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping
    public String registerAdmin(@ModelAttribute("admin") AdminRegi adminRegi, RedirectAttributes redirectAttributes, Model model) {
        if (adminService.emailExists(adminRegi.getEmail())) {
            model.addAttribute("registrationError", "Email already exists. Please choose a different email.");
            return "register";
        }

        System.out.println("Fecha de nacimiento: " + adminRegi.getDateOfBirth());

        Admin savedAdmin = adminService.save(adminRegi);
        redirectAttributes.addFlashAttribute("registrationSuccess", true);


        return "redirect:/login";
    }
}
