/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.receptes.APIrequests;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.io.IOUtils;




/**
 *
 * @author eloi
 */
public class APIRequests {
    
    static final String API_URL = "http://141.94.205.198:8080/daw_API";
    
    public static String loginRegisterRequest (String JSONBody, String endpoint) throws IOException{
        
        String response="";
        
        try {
            URL url = new URL(API_URL + endpoint);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            //Passem el JSONBody
            OutputStream os = conn.getOutputStream();
            os.write(JSONBody.getBytes("UTF-8"));
            os.close();
            
            //Llegim la resposta
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = IOUtils.toString(in, "UTF-8");
            in.close();
            conn.disconnect();
    
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return response;
    }
    
    
    public static String newRequest (String JSONBody, String token, String endpoint) throws IOException{
        
        String response="";
        
        try {
            URL url = new URL(API_URL + endpoint);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Authorization", "Bearer " + token);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            //Passem el JSONBody
            OutputStream os = conn.getOutputStream();
            os.write(JSONBody.getBytes("UTF-8"));
            os.close();
            
            //Llegim la resposta
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = IOUtils.toString(in, "UTF-8");
            in.close();
            conn.disconnect();
    
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return response;
    
    }
    
    public static String userDetailsRequest (String JSONBody, String token) throws IOException{
        
        String response="";
        
        try {
            URL url = new URL(API_URL + "/userDetails");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Authorization", "Bearer " + token);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            //Passem el JSONBody
            OutputStream os = conn.getOutputStream();
            os.write(JSONBody.getBytes("UTF-8"));
            os.close();
            
            //Llegim la resposta
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = IOUtils.toString(in, "UTF-8");
            in.close();
            conn.disconnect();
    
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return response;
    
    }
     
}
