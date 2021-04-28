package com.roblesc.controller;

import com.roblesc.model.User;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.json.JsonObject;
import javax.json.JsonArray;
import javax.json.Json;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.Random;
import org.eclipse.microprofile.faulttolerance.*;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserManager
{
    List<User> users = new ArrayList<User>();
    Logger log = Logger.getLogger("DemoLogger");
   
    @GET
    @Timeout(5000L)
    @Fallback(fallbackMethod = "getUsersFallback")
    public JsonArray getUsers() {
        this.log.info("Ejecución de método GET");
        JsonObject aux = Json.createObjectBuilder().build();
        JsonArray arr = Json.createArrayBuilder().build();
        doWait();
        for (int i = 0; i < this.users.size(); i++) {
            aux = Json.createObjectBuilder()
                  .add("bio", this.users.get(i).getBio())
                  .add("username", this.users.get(i).getUsername())
                  .add("age", this.users.get(i).getAge())
                  .add("webpage", this.users.get(i).getWebpage())
                  .build();
            arr = Json.createArrayBuilder()
                  .add(aux)
                  .build();
        }
        return arr;
    }    

    public void doWait(){
        var random = new Random();
        try {
            this.log.warning("Ejecutando sleep");
            Thread.sleep((random.nextInt(5) + 2) * 1000L);
        }catch (Exception ex){}
    }

    public JsonArray getUsersFallback() {
        this.log.warning("Fallback de método get");
        JsonObject dummy = Json.createObjectBuilder()
                           .add("bio", "dummy bio")
                           .add("username", "dummy username")
                           .add("age", -1)
                           .add("webpage", "dummy webpage")
                           .build();
        JsonArray aux = Json.createArrayBuilder()
                        .add(dummy)
                        .build();
        return aux;
    }
     
    @POST
    @Fallback(fallbackMethod = "createUserFallback")
    public void createUser(JsonObject userRequest) {
        this.log.info("Ejecución de método POST");
        User auxUser = new User();
        auxUser.setBio(userRequest.getString("bio"));
        auxUser.setWebpage(userRequest.getString("webpage"));
        auxUser.setUsername(userRequest.getString("username"));
        auxUser.setAge(userRequest.getInt("age"));
        this.users.add(auxUser);
    }

    public void createUserFallback(JsonObject userRequest) {

        this.log.warning("Creación de usuario fallida");
        if (userRequest.get("username") == null) {
            this.log.warning("No se ha introducido la key \"username\"");
        }
        if (userRequest.get("bio") == null) {
            this.log.warning("No se ha introducido la key \"bio\"");
        }
        if (userRequest.get("age") == null) {
            this.log.warning("No se ha introducido la key \"age\"");
        }
        if (userRequest.get("webpage") == null) {
            this.log.warning("No se ha introducido la key \"webpage\"");
        }
    }
}
