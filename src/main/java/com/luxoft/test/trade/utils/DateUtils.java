package com.luxoft.test.trade.utils;

import de.jollyday.HolidayCalendar;
import de.jollyday.HolidayManager;
import org.joda.time.LocalDate;

/**
 * This is an util class for finding non-working days
 */
public class DateUtils {

    public static boolean isNonWorkingDay(String valueDate) {
        HolidayManager holidayManager = HolidayManager.getInstance(HolidayCalendar.SWITZERLAND);
        return holidayManager.isHoliday(LocalDate.parse(valueDate));
    }
}
