package com.woact.dolplads.repository;

import com.woact.dolplads.annotations.Repository;
import com.woact.dolplads.entity.Comment;
import com.woact.dolplads.entity.Post;
import com.woact.dolplads.entity.User;
import com.woact.dolplads.enums.CountryEnum;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by dolplads on 12/10/2016.
 * Extension of the crudrepository
 * manages userspesific DB handling
 */
@SuppressWarnings("unchecked")
@Stateless
public class UserRepository extends CrudRepository<Long, User> {
    @Inject
    private Logger logger;

    public UserRepository() {
        super(User.class);
    }

    /**
     * looks up a user by its username
     *
     * @param userName must be a non null String
     * @return if found
     */
    public User findByUserName(String userName) {
        logger.log(Level.INFO, "suppp it works");
        List<User> users = entityManager.createNamedQuery(User.FIND_BY_USERNAME)
                .setParameter("userName", userName)
                .getResultList();
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    public List<CountryEnum> findDistinctCountries() {
        return entityManager.createNamedQuery(User.FIND_DISTINCT_COUNTRIES)
                .getResultList();
    }

    public List<User> findMostActive(int max) {
        return entityManager.createNamedQuery(User.MOST_ACTIVE)
                .setMaxResults(max)
                .getResultList();
    }

    public List<Post> findPostsByUser(Long userId) {
        return entityManager.createQuery("select user.posts from User user where user.id = :userId")
                .setParameter("userId", userId)
                .getResultList();
    }

    public List<Comment> findCommentsByUser(Long userId) {
        return entityManager.createQuery("select user.comments from User user where user.id = :userId")
                .setParameter("userId", userId)
                .getResultList();
    }
}
