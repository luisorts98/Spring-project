package es.javaschool.train.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterControl {

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/trains")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("trains");
        return modelAndView;
    }
}

