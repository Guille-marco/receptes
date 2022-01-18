/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.receptes.controller;

import daw.receptes.models.Input_Recipe;
import daw.receptes.models.Product;
import daw.receptes.models.Usuari;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author feloi
 */

@Controller
public class AppController {
    
    @GetMapping("/usuarihome")
    public String usuariHome(Model model) {
        model.addAttribute("usuari", new Usuari());
        model.addAttribute("Recipe", new Input_Recipe());
        
        return "usuarihome";
    }
    
    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }
    
}
