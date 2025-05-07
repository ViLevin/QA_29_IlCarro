package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperUser extends HelperBase{
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm() {
        click(By.xpath("//a [text()=' Log in ']"));
    }


    public void fillingLoginForm(String email, String rassword) {
        type(By.id("email"), email);
        type(By.)


    }
}
