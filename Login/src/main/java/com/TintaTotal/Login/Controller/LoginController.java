package com.TintaTotal.Login.Controller;
import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";  // Nombre del archivo HTML para la página de login
    }
    @GetMapping("/correct")
    public String correct(Model model) {
        return "correct"; // Asegúrate de que 'correct.html' esté en el directorio templates
    }
}