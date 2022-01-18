/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.receptes.controller;

/**
 *
 * @author guill
 */
import daw.receptes.models.Recipe;
import daw.receptes.models.Usuari;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {

    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }
    
    @RequestMapping("/index.html")
    public String home(Model model) {
        return "index";
    }
    
    @RequestMapping("/index")
    public String inici(Model model) {
        return "index";
    }
    
    @RequestMapping("/contact.html")
    public String contact(Model model) {
        return "contact";
    }
    
    @RequestMapping("/login.html")
    public String login(Model model) {
        model.addAttribute("usuari", new Usuari());
        return "login";
    }
}