package at.itkollegimst.Springboot.tutorial.controller.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThyMainController {

    @GetMapping("/index")
    public String homePage() {
        return "index"; //link zu meiner index.html
    }
}