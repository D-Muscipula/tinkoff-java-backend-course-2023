package edu.hw5.Task2;

import edu.hw5.Task3.Task3;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.jetbrains.annotations.NotNull;

public final class Task2 {
    public static final int THIRTEEN = 13;
    public static final int MONTHS_IN_YEAR = 12;
    public static final String FORMAT = "yyyy-MM-dd";

    public static String[] getFridaysOf13th(int year) {
        ArrayList<String> arrayList = new ArrayList<>();
        DateFormat df = new SimpleDateFormat(FORMAT);
        Calendar calendar = new GregorianCalendar(year, Calendar.JANUARY, THIRTEEN);
        for (int i = 0; i < MONTHS_IN_YEAR; i++) {
            calendar.set(Calendar.MONTH, i);
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                arrayList.add(df.format(calendar.getTime()));
            }
        }
        return arrayList.toArray(new String[0]);
    }

    public static String getNextFridayOf13th(String date) {
        //LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(FORMAT));
        LocalDate localDate;
        if (Task3.parseDate(date).isPresent()) { //Можно использовать форматы из 3 задания
            localDate = Task3.parseDate(date).get();
        } else {
            throw new IllegalArgumentException();
        }
        LocalDate result = getLocalDate(localDate);
        DateTimeFormatter df = DateTimeFormatter.ofPattern(FORMAT);
        return df.format(result);
    }

    @NotNull private static LocalDate getLocalDate(LocalDate localDate) {
        TemporalAdjuster temporalAdjuster = TemporalAdjusters.ofDateAdjuster(date1 -> {
            var tempDate = date1;
            tempDate = tempDate.plusDays(1); //На случай если текущая дата пятница 13, чтобы найти следующую
            while (tempDate.getDayOfMonth() != THIRTEEN || tempDate.getDayOfWeek() != DayOfWeek.FRIDAY) {
                tempDate = tempDate.withDayOfMonth(THIRTEEN);
                tempDate = tempDate.plusMonths(1);
            }
            return tempDate;
        });
        return localDate.with(temporalAdjuster);
    }

    private Task2() {

    }
}
