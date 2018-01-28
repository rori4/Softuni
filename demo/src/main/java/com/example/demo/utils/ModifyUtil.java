package com.example.demo.utils;


import com.example.demo.entities.Giveaway;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

public class ModifyUtil {
    public static String trimURL(String urlAsText) throws MalformedURLException {
        URL url = new URL(urlAsText);
        String path = url.getFile().substring(0, url.getFile().lastIndexOf('/'));
        return url.getProtocol() + "://" + url.getHost() + path;
    }

    public static File imageAsSource(Giveaway giveaway) throws MalformedURLException {
        URL url = new URL(giveaway.getGiveawayImgUrl());
        File giveawayImg = new File(FilenameUtils.getName(url.getPath()));
        try {
            FileUtils.copyURLToFile(url, giveawayImg);
        } catch (IOException e) {
            e.getMessage();
        }
        return giveawayImg;
    }

    public static File imageAsJpg(Giveaway giveaway) throws IOException {
        BufferedImage input_image = null;
        //read PNG image as BufferedImage
        URL url = new URL(giveaway.getGiveawayImgUrl());
        if (!new File(FilenameUtils.getName(url.getPath())).exists()){
            ModifyUtil.imageAsSource(giveaway);
        }
        input_image = ImageIO.read(new File( FilenameUtils.getName(url.getPath())));
        //JPG output
        File outputfile = new File(FilenameUtils.getBaseName(url.getPath())+".jpg");
        //write output in JPG format
        ImageIO.write(input_image, "jpg", outputfile);

        System.out.println("Done");
        return outputfile;
    }

    public static String addZero(String date) {
        StringBuilder stringBuilder = new StringBuilder();
        if (String.valueOf(date).length()==1){
            stringBuilder.append("0");
            stringBuilder.append(date);
            return stringBuilder.toString();
        } else {
            return String.valueOf(date);
        }
    }

    public static void shootWebElement(WebElement element, FirefoxDriver driver) throws IOException  {

        File screen = driver.getScreenshotAs(OutputType.FILE);

        Point p = element.getLocation();

        int width = element.getSize().getWidth();
        int height = element.getSize().getHeight();

        BufferedImage img = ImageIO.read(screen);

        BufferedImage dest = img.getSubimage(p.getX(), p.getY(), width,
                height);

        ImageIO.write(dest, "png", screen);

        File f = new File("");

        FileUtils.copyFile(screen, f);
    }

    public static void compressImage(String uncompressed, String compressed) throws IOException, InterruptedException {
        File input = new File(uncompressed);
        BufferedImage image = ImageIO.read(input);

        File compressedImageFile = new File(compressed);
        OutputStream os = new FileOutputStream(compressedImageFile);

        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
        ImageWriter writer = (ImageWriter) writers.next();

        ImageOutputStream ios = ImageIO.createImageOutputStream(os);
        writer.setOutput(ios);

        ImageWriteParam param = writer.getDefaultWriteParam();
        // Check if canWriteCompressed is true
        if(param.canWriteCompressed()) {
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        }
        // End of check
        writer.write(null, new IIOImage(image, null, null), param);
    }

    public static int[][] captchaToArrays(String r){
        r=r.replace("[","");//replacing all [ to ""
        r=r.substring(0,r.length()-2);//ignoring last two ]]
        String r1[]=r.split("],");//separating all by "],"
        int my_metrics[][] = new int[r1.length][2];//declaring two dimensional matrix for input

        for(int i=0;i<r1.length;i++){
            r1[i]=r1[i].trim();//ignoring all extra space if the string s1[i] has
            String single_int[]=r1[i].split(",");//separating integers by ", "
            for(int j=0;j<single_int.length;j++){
                my_metrics[i][j]=Integer.parseInt(single_int[j]); //adding single values
            }
        }
        return my_metrics;
    }
}
