package com.rangelstoilov.marketing.utils;

import com.rangelstoilov.marketing.exceptions.SMSPVA.ExpensiveNumbersException;
import com.rangelstoilov.marketing.exceptions.SMSPVA.InsufficientBalanceException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

import static java.lang.Double.valueOf;

public class SMSPVA {
    private static final String api = "IHmZTWwR26GY1ihBWwFznBARpSJQfq";


    public static Map<String, String> checkAccountGetNumber() throws InterruptedException, IOException, InsufficientBalanceException, ExpensiveNumbersException {
        if(getBalance()>1){
            System.out.println("We are in business!");
            if (getCount()<0){
                throw  new ExpensiveNumbersException("You can't buy numbers! The prices might have gone up!");
            } else {
                Map<String, String> number = getNumber();
                while (Integer.valueOf(number.get("response")) == 2){
                    Thread.sleep(30000);
                    number = getNumber();
                }
                //TODO: check if locked from the service -> getBan
                return number;
            }
        } else {
            throw new InsufficientBalanceException(String.format("You have %.3f balance! Please recharge!", getBalance()));
        }
    }



    public static Double getBalance() throws IOException {
        InputStream input = new URL("http://smspva.com/priemnik.php?metod=get_balance&service=opt1&apikey="+api).openStream();
        Map<String, String> map = new Gson().fromJson(new InputStreamReader(input, "UTF-8"), new TypeToken<Map<String, String>>() {
        }.getType());
        return valueOf(map.get("balance"));
    }

    public static Double getCount() throws IOException {
        InputStream input = new URL("http://smspva.com/priemnik.php?metod=get_count&service=opt1&apikey="+api+"&service_id=gmail").openStream();
        Map<String, String> map = new Gson().fromJson(new InputStreamReader(input, "UTF-8"), new TypeToken<Map<String, String>>() {
        }.getType());
        return valueOf(map.get("counts Gmail"));
    }

    public static Map<String, String> getNumber() throws IOException {
        InputStream input = new URL("http://smspva.com/priemnik.php?metod=get_number&service=opt1&id=1&apikey="+api).openStream();
        Map<String, String> map = new Gson().fromJson(new InputStreamReader(input, "UTF-8"), new TypeToken<Map<String, String>>() {
        }.getType());
        return map;
    }

    public static String getSMSCode(String id) throws IOException, InterruptedException {
        InputStream input = new URL("http://smspva.com/priemnik.php?metod=get_sms&service=opt1&id="+id+"&apikey="+api).openStream();
        Map<String, String> map = new Gson().fromJson(new InputStreamReader(input, "UTF-8"), new TypeToken<Map<String, String>>() {
        }.getType());
        return map.get("sms");
    }
}
