package manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;
import java.util.Random;

public class WDListener implements WebDriverListener {
    Logger logger = LoggerFactory.getLogger(WDListener.class);

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        WebDriverListener.super.beforeFindElement(driver, locator);
        logger.info("Before Find Element --->" + locator);
    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        WebDriverListener.super.afterFindElement(driver, locator, result);
        logger.info("After Find Element --->" + locator);
        logger.info("After Find Element --->" + result.getLocation());
    }

    @Override
    public void beforeClick(WebElement element) {
        WebDriverListener.super.beforeClick(element);
        logger.info("Before click element --->" + element.getTagName() + "text" + element.getText());
    }

    @Override
    public void afterClick(WebElement element) {
        WebDriverListener.super.afterClick(element);
        logger.info("After click element --->" + element.getTagName() + "text" + element.getText());
    }

    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        WebDriverListener.super.afterSendKeys(element, keysToSend);
        logger.info("sendKeys --->" + element.getTagName() + "text" + element.getText());
    }

    @Override
    public void beforeTo(WebDriver.Navigation navigation, URL url) {
        WebDriverListener.super.beforeTo(navigation, url);
        logger.info("URL --->" + url);
    }

    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        WebDriverListener.super.onError(target, method, args, e);
        logger.info("Huston, we have a problem!");
        logger.info("********************");
        logger.info("Object target  ---> "+ target.toString());
        logger.info("********************");
        logger.info("Method name --->" +method.toString());
        logger.info("********************");
        logger.info("InvocationTargetException --->"+e.getTargetException());
        logger.info("********************");


        int i = new Random().nextInt(1000)+1000;
        String link = "src/test/screenshots/screen_"+i+".png";
        WebDriver wd = (WebDriver) target;

        TakesScreenshot takesScreenshot = (TakesScreenshot) wd;
        File tmp = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tmp, new File(link));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        logger.info("Screen with error is --->" + link);


    }
}

