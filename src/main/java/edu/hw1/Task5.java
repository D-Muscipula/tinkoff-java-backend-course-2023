package edu.hw1;

public final class Task5 {

    private Task5() {

    }

    public static boolean isPalindromeDescendant(int number) {
        //String regex = "\\d+";
        String string = String.valueOf(number);
        String reverse = new StringBuilder(string).reverse().toString();
        String newString = string;
        if (string.equals(reverse)) {
            return true;
        } else if (string.length() % 2 != 0 || number < 0) {
            return false;
        } else {
            while (newString.length() % 2 != 1 || newString.length() > 1) {
                StringBuilder sb = new StringBuilder();
                //newString = "";
                if (reverse.equals(string)) {
                    return true;
                }
                for (int i = 0; i < string.length() - 1; i += 2) {
                    char first = string.charAt(i);
                    char second = string.charAt(i + 1);
                    int sum = Character.getNumericValue(first) + Character.getNumericValue(second);
                    String temp = String.valueOf(sum);
                    sb.append(temp);
                    //newString += temp;
                    //newString = string.replaceFirst(String.valueOf(first) + String.valueOf(second), temp);
                }
                newString = sb.toString();
                //LOGGER.info(newString);
                string = newString;
                reverse = new StringBuilder(string).reverse().toString();
            }
        }
        return false;
    }
}
