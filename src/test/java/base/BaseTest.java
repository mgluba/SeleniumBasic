package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BaseTest {
    private final String browserName = "chrome";
    protected static WebDriver driver;
    private final String APP_URL = "http://www.automationpractice.pl/index.php";
    private Logger log= LoggerFactory.getLogger(BaseTest .class);
    protected     String expected_message = "Your message has been successfully sent to our team.";

    @BeforeEach
//ta metoda będzie się wykonywała przed każdą metodą testową
    void setup(){
        driver = getDriver();
        driver.get(APP_URL);
        log.info("URL opened "+APP_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));//czekanie
        log.info("Driver intializet properly");

    }
    @AfterEach
        //
    void teardown(){
        driver.quit(); // zamknięcie procesu
        log.info("Driver closed properly!!! ");
    }
    private WebDriver getDriver() {
        //inicjalizacja drivera

        switch (this.browserName) {
            case "chrome" -> {
                ChromeOptions chromeOptions = new ChromeOptions(); //tworzenie obeiktu klasy ChromeOptions
                WebDriverManager.chromedriver().setup();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("user-data-dir=C:/Users/glubam/Documents/User Data");
                chromeOptions.addArguments("--profile-directory=Default");
                chromeOptions.addArguments("--remote-allow-origins=*");//otwarcie ruchu przeglądarki żeby nie blokować połączeń
                chromeOptions.addArguments("--disable-search-engine-choice-screen");
                //chromeOptions.addArguments("--headless"); //nic nie zobaczymy zrobi się w tle test
                driver = new ChromeDriver(chromeOptions);
                log.info("Chrome Driver initials properly");
            }
            case "firefox" -> {

            }
            case "edge" -> {
                EdgeOptions edgeOptions = new EdgeOptions(); //tworzenie obeiktu klasy ChromeOptions
                WebDriverManager.edgedriver().setup();
                edgeOptions.addArguments("--start-maximized");
                edgeOptions.addArguments("--remote-allow-origins=*");//otwarcie ruchu przeglądarki żeby
                // nie blokować połączeń
                edgeOptions.addArguments("--disable-search-engine-choice-screen");
                //edgeOptions.addArguments("--headless"); //nic nie zobaczymy zrobi się w tle test
                driver = new EdgeDriver(edgeOptions);

            }
            /*case "chromium" -> {
                ChromiumOptions chromiumOptions = new ChromiumOptions(); //tworzenie obeiktu klasy ChromeOptions
                WebDriverManager.chromiumdriver().setup();
                chromiumOptions.addArguments("--start-maximized");
                //chromiumOptions.addArguments("user-data-dir=C:/Users/glubam/Documents/User Data");
                //chromiumOptions.addArguments("--profile-directory=Default");
                chromiumOptions.addArguments("--remote-allow-origins=*");//otwarcie ruchu przeglądarki żeby nie blokować połączeń
                chromiumOptions.addArguments("--disable-search-engine-choice-screen");
                //chromeOptions.addArguments("--headless"); //nic nie zobaczymy zrobi się w tle test
                driver = new ChromiumDriver(chromiumOptions);
                log.info("Chromium Driver initials properly"); }*/
            default -> throw new UnsupportedOperationException("Unsupported browser selected");

        }
        return driver;
    }
}
