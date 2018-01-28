package com.rangelstoilov.marketing.exceptions.SMSPVA;

public class WaitingForSMSException extends Throwable {
    public WaitingForSMSException(String s) throws InterruptedException {
        Thread.sleep(3000);
    }
}
