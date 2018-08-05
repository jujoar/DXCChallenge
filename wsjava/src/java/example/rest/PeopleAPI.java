/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.PUT;

/**
 *
 * @author Juan José
 */

@Path("/PeopleAPI")
public class PeopleAPI {
    
    private String path = "C:\\Users\\Juan José\\Documents\\NetBeansProjects\\wsjava\\MOCK_DATA.json";
    
    
    public BufferedReader openFile(){
        
        
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PeopleAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bufferedReader;
    }
    
    
    @GET
    @Path("/allInfo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allInfo(){

        Gson gson = new Gson();
        
        BufferedReader reader = openFile();
        
        JsonParser parser = new JsonParser();  
        JsonElement rootNode = new JsonObject();
        
        JsonObject Person = new JsonObject();
        
        JsonArray persons = new JsonArray();
        
        
        String line = null;
        
            
        try {
            while( (line = reader.readLine()) != null){
                
                 rootNode = parser.parse(line);

             
                 persons.add(gson.toJsonTree(rootNode));
            }
            
            Person.add("Person", persons);
            
            
        } catch (IOException ex) {
            Logger.getLogger(PeopleAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(persons.size() == 0){
            return Response.noContent().build();
        }
        else{
            return Response.ok(
            Person.toString(),
            MediaType.APPLICATION_JSON).build();
        }
        
    }
    
    
    
    @GET
    @Path("/ID={value}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInfoByID(@PathParam("value") String ID_value) throws FileNotFoundException {
        
        
        BufferedReader reader = openFile();
        JsonParser parser = new JsonParser();  
        JsonElement rootNode = new JsonObject();
        
        String line = null;
        
        try {
            
            while( (line = reader.readLine()) != null){
                rootNode = parser.parse(line);

             
                JsonObject details = rootNode.getAsJsonObject();
                JsonElement idNode = details.get("id"); 

                if (idNode.getAsString().equals(ID_value)){

                    return Response.ok(
                    details.toString(),
                    MediaType.APPLICATION_JSON).build();
                }
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(PeopleAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return Response.noContent().build();
        
    }
    
    @GET
    @Path("/last_name={value}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLastName(@PathParam("value") String last_name_value) throws FileNotFoundException {
        
        Gson gson = new Gson();
        
        BufferedReader reader = openFile();
        JsonParser parser = new JsonParser();  
        JsonElement rootNode = new JsonObject();
        
        JsonObject Person = new JsonObject();
        JsonArray persons = new JsonArray();
        
        String line = null;
        
        try {
            
            while( (line = reader.readLine()) != null){
                rootNode = parser.parse(line);

             
                JsonObject details = rootNode.getAsJsonObject();
                JsonElement lastnameNode = details.get("last_name"); 

                if (lastnameNode.getAsString().regionMatches(0, last_name_value, 0, last_name_value.length())){

                    persons.add(gson.toJsonTree(rootNode));
                    
                }
                
            }
            
            Person.add("Person", persons);
            
            
        } catch (IOException ex) {
            Logger.getLogger(PeopleAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(persons.size() == 0){
            return Response.noContent().build();
        }
        else{
            return Response.ok(
            Person.toString(),
            MediaType.APPLICATION_JSON).build();
        }
        
    }
    
    @GET
    @Path("/last_nameInfo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLastNameInfo(@PathParam("value") String last_name_value) throws FileNotFoundException {
        
        Gson gson = new Gson();
        
        BufferedReader reader = openFile();
        JsonParser parser = new JsonParser();  
        JsonElement rootNode = new JsonObject();
        
        JsonObject Person = new JsonObject();
        ArrayList<JsonObject> persons = new ArrayList<>();
        
        JsonArray sortedPersons = new JsonArray();
        
        String line = null;
        
        try {
            
            while( (line = reader.readLine()) != null){
                rootNode = parser.parse(line);
                persons.add(rootNode.getAsJsonObject());   
            }
            
            Collections.sort(persons, new MyComparator());
            
            for(int i = 0; i < persons.size(); i++){
                sortedPersons.add(persons.get(i));
            }
            Person.add("Person", sortedPersons);
            
            
        } catch (IOException ex) {
            Logger.getLogger(PeopleAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(persons.size() == 0){
            return Response.noContent().build();
        }
        else{
            return Response.ok(
            Person.toString(),
            MediaType.APPLICATION_JSON).build();
        }
        
    }
    
    /*
        This function emulates the deletion by ssn.
    
    */
    
    @GET
    @Path("/delSSN={value}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSSN(@PathParam("value") String ssn) throws FileNotFoundException {
        
        Gson gson = new Gson();
        
        BufferedReader reader = openFile();
        JsonParser parser = new JsonParser();  
        JsonElement rootNode = new JsonObject();
        
        JsonObject Person = new JsonObject();
        JsonArray persons = new JsonArray();
        
        String line = null;
        
        try {
            
            while( (line = reader.readLine()) != null){
                rootNode = parser.parse(line);

             
                JsonObject details = rootNode.getAsJsonObject();
                JsonElement ssnNode = details.get("ssn"); 

                if (!ssnNode.getAsString().equals(ssn)){

                    persons.add(gson.toJsonTree(rootNode));
                    
                }
                
            }
            
            Person.add("Person", persons);
            
            
        } catch (IOException ex) {
            Logger.getLogger(PeopleAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(persons.size() == 0){
            return Response.noContent().build();
        }
        else{
            return Response.ok(
            Person.toString(),
            MediaType.APPLICATION_JSON).build();
        }
        
    }
    
    
    @GET
    @Path("/ssn={value}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInfoBySSN(@PathParam("value") String ssn_value) throws FileNotFoundException {
        
        
        BufferedReader reader = openFile();
        JsonParser parser = new JsonParser();  
        JsonElement rootNode = new JsonObject();
        
        String line = null;
        
        try {
            
            while( (line = reader.readLine()) != null){
                rootNode = parser.parse(line);

             
                JsonObject details = rootNode.getAsJsonObject();
                JsonElement ssnNode = details.get("ssn"); 

                if (ssnNode.getAsString().equals(ssn_value)){
                    
                    
                    LocalDate birthdate = LocalDate.parse(details.get("birthdate").getAsString());
                    LocalDate now = LocalDate.now();
                    long years = ChronoUnit.YEARS.between(birthdate, now);
                    
                    
                    details.addProperty("age", years);
                    

                    return Response.ok(
                    details.toString(),
                    MediaType.APPLICATION_JSON).build();
                }
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(PeopleAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return Response.noContent().build();
        
    }
    
    @PUT
    @Path("/id={IDvalue}-last_name={LNvalue}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateByID(@PathParam("IDvalue") String ID_value, @PathParam("LNvalue") String last_name_value) throws FileNotFoundException {
        
        
        BufferedReader reader = openFile();
        JsonParser parser = new JsonParser();  
        JsonElement rootNode = new JsonObject();
        
        String line = null;
        
        try {
            
            while( (line = reader.readLine()) != null){
                rootNode = parser.parse(line);

             
                JsonObject details = rootNode.getAsJsonObject();
                JsonElement idNode = details.get("id"); 

                if (idNode.getAsString().equals(ID_value)){
                    
                    details.addProperty("last_name", last_name_value);

                    return Response.ok(
                    details.toString(),
                    MediaType.APPLICATION_JSON).build();
                }
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(PeopleAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return Response.noContent().build();
        
    }
    
    
    
    
    
    public class MyComparator implements Comparator<JsonObject> {

        @Override
        public int compare(JsonObject o1, JsonObject o2) {
            return o1.getAsJsonObject().get("last_name").toString().compareTo(o2.getAsJsonObject().get("last_name").toString());
        }
    }
     
}
