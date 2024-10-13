import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class FillFormBasicTest {

    //deklaracja obiektu . będzie działał we wszytskich testach tej klasy
    private static WebDriver driver;
    private final String APP_URL = "http://www.automationpractice.pl/index.php"; //znam wartośc, daje final, stała
//każda klasa musi mieć swojego loggera
private Logger log= LoggerFactory.getLogger(FillFormBasicTest.class);
    //@Test-adnotacja
    @Test
    //metody testowe
    void shouldFillFormWithSuccess() {
        //1.open url http://www.automationpractice.pl/index.php?id_category=5&controller=category
        driver = getDriver();
        driver.get(APP_URL);
        log.info("URL opened "+APP_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));//czekanie
        log.info("Timeout 5 sec");

        //explicity wait -
        //fluent wait -

        //2. Contact us
        WebElement contactUs_link = driver.findElement(By.cssSelector("#contact-link a"));
        contactUs_link.click();
        log.info("Contact Us link clicked properly  ");
        //System.out.println("ok");

        //3. Subject Heandling
        // WebElement subjectHeading_select = driver.findElement(By.cssSelector("#id_contact"));
        // WebElement subjectHeading_select = driver.findElement(By.xpath(" "));
        WebElement subjectHeading_select = driver.findElement(By.id("id_contact"));
        Select select = new Select(subjectHeading_select); //selenium ma klasę select
        select.selectByIndex(1); //Customer service
        select.selectByVisibleText("Customer service"); //Customer service
        log.info("Subject Heandling selected  ");

        //4. Email adress
        WebElement email_input = driver.findElement(By.cssSelector("#email"));
        email_input.clear();
        email_input.sendKeys("test@test.com"); //
        log.info("email submitted properly:"+ " test@test.com ");

        //5. Order Reference
        WebElement order_reference = driver.findElement(By.cssSelector("#id_order"));
        order_reference.clear();
        order_reference.sendKeys("123"); //
        log.info("order submitted properly:"+ " 123 ");

        //6. Attach File
        WebElement attach_input_input = driver.findElement(By.cssSelector("#email"));
        attach_input_input.clear();
        attach_input_input.sendKeys("C:\\Users\\glubam\\Desktop\\test.txt");
        System.out.println("ok");
        log.info("file attached properly:"+ " test.txt ");

        //7. Message
        WebElement message = driver.findElement(By.xpath("//*[@id='message']"));
        attach_input_input.clear();
        attach_input_input.sendKeys("I have a problem with my order");
        log.info("message submitted properly:"+ "I have a problem with my order");

        //8. Submit button
        WebElement send = driver.findElement(By.id("submitMessage"));
        send.click();
        log.info("Button clicked properly");

        //Asercja
        //String expected_message = "Your message has been successfully sent to our team.";
        String expected_message = "Your message has been successfully sent to our team.";
        WebElement success_msg = driver.findElement(By.cssSelector(".alert-success"));
        String actual_mgs = success_msg.getText();

        assertThat(expected_message).contains(actual_mgs); //sprawdź, że wiadomość oczekiwana zawiera w sobie wiadomość któa
        //zawiera w sobie wiadomość pobraną z aplikacji
        //assertThat(expected_message).isEqualTo(actual_mgs);
        //assertThat((expected_message)).withFailMessage("niepoprawna wiadomość");

        //9.driver quit

        // driver.close(); // close zawiera się w quit
        driver.quit(); // zamknięcie procesu
        log.info("Driver closed properly!!! ");
    }

    private WebDriver getDriver() {
        //inicjalizacja drivera
        String browserName = "chrome";
        switch (browserName) {
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

            default -> throw new UnsupportedOperationException("Unsupported browser selected");

        }
        return driver;
    }

}
