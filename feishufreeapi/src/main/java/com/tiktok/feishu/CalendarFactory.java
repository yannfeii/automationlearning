package com.tiktok.feishu;

import com.tiktok.feishu.model.Calendar;
import com.tiktok.feishu.service.CalendarService;
import com.tiktok.feishu.web.CalendarWeb;

public class CalendarFactory {
    public static Calendar createCalendar(String automation) throws Exception {
        if (automation.equals("web")) {
            return new CalendarWeb();
        } else if (automation.equals("service")) {
            return new CalendarService();
        } else {
            throw new Exception(automation);
        }
    }
}
