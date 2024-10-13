package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage {
    private Logger log= LoggerFactory.getLogger(HomePage.class);
    public HomePage(WebDriver driver){
        PageFactory.initElements(driver,this); //inicjalizacja drivera
    }

    @FindBy(css="#contact-link a")
    WebElement contactUs_link;   // to samo co: WebElement contactUs_link = driver.findElement(By.cssSelector("#contact-link a"));

    public HomePage contact_Us_click(){
        contactUs_link.click();
        log.info("Contact Us link clicked properly  ");
        return this;
    }

}
