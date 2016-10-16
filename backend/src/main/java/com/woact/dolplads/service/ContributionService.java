package com.woact.dolplads.service;

import com.woact.dolplads.entity.Comment;
import com.woact.dolplads.entity.Post;
import com.woact.dolplads.entity.User;
import com.woact.dolplads.repository.CommentRepository;
import com.woact.dolplads.repository.PostRepository;
import com.woact.dolplads.repository.UserRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by dolplads on 15/10/2016.
 */
@Stateless
public class ContributionService {
    @Inject
    private Logger logger;
    @Inject
    private PostRepository postRepository;
    @Inject
    private CommentRepository commentRepository;
    @Inject
    private UserRepository userRepository;

    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    public Comment addCommentToPost(Comment comment) {
        Comment persisted = commentRepository.save(comment);
        logger
                .log(Level.INFO
                        , "added comment: " + comment.getId() + " to post: " + comment.getPost().getId()
                                + "with user: " + comment.getUser().getId());
        return persisted;
    }

    public Comment addCommentToComment(Long commentId, Comment comment) {
        Comment persisted = commentRepository.findById(commentId);
        persisted.addComment(comment);

        persisted = commentRepository.save(comment);
        logger
                .log(Level.INFO
                        , "toComment: added comment: " + comment.getId() + " to post: " + comment.getPost().getId()
                                + "with user: " + comment.getUser().getId());
        return persisted;
    }
}
