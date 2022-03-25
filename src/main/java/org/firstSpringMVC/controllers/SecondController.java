package org.firstSpringMVC.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class SecondController {

    @GetMapping("/exit")
    public String exit(){
        return "second/exit";
    }
}
