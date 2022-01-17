/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.receptes.controller;

import daw.receptes.APIrequests.APIRequests;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import daw.receptes.models.Product;
import daw.receptes.models.Recipe;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author eloi
 */

@Controller
public class RecipeController {
    
    @GetMapping("/novaRecepta")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "novarecepta";
    }
    
    @PostMapping("/novaRecepta")
    public String recipeSubmit(@ModelAttribute Recipe recipe, Model model, HttpServletRequest request) throws IOException {
        model.addAttribute("recipe", recipe);
        
        //Passem els ingredients en un JSONArray
        String [] ingredients = recipe.getIngredients().split(",");
        String [] quantity = recipe.getQuantity().split(",");

        JSONArray productsArray = new JSONArray();
        for (int i=0; i<ingredients.length; i++) {
            productsArray.put(new JSONObject().put("name", ingredients[i]).put("quantity", quantity[i]));
        }
        
        //Passem els steps a un Array
        String[] stepsArray = new String[] {recipe.getStep1(),recipe.getStep2(),recipe.getStep3(),recipe.getStep4(),recipe.getStep5()};
        
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
        
        //Creem el JSONObject amb la recepta introduida
        JSONObject newRecipe = new JSONObject();
        newRecipe.put("name", recipe.getName());
        newRecipe.put("description", recipe.getDescription());
        newRecipe.put("steps", stepsArray);
        newRecipe.put("products", productsArray);
        newRecipe.put("category", recipe.getCategory());
        newRecipe.put("picture", recipe.getPicture());
        newRecipe.put("user", username);
        
        String JSONBody = newRecipe.toString();
        System.out.println(JSONBody);
        
        //Capturem el token de la cookie
        String token = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                }
            }
        }
        System.out.println(token);
        //System.out.println(username);
        
        //Fem la petició a l’API; ens respon amb el token
        //String apiResponse = APIRequests.newRecipeRequest(JSONBody, token);
        //System.out.println(apiResponse);
        
        //Per capturar dades de la resposta, hem de passar l’String a JSONObject
        //JSONObject myResponse = new JSONObject(apiResponse);
                
        //Verifiquem l’estat de la resposta
        
        
        return "result";
    }
}
