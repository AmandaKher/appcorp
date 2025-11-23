package br.cefetrj.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate parse(String date) {
        if (date == null)
            return null;
        return LocalDate.parse(date, formatter);
    }

    public static String format(LocalDate date) {
        if (date == null)
            return null;
        return date.format(formatter);
    }
}
