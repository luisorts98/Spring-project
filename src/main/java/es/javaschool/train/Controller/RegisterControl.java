package es.javaschool.train.Controller;

import es.javaschool.train.Entity.Station;
import es.javaschool.train.Service.Impl.StationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.ui.Model;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class RegisterControl {
    @Autowired
    private StationServiceImpl stationService;
    @GetMapping("/login")
    public String login(Model model, RedirectAttributes redirectAttributes) {
        // Obtén la autenticación actual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Verifica si el usuario está autenticado
        if (authentication != null && authentication.isAuthenticated()) {
            // Obtén los roles del usuario autenticado
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            List<String> userRoles = authorities.stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            // Pasa los roles al modelo
            model.addAttribute("userRoles", userRoles);
        }

        // Pasa el mensaje de registro exitoso al modelo
        if (redirectAttributes.getFlashAttributes().containsKey("registrationSuccess")) {
            // Pasa el mensaje de registro exitoso al modelo
            model.addAttribute("registrationSuccess", true);
        }
        return "login";
    }


    @GetMapping("/")
    public String index(Model model) {
        // Obtén la autenticación actual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Station> allStations = stationService.consultStations();
        model.addAttribute("allStations", allStations);
        // Verifica si el usuario está autenticado
        if (authentication != null && authentication.isAuthenticated()) {
            // Obtén los roles del usuario autenticado
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            List<String> userRoles = authorities.stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            // Pasa los roles al modelo
            model.addAttribute("userRoles", userRoles);
        }

        return "index";
    }

    @RequestMapping(value = "/trains")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("trains");
        return modelAndView;
    }
}

