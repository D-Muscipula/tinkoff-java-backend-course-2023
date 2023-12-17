package edu.project3;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class MyStats {
    private final static int NUMBER_TO_FIND_OUT_IF_ERROR = 100;
    private final static int START_DIGIT_OF_ERRORS = 4;
    public final HashMap<String, Integer> methods = new HashMap<>();
    public final LinkedHashMap<String, Integer> quantitiesOfErrors = new LinkedHashMap<>();

    public void getMethods(LogRecord logRecord) {
        String method = logRecord.requestMethod();
        if (!method.equals("-") && !methods.containsKey(method)) {
            methods.put(method, 1);
        } else if (methods.containsKey(method)) {
            methods.put(method, methods.get(method) + 1);
        }
    }

    public void getErrors(LogRecord logRecord) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        String date = logRecord.timeLocal().format(formatter).split(" ")[0];
        int code = logRecord.status();
        if (code / NUMBER_TO_FIND_OUT_IF_ERROR >= START_DIGIT_OF_ERRORS && !quantitiesOfErrors.containsKey(date)) {
            quantitiesOfErrors.put(date, 1);
        } else if (quantitiesOfErrors.containsKey(date)) {
            quantitiesOfErrors.put(date, quantitiesOfErrors.get(date) + 1);
        }
    }
}
