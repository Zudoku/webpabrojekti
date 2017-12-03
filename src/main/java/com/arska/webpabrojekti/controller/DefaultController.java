package com.arska.webpabrojekti.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Created Dec 3, 2017
 * @author arska
 */
@Controller
public class DefaultController {

    @RequestMapping("*")
    public String handleDefault() {
        return "index";
    }
}
