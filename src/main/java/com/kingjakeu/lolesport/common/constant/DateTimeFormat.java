package com.kingjakeu.lolesport.common.constant;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeFormat {
    public static final DateTimeFormatter PLAYER_BIRTHDAY = DateTimeFormatter
            .ofPattern("[MMM dd, yyyy ][MMM d, yyyy ][MMMM dd, yyyy ][MMMM d, yyyy ]", Locale.US);
}
