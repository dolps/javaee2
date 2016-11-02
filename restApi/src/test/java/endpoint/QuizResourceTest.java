package endpoint;

import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.plugins.server.resourcefactory.POJOResourceFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

/**
 * Created by dolplads on 27/10/2016.
 */
public class QuizResourceTest {
    private Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();
    private POJOResourceFactory noDefaults = new POJOResourceFactory(QuizResource.class);

    private MockHttpRequest request;
    private MockHttpResponse response;

    @Before
    public void setUp() throws Exception {
        dispatcher.getRegistry().addResourceFactory(noDefaults);

        this.request = MockHttpRequest.get("/quiz/thomas");
        this.response = new MockHttpResponse();

        dispatcher.invoke(request, response);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void test1() throws Exception {
        Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());
        Assert.assertEquals("test", response.getContentAsString());
    }

    @Test
    public void user() throws Exception {

    }

}