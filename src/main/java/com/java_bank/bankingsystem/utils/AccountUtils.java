package com.java_bank.bankingsystem.utils;

import java.time.Year;
import java.util.concurrent.ThreadLocalRandom;

public class AccountUtils {
    public static final String ACCOUNT_EXISTS_CODE = "001";
    public static final String ACCOUNT_EXISTS_MESSAGE = "Account Already Added";

    public static final String ACCOUNT_CREATION_CODE = "002";
    public static final String ACCOUNT_CREATION_MESSAGE = "Account Successfully Created";
    public static String generateAccountNumber(){
        var year = Year.now().toString();
        StringBuilder accountNumber = new StringBuilder();
        accountNumber.append(year).append(generateRandomSixDigitNumber())
                .append(generateRandomSixDigitNumber());

        return accountNumber.toString();

    }
    public static String generateRandomSixDigitNumber(){
        var randomNumber = ThreadLocalRandom.current().nextInt(10000, 99999);
        return String.valueOf(randomNumber);
    }

    public static String generateWelcomeMessageMailBody(String fullName, String accountNumber){
        return "Welcome Mr "+ fullName +" to Banking system By java which developed by MO \n " +
                "Your Account Number is "+ accountNumber;
    }

}
