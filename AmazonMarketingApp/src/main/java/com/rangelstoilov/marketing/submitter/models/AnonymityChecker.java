package com.rangelstoilov.marketing.submitter.models;

import com.rangelstoilov.marketing.entities.Persona;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AnonymityChecker {
    public static void checkAnanymity(FirefoxDriver driver, Persona persona) throws InterruptedException {
        driver.get("https://whoer.net/");
        Thread.sleep(10000);
    }
}
