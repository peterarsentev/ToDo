package ru.job4j.todo;

import java.util.Calendar;
import java.util.Locale;

public class DateFrm {
    public static String format(Calendar cal) {
        return String.format(
                Locale.getDefault(), "%02d.%02d.%d",
                cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR)
        );
    }
}
