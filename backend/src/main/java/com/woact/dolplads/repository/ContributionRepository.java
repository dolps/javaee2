package com.woact.dolplads.repository;

import com.woact.dolplads.entity.Comment;
import com.woact.dolplads.entity.Post;

import java.util.List;

/**
 * Created by dolplads on 15/10/2016.
 */
@SuppressWarnings("unchecked")
public class ContributionRepository<E, T> extends CrudRepository<E, T> {
    ContributionRepository(Class<T> entityClass) {
        super(entityClass);
    }

    public List<Post> findPostsByUser(Long userId) {
        return entityManager.createQuery("select user.posts from User user where user.id = :userId")
                .setParameter("userId", userId).getResultList();
    }

    public List<Comment> findCommentsByPost(Long postId) {
        return entityManager.createQuery("select post.comments from Post post where post.id = :postId")
                .setParameter("postId", postId)
                .getResultList();
    }
}
