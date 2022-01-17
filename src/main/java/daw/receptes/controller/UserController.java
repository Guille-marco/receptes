/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.receptes.controller;

/**
 *
 * @author eloi
 */
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.json.JSONObject;
import org.json.JSONArray;

import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import daw.receptes.models.Usuari;
import daw.receptes.models.UserDetails;
import daw.receptes.APIrequests.APIRequests;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;



@Controller
public class UserController {
    
    private String SUCCESS = "success";
    
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("usuari", new Usuari());
        return "login";
    }

    
    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute("usuari") Usuari usuari, Model model, HttpServletResponse response) throws IOException {
        model.addAttribute("usuari", new Usuari());

        String JSONBody = new JSONObject(usuari).toString();
        //System.out.println(JSONBody);
        
        //Fem la petició a l’API; ens respon amb el token
        String endpoint = "/login";
        String apiResponse = APIRequests.loginRegisterRequest(JSONBody, endpoint);
        //System.out.println(response);
        
        //Per capturar dades de la resposta, hem de passar l’String a JSONObject
        JSONObject myResponse = new JSONObject(apiResponse);
                
        //Verifiquem l’estat de la resposta
        if (myResponse.get("status").equals(SUCCESS)) {
            //Capturem el token de resposta
            JSONArray myDataResponse = myResponse.getJSONArray("data");
            JSONObject myToken = myDataResponse.getJSONObject(0);
            String token = myToken.getString("token").split(" ")[1];
            System.out.println(token);
            
            //Establim el token de l’usuari
            usuari.setToken(token);
            System.out.println(usuari.getToken());
            System.out.println(usuari.getUser());
            //Guardem el token a una cookie del costat del client
            Cookie tokenCookie = new Cookie("token", token);
            response.addCookie(tokenCookie);
            //També guardem el username al costat client
            Cookie usernameCookie = new Cookie("username", URLEncoder.encode(usuari.getUser(), "UTF-8"));
            response.addCookie(usernameCookie);
          
            //Redireccionem a la pàgina d’inici d’usuari
            return "redirect:/usuarihome";
        } else {
            //El login no és vàlid
            return "login_failed";
        }
      
    }
    
    @GetMapping("/login_failed")
    public String login_failed(Model model) {
        return "login_failed";
    }
    
   
    @GetMapping("/registration")
    public String registrationForm(Model model) {
        model.addAttribute("userDetails", new UserDetails());
        return "registration";
    }
    
    @PostMapping("/registration")
    public String registrationSubmit(@ModelAttribute UserDetails userDetails, Model model) throws IOException {
        userDetails.setRole("0");
        userDetails.setStatus("1");
        model.addAttribute("userDetails", userDetails);
        String JSONBody = new JSONObject(userDetails).toString();
        System.out.println(JSONBody);
        
        //Fem la petició a l’API; ens respon amb SUCCESS
        String endpoint = "/register";
        String response = APIRequests.loginRegisterRequest(JSONBody, endpoint);
        System.out.println(response);
        
        //Per capturar dades de la resposta, hem de passar l’String a JSONObject
        JSONObject myResponse = new JSONObject(response);
        //userDetails.setUser(myResponse.getString("username"));
        System.out.println(myResponse.get("status"));

        //Mostrem una View diferent en funció de la resposta
        if (myResponse.get("status").equals(SUCCESS)) {
            return "result";
        } else {
            return "result2";
        }
        
    }
    
    @GetMapping("/profile")
    public String profile(Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        
        //Agafem el username de la cookie del client
        String username = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = URLDecoder.decode(cookie.getValue(), "UTF-8");
                }
            }
        }

        //Creem el JSONObject amb el nom d’usuari
        JSONObject user = new JSONObject();
        user.put("user", username);
        String JSONBody = user.toString();
        
        //Capturem el token de la cookie
        String token = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                }
            }
        }
        String endpoint = "/userDetails";
        
        //Fem la petició a l’API; ens respon amb el token
        String apiResponse = APIRequests.newRequest(JSONBody, token, endpoint);
        //System.out.println(apiResponse);
        
        //Per capturar dades de la resposta, hem de passar l’String a JSONObject
        JSONObject myResponse = new JSONObject(apiResponse);
        
        //convertim la resposta en l’objecte userDetais
        JSONArray myDataResponse = myResponse.getJSONArray("data");
        
        for (int i = 0; i < myDataResponse.length(); i++) {
            JSONObject explrObject = jsonArray.getJSONObject(i);
        }
        
        //Afegim les dades de l’usuari a la vista
        model.addAttribute("userDetails", new UserDetails());
        

        return "profile";
    }
    
    @PostMapping("/profile")
    public String editProfile(@ModelAttribute UserDetails userDetails, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        userDetails.setRole("0");
        model.addAttribute("userDetails", userDetails);
        String JSONBody = new JSONObject(userDetails).toString();
                      
        //Capturem el token de la cookie
        String token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                }
            }
        }
        String endpoint = "/userDetails";
        //Fem la petició a l’API; ens respon amb el token
        String apiResponse = APIRequests.newRequest(JSONBody, token, endpoint);
        
        //Per capturar dades de la resposta, hem de passar l’String a JSONObject
        JSONObject myResponse = new JSONObject(apiResponse);
        //userDetails.setUser(myResponse.getString("username"));
        //System.out.println(myResponse.get("status"));

        //Mostrem una View diferent en funció de la resposta
        if (myResponse.get("status").equals(SUCCESS)) {
            return "result";
        } else {
            return "result2";
        }
        
    }
    
}