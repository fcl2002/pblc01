package wealthwise.backend.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/")
public class HomeController {

    //localhost:8080/usuario
    @GetMapping
    public String getMethodName() {
        StringBuilder s = new StringBuilder();
        s.append("Backend do Sistema<br>");
        s.append("Spring Boot + JPA + MariaDB<br>");
        s.append("Desenvolvido por: Enzo<br>");
        s.append("Em:out/2024<br>");
        s.append("Universidade Federal de Itajubá<br>");
        return s.toString();
    }
}
