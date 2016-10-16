package com.woact.dolplads.service;

import com.woact.dolplads.entity.Address;
import com.woact.dolplads.entity.Comment;
import com.woact.dolplads.entity.Post;
import com.woact.dolplads.entity.User;
import com.woact.dolplads.enums.CountryEnum;
import com.woact.dolplads.repository.CommentRepository;
import com.woact.dolplads.repository.PostRepository;
import com.woact.dolplads.repository.UserRepository;
import com.woact.dolplads.testUtils.ArquillianTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by dolplads on 15/10/2016.
 */
public class ContributionServiceTest extends ArquillianTestHelper {
    @Inject
    private UserRepository userRepository;
    @Inject
    private PostRepository postRepository;
    @Inject
    private CommentRepository commentRepository;
    @Inject
    private ContributionService contributionService;

    @Before
    @After
    public void setUp() throws Exception {
        super.deleteEntites(User.class, Post.class, Comment.class);
    }

    @Test
    public void addPosts() throws Exception {
        User user = saveAndGetUser("dolpen");

        Post post = addAndGetPost(user);
        Post post2 = addAndGetPost(user);

        post = postRepository.findById(post.getId());
        user = userRepository.findById(user.getId());

        assertEquals(post.getUser().getId(), user.getId());
        assertEquals(post2.getUser().getId(), user.getId());
        assertEquals(postRepository.findPostsByUser(user.getId()).size(), 2);
    }

    @Test
    public void addCommentsToPost() throws Exception {
        User user = saveAndGetUser("dolpen");
        Post post = addAndGetPost(user);
        assertEquals(1, postRepository.findPostsByUser(user.getId()).size());

        Comment comment = saveAndGetComment(user, post);
        Comment comment2 = saveAndGetComment(user, post);


        assertEquals(user.getId(), commentRepository.findById(comment.getId()).getUser().getId());
        assertEquals(user.getId(), commentRepository.findById(comment2.getId()).getUser().getId());
        assertEquals(2, commentRepository.findCommentsByPost(post.getId()).size());
        assertEquals(2, commentRepository.findCommentsByUser(user.getId()).size());
    }

    @Test
    public void addCommentsToComments() throws Exception {
        User user = saveAndGetUser("dolpen");
        User user2 = saveAndGetUser("dolpen2");
        Post post = addAndGetPost(user);
        assertEquals(1, postRepository.findPostsByUser(user.getId()).size());

        Comment comment = saveAndGetComment(user2, post);
        assertNotNull(comment.getId());

        Comment comment2 = addToCommentAndGetComment(user, post, comment);
        Comment comment3 = addToCommentAndGetComment(user, post, comment2);
        Comment comment4 = addToCommentAndGetComment(user2, post, comment2);

        assertEquals(1, commentRepository.findCommentsByComment(comment.getId()).size());
        assertEquals(2, commentRepository.findCommentsByComment(comment2.getId()).size());
    }

    private User saveAndGetUser(String userName) {
        User user = new User(userName, "thomas", "dolplads", new Address("street", "1234", CountryEnum.Norway));
        user.setPasswordHash("hash");
        user.setSalt("salt");
        user = userRepository.save(user);

        assertNotNull(user.getId());

        return user;
    }


    private Post addAndGetPost(User user) {
        Post post = new Post(user, "example", new Date());
        contributionService.addPost(post);

        assertNotNull(post.getId());

        return post;
    }

    private Comment saveAndGetComment(User user, Post post) {
        Comment comment = new Comment(user, post, "someText", new Date());
        comment = contributionService.addCommentToPost(comment);

        assertNotNull(comment.getId());

        return comment;
    }

    private Comment addToCommentAndGetComment(User user, Post post, Comment comment) {
        Comment toPersist = new Comment(user, post, "someText", new Date());
        toPersist = contributionService.addCommentToComment(comment.getId(), toPersist);

        assertNotNull(toPersist.getId());

        return toPersist;
    }

}