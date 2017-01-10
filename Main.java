package io.github.bonigarcia.wdm;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by nqlong on 09-Jan-17.
 */
public class Main {
    protected static final int TIMEOUT = 30; // seconds
    protected static boolean validOS = true;
    protected WebDriver driver;
    static final String CHROME = "chrome";

    public static void main(String[] args) {
        String[] arg = {"chrome"};
        if (null == arg) {
            throw new NullPointerException("Argument not a null");
        }
        Main main = new Main();
        if (CHROME.equals(arg[0])) {
            main.setUpChrome();
        }

        main.autoShare();
    }

    private void setUpChrome() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
    }

    private void autoShare() {
        long timeStart = System.currentTimeMillis();
        if (validOS) {
            WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
            driver.get("http://www.10puzzle.com/piece/28317-t%E1%BA%A5t-ni%C3%AAn-new-year-s-eve-party-ikornsolutions-vn.html");
            By shareAreas = By.cssSelector(".inner.share-facebook");
            wait.until(ExpectedConditions.elementToBeClickable(shareAreas));
            driver.findElement(shareAreas).click();
//            wait.until(ExpectedConditions.textToBePresentInElementLocated(
//                    By.tagName("body"), "Computer software"));
//            inputtext _55r1 inputtext inputtext
            // id: email
            //inputtext _55r1 inputtext inputtext
            // id: pass

            // btn: _42ft _4jy0 _4jy3 _4jy2 selected _51sy
            Set<String> windowId = driver.getWindowHandles();    // get  window id of current window
            Iterator<String> itererator = windowId.iterator();

            System.out.println(itererator);
//            String mainWinID = itererator.next();
            String  newAdwinID = "";

            while (itererator.hasNext()) {
                newAdwinID = itererator.next();
            }
            // http authentication popup displayed try to switch on it
//            Alert alert = driver.switchTo().(); // this step not executed.
//            alert.dismiss();// not working
            System.out.println("newAdwinID: " + newAdwinID);

//            driver.switchTo().frame(newAdwinID);
            driver.switchTo().window(newAdwinID);
            System.out.println(driver.getTitle());
            // perform the operation on the popup driver.operations

//            By inputEmail = By.id("email");
            By inputEmail = By.cssSelector(".login_page #email");
            wait.until(ExpectedConditions.presenceOfElementLocated(inputEmail));
            driver.findElement(inputEmail).sendKeys("ikorn.nqlong3@gmail.com");
            // pass
            By inputPass = By.id("pass");
            wait.until(ExpectedConditions.presenceOfElementLocated(inputPass));
            driver.findElement(inputPass).sendKeys("#facebook/24/7#");
            // LOGIN

            By loginClt = By.id("loginbutton");
            wait.until(ExpectedConditions.elementToBeClickable(loginClt));
            driver.findElement(loginClt).click();

            // id: u_0_k
            By postFace = By.id("u_0_k");
            wait.until(ExpectedConditions.elementToBeClickable(postFace));
            driver.findElement(postFace).click();

            driver.quit();

            System.out.println("Bind click success");
            long timeE = System.currentTimeMillis();

            System.out.println("Time: " + (timeE - timeStart)/1000);
        }
    }


}
