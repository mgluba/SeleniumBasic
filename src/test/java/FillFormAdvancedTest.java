import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class FillFormAdvancedTest extends BaseTest {
    private Logger log= LoggerFactory.getLogger(FillFormAdvancedTest.class);
    @Test
    void fillFormAdvancedScenario(){

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
        WebElement attach_input_input = driver.findElement(By.cssSelector("#fileUpload"));
        attach_input_input.clear();
        attach_input_input.sendKeys("C:\\Users\\glubam\\Desktop\\test.txt");
        log.info("file attached properly:"+ " test.txt ");

        //7. Message
        WebElement message = driver.findElement(By.xpath("//*[@id='message']"));
        message.clear();
        message.sendKeys("I have a problem with my order");
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



    }
}
