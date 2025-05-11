package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    public void submitLogin() {
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

    public boolean isExistError(){
        WebElement element = wd.findElement(By.xpath("//*[@ class='error']"));

        return element != null;
    }

    public void logout() {
    click(By.xpath("//a[contains(@href, '/logout')]"));
    }
}
