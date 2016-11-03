package com.woact.dolplads.service;


import com.woact.dolplads.model.Category;
import com.woact.dolplads.model.CategoryEnum;
import com.woact.dolplads.model.Quiz;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by dolplads on 26/10/2016.
 */
public class QuizService {
    @PersistenceContext
    private EntityManager entityManager;

    public Category createCategory(Category category) {
        entityManager.persist(category);

        return category;
    }

    public Quiz createQuiz(Quiz quiz) {
        entityManager.persist(quiz);

        return quiz;
    }

    public void removeQuiz(Quiz quiz) {
        entityManager.remove(entityManager.merge(quiz));
    }

    public void updateQuiz(Quiz quiz) {
        entityManager.merge(quiz);
    }

    @SuppressWarnings("unchecked")
    public List<Quiz> getQuizzesByCategory(CategoryEnum categoryEnum) {
        return entityManager.createQuery("select q from Quiz q where q.category.categoryEnum = :ce")
                .setParameter("ce", categoryEnum)
                .getResultList();
    }

}
