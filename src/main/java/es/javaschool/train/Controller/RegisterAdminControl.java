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
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/register")
public class RegisterAdminControl {

    private AdminService adminService;
    @GetMapping("/login")
    public String login(Model model, @RequestParam(name = "success", required = false) boolean success) {
        // ... Código existente ...

        // Pasa el mensaje de registro exitoso al modelo
        model.addAttribute("registrationSuccess", success);

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
    public String registerAdmin(@ModelAttribute("admin") AdminRegi adminRegi, Model model) {
        // Lógica de registro aquí

        // Guardar el administrador
        Admin savedAdmin = adminService.save(adminRegi);

        // Agregar mensaje al modelo
        model.addAttribute("registrationSuccess", true);

        // Redirigir a la página de inicio de sesión
        return "redirect:/login?success=true";
    }
}
