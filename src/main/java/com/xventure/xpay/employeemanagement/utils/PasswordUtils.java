package com.xventure.xpay.employeemanagement.utils;

import java.util.Random;

public class PasswordUtils {

   public static char[] passwordGenerator(int len) {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String num = "1234567890";
        String charcter = "@#$%^&*+_";

        String combination = upper + lower + num + charcter;

        char[] password = new char[len];
        Random r = new Random();
        for (int i = 0; i < len; i++) {
            password[i] = combination.charAt(r.nextInt(combination.length()));

        }

        return password;


    }
}
