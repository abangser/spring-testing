package example;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"weather.url=https://api.darksky.net/forecast",
                "weather.api_secret=${WEATHER_API}"})
public class E2ESeleniumTest {

    private WebDriver driver;
    private static ChromeOptions options;

    @LocalServerPort
    private int port;

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\path\\to\\chromedriver.exe");
        options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.setAcceptInsecureCerts(true);
        options.addArguments("--ignore-ssl-errors=true");
        options.addArguments("--ssl-protocol=any");
        options.setHeadless(true);
        options.addArguments("--remote-debugging-port=9222");
        options.addArguments("window-size=1400,600");
        ChromeDriverManager.getInstance().setup();
    }

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver(options);
    }

    @After
    public void tearDown(){
        driver.close();
    }

    @Test
    public void weatherPageShowsWeatherAtSpecificTime(){
        System.out.println(String.format("http://localhost:%s/weather/255657600", port));
        driver.navigate().to(String.format("http://localhost:%s/weather/255657600", port));


        WebElement body = driver.findElement(By.tagName("body"));

        assertThat(body.getText(), containsString("Overcast"));
    }
}