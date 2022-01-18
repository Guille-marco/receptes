/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.receptes.controller;

import daw.receptes.APIrequests.APIRequests;
import daw.receptes.functions.functions_Recipe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import daw.receptes.models.Product;
import daw.receptes.models.Input_Recipe;
import daw.receptes.models.Output_Recipe;
import daw.receptes.models.Recipe;
import daw.receptes.models.RecipeListContainer;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author eloi
 */

@Controller
public class RecipeController {
    
    private String SUCCESS = "success";
    
    @GetMapping("/novaRecepta")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new Input_Recipe());
        return "novaRecepta";
    }
    
    @PostMapping("/novaRecepta")
    public String recipeSubmit(@ModelAttribute Input_Recipe recipe, Model model, HttpServletRequest request) throws IOException {
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
        
        //Agafem el username i el token de la cookie del client
        String username = null;
        String token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = URLDecoder.decode(cookie.getValue(), "UTF-8");
                }
                else if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                }
            }
        }
        //System.out.println(token);
        //System.out.println(username);
        
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
        //System.out.println(JSONBody);
        String endpoint = "/newRecipe";
        
        //Fem la petició a l’API; ens respon amb el token
        String apiResponse = APIRequests.newRequest(JSONBody, token, endpoint);
        //System.out.println(apiResponse);
        
        //Per capturar dades de la resposta, hem de passar l’String a JSONObject
        JSONObject myResponse = new JSONObject(apiResponse);
                
        //Verifiquem l’estat de la resposta
        //Mostrem una View diferent en funció de la resposta
        if (myResponse.get("status").equals(SUCCESS)) {
            return "usuarihome";
        } else {
            return "recipe_failed";
        }

    }
    
    @GetMapping("/recipe_failed")
    public String recipe_failed(Model model) {
        return "recipe_failed";
    }
    
    @GetMapping("/receptes")
    public String recipes(@ModelAttribute RecipeListContainer recipes, @ModelAttribute String titol, Model model) {
        model.addAttribute("recipes", recipes);
        model.addAttribute("titol", titol);
            
        return "receptes";
    }
    
    @GetMapping("/recepta")
    public String recipe(@ModelAttribute Recipe recipe, Model model) {
        model.addAttribute("recipe", recipe);
            
        return "recepta";
    }
    
    @GetMapping("/receptes/{category}")
    public String recipesByCategory(@PathVariable String category, Model model, HttpServletRequest request) throws IOException {
                
        
        //Agafem el username i el token de la cookie del client
        String username = null;
        String token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = URLDecoder.decode(cookie.getValue(), "UTF-8");
                }
                else if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                }
            }
        }

        JSONObject user = new JSONObject().put("user", username);
        String JSONBody = user.toString();
        String endpoint = "/recipesList";
        
        //Fem la petició a l’API
        String apiResponse = APIRequests.newRequest(JSONBody, token, endpoint);
        
        //Tractem la resposta
        ArrayList <Output_Recipe> receptes = functions_Recipe.getRecipesList(apiResponse);
        
        //Filtrem les receptes per categoria
        String categoria = category;
        ArrayList <Output_Recipe> receptesFiltrades = functions_Recipe.filteredRecipes(receptes, categoria);
        
        //Afegim les receptes a la vista
        RecipeListContainer recipesList = new RecipeListContainer();
        recipesList.setRecipes(receptesFiltrades);
        model.addAttribute("recipes", recipesList);
        
        //Afegim el títol de la vista de receptes
        String titol = "Receptes per categoria: " + category;
        model.addAttribute("titol", titol);
        
        return "receptes";
    }
    
    
    @GetMapping("/recepta/{name}")
    public String getRecipe(@PathVariable String name, Model model, HttpServletRequest request) throws IOException {
                
        
        //Agafem el username i el token de la cookie del client
        String token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                }
            }
        }
        String endpoint = "/getRecipe";
        JSONObject recipe = new JSONObject();
        recipe.put("name", name);
        String JSONBody = recipe.toString();
        
        //Fem la petició a l’API
        String apiResponse = APIRequests.newRequest(JSONBody, token, endpoint);
        
        //Tractem la resposta
        Output_Recipe recepta = functions_Recipe.getRecipe(apiResponse);
                    
        //Afegim la recepta a la vista
        model.addAttribute("recipe", recepta);
        
        return "recepta";
    }
    
    
    @PostMapping("/recipesByName")
    public String recipeByName(@ModelAttribute Input_Recipe recipe, Model model, HttpServletRequest request) throws IOException {
                 
        //Agafem el username i el token de la cookie del client
        String username = null;
        String token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = URLDecoder.decode(cookie.getValue(), "UTF-8");
                }
                else if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                }
            }
        }

        JSONObject user = new JSONObject().put("user", username);
        String JSONBody = user.toString();
        
        String endpoint = "/recipesList";
        
        //Fem la petició a l’API
        String apiResponse = APIRequests.newRequest(JSONBody, token, endpoint);
        
        //Tractem la resposta
        ArrayList <Output_Recipe> receptes = functions_Recipe.getRecipesList(apiResponse);
        
        //Busquem les receptes que continguin el nom buscat
        String nomRecepta = recipe.getName();
        ArrayList <Output_Recipe> recipesByName = functions_Recipe.recipesByName(receptes, nomRecepta);
        
        //Afegim les receptes a la vista
        RecipeListContainer recipesList = new RecipeListContainer();
        recipesList.setRecipes(recipesByName);
        model.addAttribute("recipes", recipesList);
        
        //Afegim el títol de la vista de receptes
        String titol = "Receptes filtrades pel nom: " + nomRecepta;
        model.addAttribute("titol", titol);
        
        return "receptes";

    }
    
    @PostMapping("/recipesByAuthor")
    public String recipeByAuthor(@ModelAttribute Input_Recipe recipe, Model model, HttpServletRequest request) throws IOException {
                 
        //Agafem el username i el token de la cookie del client
        String username = null;
        String token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = URLDecoder.decode(cookie.getValue(), "UTF-8");
                }
                else if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                }
            }
        }
        //Request per obtenir detalls de l’usuari
        JSONObject user = new JSONObject();
        user.put("user", username);     
        String JSONBody = user.toString();
        
        //Fem la petició de llistat receptes
        String endpoint = "/recipesList";
        String apiResponse = APIRequests.newRequest(JSONBody, token, endpoint);
        
        //Tractem la resposta
        ArrayList <Output_Recipe> receptes = functions_Recipe.getRecipesList(apiResponse);
        
        //Busquem les receptes que continguin el nom buscat
        String author = recipe.getUser();
        ArrayList <Output_Recipe> recipesByAuthor = functions_Recipe.recipesByAuthor(receptes, author);
        
        //Afegim les receptes a la vista
        RecipeListContainer recipesList = new RecipeListContainer();
        recipesList.setRecipes(recipesByAuthor);
        model.addAttribute("recipes", recipesList);
        
        //Afegim el títol de la vista de receptes
        String titol = "Receptes filtrades per l’autor: " + author;
        model.addAttribute("titol", titol);
        
        return "receptes";

    }
    
    
    @PostMapping("/recipesByIngredient")
    public String recipeByIngredient(@ModelAttribute Input_Recipe recipe, Model model, HttpServletRequest request) throws IOException {
                 
        //Agafem el username i el token de la cookie del client
        String username = null;
        String token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = URLDecoder.decode(cookie.getValue(), "UTF-8");
                }
                else if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                }
            }
        }

        JSONObject user = new JSONObject().put("user", username);
        String JSONBody = user.toString();
        
        String endpoint = "/recipesList";
        
        //Fem la petició a l’API
        String apiResponse = APIRequests.newRequest(JSONBody, token, endpoint);
        
        //Tractem la resposta
        ArrayList <Output_Recipe> receptes = functions_Recipe.getRecipesList(apiResponse);
        
        //Busquem les receptes que continguin el nom buscat
        String ingredient = recipe.getIngredients();
        ArrayList <Output_Recipe> recipesByIngredient = functions_Recipe.recipesByProduct(receptes, ingredient);
        
        //Afegim les receptes a la vista
        RecipeListContainer recipesList = new RecipeListContainer();
        recipesList.setRecipes(recipesByIngredient);
        model.addAttribute("recipes", recipesList);
        
        //Afegim el títol de la vista de receptes
        String titol = "Receptes filtrades per l’ingredient: " + ingredient;
        model.addAttribute("titol", titol);
        
        return "receptes";

    }
    
    @GetMapping("/myRecipes")
    public String myRecipes(Model model, HttpServletRequest request) throws IOException {
                 
        //Agafem el username i el token de la cookie del client
        String username = null;
        String token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = URLDecoder.decode(cookie.getValue(), "UTF-8");
                }
                else if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                }
            }
        }
        //Request per obtenir detalls de l’usuari
        JSONObject user = new JSONObject().put("user", username);
        String JSONBody = user.toString();
        
        //Fem la petició de llistat receptes
        String endpoint = "/recipesList";
        String apiResponse = APIRequests.newRequest(JSONBody, token, endpoint);
        
        //Tractem la resposta
        ArrayList <Output_Recipe> receptes = functions_Recipe.getRecipesList(apiResponse);
        
        //Busquem les receptes de l’usuari
        ArrayList <Output_Recipe> myRecipes = functions_Recipe.recipesByAuthor(receptes, username);
        
        //Afegim les receptes a la vista
        RecipeListContainer recipesList = new RecipeListContainer();
        recipesList.setRecipes(myRecipes);
        model.addAttribute("recipes", recipesList);
        
        return "myRecipes";

    }
    
    @PostMapping("/deleteRecipe")
    public String deleteRecipe(@ModelAttribute Output_Recipe recipe, Model model, HttpServletRequest request) throws IOException {
        model.addAttribute("recipe", recipe);
        
        //Agafem el username i el token de la cookie del client
        String username = null;
        String token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = URLDecoder.decode(cookie.getValue(), "UTF-8");
                }
                else if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                }
            }
        }
        
        //Creem el JSONObject amb la recepta a esborrar
        JSONObject deletedRecipe = new JSONObject();
        deletedRecipe.put("name", recipe.getName());
        deletedRecipe.put("user", username);
        
        String JSONBody = deletedRecipe.toString();
        String endpoint = "/deleteRecipe";
    
        //Fem la petició
        String apiResponse = APIRequests.newRequest(JSONBody, token, endpoint);
        JSONObject myResponse = new JSONObject(apiResponse);
        
        if (myResponse.get("status").equals(SUCCESS)) {
            return "myRecipes";
        } else {
            return "result2";
        }
    }
}
