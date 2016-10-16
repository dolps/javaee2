package com.woact.dolplads.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dolplads on 15/10/2016.
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Comment extends Contribution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn
    private Post post;

    @OneToMany
    private List<Comment> comments;

    public Comment(User user, Post post, String text, Date creationDate) {
        super(user, text, creationDate);
        this.post = post;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
}
