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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("tstamp", LocalDateTime.now());
        return "index";
    }
}