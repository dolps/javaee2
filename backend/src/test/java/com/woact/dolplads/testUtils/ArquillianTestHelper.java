package com.woact.dolplads.testUtils;

import com.woact.dolplads.entity.Comment;
import com.woact.dolplads.entity.Post;
import com.woact.dolplads.entity.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;

import javax.inject.Inject;

/**
 * Created by dolplads on 25/09/16.
 */
@RunWith(Arquillian.class)
public abstract class ArquillianTestHelper {
    @Inject
    private DeleterEJB deleterEJB;

    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive war = ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, "com.woact.dolplads")
                .addPackages(true, "org.apache.commons.codec")
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

        System.out.println(war.toString(true));
        return war;
    }

    public void deleteEntites(Class<User> userClass, Class<Post> postClass, Class<Comment> commentClass) {

        if (commentClass != null)
            deleterEJB.deleteEntities(commentClass);

        if (postClass != null)
            deleterEJB.deleteEntities(postClass);

        if (userClass != null)
            deleterEJB.deleteEntities(userClass);
    }
}
