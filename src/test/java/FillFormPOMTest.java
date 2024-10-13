import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.ContactUsPage;
import pages.ContactUsVerificationPage;
import pages.HomePage;

import static org.assertj.core.api.Assertions.assertThat;

public class FillFormPOMTest extends BaseTest {

    private String path = "C:\\Users\\glubam\\Desktop\\test.txt";
    private String customerService = "Customer service";
    private String   mail = "testowy@test.pl";
    private String orderReference= "a123";
    private String text = "I have a problem with my order";


    @Test
    public void should_send_form_with_POM(){

        HomePage homePage= new HomePage(driver);
        ContactUsPage contactUsPage=new ContactUsPage(driver);
        ContactUsVerificationPage contactUsVerificationPage=new ContactUsVerificationPage(driver);

        homePage.contact_Us_click();

        contactUsPage.select_object_from_subject_heading(customerService);
        contactUsPage.type_email(mail);
        contactUsPage.type_order_reference(orderReference);
        contactUsPage.file_upload(path);
        contactUsPage.type_message(text);
        contactUsPage.click_submit_button();
        //inna możliwość:
        //contactUsPage.select_object_from_subject_heading("Customer service").type_email("testowy@test.pl").type_order_reference("123");

        String messageText = contactUsVerificationPage.check_alert_success();
        assertThat(expected_message).withFailMessage("niepoprawna wiadomość").isEqualTo(messageText);
    }

}
