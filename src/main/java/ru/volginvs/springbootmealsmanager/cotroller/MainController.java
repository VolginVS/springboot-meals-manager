package ru.volginvs.springbootmealsmanager.cotroller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Map;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(Map<String, Object> model) {
        return "index";
    }   
    @GetMapping("/greeting")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }   
}
