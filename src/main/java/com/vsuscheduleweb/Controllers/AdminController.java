package com.vsuscheduleweb.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping(value = "/vsuAdmin")
public class AdminController {


    @GetMapping()
    public String getAdminPage(){
        return "index";
    }



}
