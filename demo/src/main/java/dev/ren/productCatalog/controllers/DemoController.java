package dev.ren.productCatalog.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
//articles to refer : https://docs.spring.io/spring-framework/reference/overview.html
//https://spring.io/guides/gs/rest-service/
@RestController
public class DemoController {
    @GetMapping("/hi")
    public String sayHello(String msg){
        return "Hello everyone!";
    }
}
