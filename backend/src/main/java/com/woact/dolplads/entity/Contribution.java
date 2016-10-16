package com.woact.dolplads.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dolplads on 15/10/2016.
 */
@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
public abstract class Contribution {

    @NotNull
    @ManyToOne
    @JoinColumn
    private User user;

    private String text;

    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date datePersisted;

    private int upVotes;
    private int downVotes;

    public Contribution(User user, String text, Date creationDate) {
        this.user = user;
        this.text = text;
        this.creationDate = creationDate;
    }
}
