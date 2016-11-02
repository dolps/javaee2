package com.woact.dolplads.quiz.model;

import java.util.ArrayList;

/**
 * Created by dolplads on 26/10/2016.
 * A quiz game
 */
public class QuizGame {
    public static void main(String[] args) {
        Category category = new Category(Category.CategoryEnum.GEOGRAPHY, null);
        Category subCategory = new Category(Category.CategoryEnum.COUNTRIES, category);
        Category subsubCategory = new Category(Category.CategoryEnum.COUNTRIES_IN_EUROPE, subCategory);

        Answer answer1 = new Answer("Norway", true);
        Answer answer2 = new Answer("Sweden", false);
        Answer answer3 = new Answer("Denmark", false);
        Answer answer4 = new Answer("England", false);
        Answer[] answers = {answer1, answer2, answer3, answer4};
        Question question = new Question(category, answers, "where do they speak norwegian?");

        Quiz quiz = new Quiz(subsubCategory, question);
        System.out.println("lets start a quiz");
        System.out.println("the category will be: " + quiz.getCategory().getCategoryEnum());

        Question q = quiz.getQuestion();
        System.out.println("first question: " + q.getQuestion());
        System.out.println("here comes alternatetives");


        int index = 0;
        for (Answer a : q.getAnswers()) {
            System.out.println(index++ + ": " + a.getAnswer());
        }
        int userAnswer = 0;
        System.out.println("user picked " + userAnswer);
        boolean correct = question.checkIfCorrect(userAnswer);

        if (correct) {
            System.out.println("That is correct");
        } else {
            System.out.println("That is not correct");
        }


    }
}
