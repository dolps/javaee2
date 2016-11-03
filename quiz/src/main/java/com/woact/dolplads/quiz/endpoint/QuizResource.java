package com.woact.dolplads.quiz.endpoint;

import com.woact.dolplads.model.*;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dolplads on 02/11/2016.
 */
@Path("/q")
public class QuizResource {

    @GET
    @Path("/lok")
    @Produces("text/plain;charset=utf-8")
    public String lok() {
        if (1 == 1)
            throw new WebApplicationException("not working", 500);
        return "løk";
    }

    @GET
    @Path("/categories")
    @Produces("application/json")
    public List<Category> findCategories() {
        ArrayList<Category> categories = new ArrayList<>();
        Category subCategory = new Category(CategoryEnum.COUNTRIES_IN_EUROPE, null);
        categories.add(new Category(CategoryEnum.COMPUTER_SCIENCE, null));
        categories.add(new Category(CategoryEnum.COUNTRIES, subCategory));

        return categories;
    }

    @GET
    @Path("/subcategories")
    @Produces("application/json")
    public List<Category> findSubCategories() {
        return findCategories();
    }

    @GET
    @Path("/categories/{id}")
    @Produces("application/json")
    public List<Category> findCategoryById(@PathParam("id") String id) {

        return findCategories();
    }

    @GET
    @Path("/quizzes")
    @Produces("application/json")
    public List<Quiz> findQuizzes() {
        ArrayList<Quiz> quizList = new ArrayList<>();
        quizList.add(new Quiz());

        return quizList;
    }


}
