package es.javaschool.train.Controller;

import es.javaschool.train.Entity.Schedule;
import es.javaschool.train.Entity.Station;
import es.javaschool.train.Service.Impl.StationServiceImpl;
import es.javaschool.train.Service.Impl.ScheduleServiceImpl;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
public class RegisterControl {
    @Autowired
    private StationServiceImpl stationService;

    @Autowired
    private ScheduleServiceImpl scheduleService;
    @GetMapping("/login")
    public String login(Model model, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            List<String> userRoles = authorities.stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            model.addAttribute("userRoles", userRoles);
        }

        if (redirectAttributes.getFlashAttributes().containsKey("registrationSuccess")) {
            model.addAttribute("registrationSuccess", true);
        }
        return "login";
    }


    @GetMapping("/")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Station> allStations = stationService.consultStations();
        List<Schedule> schedules = scheduleService.consultSchedules();
        model.addAttribute("allStations", allStations);
        model.addAttribute("schedules", schedules);
        if (authentication != null && authentication.isAuthenticated()) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            List<String> userRoles = authorities.stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
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

