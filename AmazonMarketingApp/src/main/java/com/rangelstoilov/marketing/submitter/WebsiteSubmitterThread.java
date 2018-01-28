package com.rangelstoilov.marketing.submitter;

import com.rangelstoilov.marketing.entities.Persona;
import com.rangelstoilov.marketing.exceptions.SMSPVA.ExpensiveNumbersException;
import com.rangelstoilov.marketing.exceptions.SMSPVA.InsufficientBalanceException;
import com.rangelstoilov.marketing.submitter.models.PersonaCreator;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.awt.*;
import java.lang.reflect.Method;

public class WebsiteSubmitterThread extends Thread {

    WebDriver driver;

    public WebsiteSubmitterThread invokeSession(Object object) throws InsufficientBalanceException, ExpensiveNumbersException {

        try {
            System.setProperty("webdriver.gecko.driver", "src/app/resources/drivers/geckodriver.exe");
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("media.peerconnection.enabled",false);
            WebDriver driver = new FirefoxDriver(profile);
            driver.manage().deleteAllCookies();
            java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int width = (int) (screenSize.getWidth() * 0.81);
            int height = (int) (screenSize.getHeight() - 33);
            Dimension dimension = new Dimension(width, height);
            Point position = new Point(-5, 0);
            driver.manage().window().setSize(dimension);
            driver.manage().window().setPosition(position);
//            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);


            if (object instanceof Persona) {
//                PersonaCreator.createGoogleAccount((FirefoxDriver) driver, (Persona) object);
                for (Method method : PersonaCreator.class.getMethods()) {
                    if (method.getName().startsWith("create")) {
                        method.invoke(null, driver, object);
                        Thread.sleep(1000);
                    }
                }
            }

//            driver.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
