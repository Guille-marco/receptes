/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.receptes.functions;

import daw.receptes.models.Output_Recipe;
import daw.receptes.models.UserDetails;
import org.json.JSONObject;

/**
 *
 * @author eloi
 */
public class functions_User {
    
    public static UserDetails getUser (String apiResponse) {
        //Convertim l’string de resposta en un JSONObject
        JSONObject jsnobject = new JSONObject(apiResponse);
        //Creem un usuari on guardar les dades
        UserDetails user = new UserDetails();
        //Guardem la info dins la recepta
        user.setUser((String) jsnobject.get("user"));
        user.setPwd((String) jsnobject.get("pwd"));
        user.setEmail((String) jsnobject.get("email"));
        user.setRole((String) jsnobject.get("role"));
        user.setStatus((String) jsnobject.get("status"));
        
        return user;
    }
    
}
