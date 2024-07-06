package net.anyforum.backend.util.time;

import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
    private static int MILLISECONDS_IN_DAY = 86400000;
    public static String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date currentDate = new Date();

        return formatter.format(currentDate);
    }

    //returns what date would be in x days
    public static String getFutureDate(long days) {
        long dayNumberMs = days * 86400000;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date futureDate = new Date(System.currentTimeMillis() + dayNumberMs);
        return formatter.format(futureDate);
    }
}
