package model.util;

import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    public static DateTimeFormatter fmt() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    }
}
