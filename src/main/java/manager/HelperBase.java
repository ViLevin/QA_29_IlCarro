package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HelperBase {
    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void clearNew(WebElement element) {  // type+backspace
        element.sendKeys(" ");
        element.sendKeys(Keys.BACK_SPACE);

    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }


    public void type(By locator, String text) {
        WebElement element = wd.findElement(locator);
        element.click();
        element.clear();
        clearNew(element);
        if (text != null) {
            element.sendKeys(text);
        }
    }


    public boolean isElementPresent(By locator) {
        List<WebElement> list = wd.findElements(locator);
        return list.size() > 0;
    }

    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }


    public void submit() {
        click(By.xpath("//*[@type='submit']"));
    }

    public String getMessage() {
//        WebElement element = wd.findElement(By.cssSelector(".dialog-container>h2"));
//        String text = element.getText();
//        return text;
        pause(2000);
        return wd.findElement(By.cssSelector(".dialog-container>h2")).getText();

    }

}
