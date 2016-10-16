package com.woact.dolplads.repository;

import com.woact.dolplads.entity.Post;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by dolplads on 15/10/2016.
 */
@Stateless
public class PostRepository extends ContributionRepository<Long, Post> {
    protected PostRepository() {
        super(Post.class);
    }
}
