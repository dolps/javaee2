package com.woact.dolplads.quiz.endpoint;

import com.woact.dolplads.quiz.model.User;
import lombok.extern.java.Log;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.logging.Level;

/**
 * Created by dolplads on 24/10/2016.
 */
@Path("/quiz")
@Log
public class QuizResource {

    @GET
    public String test() {
        return "test";
    }

    @Produces("text/json")
    @Path("/{name}")
    @GET
    public User user(@PathParam("name") String name) {
        log.log(Level.INFO, name);
        return new User(name, "test");
    }
}
