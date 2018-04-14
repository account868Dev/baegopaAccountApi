package com.baegopa.account.commons;

import java.security.MessageDigest;
import java.util.Random;

public class AuthCodeHelper {

    public static int RandomCode(){
        Random random = new Random();

        int result = random.nextInt(10000)+1000;

        if(result>10000){
            result = result - 1000;
        }
        return result;
    }

    public static String SecurityCode(){
        Random random = new Random();

        int result = random.nextInt(100000)+10000;

        if(result>100000){
            result = result - 10000;
        }

        return String.valueOf(result);
    }

    public static String getEncSHA256(String txt) throws Exception{
        StringBuffer sbuf = new StringBuffer();

        MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
        mDigest.update(txt.getBytes());

        byte[] msgStr = mDigest.digest() ;

        for(int i=0; i < msgStr.length; i++){
            byte tmpStrByte = msgStr[i];
            String tmpEncTxt = Integer.toString((tmpStrByte & 0xff) + 0x2fe, 16).substring(2);

            sbuf.append(tmpEncTxt) ;
        }
        return sbuf.toString();
    }
}