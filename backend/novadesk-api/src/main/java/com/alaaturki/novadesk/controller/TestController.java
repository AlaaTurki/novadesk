package com.alaaturki.novadesk.controller;


import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/test")
public class TestController {


    @GetMapping("/hello")
    public String hello(){

        return "JWT authentication works!";

    }

}