package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContactUsPage {
    private Logger log= LoggerFactory.getLogger(ContactUsPage.class);

    @FindBy(id="id_contact")
    WebElement subjectHeading_select;


  @FindBy(css="#email")
  WebElement email_input;

  @FindBy(css="#id_order")
  WebElement order_reference;

  @FindBy(css="#fileUpload")
  WebElement attach_input_input;

  @FindBy(xpath = "//*[@id='message']")
  WebElement message;

  @FindBy(id="submitMessage")
  WebElement send;

    public ContactUsPage(WebDriver driver){
        PageFactory.initElements(driver,this); //inicjalizacja drivera
    }

  public ContactUsPage select_object_from_subject_heading(String customerService){
      Select select = new Select(subjectHeading_select); //selenium ma klasę select
      select.selectByIndex(1); //Customer service
      select.selectByVisibleText(customerService); //Customer service
      log.info("Subject Heandling selected  ");
      return this;
  }

  public ContactUsPage type_email(String mail){
      email_input.clear();
      email_input.sendKeys(mail); //
      log.info("email submitted properly:"+ mail);
      return this;
  }
  public ContactUsPage type_order_reference(String number){
      order_reference.clear();
      order_reference.sendKeys(number); //
      log.info("order submitted properly:"+ number);
      return this;
  }

    public ContactUsPage file_upload(String path){
        attach_input_input.clear();
        attach_input_input.sendKeys(path);
        log.info("file attached properly:"+ path);
        return this;
    }
    public ContactUsPage type_message(String text){
        message.clear();
        message.sendKeys(text);
        log.info("message submitted properly:"+ text);
        return this;
    }
    public ContactUsPage click_submit_button() {
        send.click();
        log.info("Button clicked properly");
        return this;
    }

}
