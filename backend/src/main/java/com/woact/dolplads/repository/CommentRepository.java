package com.woact.dolplads.repository;

import com.woact.dolplads.entity.Comment;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by dolplads on 15/10/2016.
 */
@Stateless
@SuppressWarnings("unchecked")
public class CommentRepository extends ContributionRepository<Long, Comment> {
    protected CommentRepository() {
        super(Comment.class);
    }

    public List<Comment> findCommentsByComment(Long commentId) {
        return entityManager.createQuery("select comment.comments from Comment comment where comment.id = :commentId")
                .setParameter("commentId", commentId)
                .getResultList();
    }
}
