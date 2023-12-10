package es.javaschool.train.Controller;

import es.javaschool.train.Service.AdminService;
import es.javaschool.train.Service.Impl.AdminServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import es.javaschool.train.Entity.dto.AdminRegi;
import es.javaschool.train.Entity.Admin;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/register")
public class RegisterAdminControl {

    private AdminService adminService;
// @RequestParam(name = "success", required = false) boolean success
    @GetMapping("/login")
    public String login(Model model, HttpSession session) {
        if (session.getAttribute("registrationSuccess") != null) {
            // Pasa el mensaje de registro exitoso al modelo
            model.addAttribute("registrationSuccess", true);

            // Elimina el atributo de la sesión para que no persista
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
            // Agregar un mensaje de error al modelo
            model.addAttribute("registrationError", "Email already exists. Please choose a different email.");
        // Redirigir de nuevo al formulario de registro
        return "register";
    }

    // Lógica de registro aquí
        System.out.println("Fecha de nacimiento: " + adminRegi.getDateOfBirth());

        // Guardar el administrador
        Admin savedAdmin = adminService.save(adminRegi);
        redirectAttributes.addFlashAttribute("registrationSuccess", true);

        // Agregar mensaje al modelo
      //  model.addAttribute("registrationSuccess", true);

        // Redirigir a la página de inicio de sesión
        return "redirect:/login";
    }
}
