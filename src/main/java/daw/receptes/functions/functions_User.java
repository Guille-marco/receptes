/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.receptes.functions;

import daw.receptes.models.Output_Recipe;
import daw.receptes.models.UserDetails;
import java.util.ArrayList;
import org.json.JSONArray;
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
    
    public static ArrayList <UserDetails> getUsersList (String apiResponse) {
        //Creem la llista que guardarà les receptes
        ArrayList<UserDetails> list = new ArrayList<>();
        //Convertim l’string de resposta en un JSONObject
        JSONObject jsnobject = new JSONObject(apiResponse);
        //I agafem l’array amb les receptes de dins el JSONObject
        JSONArray jsonArray = jsnobject.getJSONArray("users");
        
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                list.add((UserDetails) jsonArray.get(i));
            }
        }

        return list;

    }
    
}
