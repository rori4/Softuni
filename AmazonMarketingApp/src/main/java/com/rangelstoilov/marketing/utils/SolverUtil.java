package app.utils;

public class SolverUtil {/*
    public static void reCaptchaSolver(FirefoxDriver driver) throws IOException, Exception, InterruptedException {
        ByteArrayOutputStream os = getScreenshotOfElement(driver, "recaptcha_challenge_image");
        Client client = (Client) (new SocketClient(Constants.USERNAME_CAPTCHA, Constants.PASSWORD));
        client.isVerbose = true;
        Captcha result = client.decode(new ByteArrayInputStream(os.toByteArray()));
        driver.findElement(By.id("recaptcha_response_field")).click();
        driver.findElement(By.id("recaptcha_response_field")).sendKeys(result.text);
    }

    public static void newReCaptchaSolver(FirefoxDriver driver) throws IOException, InterruptedException {
        WebElement element = driver.findElement(By.className("g-recaptcha"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);

        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@title='recaptcha widget']")));
        WebElement captchaWidget = driver.findElement(By.xpath(".//*[@title='recaptcha widget']"));
        driver.switchTo().frame(captchaWidget);
        WebElement checkBox = new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.id("recaptcha-anchor")));
        Actions builder = new Actions(driver);
        builder.moveToElement(checkBox).perform();
        checkBox.click();
        Thread.sleep(3000);
        driver.switchTo().defaultContent();
        uploadCaptchaAndClick(driver);
        driver.switchTo().frame(driver.findElement(By.xpath(".//*[@title='recaptcha challenge']")));
        driver.findElement(By.id("recaptcha-verify-button")).click();
        Thread.sleep(3000);
        while (driver.findElement(By.id("recaptcha-verify-button")).isEnabled()){
            driver.switchTo().defaultContent();
            uploadCaptchaAndClick(driver);
            driver.switchTo().frame(driver.findElement(By.xpath(".//*[@title='recaptcha challenge']")));
            driver.findElement(By.id("recaptcha-verify-button")).click();
            Thread.sleep(3000);
        }
        driver.switchTo().defaultContent();
    }

    private static void uploadCaptchaAndClick(FirefoxDriver driver) throws IOException, InterruptedException {
        ByteArrayOutputStream imageCaptcha = getScreenshotOfiFrame(driver, Constants.NEW_RECAPTCHA_DIV);
        Client client = (Client) (new HttpClient(Constants.USERNAME_CAPTCHA, Constants.PASSWORD));
        client.isVerbose = true;
        try (OutputStream outputStream = new FileOutputStream("captcha.jpeg")) {
            imageCaptcha.writeTo(outputStream);
            ModifyUtil.compressImage("captcha.jpeg", "captchaCompressed.jpeg");
            try {
                try {
                    out.println(
                            "Your balance is " + client.getBalance()
                                    + " US cents");
                } catch (IOException e) {
                    out.println("Failed fetching balance: "
                            + e.toString());
                    return;
                }

                Captcha captcha = null;
                try {
                    // Upload a CAPTCHA and poll for its status with 120
                    // seconds timeout.
                    // Put you CAPTCHA image file name, file object,
                    // input stream, or vector of bytes, and solving
                    // timeout (in seconds).
                    // If 0 then the default value take place.
                    // Please note we are specifying type=2 in the
                    // second argument.
                    //TODO:check how to upload this! Everything else is good ubtil now
                    captcha = client.decode("captchaCompressed.jpeg", 2, 0);
                } catch (IOException e) {
                    out.println("Failed uploading CAPTCHA");
                    return;
                } catch (Exception | InterruptedException e) {
                    e.printStackTrace();
                }
                if (null != captcha) {
                    out.println(
                            "CAPTCHA " + captcha.id + " solved: " + captcha.text);
                    //TODO: write down the coordinates of the element
                    int[][] coordinates = ModifyUtil.captchaToArrays(captcha.text);
                    Point point = driver.findElement(By.xpath(Constants.NEW_RECAPTCHA_DIV)).getLocation();
                    for (int[] coordinate : coordinates) {
                        Actions actions = new Actions(driver);
                        WebElement captchaInside = driver.findElement(By.xpath(Constants.NEW_RECAPTCHA_DIV));
                        int halfWidthOfCaptcha = captchaInside.getSize().width/2;
                        int halfHeightOfCaptcha = captchaInside.getSize().height/2;
                        actions.moveToElement(captchaInside, coordinate[0]-halfWidthOfCaptcha, coordinate[1]-halfHeightOfCaptcha).click().build().perform();
                        System.out.println("click!");
                        Thread.sleep(123);
                    }

                    //TODO: movemouse.click


                    // Report incorrectly solved CAPTCHA if necessary.
                    // Make sure you've checked if the CAPTCHA was in
                    // fact incorrectly solved, or else you might get
                    // banned as abuser.
                    /*try {
                        if (client.report(captcha)) {
                            System.out.println(
                                "Reported as incorrectly solved");
                        } else {
                            System.out.println(
                                "Failed reporting incorrectly solved CAPTCHA");
                        }
                    } catch (IOException e) {
                        System.out.println(
                            "Failed reporting incorrectly solved CAPTCHA: "
                            + e.toString());
                    }*/
    /*
                } else {
                    out.println("Failed solving CAPTCHA");
                }
            } catch (Exception e) {
                out.println(e);
            }
        }
    }

    private static ByteArrayOutputStream getScreenshotOfElement(FirefoxDriver driver, String elementId) throws IOException {
        WebElement cap = driver.findElement(By.id(elementId));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cap);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        Long scroll = (Long) executor.executeScript("return window.pageYOffset;");
        byte[] arrScreen = driver.getScreenshotAs(OutputType.BYTES);
        BufferedImage imageScreen = ImageIO.read(new ByteArrayInputStream(arrScreen));
        Dimension capDimension = cap.getSize();
        Point capLocation = cap.getLocation();
        BufferedImage imgCap = imageScreen.getSubimage(capLocation.x, (int) (capLocation.y - scroll), capDimension.width, capDimension.height);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(imgCap, "png", os);
        return os;
    }

    private static ByteArrayOutputStream getScreenshotOfiFrame(FirefoxDriver driver, String elementXpath) throws IOException {
        try {
            WebElement iframe = driver.findElement(By.xpath(elementXpath));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", iframe);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            Long scroll = (Long) executor.executeScript("return window.pageYOffset;");
            byte[] arrScreen = driver.getScreenshotAs(OutputType.BYTES);
            BufferedImage imageScreen = ImageIO.read(new ByteArrayInputStream(arrScreen));
            Dimension capDimension = iframe.getSize();
            Point capLocation = iframe.getLocation();
            BufferedImage imgCap = imageScreen.getSubimage(capLocation.x, (int) (capLocation.y - scroll), capDimension.width, capDimension.height);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(imgCap, "png", os);
            return os;
        } catch (Exception e){
            System.out.println("cant find element");
            System.out.println(driver.getPageSource());
        }
        return null;
    }

    public static String aritmeticQuestionSolver(FirefoxDriver driver, String locator) {
        String question = driver.findElement(By.xpath(locator)).getText();
        String aritmeticQuestion = question.substring(0, question.indexOf("="));
        aritmeticQuestion = aritmeticQuestion.replaceAll("\\s", "");
        //might have to change to allow for negative numbers
        String[] splitStrings = (aritmeticQuestion.split("((?<=[+-/*])|(?=[+-/*]))"));
        int result = 0;

        out.println(Arrays.toString(splitStrings));

        int left = Integer.parseInt(splitStrings[0]);
        String op = splitStrings[1];
        int right = Integer.parseInt(splitStrings[2]);
        switch (op) {
            case "+":
                result = left + right;
                break;
            case "-":
                result = left - right;
                break;
            case "*":
                result = left * right;
                break;
            case "/":
                result = left / right;
                break;
        }
        return String.valueOf(result);
    }*/
}
