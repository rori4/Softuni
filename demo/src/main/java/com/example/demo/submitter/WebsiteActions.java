package com.example.demo.submitter;

import com.example.demo.entities.Giveaway;
import com.example.demo.submitter.credentials.Constants;
import com.example.demo.utils.DateUtil;
import com.example.demo.utils.ModifyUtil;
import com.example.demo.utils.SolverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.text.ParseException;

public class WebsiteActions {
    //TODO: Try catch to see if there needs to be an update and also send some info reports
    public static void initiateSubmitionToAll(FirefoxDriver driver, Giveaway giveaway) throws InterruptedException, InvocationTargetException, IllegalAccessException {
        Method[] methods = WebsiteActions.class.getMethods();
        for (Method m : methods) {
            if (m.getName().startsWith("submit")) {
                m.invoke(null, driver, giveaway);
                Thread.sleep(2000);
            }
        }
    }

    public static void submitToAboutCom(FirefoxDriver driver, Giveaway giveaway) throws InterruptedException {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfHuGZUd598gC-9001vXm3MSB-2jnLpBulGoXQPy58lfRa-Lg/viewform");
        driver.findElement(By.id("entry_1579684552")).sendKeys(giveaway.getSponsor());
        driver.findElement(By.id("entry_668817024")).sendKeys(giveaway.getSweepstakesName());
        driver.findElement(By.id("entry_1313070778")).sendKeys(giveaway.getUrl());
        driver.findElement(By.id("entry_1103244773")).sendKeys(giveaway.getEndDate().toString());
        driver.findElement(By.id("entry_1448572385")).sendKeys(giveaway.getFrequency());
        driver.findElement(By.id("entry_939182932")).sendKeys(giveaway.getRestrictions());
        driver.findElement(By.id("entry_403716395")).sendKeys(giveaway.getPrize());
        driver.findElement(By.id("entry_1006442532")).sendKeys(giveaway.getComment());
        driver.findElement(By.id("entry_1868501108")).sendKeys(giveaway.getPersonalName());
        driver.findElement(By.id("entry_1338926819")).sendKeys(giveaway.getEmail());
        //driver.findElement(By.id("ss-submit")).click();
    }

    //TODO: Javascript problem
    public static void fixSubmitToBigSweeps(FirefoxDriver driver, Giveaway giveaway) throws ParseException, InterruptedException {
        driver.get("http://www.bigsweeps.com/");
        if (driver.findElement(By.id("user_name")).isDisplayed()) {
            driver.findElement(By.id("user_name")).sendKeys(Constants.USERNAME);
            driver.findElement(By.id("user_password")).sendKeys(Constants.PASSWORD);
            driver.findElement(By.xpath("/html/body/div[@id='topbar']/span[@class='px14']/form/button[@class='roundgreen']")).click();
        }
        driver.get("http://www.bigsweeps.com/sweepsform_user.cfm");
        System.out.println(driver.getPageSource());

        driver.findElement(By.linkText("Enabling Javascript")).click();


        driver.findElement(By.id("sweep_entryurl")).sendKeys(giveaway.getUrl());
        driver.findElement(By.id("sweep_sponsorco")).sendKeys(giveaway.getSponsor());
        try {
            driver.findElement(By.id("sweep_homeurl")).sendKeys(ModifyUtil.trimURL(giveaway.getUrl()));
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }
        driver.findElement(By.id("sweep_rulesurl")).sendKeys(giveaway.getUrl());
        driver.findElement(By.id("sweep_winnerurl")).sendKeys(giveaway.getUrl());
        driver.findElement(By.id("sweep_startdate")).sendKeys(DateUtil.getDateTodayWithSlashes());
        driver.findElement(By.id("sweep_enddate")).sendKeys(DateUtil.changeDateToSlashes(giveaway.getEndDate().toString()));
        driver.findElement(By.id("sweep_drawingdate")).sendKeys(DateUtil.changeDateToSlashes(giveaway.getEndDate().toString()));
        driver.findElement(By.id("sweep_enterfreq")).sendKeys(giveaway.getFrequency());
        Select enterFrequency = new Select(driver.findElement(By.id("sweep_enterfreqper")));
        enterFrequency.selectByValue("d");
    }

    public static void submitToGiveawayJunkie(FirefoxDriver driver, Giveaway giveaway) throws ParseException, InterruptedException {
        driver.get("http://giveawayjunkie.com/submit_your_giveaway.php");
        driver.findElement(By.id("title")).sendKeys(giveaway.getSweepstakesName());
        driver.findElement(By.id("desc")).sendKeys(giveaway.getDescription());
        driver.findElement(By.id("url")).sendKeys(giveaway.getUrl());

        new Select(driver.findElement(By.id("month"))).selectByValue(ModifyUtil.addZero(DateUtil.stringDateMonth(giveaway.getEndDate().toString())));
        new Select(driver.findElement(By.id("day"))).selectByValue(ModifyUtil.addZero(DateUtil.stringDateDay(giveaway.getEndDate().toString())));
        new Select(driver.findElement(By.id("year"))).selectByValue(DateUtil.stringDateYear(giveaway.getEndDate().toString()));
        new Select(driver.findElement(By.id("category"))).selectByVisibleText("Home");

        driver.findElement(By.xpath(".//*[@id='sign-up-form']/fieldset/div[3]/div[6]/div/label[2]/input")).click();
        driver.findElement(By.xpath(".//*[@id='sign-up-form']/fieldset/div[3]/div[6]/div/label[3]/input")).click();
        driver.findElement(By.xpath(".//*[@id='sign-up-form']/fieldset/div[3]/div[6]/div/label[4]/input")).click();

        driver.findElement(By.id("email2")).sendKeys(giveaway.getEmail());

        driver.findElement(By.name("button")).click();
    }

    public static void submitToHyperSweep(FirefoxDriver driver, Giveaway giveaway) throws ParseException {
        driver.get("http://www.hypersweep.com/submit/submit.cfm");
        driver.findElement(By.id("subsearch")).sendKeys(giveaway.getUrl());
        driver.findElement(By.name("Submit")).click();
        driver.findElement(By.linkText("To Submission Form >>")).click();

        driver.findElement(By.name("CoName")).sendKeys(giveaway.getSponsor());
        driver.findElement(By.name("EventName")).sendKeys(giveaway.getSweepstakesName());
        driver.findElement(By.name("Details")).sendKeys(giveaway.getDescription());
        driver.findElement(By.name("PrizeHeadline")).sendKeys(giveaway.getPrize());
        driver.findElement(By.name("ImgURL")).sendKeys(giveaway.getGiveawayImgUrl());
        driver.findElement(By.name("CashValue")).sendKeys(giveaway.getPrizeValue().toString());

        //TODO: Add categories to Giveaway class and map them
        new Select(driver.findElement(By.name("PrizeCategory"))).selectByValue("32"); // Arts & Crafts/Knitting & Sewing
        new Select(driver.findElement(By.name("PrizeCategory2"))).selectByValue("20"); // Media - Books/Music & Movies
        new Select(driver.findElement(By.name("PrizeCategory3"))).selectByValue("1"); // Misc/Other

        new Select(driver.findElement(By.name("Entry"))).selectByValue("10"); // Unlimited

        new Select(driver.findElement(By.name("Eligibility"))).selectByValue("USA And Canada");
        driver.findElement(By.name("Ages")).sendKeys(giveaway.getRestrictions());

        driver.findElement(By.name("EndDate")).sendKeys(DateUtil.changeDateToSlashes(giveaway.getEndDate().toString()));
        driver.findElement(By.name("LinkBack")).sendKeys(giveaway.getUrl());
        driver.findElement(By.name("Submit")).click();
    }

    public static void submitToInfiniteSweeps(FirefoxDriver driver, Giveaway giveaway) throws ParseException {
        driver.get("http://www.infinitesweeps.com/suggest.php");
        driver.findElement(By.id("url")).sendKeys(giveaway.getUrl());
        driver.findElement(By.name("urlrules")).sendKeys(giveaway.getUrl());
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.id("burl")).sendKeys(giveaway.getUrl());

        driver.findElement(By.id("end")).click();

        ((JavascriptExecutor) driver).executeScript("document.getElementById('end').removeAttribute('readonly',0);"); // Enables the from date box

        WebElement toDateBox = driver.findElement(By.id("end"));
        toDateBox.clear();
        toDateBox.sendKeys(DateUtil.changeDateToDashes(giveaway.getEndDate().toString()));

        new Select(driver.findElement(By.name("entry"))).selectByValue("3"); // Daily Entry
        new Select(driver.findElement(By.name("category"))).selectByValue("2"); //Category Others

        driver.findElement(By.name("notes")).sendKeys(giveaway.getDescription());
        driver.findElement(By.id("email")).sendKeys(giveaway.getEmail());
        driver.findElement(By.name("submit")).click();
    }

    public static void submitToLuckyContests(FirefoxDriver driver, Giveaway giveaway) throws InterruptedException {
        driver.get("http://www.luckycontests.com/contact/");
        driver.findElement(By.id("contactName")).sendKeys(giveaway.getPersonalName());
        driver.findElement(By.id("email")).sendKeys(giveaway.getEmail());

        StringBuilder msg = new StringBuilder();
        msg.append(giveaway.getSweepstakesName() + "\n\n");
        msg.append(giveaway.getDescription() + "\n\n");
        msg.append("Giveaway link -> " + giveaway.getUrl());

        driver.findElement(By.id("commentsText")).sendKeys(msg);

        driver.findElement(By.id("mathCheck")).sendKeys("9");
        driver.findElement(By.id("sendCopy")).click();

        driver.findElement(By.className("submit")).click();
        Thread.sleep(5000);
    }

    public static void submitToSweepstakesBug(FirefoxDriver driver, Giveaway giveaway) {
        driver.get("http://www.sweepstakesbug.com/submit/");
        driver.findElement(By.id("author")).sendKeys(giveaway.getPersonalName());
        driver.findElement(By.id("email")).sendKeys(giveaway.getEmail());
        driver.findElement(By.id("url")).sendKeys(giveaway.getUrl());

        StringBuilder msg = new StringBuilder();
        msg.append(giveaway.getSweepstakesName() + "\n\n");
        msg.append(giveaway.getDescription() + "\n\n");
        msg.append("Giveaway link -> " + giveaway.getUrl());

        driver.findElement(By.id("comment")).sendKeys(msg);
        driver.findElement(By.id("submit")).click();
    }

    //has captcha
    public static void submitToSweepstakesSearch(FirefoxDriver driver, Giveaway giveaway) throws ParseException, Exception, InterruptedException, IOException {
        driver.get("http://www.sweepstakes-search.com/add-sweepstakes.html");
        driver.findElement(By.name("sponsor")).sendKeys(giveaway.getSponsor());
        driver.findElement(By.name("url")).sendKeys(giveaway.getUrl());
        driver.findElement(By.name("sweepstakes")).sendKeys(giveaway.getDescription());
        driver.findElement(By.name("prizeval")).clear();
        driver.findElement(By.name("prizeval")).sendKeys(giveaway.getPrizeValue().toString());


        driver.findElement(By.name("enddate")).clear();
        driver.findElement(By.name("enddate")).sendKeys(DateUtil.changeDateToDashes(giveaway.getEndDate().toString()));
        driver.findElement(By.name("sponsor")).click();
        Thread.sleep(1000);

        new Select(driver.findElement(By.name("category"))).selectByValue("Miscellaneous");
        new Select(driver.findElement(By.name("frequency"))).selectByValue("Daily");

        driver.findElement(By.xpath(".//*[@id='countries']/input[2]")).click();
        driver.findElement(By.xpath(".//*[@id='countries']/input[4]")).click();
        driver.findElement(By.xpath(".//*[@id='countries']/input[5]")).click();

        driver.findElement(By.name("eligibility")).sendKeys(giveaway.getRestrictions());
        driver.findElement(By.name("email")).sendKeys(giveaway.getEmail());

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,250)", "");

        //keep in mind if you have scrolled
        SolverUtil.reCaptchaSolver(driver);

        //driver.findElement(By.xpath(".//*[@id='center']/form/p[5]/input")).click();

        //driver.findElement(By.linkText("Add Sweepstakes")).click();
    }

    //has file submit
    public static void submitToAnyLuckyDay(FirefoxDriver driver, Giveaway giveaway) throws MalformedURLException, ParseException {
        driver.get("http://www.anyluckyday.com/submit-giveaway");

        driver.findElement(By.id("field1")).sendKeys(giveaway.getUrl());
        driver.findElement(By.id("field2")).sendKeys(giveaway.getEmail());
        driver.findElement(By.id("field3")).sendKeys(giveaway.getSweepstakesName());
        driver.findElement(By.id("field4")).sendKeys(giveaway.getDescription());
        driver.findElement(By.id("field5")).sendKeys(ModifyUtil.imageAsSource(giveaway).getAbsolutePath());
        new Select(driver.findElement(By.name("openTo"))).selectByValue("US & Canada");
        new Select(driver.findElement(By.id("field7"))).selectByValue(DateUtil.stringDateMonth(giveaway.getEndDate().toString()));
        new Select(driver.findElement(By.id("field8"))).selectByValue(DateUtil.stringDateDay(giveaway.getEndDate().toString()));
        new Select(driver.findElement(By.id("field9"))).selectByValue(DateUtil.stringDateYear(giveaway.getEndDate().toString()));
        driver.findElement(By.className("bt-next")).click();
        driver.findElement(By.className("free-bg")).click();
        driver.switchTo().alert().accept();
    }

    public static void submitToBlogGiveawayDirectory(FirefoxDriver driver, Giveaway giveaway) throws MalformedURLException, ParseException {
        driver.get("https://www.bloggiveawaydirectory.com/submit-a-giveaway/");
        driver.findElement(By.id("input_14_1")).sendKeys(giveaway.getSweepstakesName());
        driver.findElement(By.id("input_14_2")).sendKeys(giveaway.getDescription());
        driver.findElement(By.id("input_14_3")).sendKeys(ModifyUtil.imageAsSource(giveaway).getAbsolutePath());
        new Select(driver.findElement(By.id("input_14_4"))).selectByValue("3737");
        driver.findElement(By.id("input_14_5")).sendKeys(DateUtil.changeDateToSlashes(giveaway.getEndDate().toString()));
        driver.findElement(By.id("choice_14_6_1")).click();
        driver.findElement(By.id("choice_14_6_2")).click();
        driver.findElement(By.id("choice_14_6_3")).click();
        driver.findElement(By.id("choice_14_6_4")).click();
        new Select(driver.findElement(By.id("input_14_7"))).selectByValue("3732");
        new Select(driver.findElement(By.id("input_14_8"))).selectByValue("3722");
        driver.findElement(By.id("input_14_9")).sendKeys(giveaway.getUrl());
        new Select(driver.findElement(By.id("input_14_10"))).selectByValue("3715");
        driver.findElement(By.id("choice_14_11_6")).click();
        driver.findElement(By.id("choice_14_11_12")).click();
        driver.findElement(By.id("choice_14_11_14")).click();
        String[] personalName = giveaway.getPersonalName().split("\\s");
        driver.findElement(By.id("input_14_12_3")).sendKeys(personalName[0]);
        driver.findElement(By.id("input_14_12_6")).sendKeys(personalName[1]);
        driver.findElement(By.id("input_14_13")).sendKeys(giveaway.getEmail());

        driver.findElement(By.id("choice_14_14_0")).click();

        driver.findElement(By.id("choice_14_15_1")).click();
        driver.findElement(By.id("choice_14_16_1")).click();

        driver.findElement(By.id("choice_14_23_1")).click();

        driver.findElement(By.id("gform_submit_button_14")).click();
    }

    public static void submitToContestCanada(FirefoxDriver driver, Giveaway giveaway) throws ParseException, InterruptedException {
        driver.get("http://lookcontests.com/submits/new");
        driver.findElement(By.id("submit_name")).sendKeys(giveaway.getSweepstakesName());
        driver.findElement(By.id("submit_company")).sendKeys(giveaway.getSponsor());
        driver.findElement(By.id("submit_url")).sendKeys(giveaway.getUrl());
        ((JavascriptExecutor) driver).executeScript("document.getElementById('submit_expiry_date').removeAttribute('readonly',0);"); // Enables the from date box

        driver.findElement(By.id("submit_expiry_date")).clear();
        driver.findElement(By.id("submit_expiry_date")).sendKeys(DateUtil.changeDateToDashes(giveaway.getEndDate().toString()));
        new Select(driver.findElement(By.id("submit_frequency"))).selectByValue("daily");
        driver.findElement(By.id("submit_contact_info")).sendKeys(giveaway.getEmail());
        new Select(driver.findElement(By.id("submit_country"))).selectByValue("can/usa");
        driver.findElement(By.id("submit_description")).sendKeys(giveaway.getDescription());
        driver.findElement(By.id("submit_submit")).click();
    }

    public static void submitToContestChest(FirefoxDriver driver, Giveaway giveaway) throws ParseException {
        driver.get("http://www.contestchest.com/list-a-contest");
        driver.findElement(By.id("log-in-button2")).click();
        driver.findElement(By.id("email")).sendKeys(Constants.CONTEST_CHEST_USERNAME);
        driver.findElement(By.id("password")).sendKeys(Constants.CONTEST_CHEST_PASSWORD);
        driver.findElement(By.className("btn-primary")).click();
        driver.findElement(By.id("contestUrl")).sendKeys(giveaway.getUrl());
        driver.findElement(By.id("dontOpenInFrame")).click();
        driver.findElement(By.id("urlCheckButton")).click();
        driver.findElement(By.className("btn-primary")).click();

        try {
            driver.findElement(By.id("year")).sendKeys(DateUtil.stringDateYear(giveaway.getEndDate().toString()));
            driver.findElement(By.id("month")).sendKeys(DateUtil.stringDateMonth(giveaway.getEndDate().toString()));
            driver.findElement(By.id("day")).sendKeys(DateUtil.stringDateDay(giveaway.getEndDate().toString()));

            new Select(driver.findElement(By.id("entryFrequency"))).selectByValue("4");
            driver.findElement(By.id("title")).sendKeys(giveaway.getSweepstakesName());
            driver.findElement(By.id("prize")).sendKeys(giveaway.getDescription());
            driver.findElement(By.id("arv")).clear();
            driver.findElement(By.id("arv")).sendKeys(giveaway.getPrizeValue().toString());
            driver.findElement(By.id("category_3")).click();
            driver.findElement(By.id("category_16")).click();
            driver.findElement(By.id("whatYouMustDoToEnter")).sendKeys("Enter name and email for contacting if you win");
            driver.findElement(By.id("requiresAgeOfMajority")).click();
            driver.findElement(By.id("select-eu")).click();
            driver.findElement(By.id("select-all-181")).click();
            driver.findElement(By.id("select-b-182")).click();
        } catch (NoSuchElementException e) {
            System.out.println("Probably contest exists: " + e.getMessage());
        }

        try {
            driver.findElement(By.linkText("Pick an image...")).click();
            driver.findElement(By.id("pageUrl")).sendKeys(giveaway.getUrl());
            driver.findElement(By.id("save-button")).click();
            driver.findElement(By.id("html/body/form/input[4]")).click();

            while (!driver.findElement(By.id("save-button")).isEnabled()) {
                driver.findElement(By.id("reduce-button")).click();
            }
            driver.findElement(By.id("name")).sendKeys(giveaway.getSweepstakesName());
            driver.findElement(By.id("save-button")).click();
        } catch (NoSuchElementException e) {
            System.out.println("The image is already uploaded: " + e.getMessage());
        }

    }

    public static void submitToContestCorner(FirefoxDriver driver, Giveaway giveaway) throws ParseException, Exception, InterruptedException, IOException {
        driver.get("http://www.contest-corner.com/submit-a-giveaway/");
        driver.findElement(By.id("input_1_1")).sendKeys(giveaway.getSponsor());
        driver.findElement(By.id("input_1_6")).sendKeys(giveaway.getPrize());
        driver.findElement(By.id("input_1_5")).sendKeys("Enter to win " + giveaway.getPrize() + " \n" + giveaway.getDescription());
        driver.findElement(By.id("input_1_3")).sendKeys(DateUtil.changeDateToSlashes(giveaway.getEndDate().toString()));
        driver.findElement(By.id("input_1_2")).clear();
        driver.findElement(By.id("input_1_2")).sendKeys(giveaway.getUrl());
        driver.findElement(By.id("input_1_4")).sendKeys(giveaway.getRestrictions());
        //keep in mind if you have scrolled
        SolverUtil.reCaptchaSolver(driver);
        driver.findElement(By.id("gform_submit_button_1")).click();
    }

    public static void submitToContestGirl(FirefoxDriver driver, Giveaway giveaway) throws ParseException, InterruptedException {
        driver.get("http://www.contestgirl.com/newGiveaway.pl");
        driver.findElement(By.name("url")).sendKeys(giveaway.getUrl());
        driver.findElement(By.name("siteName")).sendKeys(giveaway.getSponsor());
        driver.findElement(By.name("sweepsName")).sendKeys(giveaway.getSweepstakesName());
        driver.findElement(By.xpath(".//*[@id='center_text']/center[2]/form/table/tbody/tr[4]/td[2]/textarea")).sendKeys(giveaway.getPrize() + "\n" + giveaway.getDescription());
        driver.findElement(By.name("ca")).click();
        driver.findElement(By.name("us")).click();

        driver.findElement(By.name("expiry")).sendKeys(DateUtil.changeDateToSlashes(giveaway.getEndDate().toString()));

        driver.findElement(By.xpath(".//*[@id='center_text']/center[2]/form/table/tbody/tr[8]/td[2]/input[2]")).click();
        driver.findElement(By.xpath(".//*[@id='center_text']/center[2]/form/table/tbody/tr[10]/td[2]/input[1]")).click();
        driver.findElement(By.name("send")).click();
    }

    public static void submitToDoublePrizes(FirefoxDriver driver, Giveaway giveaway) throws ParseException {
        driver.get("http://www.doubleprizes.com/contests/new");
        driver.findElement(By.id("contest_name")).sendKeys(giveaway.getSweepstakesName());
        driver.findElement(By.id("contest_contest_url")).sendKeys(giveaway.getUrl());
        new Select(driver.findElement(By.id("contest_end_date_2i"))).selectByValue(DateUtil.stringDateMonth(giveaway.getEndDate().toString()));
        new Select(driver.findElement(By.id("contest_end_date_3i"))).selectByValue(DateUtil.stringDateDay(giveaway.getEndDate().toString()));
        new Select(driver.findElement(By.id("contest_end_date_1i"))).selectByValue(DateUtil.stringDateYear(giveaway.getEndDate().toString()));

        driver.findElement(By.id("contest_description")).sendKeys("Enter to win " + giveaway.getPrize() + "\n" + giveaway.getDescription());
        String result = SolverUtil.aritmeticQuestionSolver(driver, ".//*[@id='new_contest']/table/tbody/tr[6]/th/label");
        driver.findElement(By.id("captcha")).sendKeys(result);
        driver.findElement(By.name("submit")).click();
    }

    public static void submitToEmperola(FirefoxDriver driver, Giveaway giveaway) throws ParseException, MalformedURLException {
        driver.get("http://www.emperola.com/submit-contest");
        driver.findElement(By.name("title")).sendKeys(giveaway.getSweepstakesName());
        driver.findElement(By.name("sponsor")).sendKeys(giveaway.getSponsor());
        driver.findElement(By.xpath(".//*[@id='textarea']")).sendKeys(giveaway.getDescription());
        new Select(driver.findElement(By.name("category"))).selectByValue("278");
        driver.findElement(By.name("date")).sendKeys(DateUtil.changeDateToDashes(giveaway.getEndDate().toString()));
        driver.findElement(By.name("web")).clear();
        driver.findElement(By.name("web")).sendKeys(giveaway.getUrl());
        driver.findElement(By.name("st1")).click();
        driver.findElement(By.name("st2")).click();
        driver.findElement(By.name("email")).sendKeys(giveaway.getEmail());
        driver.findElement(By.id("fileInput")).sendKeys(ModifyUtil.imageAsSource(giveaway).getAbsolutePath());
        driver.findElement(By.xpath(".//button[1]")).click();
    }

    public static void submitToGiveawayFrenzy(FirefoxDriver driver, Giveaway giveaway) throws ParseException {
        driver.get("http://giveawayfrenzy.com/giveaway-submit/");
        driver.findElement(By.id("ninja_forms_field_40")).sendKeys(giveaway.getSponsor());
        driver.findElement(By.id("ninja_forms_field_6")).sendKeys(giveaway.getUrl());
        driver.findElement(By.id("ninja_forms_field_7")).sendKeys(giveaway.getSweepstakesName());
        driver.findElement(By.id("ninja_forms_field_8")).sendKeys(DateUtil.changeDateToSlashes(giveaway.getEndDate().toString()));
        driver.findElement(By.id("ninja_forms_field_36_0")).click();
        driver.findElement(By.id("ninja_forms_field_36_1")).click();
        driver.findElement(By.id("ninja_forms_field_12")).sendKeys(giveaway.getEmail());
        driver.findElement(By.id("ninja_forms_field_4")).sendKeys("7");
        driver.findElement(By.id("ninja_forms_field_5")).click();
    }

    //TODO: slow loading page gives issue
    public static void fixSubmitToGieawayHunt(FirefoxDriver driver, Giveaway giveaway) throws MalformedURLException, ParseException, InterruptedException {
        driver.get("http://giveawayhunt.com/submit-your-giveaway-for-free/");

        String[] personalName = giveaway.getPersonalName().split("\\s");

        driver.findElement(By.name("Field2")).sendKeys(personalName[0]);
        driver.findElement(By.name("Field3")).sendKeys(personalName[1]);
        driver.findElement(By.name("Field6")).sendKeys(giveaway.getSweepstakesName());
        driver.findElement(By.name("Field8")).sendKeys(giveaway.getSponsor());
        driver.findElement(By.name("Field9")).sendKeys(giveaway.getDescription());
        driver.findElement(By.name("Field10")).sendKeys(giveaway.getPrize());
        new Select(driver.findElement(By.name("Field11"))).selectByValue("Other automated form");
        driver.findElement(By.name("Field13")).sendKeys(giveaway.getUrl());
        driver.findElement(By.name("Field14-1")).sendKeys(ModifyUtil.addZero(DateUtil.stringDateMonth(giveaway.getEndDate().toString())));
        driver.findElement(By.name("Field14-1")).sendKeys(ModifyUtil.addZero(DateUtil.stringDateDay(giveaway.getEndDate().toString())));
        driver.findElement(By.name("Field14-1")).sendKeys(DateUtil.stringDateYear(giveaway.getEndDate().toString()));
        //TODO: more fields left
    }

    public static void submitToGiveawayMonkey(FirefoxDriver driver, Giveaway giveaway) throws IOException {
        driver.get("http://www.giveawaymonkey.com/submit-giveaway/");
        driver.findElement(By.id("g_url")).sendKeys(giveaway.getUrl());
        driver.findElement(By.id("g_prize")).sendKeys(giveaway.getSweepstakesName());
        driver.findElement(By.id("g_desc")).sendKeys(giveaway.getDescription());
        driver.findElement(By.id("g_date")).sendKeys(giveaway.getEndDate().toString());
        new Select(driver.findElement(By.id("g_meth"))).selectByValue("via Gleam giveaway form");
        new Select(driver.findElement(By.id("g_elig"))).selectByValue("18+");
        driver.findElement(By.id("us")).click();
        driver.findElement(By.id("cn")).click();
        driver.findElement(By.id("uk")).click();
        new Select(driver.findElement(By.id("g_cat"))).selectByValue("12");
        driver.findElement(By.id("g_img")).sendKeys(ModifyUtil.imageAsJpg(giveaway).getAbsolutePath());
        driver.findElement(By.id("g_name")).sendKeys(giveaway.getPersonalName());
        driver.findElement(By.id("g_email")).sendKeys(giveaway.getEmail());
        driver.findElement(By.id("submit")).click();
    }

    //Paid Submission :(
    public static void paidSubmitToGiveawayPromote(FirefoxDriver driver, Giveaway giveaway) throws ParseException, InterruptedException {
        driver.get("http://www.giveawaypromote.com/submit-giveaway/");
        driver.findElement(By.id("gform_next_button_124_46")).click();
        Thread.sleep(1000);
        String[] personalName = giveaway.getPersonalName().split("\\s");
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.id("input_124_36_3")));
        driver.findElement(By.id("input_124_36_3")).sendKeys(personalName[0]);
        driver.findElement(By.id("input_124_36_6")).sendKeys(personalName[1]);
        driver.findElement(By.id("input_124_10")).sendKeys(giveaway.getEmail());
        driver.findElement(By.id("input_124_10_2")).sendKeys(giveaway.getEmail());
        driver.findElement(By.id("input_124_4")).sendKeys(giveaway.getSponsor());
        driver.findElement(By.id("choice_124_21_0")).click();
        driver.findElement(By.id("input_124_56")).sendKeys(giveaway.getUrl());
        driver.findElement(By.id("input_124_1")).sendKeys(giveaway.getPrize());
        driver.findElement(By.id("input_124_3")).sendKeys(giveaway.getDescription());
        driver.findElement(By.id("input_124_17")).sendKeys(DateUtil.changeDateToSlashes(giveaway.getEndDate().toString()));
        new Select(driver.findElement(By.id("input_124_22"))).selectByValue(DateUtil.getMonthName(giveaway.getEndDate().toString()));
        new Select(driver.findElement(By.id("input_124_14"))).selectByValue("4");
        new Select(driver.findElement(By.id("input_124_42"))).selectByValue("Gleam");
        driver.findElement(By.id("choice_124_62_1")).click();
        driver.findElement(By.id("choice_124_62_2")).click();
        driver.findElement(By.id("gform_submit_button_124")).click();
    }

    public static void submitToILoveGiveaways(FirefoxDriver driver, Giveaway giveaway) throws InterruptedException, MalformedURLException, ParseException {
        driver.get("http://www.ilovegiveaways.com/submit-giveaway");
        driver.findElement(By.name("gurl")).sendKeys(giveaway.getUrl());
        driver.findElement(By.name("isblogger")).click();
        driver.findElement(By.xpath(".//*[@id='content-main']/div[2]/div[2]/form/input[3]")).click();
        driver.findElement(By.name("Filedata")).sendKeys(ModifyUtil.imageAsSource(giveaway).getAbsolutePath());
        driver.findElement(By.id("gendson")).clear();
        driver.findElement(By.id("gendson")).sendKeys(DateUtil.changeDateToSlashes(giveaway.getEndDate().toString()) + " 23:59");
        driver.findElement(By.id("gtitle")).sendKeys(giveaway.getSweepstakesName());
        driver.findElement(By.id("gdesc")).sendKeys(giveaway.getDescription());
        driver.findElement(By.xpath(".//*[@id='content-main']/div[2]/center[2]/div[1]/form/div[4]/div[1]/input[1]")).click();
        driver.findElement(By.xpath(".//*[@id='content-main']/div[2]/center[2]/div[1]/form/div[4]/div[2]/input[1]")).click();
        driver.findElement(By.xpath(".//*[@id='content-main']/div[2]/center[2]/div[1]/form/div[4]/div[2]/input[3]")).click();
        driver.findElement(By.xpath(".//*[@id='content-main']/div[2]/center[2]/div[1]/form/div[4]/div[2]/input[4]")).click();
        driver.findElement(By.xpath(".//*[@id='content-main']/div[2]/center[2]/div[1]/form/div[4]/div[3]/input[4]")).click();
        driver.findElement(By.id("ownername")).sendKeys(giveaway.getPersonalName());
        driver.findElement(By.id("owneremail")).sendKeys(giveaway.getEmail());
        driver.findElement(By.name("submit2")).click();


    }

    public static void submitToInternationalGiveaways(FirefoxDriver driver, Giveaway giveaway) throws ParseException {
        driver.get("http://www.123contactform.com/js-form-username-1128409.html?ref=http%3A%2F%2Finternational-giveaways.blogspot.bg%2Fp%2Fsubmit-your-giveaway.html&_referrer_=http%3A%2F%2Fwww.contestlisting.com%2Fcms%2F%3Fid%3D1341[%ANDCHAR%]pin%3D8134&_embedType_=embed.js");
        driver.findElement(By.name("control10217601")).sendKeys(giveaway.getPersonalName());
        driver.findElement(By.id("id123-control9328322")).sendKeys(giveaway.getEmail());
        driver.findElement(By.id("id123-control9328222")).sendKeys(giveaway.getSweepstakesName());
        driver.findElement(By.id("id123-control9328251")).sendKeys(DateUtil.changeDateToSlashes(giveaway.getEndDate().toString()));
        driver.findElement(By.id("id123-control9328241")).sendKeys(giveaway.getUrl());
        driver.findElement(By.xpath(".//*[@id='row6']/div[2]/div/div/label[2]/span[1]/span")).click();
        driver.findElement(By.xpath(".//*[@id='row6']/div[2]/div/div/label[3]/span[1]/span")).click();
        driver.findElement(By.id("id123-control9328278")).sendKeys(giveaway.getPrize());
        driver.findElement(By.id("id123-control9328277")).sendKeys(giveaway.getGiveawayImgUrl());
        driver.findElement(By.xpath(".//*[@id='row9']/div[2]/div/div/label[5]/span[1]/span")).click();
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,250)", "");
        driver.findElement(By.id("id123-button-send")).click();
    }

    public static void submitToJuliesFreebies(FirefoxDriver driver, Giveaway giveaway) throws ParseException, MalformedURLException, InterruptedException {
        driver.get("http://juliesfreebies.com/giveaway-submission-form/");
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.className("popup")));
        System.out.println("popup appeared");
        driver.findElement(By.linkText("[close]")).click();
        driver.findElement(By.id("choice_1_24_0")).click();
        String[] personalName = giveaway.getPersonalName().split("\\s");
        driver.findElement(By.id("input_1_14_3")).sendKeys(personalName[0]);
        driver.findElement(By.id("input_1_14_6")).sendKeys(personalName[1]);
        driver.findElement(By.id("input_1_15")).sendKeys(giveaway.getEmail());
        driver.findElement(By.id("input_1_1")).sendKeys(giveaway.getSweepstakesName());
        driver.findElement(By.id("input_1_8")).sendKeys(giveaway.getUrl());
        driver.findElement(By.id("input_1_16")).sendKeys(giveaway.getUrl());
        driver.findElement(By.id("input_1_2")).sendKeys(String.format("Enter for a chance to win %s \n %s", giveaway.getPrize(), giveaway.getDescription()));
        driver.findElement(By.id("input_1_17")).sendKeys(DateUtil.changeDateToSlashes(giveaway.getEndDate().toString()));
        driver.findElement(By.id("choice_1_11_1")).click();
        driver.findElement(By.id("choice_1_11_2")).click();
        driver.findElement(By.id("choice_1_19_1")).click();
        driver.findElement(By.id("choice_1_19_2")).click();
        driver.findElement(By.id("choice_1_19_3")).click();
        driver.findElement(By.id("choice_1_19_4")).click();
        driver.findElement(By.id("choice_1_19_5")).click();
        driver.findElement(By.id("choice_1_19_7")).click();
        driver.findElement(By.id("choice_1_19_8")).click();
        driver.findElement(By.id("input_1_4")).sendKeys(ModifyUtil.imageAsSource(giveaway).getAbsolutePath());
        new Select(driver.findElement(By.id("input_1_29"))).selectByVisibleText("No featured");
        driver.findElement(By.id("gform_submit_button_1")).click();
    }

    public static void submitToJustSweep(FirefoxDriver driver, Giveaway giveaway) throws ParseException, MalformedURLException, InterruptedException {
        driver.get("http://justsweep.com/submit-a-giveaway/");
        driver.findElement(By.name("input_26.1")).click();
        driver.findElement(By.name("input_9")).sendKeys(giveaway.getEmail());
        driver.findElement(By.name("input_18")).sendKeys(giveaway.getUrl());
        driver.findElement(By.name("input_10")).sendKeys(giveaway.getPrize());
        driver.findElement(By.name("input_24")).sendKeys(giveaway.getSponsor());
        driver.findElement(By.id("input_3_11")).sendKeys(String.format("Enter for a chance to win %s \n %s", giveaway.getPrize(), giveaway.getDescription()));
        driver.findElement(By.id("input_3_15_1")).sendKeys(ModifyUtil.addZero(DateUtil.stringDateMonth(giveaway.getEndDate().toString())));
        driver.findElement(By.id("input_3_15_2")).sendKeys(ModifyUtil.addZero(DateUtil.stringDateDay(giveaway.getEndDate().toString())));
        driver.findElement(By.id("input_3_15_3")).sendKeys(DateUtil.stringDateYear(giveaway.getEndDate().toString()));

        driver.findElement(By.id("choice_3_16_1")).click();
        driver.findElement(By.id("choice_3_16_2")).click();
        driver.findElement(By.id("choice_3_16_3")).click();
        driver.findElement(By.id("choice_3_13_2")).click();
        driver.findElement(By.id("choice_3_13_11")).click();
        driver.findElement(By.id("choice_3_20_5")).click();
        driver.findElement(By.id("input_3_17")).sendKeys(ModifyUtil.imageAsSource(giveaway).getAbsolutePath());
        driver.findElement(By.id("choice_3_23_0")).click();
        driver.findElement(By.id("gform_submit_button_3")).click();
        Thread.sleep(1000);
    }

    public static void submitToOnlineSweepstakes(FirefoxDriver driver, Giveaway giveaway) throws ParseException {
        driver.get("http://www.online-sweepstakes.com/addasweep/");
        driver.findElement(By.name("vb_login_username")).sendKeys(Constants.USERNAME);
        driver.findElement(By.name("vb_login_password")).sendKeys(Constants.PASSWORD);
        driver.findElement(By.xpath(".//*[@id='listings']/div/center/form/input[12]")).click();
        driver.findElement(By.name("sweepurl")).sendKeys(giveaway.getUrl());
        driver.findElement(By.xpath(".//*[@id='listings']/div[1]/center/form/input[4]")).click();
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.name("Title")).sendKeys(giveaway.getSweepstakesName());
        driver.findElement(By.name("EntryForm_Url")).sendKeys(giveaway.getSweepstakesName());
        driver.findElement(By.name("Rules_Url")).sendKeys(giveaway.getSweepstakesName());
        driver.findElement(By.name("Rules_Url")).sendKeys(giveaway.getSweepstakesName());
        driver.findElement(By.id("astab2")).click();
        driver.findElement(By.name("eleven59")).click();
        driver.findElement(By.name("eleven59")).click();
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.getElementsByName('ExpirationDate')[0].setAttribute('type', 'text');");
        driver.findElement(By.name("ExpirationDate")).sendKeys(DateUtil.changeDateToSlashes(giveaway.getEndDate().toString()));
        jse.executeScript("document.getElementsByName('StartDate')[0].setAttribute('type', 'text');");
        driver.findElement(By.name("StartDate")).sendKeys(DateUtil.getCurrentDate());
        driver.findElement(By.id("astab3")).click();
        driver.findElement(By.name("maxentry")).clear();
        new Select(driver.findElement(By.name("freq_select"))).selectByValue("unlimited");
        driver.findElement(By.name("email")).click();
        new Select(driver.findElement(By.name("el_include"))).selectByVisibleText("U.S.");
        new Select(driver.findElement(By.name("el_include"))).selectByVisibleText("Canada");
        driver.findElement(By.id("astab4")).click();
        driver.findElement(By.name("Description")).sendKeys(String.format("Enter for a chance to win %s \n%s", giveaway.getPrize(), giveaway.getDescription()));
        driver.findElement(By.id("isBlog")).click();
        driver.findElement(By.name("submit")).click();
    }

    public static void hardsubmitToRedditContests(FirefoxDriver driver, Giveaway giveaway) throws InterruptedException, IOException {
//        driver.get("http://www.primefaces.org/showcase/ui/misc/captcha.xhtml");
        driver.get("https://www.reddit.com/r/contests/submit");
        driver.findElement(By.id("user_login")).sendKeys(Constants.USERNAME);
        driver.findElement(By.id("passwd_login")).sendKeys(Constants.PASSWORD);
        driver.findElement(By.xpath(".//*[@id='login-form']/div[5]/button")).click();
        driver.findElement(By.id("url")).sendKeys(giveaway.getUrl());
        driver.findElement(By.name("title")).sendKeys(giveaway.getSweepstakesName());
        //TODO: check if you need to "Click verify once there are none left."
        SolverUtil.newReCaptchaSolver(driver);
        driver.findElement(By.name("submit")).click();
    }

    public static void hardSubmitToRedditGiveaways(FirefoxDriver driver, Giveaway giveaway) throws InterruptedException, IOException {
//        driver.get("http://www.primefaces.org/showcase/ui/misc/captcha.xhtml");
        driver.get("https://www.reddit.com/r/giveaways/submit");
        driver.findElement(By.id("user_login")).sendKeys(Constants.USERNAME);
        driver.findElement(By.id("passwd_login")).sendKeys(Constants.PASSWORD);
        driver.findElement(By.xpath(".//*[@id='login-form']/div[5]/button")).click();
        driver.findElement(By.id("url")).sendKeys(giveaway.getUrl());
        driver.findElement(By.name("title")).sendKeys(giveaway.getSweepstakesName());
        //TODO: check if you need to "Click verify once there are none left."
        SolverUtil.newReCaptchaSolver(driver);
        driver.findElement(By.name("submit")).click();
    }

    public static void hardSubmitToRedditSweepstake(FirefoxDriver driver, Giveaway giveaway) throws InterruptedException, IOException {
//        driver.get("http://www.primefaces.org/showcase/ui/misc/captcha.xhtml");
        driver.get("http://www.reddit.com/r/sweepstake/submit");
        driver.findElement(By.id("user_login")).sendKeys(Constants.USERNAME);
        driver.findElement(By.id("passwd_login")).sendKeys(Constants.PASSWORD);
        driver.findElement(By.xpath(".//*[@id='login-form']/div[5]/button")).click();
        driver.findElement(By.id("url")).sendKeys(giveaway.getUrl());
        driver.findElement(By.name("title")).sendKeys(giveaway.getSweepstakesName());
        //TODO: check if you need to "Click verify once there are none left."
        SolverUtil.newReCaptchaSolver(driver);
        driver.findElement(By.name("submit")).click();
    }

    public static void hardSubmitToRedditSweepstakes(FirefoxDriver driver, Giveaway giveaway) throws InterruptedException, IOException {
//        driver.get("http://www.primefaces.org/showcase/ui/misc/captcha.xhtml");
        driver.get("http://www.reddit.com/r/sweepstakes/submit");
        driver.findElement(By.id("user_login")).sendKeys(Constants.USERNAME);
        driver.findElement(By.id("passwd_login")).sendKeys(Constants.PASSWORD);
        driver.findElement(By.xpath(".//*[@id='login-form']/div[5]/button")).click();
        driver.findElement(By.id("url")).sendKeys(giveaway.getUrl());
        driver.findElement(By.name("title")).sendKeys(giveaway.getSweepstakesName());
        //TODO: check if you need to "Click verify once there are none left."
        SolverUtil.newReCaptchaSolver(driver);
        driver.findElement(By.name("submit")).click();
    }

    public static void submitToSweepstakesLovers(FirefoxDriver driver, Giveaway giveaway) throws MalformedURLException {
        driver.get("http://www.sweepstakeslovers.com/suggest-a-sweepstakes/");
        driver.findElement(By.name("your-name")).sendKeys(giveaway.getPersonalName());
        driver.findElement(By.name("your-email")).sendKeys(giveaway.getEmail());
        driver.findElement(By.name("your-website")).sendKeys(ModifyUtil.trimURL(giveaway.getUrl()));
        driver.findElement(By.name("your-subject")).sendKeys(giveaway.getSweepstakesName());
        driver.findElement(By.name("sweeps-url")).sendKeys(giveaway.getUrl());
        driver.findElement(By.name("rules-url")).sendKeys(giveaway.getUrl());
        driver.findElement(By.name("rules-url")).sendKeys(giveaway.getUrl());
        driver.findElement(By.name("your-message")).sendKeys(giveaway.getDescription());
        driver.findElement(By.name("quiz")).sendKeys(SolverUtil.aritmeticQuestionSolver(driver, ".//*[@id='wpcf7-f12973-p86-o1']/form/p[8]/span/label/span"));
        driver.findElement(By.className("wpcf7-submit")).click();
    }

    public static void submitToSweepstakesMax(FirefoxDriver driver, Giveaway giveaway) throws ParseException {
        driver.get("http://sweepstakesmax.com/Add-Sweepstakes");
        driver.findElement(By.name("title")).sendKeys(giveaway.getSweepstakesName());
        driver.findElement(By.name("url")).sendKeys(giveaway.getUrl());
        driver.findElement(By.id("dijit_form_DateTextBox_0")).sendKeys(DateUtil.changeDateToDashes(giveaway.getEndDate().toString()));
        new Select(driver.findElement(By.name("catId"))).selectByValue("12");
        driver.findElement(By.name("grandPrizeVal")).sendKeys(giveaway.getPrizeValue().toString());
        driver.findElement(By.name("prizeQty")).sendKeys("1");
        driver.findElement(By.name("prize")).sendKeys(giveaway.getDescription());
        driver.findElement(By.name("eligUSA")).click();
        driver.findElement(By.name("eligCA")).click();
        driver.findElement(By.name("eligible")).sendKeys(giveaway.getRestrictions());
        new Select(driver.findElement(By.name("entryFreq"))).selectByValue("0");
        driver.findElement(By.name("contactName")).sendKeys(giveaway.getPersonalName());
        driver.findElement(By.name("contactEmail")).sendKeys(giveaway.getEmail());
        driver.findElement(By.name("byBlog")).click();
        String question = driver.findElement(By.xpath("html/body/div[2]/div[1]/div[2]/form/table/tbody/tr[23]/td[2]")).getText();
        String[] list = question.split(",");
        int answer = Integer.valueOf(list[list.length - 1]) + 1;
        driver.findElement(By.name("next")).sendKeys(String.valueOf(answer));
        driver.findElement(By.name("submit")).click();
    }

    public static void submitToSweepstakesMag(FirefoxDriver driver, Giveaway giveaway) {
        driver.get("http://www.sweepstakesmag.com/submit/");
        driver.findElement(By.name("your-name")).sendKeys(giveaway.getPersonalName());
        driver.findElement(By.name("your-email")).sendKeys(giveaway.getEmail());
        driver.findElement(By.name("sweeps-url")).sendKeys(giveaway.getUrl());
        driver.findElement(By.name("your-message")).sendKeys(giveaway.getDescription());
        driver.findElement(By.name("quiz")).sendKeys(SolverUtil.aritmeticQuestionSolver(driver, ".//*[@class='wpcf7-quiz-label']"));
        driver.findElement(By.className("wpcf7-submit")).click();
    }

    public static void submitToWinASweepstakes(FirefoxDriver driver, Giveaway giveaway) throws ParseException, IOException, Exception, InterruptedException {
        driver.get("http://winasweepstakes.com/submit-sweepstakes");
        driver.findElement(By.name("title")).sendKeys(giveaway.getSweepstakesName());
        driver.findElement(By.name("website_form_url")).sendKeys(giveaway.getUrl());
        driver.findElement(By.name("prize_value")).sendKeys(giveaway.getPrizeValue().toString());
        new Select(driver.findElement(By.name("prize_category"))).selectByValue("9");
        driver.switchTo().frame(driver.findElement(By.xpath(".//*[@title='Rich Text Editor, editor1']")));
        driver.findElement(By.className("cke_editable")).sendKeys(giveaway.getDescription());
        driver.switchTo().defaultContent();
        driver.findElement(By.name("end_date")).sendKeys(DateUtil.changeDateToSlashes(giveaway.getEndDate().toString()));
        new Select(driver.findElement(By.name("time_zone"))).selectByValue("EST");
        driver.findElement(By.name("eligibility")).sendKeys(giveaway.getRestrictions());
        driver.findElement(By.xpath(".//*[@value='Gleam']")).click();
    new Select(driver.findElement(By.name("entry_limit"))).selectByValue("Daily");
    driver.findElement(By.name("official_rules_url")).sendKeys(giveaway.getUrl());
    driver.findElement(By.name("winners_page_url")).sendKeys(giveaway.getUrl());
    driver.findElement(By.name("website_name")).sendKeys(giveaway.getSponsor());
    driver.findElement(By.name("main_image")).sendKeys(ModifyUtil.imageAsSource(giveaway).getAbsolutePath());
    driver.findElement(By.name("username")).sendKeys(giveaway.getPersonalName());
    driver.findElement(By.name("email")).sendKeys(giveaway.getEmail());
    SolverUtil.reCaptchaSolver(driver);
    driver.findElement(By.name("submit_sweepstakes_submit")).click();
    }
}
