import base.BaseTest;
import base.BaseTestWebtable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WebTableSeleniumTest extends BaseTestWebtable {
    private Logger log= LoggerFactory.getLogger(WebTableSeleniumTest.class);


    @Test
    @DisplayName("Fajny test na webtable")
    void should_check_capital_in_webtable() throws InterruptedException {
    String searchCountry="Portugal";
    String expected_capitol = "Lisbon";

    String capitalForCountry = getCapitalForCountry(searchCountry);

    assertThat(capitalForCountry).isEqualTo(expected_capitol);
    }

    private String getCapitalForCountry(String country) throws InterruptedException { //podajemy jakis kraj a ona ma nam dac stolicę operując na webtable
        List<WebElement> rows = driver.findElements(By.cssSelector("#countries tbody tr"));
        int size = rows.size();
        log.info("wielkość tablicy= "+ size);

        int rowCount=0;
        for (WebElement row : rows) {
            if(row.getText().contains(country)){
                break;
            }
            rowCount ++;

        }
        int rowIndex= rowCount + 1;
        log.info("My row= "+(rowIndex));

        scrollToWebElement(rows.get(rowIndex+5));

        WebElement CapitolOfCountry = driver.findElement(By.cssSelector("#countries tbody tr:nth-child(" + rowIndex + ") td:nth-child(3)"));

        //scrollToWebElement(CapitolOfCountry);
        Thread.sleep(5000);
        log.info("Capitol of country "+country+" is "+CapitolOfCountry.getText()) ;
        return CapitolOfCountry.getText();

    }
    @Test
    @DisplayName("Test na szukanie checkboxa")
    void shouldSelectCheckbox() throws InterruptedException {
        //select checkbox for Poland
        List<WebElement> checkboxes = driver.findElements(By.cssSelector(".hasVisited[type='checkbox']"));
        WebElement checkbox = find_checkbox(checkboxes, 139);
        scrollToWebElement(checkboxes.get(145));
        click_checkbox(checkbox);
        assertThat(checkbox.isSelected()).isTrue();
        Thread.sleep(5000);
    }

    private void scrollToWebElement(WebElement element){
        new Actions(driver).scrollToElement(element).perform();
    }


}
