package at.itkollegimst.Springboot.tutorial.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*@Component //added to the Spring Container - if we need the class, spring give it to us (Injection)*/
/*@Controller //is also a Component*/
@RestController //particular Controller (responsebody - API)
public class HelloController {

    @Value("${welcome.message}")
    private String welcomeMessage;

    //@RequestMapping(value = "/", method = RequestMethod.GET)
    @GetMapping("/")
    public String helloWorld() {
        return welcomeMessage;
    }
}