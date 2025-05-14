package manager;

import models.User;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;

public class HelperUser extends HelperBase {
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm() {
        click(By.xpath("//a [text()=' Log in ']"));
    }


    public void fillLoginForm(String email, String password) {
        type(By.id("email"), email);
        type(By.id("password"), password);
    }

    public void fillLoginForm(User user) {
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
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


    public boolean isLogged() {
        return isElementPresent(By.xpath("//a[contains(@href, '/logout')]"));
    }

//    public boolean isExistError() {   ---> myHW
//        WebElement element = wd.findElement(By.xpath("//*[@ class='error']"));
//
//        return element != null;
//    }


    public String getErrorText() {
        return wd.findElement(By.cssSelector("div.error")).getText();
    }

    public void clickOkButton() {
        if (isElementPresent(By.xpath("//button[text() = 'Ok']")))
            click(By.xpath("//*[button = 'Ok']"));
    }


    public void logout() {
        click(By.xpath("//a[contains(@href, '/logout')]"));
    }

    public boolean isYallaBtnNotActive() {
        boolean res = isElementPresent(By.cssSelector("button[disabled]"));
//        ====================================
        WebElement element = wd.findElement(By.cssSelector("button[type = 'submit']"));
        boolean result = element.isEnabled();

        return res && !result;
    }

//    ************************RegistrationForm******************************************

    public void openRegistrationForm() {
        click(By.xpath("//a[text()=' Sign up ']"));
    }

    public void fillRegistrationForm(User user) {
        type(By.id("name"), user.getFirstName());
        type(By.id("lastName"), user.getLastName());
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }

//    public void checkPolicy() {
//        click(By.id("terms-of-use")); (0x0)

//        click(By.cssSelector("label[for='terms-of-use']"));


//        JavascriptExecutor js = (JavascriptExecutor) wd;
//        js.executeScript("document.querySelector('#terms-of-use').click()");

    public void checkPolicyXY() {
        WebElement label = wd.findElement(By.cssSelector("label[for='terms-of-use']"));
        Rectangle rec = label.getRect();
        int w = rec.getWidth();


        int xOffSet = -w/2;

        Actions actions = new Actions(wd);
        actions.moveToElement(label, xOffSet, 0);

    }

    public void login(User user) {
        openLoginForm();
        fillLoginForm(user);

    }
}

