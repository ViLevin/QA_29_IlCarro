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
        pause(1000);
        type(By.id("email"), email);
        pause(3000);
        type(By.id("password"), password);
        pause(3000);
    }

    public void fillLoginFormObj(User user) {
        pause(1000);
        type(By.id("email"), user.getEmail());
        pause(3000);
        type(By.id("password"), user.getPassword());
        pause(3000);
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
//        if (isElementPresent(By.xpath("//button[text() = 'Ok']")))
//            click(By.xpath("//*[button = 'Ok']"));
        if (isElementPresent(By.xpath("//button[text() = 'Ok']"))) {
            click(By.xpath("//button[text() = 'Ok']"));
        }
    }


    public void logout() {
        click(By.xpath("//*[text()=' Logout ']"));
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
        if (!wd.findElement(By.id("terms-of-use")).isSelected()) {
            WebElement label = wd.findElement(By.cssSelector("label[for='terms-of-use']"));
            Rectangle rec = label.getRect();
            int w = rec.getWidth();


            int xOffSet = -w / 2;

            Actions actions = new Actions(wd);
            actions.moveToElement(label, xOffSet, 0).click().release().perform();
        }
    }


    public void loginData(String email, String password) {
        openLoginForm();
        fillLoginForm(email, password);
        submit();
        clickOkButton();

    }

    public void loginModel(User user) {
        openLoginForm();
        fillLoginFormObj(user);
        submit();
        clickOkButton();

    }

}

