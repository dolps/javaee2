package com.woact.dolplads.service;

import com.woact.dolplads.annotations.Repository;
import com.woact.dolplads.entity.Comment;
import com.woact.dolplads.entity.Post;
import com.woact.dolplads.entity.User;
import com.woact.dolplads.enums.CountryEnum;
import com.woact.dolplads.repository.PostRepository;
import com.woact.dolplads.repository.UserRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by dolplads on 16/10/2016.
 */
@Stateless
public class StatisticsService {
    @EJB
    private UserRepository userRepository;
    @EJB
    private PostRepository postRepository;
    // set of countries users are from
    // posts in total and for a spesific country
    // top x users that wrote the most posts/comments

    public List<CountryEnum> findDistinctCountries() {
        return userRepository.findDistinctCountries();
    }

    public int findNumberOfPosts() {
        return postRepository.findNumberOfPosts();
    }

    public List<Post> findNumberOfPostsByUser(Long userId) {
        return userRepository.findPostsByUser(userId);
    }

    public List<Comment> findNumberOfCommentsByUser(Long userId) {
        return userRepository.findCommentsByUser(userId);
    }

    public List<User> findMostActiveUsers(int max) {
        return userRepository.findMostActive(max);
    }

}
