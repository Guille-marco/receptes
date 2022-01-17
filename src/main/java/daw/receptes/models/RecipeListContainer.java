/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.receptes.models;

import java.util.ArrayList;

/**
 *
 * @author eloi
 */
public class RecipeListContainer {
    
    private ArrayList <Output_Recipe> recipes;
    
    public ArrayList <Output_Recipe> getRecipes() {
        return recipes;
    }
    
    public void setRecipes(ArrayList <Output_Recipe> recipes) {
        this.recipes = recipes;
    }
}
