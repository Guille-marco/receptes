/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.receptes.models;

/**
 *
 * @author eloi
 */
public class Output_Recipe {
    
    private String name;
    private String description;
    private String steps;
    private String products;
    private String user;
    private String picture;
    private String category;
    private String status;
    
    
    public Output_Recipe(String name, String description, String steps, String products, String user, String category, String picture, String status) {
        this.name = name;
        this.description = description;
        this.steps = steps;
        this.products = products;
        this.user = user;
        this.category = category;
        this.picture = picture;
        this.status = status;
    }
    
    public Output_Recipe() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
