package com.woact.dolplads.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.persistence.*;
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
public class Post extends Contribution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    public Post(User user, String text, Date creationDate) {
        super(user, text, creationDate);
    }

    @PostConstruct
    public void init() {
        this.comments = new ArrayList<>();
    }
}
