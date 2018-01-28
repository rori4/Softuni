package com.rangelstoilov.marketing.submitter.models;

import com.rangelstoilov.marketing.utils.SMSPVA;
import com.rangelstoilov.marketing.entities.Persona;
import com.rangelstoilov.marketing.exceptions.SMSPVA.ExpensiveNumbersException;
import com.rangelstoilov.marketing.exceptions.SMSPVA.InsufficientBalanceException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.time.Month;
import java.util.Map;

public class PersonaCreator{
    public static void createGoogleAccount(FirefoxDriver driver, Persona persona) throws InterruptedException, InsufficientBalanceException, ExpensiveNumbersException, IOException {
        //Filling the data
        System.out.println("We are filling your data");
        driver.get("https://accounts.google.com/SignUp");
        driver.findElement(By.id("FirstName")).sendKeys(persona.getFirstName());
        driver.findElement(By.id("LastName")).sendKeys(persona.getLastName());
        driver.findElement(By.id("GmailAddress")).sendKeys(persona.getUsername());
        driver.findElement(By.id("Passwd")).sendKeys(persona.getPassword());
        driver.findElement(By.id("PasswdAgain")).sendKeys(persona.getPassword());

        Month month = persona.getBirthday().getMonth();
        Actions builder = new Actions(driver);
        System.out.println(month.getValue());
        builder.sendKeys(Keys.TAB).perform();
        for (int i = 0; i <= month.getValue(); i++) {
            builder.sendKeys(Keys.ARROW_DOWN).perform();
        }
        builder.sendKeys(Keys.ENTER).perform();
        driver.findElement(By.id("BirthDay")).sendKeys(String.valueOf(persona.getBirthday().getDayOfMonth()));
        driver.findElement(By.id("BirthYear")).sendKeys(String.valueOf(persona.getBirthday().getYear()));
        builder.sendKeys(Keys.TAB).perform();
        if (persona.getGender().equals("Male")){
            builder.sendKeys(Keys.ARROW_DOWN).perform();
            builder.sendKeys(Keys.ARROW_DOWN).perform();
        } else {
            builder.sendKeys(Keys.ARROW_DOWN).perform();
        }
        builder.sendKeys(Keys.ENTER).perform();

        //Getting your phone number
        System.out.println("We are getting you a real phone number...");
        driver.findElement(By.name("RecoveryPhoneNumber")).clear();
        Map<String, String> mapNumber = SMSPVA.checkAccountGetNumber();
        String number = mapNumber.get("number");
        String countryCode = mapNumber.get("CountryCode");
        String id = mapNumber.get("id");
        System.out.println("We continure to fill the data");
        driver.findElement(By.id("RecoveryPhoneNumber")).sendKeys(countryCode);
        driver.findElement(By.id("RecoveryPhoneNumber")).sendKeys(number);
        Thread.sleep(1000);
        builder.sendKeys(Keys.TAB).perform();
        builder.sendKeys(Keys.TAB).perform();
        Thread.sleep(1000);
//        builder.sendKeys(Keys.chord("russia")).perform();
//        Thread.sleep(1000);
        builder.sendKeys(Keys.chord("uni")).perform();        Thread.sleep(1000);
        builder.sendKeys(Keys.chord("uni")).perform();
        Thread.sleep(1000);
        driver.findElement(By.id("submitbutton")).click();
        driver.findElement(By.id("tos-text")).click();
        //Confirming submission
        System.out.println("We accept everything that is needed");
        builder.sendKeys(Keys.PAGE_DOWN).perform();
        builder.sendKeys(Keys.PAGE_DOWN).perform();
        builder.sendKeys(Keys.PAGE_DOWN).perform();
        Thread.sleep(2000);
        String bodyText = driver.findElement(By.tagName("body")).getText();
        if (!bodyText.contains("Welcome!")){
            driver.findElement(By.id("iagreebutton")).click();
            Thread.sleep(2000);
            driver.findElement(By.id("next-button")).click();
            bodyText = driver.findElement(By.tagName("body")).getText();
            if (bodyText.contains("This phone number cannot be used for verification.")){
                //TODO: ban the phoneNumber
            }
            for (int i = 10; i >= 0; i--) {
                System.out.println(String .format("We will check for SMS code in %d seconds", i));
                Thread.sleep(1000);
            }
            String smsCode = SMSPVA.getSMSCode(id);
            while (smsCode==null){
                for (int i = 30; i >= 0; i--) {
                    System.out.println(String.format("We will try gain in %d seconds", i));
                    Thread.sleep(1000);
                }
                smsCode = SMSPVA.getSMSCode(id);
            }
            driver.findElement(By.id("verify-phone-input")).sendKeys(smsCode);
            driver.findElement(By.name("VerifyPhone")).click();
        }
        System.out.println("We created Gmail successfully! Going on...");
        Thread.sleep(2000);
    }

}

