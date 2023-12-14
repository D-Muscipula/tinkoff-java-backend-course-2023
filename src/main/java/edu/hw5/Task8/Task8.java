package edu.hw5.Task8;

import java.util.regex.Pattern;

public final class Task8 {

    public static boolean isLengthOdd(String s) {
        String regexp = "^([01]{2})+[01]$|^[01]$";
        return Pattern.compile(regexp).matcher(s).find();
    }

    public static boolean isLengthOddAnd0OrLengthEvenAnd1(String s) {
        String regexp = "^0([01]{2})*$|^1([01]{2})*[01]$";
        return Pattern.compile(regexp).matcher(s).find();
    }

    public static boolean isCountOf0DivisibleBy3(String s) {
        String regexp = "^(1*01*1*01*1*01*)*$";
        return Pattern.compile(regexp).matcher(s).find();
    }

    public static boolean isAnyStringExcept11And111(String s) {
        String regexp = "^(?!(11|111)$)[01]*$";
        return Pattern.compile(regexp).matcher(s).find();
    }

    public static boolean isEveryOddSymbol1(String s) {
        String regexp = "^([01]1)*[01]?$";
        return Pattern.compile(regexp).matcher(s).find();
    }

    public static boolean isContainingTwoAndMore0AndOneOrZero1(String s) {
        String regexp = "^0+0+$|^10+0+$|^0+10+$|^0+0+1$";
        return Pattern.compile(regexp).matcher(s).find();
    }

    public static boolean isNotContaining11(String s) {
        String regexp = "^((?!11)[01])*$";
        return Pattern.compile(regexp).matcher(s).find();
    }

    private Task8() {
    }
}
