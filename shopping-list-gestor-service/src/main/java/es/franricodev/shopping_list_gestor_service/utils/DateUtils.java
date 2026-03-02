package es.franricodev.shopping_list_gestor_service.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
public class DateUtils {

    public static String formatDate(Date date) {
        log.info("Building the date {} with the pattern dd/MM/yyyy", date);
        String pattern = "dd/MM/yyyy";
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static String formatLocalDate(LocalDate date, String pattern) {
        log.info("Building the date {} with the pattern dd/MM/yyyy", date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }

}
