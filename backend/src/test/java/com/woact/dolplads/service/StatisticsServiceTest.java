package com.woact.dolplads.service;

import com.woact.dolplads.annotations.Repository;
import com.woact.dolplads.entity.Address;
import com.woact.dolplads.entity.Comment;
import com.woact.dolplads.entity.Post;
import com.woact.dolplads.entity.User;
import com.woact.dolplads.enums.CountryEnum;
import com.woact.dolplads.repository.PostRepository;
import com.woact.dolplads.repository.UserRepository;
import com.woact.dolplads.testUtils.ArquillianTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ejb.EJB;
import javax.inject.Inject;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by dolplads on 16/10/2016.
 */
public class StatisticsServiceTest extends ArquillianTestHelper {
    @EJB
    private UserRepository userRepository;
    @EJB
    private ContributionService contributionService;
    @EJB
    private StatisticsService statisticsService;

    @Before
    @After
    public void prepareTest() throws Exception {
        deleteEntites(User.class, Post.class, Comment.class);
    }

    @Test
    public void findDistinctCountries() throws Exception {
        User user = new User("userName", "name", "surname", new Address("street", "post", CountryEnum.Norway));
        User user1 = new User("userName1", "name", "surname", new Address("street", "post", CountryEnum.Sweden));
        User user2 = new User("userName2", "name", "surname", new Address("street", "post", CountryEnum.Sweden));

        saveUsers(user, user1, user2);

        assertEquals(userRepository.findAll().size(), 3);

        List<CountryEnum> countries = statisticsService.findDistinctCountries();

        assertEquals(2, countries.size());

        int fromNorway = (int) countries.stream().filter(country ->
                country.equals(CountryEnum.Norway)).count();

        assertEquals(1, fromNorway);

        int fromSweden = (int) countries.stream().filter(country -> country.equals(CountryEnum.Sweden)).count();

        assertEquals(1, fromSweden);
    }

    @Test
    public void findNumberOfPosts() throws Exception {
        User user = new User("userName", "name", "surname", new Address("street", "post", CountryEnum.Norway));
        User user1 = new User("userName1", "name", "surname", new Address("street", "post", CountryEnum.Sweden));
        User user2 = new User("userName2", "name", "surname", new Address("street", "post", CountryEnum.Sweden));
        saveUsers(user, user1, user2);

        Post post = new Post(user, "someText", new Date());
        Post post2 = new Post(user1, "someText", new Date());
        Post post3 = new Post(user2, "someText", new Date());

        contributionService.addPost(post);
        contributionService.addPost(post2);
        contributionService.addPost(post3);

        int nOfPosts = statisticsService.findNumberOfPosts();

        assertEquals(3, nOfPosts);
    }

    @Test
    public void findMostActiveUsers() throws Exception {
        User user1 = new User("userName", "name", "surname", new Address("street", "post", CountryEnum.Norway));
        User user2 = new User("userName1", "name", "surname", new Address("street", "post", CountryEnum.Sweden));
        saveUsers(user1, user2);

        Post post = new Post(user1, "someText", new Date());
        Post post2 = new Post(user1, "someText", new Date());
        Post post3 = new Post(user2, "someText", new Date());


        contributionService.addPost(post);
        contributionService.addPost(post2);
        contributionService.addPost(post3);

        assertEquals(3, statisticsService.findNumberOfPosts());

        // user1: 2 posts mostActive user1
        // user2: 1 post
        User mostActive = statisticsService.findMostActiveUsers(2).get(0);
        User nextMostActive = statisticsService.findMostActiveUsers(2).get(1);

        assertEquals(mostActive.getId(), user1.getId());
        assertEquals(nextMostActive.getId(), user2.getId());

        Comment comment = new Comment(user2, post2, "comment", new Date());
        Comment comment2 = new Comment(user2, post2, "comment", new Date());
        Comment comment3 = new Comment(user2, post2, "comment", new Date());

        contributionService.addCommentToPost(comment);
        contributionService.addCommentToPost(comment2);
        contributionService.addCommentToPost(comment3);

        // user1: 2 posts
        // user2: 1 post 3 comments mostactive

        assertEquals(2, statisticsService.findNumberOfPostsByUser(user1.getId()).size());
        assertEquals(0, statisticsService.findNumberOfCommentsByUser(user1.getId()).size());

        assertEquals(1, statisticsService.findNumberOfPostsByUser(user2.getId()).size());
        assertEquals(3, statisticsService.findNumberOfCommentsByUser(user2.getId()).size());

        mostActive = statisticsService.findMostActiveUsers(2).get(0);
        assertEquals(mostActive.getId(), user2.getId());

        nextMostActive = statisticsService.findMostActiveUsers(2).get(1);
        assertEquals(nextMostActive.getId(), user1.getId());
    }

    private void saveUsers(User... users) {
        for (User u : users) {
            u.setPasswordHash("pass");
            u.setSalt("hash");
            userRepository.save(u);
        }
    }

}