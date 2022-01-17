/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.receptes.functions;

import daw.receptes.models.Output_Recipe;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author feloi
 */
public class functions_Recipe {
    
    public static Output_Recipe getRecipe (String apiResponse) {
        //Convertim l’string de resposta en un JSONObject
        JSONObject jsnobject = new JSONObject(apiResponse);
        //Creem una recpta on guardar les dades
        Output_Recipe recepta = new Output_Recipe();
        //Guardem la info dins la recepta
        recepta.setName((String) jsnobject.get("name"));
        recepta.setDescription((String) jsnobject.get("description"));
        recepta.setSteps((String) jsnobject.get("steps"));
        recepta.setProducts((String) jsnobject.get("ptoducts"));
        recepta.setCategory((String) jsnobject.get("category"));
        recepta.setPicture((String) jsnobject.get("picture"));
        recepta.setUser((String) jsnobject.get("user"));
        recepta.setStatus((String) jsnobject.get("status"));
        
        return recepta;
    }
    
    public static ArrayList <Output_Recipe> getRecipesList (String apiResponse) {
        //Creem la llista que guardarà les receptes
        ArrayList<Output_Recipe> list = new ArrayList<>();
        //Convertim l’string de resposta en un JSONObject
        JSONObject jsnobject = new JSONObject(apiResponse);
        //I agafem l’array amb les receptes de dins el JSONObject
        JSONArray jsonArray = jsnobject.getJSONArray("receptes");
        
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                list.add((Output_Recipe) jsonArray.get(i));
            }
        }

        return list;

    }
    
       
    public static ArrayList <Output_Recipe> filteredRecipes(ArrayList <Output_Recipe> receptes, String categoria) {
        //Creem la llista que guardarà les receptes filtrades
        ArrayList<Output_Recipe> filteredList = new ArrayList<>();
        for (int i = 0; i < receptes.size(); i++) {
            if (receptes.get(i).getCategory().equals(categoria)){
                filteredList.add(receptes.get(i));
            }
        }
        
        return filteredList;
    }
    
    public static ArrayList <Output_Recipe> recipesByAuthor(ArrayList <Output_Recipe> receptes, String author) {
        //Creem la llista que guardarà les receptes filtrades
        ArrayList<Output_Recipe> recipesByAuthorList = new ArrayList<>();
        for (int i = 0; i < receptes.size(); i++) {
            if (receptes.get(i).getUser().contains(author)){
                recipesByAuthorList.add(receptes.get(i));
            }
        }
        
        return recipesByAuthorList;
    }
    
    public static ArrayList <Output_Recipe> recipesByProduct(ArrayList <Output_Recipe> receptes, String product) {
        //Creem la llista que guardarà les receptes filtrades
        ArrayList<Output_Recipe> recipesByProductList = new ArrayList<>();
        for (int i = 0; i < receptes.size(); i++) {
            if (receptes.get(i).getProducts().equals(product)){
                recipesByProductList.add(receptes.get(i));
            }
        }
        
        return recipesByProductList;
    }
    
    public static ArrayList <Output_Recipe> recipesByName(ArrayList <Output_Recipe> receptes, String name) {
        //Creem la llista que guardarà les receptes filtrades
        ArrayList<Output_Recipe> recipesByNameList = new ArrayList<>();
        for (int i = 0; i < receptes.size(); i++) {
            if (receptes.get(i).getName().contains(name)){
                recipesByNameList.add(receptes.get(i));
            }
        }
        
        return recipesByNameList;
    }
}
