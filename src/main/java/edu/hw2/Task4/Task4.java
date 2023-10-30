package edu.hw2.Task4;

public final class Task4 {
    public static CallingInfo callingInfo() {
        StackTraceElement[] stackTraceElements = new Throwable().getStackTrace();
        return new CallingInfo(
            stackTraceElements[1].getClassName(),
            stackTraceElements[1].getMethodName()
        );
    }

    private Task4() {
    }
}



