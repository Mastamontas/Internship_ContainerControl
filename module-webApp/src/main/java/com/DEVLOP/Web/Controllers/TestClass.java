package com.DEVLOP.Web.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestClass {
    @GetMapping("/test")
    public String testEndpoint() {
        return "Controller is working!";
    }
}
