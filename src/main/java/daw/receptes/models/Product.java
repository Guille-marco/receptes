/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.receptes.models;

/**
 *
 * @author feloi
 */
public class Product {
    
    private String name;
    private String description;
    private String quantity;
    private String picture;
    private String status;
    
    public Product(String name, String description, String quantity, String picture, String status) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.picture = picture;
        this.status = status;
    }
    
    public Product() {
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
