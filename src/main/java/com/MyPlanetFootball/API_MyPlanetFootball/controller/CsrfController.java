package com.MyPlanetFootball.API_MyPlanetFootball.controller;


import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CsrfController {
    @GetMapping("/csrf-token")
    public String csrfToken(CsrfToken token) {
        return token.getToken();
    }
}
