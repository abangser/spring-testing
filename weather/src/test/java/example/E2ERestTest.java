package example;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.when;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class E2ERestTest {

    @LocalServerPort
    private int port;

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldReturnWeather() throws Exception {
        when()
                .get(String.format("http://localhost:%s/weather", port))
                .then()
                .statusCode(is(200));
    }
}
