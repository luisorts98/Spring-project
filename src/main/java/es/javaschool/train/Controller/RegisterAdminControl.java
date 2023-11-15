package es.javaschool.train.Controller;

import es.javaschool.train.Service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import es.javaschool.train.Entity.dto.AdminRegi;

@Controller
@RequestMapping("/register")
public class RegisterAdminControl {

    private AdminService adminService;

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
    public String registerAdmin(@ModelAttribute("admin") AdminRegi adminRegi) {
        adminService.save(adminRegi);
        return "redirect:/register?success";
    }
}
