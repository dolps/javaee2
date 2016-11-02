package com.woact.dolplads.quiz.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by dolplads on 26/10/2016.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Question {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Category category;

    @OneToMany(fetch = FetchType.EAGER)
    private Answer[] answers;

    private String question;

    public Question(Category category, Answer[] answers, String question) {
        this.category = category;
        this.answers = answers;
        this.question = question;
    }

    public boolean checkIfCorrect(int userAnswer) {
        return answers[userAnswer].isCorrect();
    }
}
