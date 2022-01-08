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
public class Usuari {
    
    private String user;
    private String pwd;
    private String token;
    
    public Usuari(String user, String pwd) {
        this.user = user;
        this.pwd = pwd;
    }
    
    public Usuari() {

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
