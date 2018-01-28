package com.example.demo.submitter;

import com.example.demo.entities.Giveaway;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.lang.reflect.Method;

public class WebsiteSubmitter {

    WebDriver driver;

    public void invokeSession(Giveaway giveaway) {

        try {
            //System.setProperty("webdriver.chrome.driver","D:\\OneDrive\\Softuni\\Selenium\\chromedriver_win32\\chromedriver.exe");
            //driver = new ChromeDriver();

            System.setProperty("webdriver.gecko.driver", "D:\\OneDrive\\Softuni\\Selenium\\geckodriver-v0.17.0-win64\\geckodriver.exe");

            String PROXY = "210.16.120.243:3128";

            FirefoxProfile profile = new FirefoxProfile();
//            profile.setPreference("network.proxy.http", "210.16.120.243");
//            profile.setPreference("network.proxy.http_port", "3128");
            profile.setPreference("dom.file.createInChild", true);
            FirefoxDriver driver = new FirefoxDriver(profile);
//            assert driver.getCapabilities().getCapability("proxy") == PROXY;


            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
//            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);


            for (Method method : WebsiteActions.class.getMethods()) {
                if (method.getName().startsWith("submit")){
                    try{
                        method.invoke(null,driver,giveaway);
                        System.out.println("Successful submission!");
                        Thread.sleep(1000);
                    } catch (Exception e){
                        System.out.println("Problem with " + method.getName());
                    }
                }
            }
//			WebsiteActions.initiateSubmitionToAll(driver,giveaway);
//			WebsiteActions.submitToBigSweeps(driver,giveaway);
//			WebsiteActions.submitToGiveawayJunkie(driver,giveaway);
//			WebsiteActions.submitToHyperSweep(driver,giveaway);
//			WebsiteActions.submitToInfiniteSweeps(driver,giveaway);
//			WebsiteActions.submitToLuckyContests(driver,giveaway);
//			WebsiteActions.submitToSweepstakesBug(driver,giveaway);
//			WebsiteActions.submitToSweepstakesSearch(driver,giveaway);
//			WebsiteActions.submitToAnyLuckyDay(driver,giveaway);
//			WebsiteActions.submitToBlogGiveawayDirectory(driver, giveaway);
//			WebsiteActions.submitToContestCanada(driver,giveaway);
//          WebsiteActions.submitToSweepstakesSearch(driver, giveaway);
//          WebsiteActions.submitToContestCorner(driver,giveaway);
//          WebsiteActions.submitToContestGirl(driver,giveaway);
//          WebsiteActions.submitToDoublePrizes(driver, giveaway);
//          WebsiteActions.submitToEmperola(driver, giveaway);
//            WebsiteActions.submitToGiveawayFrenzy(driver,giveaway);
//            WebsiteActions.submitToGiveawayMonkey(driver,giveaway);
//            WebsiteActions.paidSubmitToGiveawayPromote(driver,giveaway);
//            WebsiteActions.submitToILoveGiveaways(driver,giveaway);
//            WebsiteActions.submitToInternationalGiveaways(driver,giveaway);
//            WebsiteActions.submitToJuliesFreebies(driver,giveaway);
//            WebsiteActions.submitToJustSweep(driver,giveaway);
//            WebsiteActions.submitToOnlineSweepstakes(driver,giveaway);
//            WebsiteActions.submitToRedditContests(driver,giveaway);
//            WebsiteActions.hardSubmitToRedditGiveaways(driver,giveaway);
//            WebsiteActions.submitToSweepstakesMax(driver,giveaway);
//            WebsiteActions.submitToSweepstakesMag(driver,giveaway);
//            WebsiteActions.submitToSweepstakesLovers(driver,giveaway);
//            WebsiteActions.submitToWinASweepstakes(driver,giveaway);
            driver.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
