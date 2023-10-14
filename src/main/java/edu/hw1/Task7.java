package edu.hw1;

public final class Task7 {

    private Task7() {

    }

    public static int rotateLeft(int n, int shift) {
        String binary = Integer.toBinaryString(n);
        int betterShift = shift % binary.length();
        if (shift <= 0 || betterShift == 0) {
            return n;
        }
        String toMove = binary.substring(0, betterShift);
        binary = binary.substring(betterShift + 1);
        binary = binary + toMove;
        return Integer.parseInt(binary, 2);
    }

    public static int rotateRight(int n, int shift) {
        String binary = Integer.toBinaryString(n);
        int betterShift = shift % binary.length();
        if (shift <= 0 || betterShift == 0) {
            return n;
        }
        String toMove = binary.substring(binary.length() - betterShift);
        binary = binary.substring(0, binary.length() - betterShift);
        binary = toMove + binary;
        return Integer.parseInt(binary, 2);
    }
}
