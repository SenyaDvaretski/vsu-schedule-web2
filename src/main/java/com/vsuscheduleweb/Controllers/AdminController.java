package com.vsuscheduleweb.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class AdminController {


    @RequestMapping(value = "vsuAdmin/**", method = RequestMethod.GET)
    public String getAdminPage(){
        return "index";
    }



}