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
public class UserDetails extends Usuari {
    
    private String email;
    private String role;
    private String status;

    public UserDetails (String user, String pwd, String email, String role, String status) {
        super(user, pwd);
        this.email = email;
        this.role = role;
        this.status = status;
    }
    
    public UserDetails () {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
