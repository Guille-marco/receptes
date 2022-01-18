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
public class UserListContainer {
    
    private ArrayList <UserDetails> users;
    
    public ArrayList <UserDetails> getUsers() {
        return users;
    }
    
    public void setUsers(ArrayList <UserDetails> users) {
        this.users = users;
    }
}
