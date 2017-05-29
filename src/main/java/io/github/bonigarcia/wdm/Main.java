package io.github.bonigarcia.wdm;

import org.jsoup.nodes.Element;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by nqlong on 09-Jan-17.
 */
public class Main {
    protected static final int TIMEOUT = 30000; // seconds
    protected static boolean validOS = true;
    protected WebDriver driver;
    static final String CHROME = "chrome";

    public static void main(String[] args) {
        String[] arg = {"firefox"};
        if (null == arg) {
            throw new NullPointerException("Argument not a null");
        }
        Main main = new Main();
        if (CHROME.equals(arg[0])) {
            main.setUpChrome();
        } else {
            main.setUpFireFox();
        }

        main.autoShare();
    }

    private void setUpChrome() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
    }

    private void setUpFireFox() {
        FirefoxDriverManager.getInstance().setup();
//        DesiredCapabilities cap = DesiredCapabilities.firefox();
//        System.out.println(System.getProperty("java.class.path"));
//        System.out.println(com.google.common.collect.ImmutableSet.class.getResource("ImmutableSet.class"));
        driver = new FirefoxDriver();
    }
    private void autoShare() {
        long timeStart = System.currentTimeMillis();
        if (validOS) {
            WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
            final String url = "http://www.globalsources.com/gsol/I/Car-DVR/p/sm/1138545156.htm#1138545156";
//            driver.get("http://www.10puzzle.com/piece/28317-t%E1%BA%A5t-ni%C3%AAn-new-year-s-eve-party-ikornsolutions-vn.html");

            driver.get(url);

            By producNameCss = By.cssSelector("div.pp_box.mt20.clearfix > div.pp_con_detail >p");

            String productName = driver.findElements(producNameCss).get(0).getText();

            // link company
            By companyLinkCss = By.cssSelector("div.pp_box.mt20.clearfix > div.pp_supDetail > ul.pp_supInfo > li.pp_supName.mt5 > a");
            String linkCompany = driver.findElement(companyLinkCss).getAttribute("href");

            // image
            By imgCss = By.cssSelector(" div.pp_box.mt20.clearfix > div.pp_img_box  > div.pp_con_img.ui_scroll_section.clearfix " +
                    "> div.pp_con_bigImg > div.img_frame.ui_scroll_win > div.img_frame_con.ui_scroll_con " +
                    "> img");
            String imgUrl = driver.findElement(imgCss).getAttribute("src");
            // price
            By priceCss = By.cssSelector("div.pp_box.mt20.clearfix  div.pp_con_detail > ul.pp_mInfo > li > strong.pp_mFOB > em");
            String prices = driver.findElement(priceCss).getText();
            // valueband
            By valueBandCss = By.cssSelector("p.path.mt5 > a");
            List<WebElement> valueBandTag = driver.findElements(valueBandCss);
            int size = valueBandTag.size();

            String valuebandName = driver.findElements(valueBandCss).get(size - 1).getText();


            getCompanyInfo(linkCompany);

            driver.quit();

            System.out.println("Bind click success");
            long timeE = System.currentTimeMillis();

            System.out.println("Time: " + (timeE - timeStart)/1000);
        }
    }

    private void getCompanyInfo(String url) {
        driver.get(url);
        By logoCss = By.cssSelector("div.mw_wrapper.grayTpl > div.spNameInfo " +
                "> div.spNameInfoLeft > a.spLogo > img");
        String logo = driver.findElement(logoCss).getAttribute("src");

        By addressCss = By.cssSelector("div.spContact.clearfix > div.spCompanyInfo.fl > " +
                "address.clearfix > span");
        String address = driver.findElement(addressCss).getText();
        System.out.print(logo+  address);
    }

}
