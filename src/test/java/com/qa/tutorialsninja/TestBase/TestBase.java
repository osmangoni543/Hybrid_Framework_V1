package com.qa.tutorialsninja.TestBase;

import com.qa.tutorialsninja.Utilities.Util;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class TestBase {

    public WebDriver driver;
    public ChromeOptions options;
    public Properties prop;
    public FileInputStream ip;
    public Properties dataprop;

    public TestBase() throws Exception {
        prop = new Properties();
        ip = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\com\\qa\\tutorialsninja\\config\\config.properties");
        prop.load(ip);

        dataprop = new Properties();
        ip = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\com\\qa\\tutorialsninja\\TestData\\testData.properties");
        dataprop.load(ip);
    }

    public WebDriver initializeBrowserAndOpenApplication(String browserName) {

        if(browserName.equals("chrome")) {
            options = new ChromeOptions();
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);
            options.addArguments("--incognito");
            options.addArguments("--start-maximized");
            options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation","disable-infobars"));
            this.driver = new ChromeDriver(options);
        } else if(browserName.equals("firefox")){
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        } else if(browserName.equals("edge")) {
            driver = new EdgeDriver();
            driver.manage().window().maximize();
        }
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Util.IMPLICIT_WAIT_TIME));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Util.PAGE_LOAD_TIME));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Util.SCRIPT_TIME));
        driver.get(prop.getProperty("url"));
        return driver;
    }

}
