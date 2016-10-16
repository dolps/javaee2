package com.woact.dolplads.repository;

import com.woact.dolplads.annotations.Repository;
import com.woact.dolplads.entity.User;

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
}
