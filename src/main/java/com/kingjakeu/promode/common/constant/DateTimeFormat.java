package com.kingjakeu.promode.common.constant;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeFormat {
    public static final DateTimeFormatter PLAYER_BIRTHDAY = DateTimeFormatter
            .ofPattern("[MMM dd, yyyy ][MMM d, yyyy ][MMMM dd, yyyy ][MMMM d, yyyy ]", Locale.US);
    public static final DateTimeFormatter REQUEST_DATETIME = DateTimeFormatter
            .ofPattern("yyyyMMddHHmmss");
}
