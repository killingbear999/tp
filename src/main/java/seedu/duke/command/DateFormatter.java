package seedu.duke.command;

import java.time.LocalDate;
import java.util.HashMap;

public class DateFormatter {
    public String currentDate() {
        LocalDate date = LocalDate.now();
        return date.toString();
    }

    public String getCurrentMonth() {
        return currentDate().substring(5, 7);
    }

    public String getCurrentYear() {
        return currentDate().substring(0, 4);
    }

    public String changeMonthFormat(String month) {
        HashMap<String, String> months = new HashMap<>();
        months.put("Jan", "01");
        months.put("Feb", "02");
        months.put("Mar", "03");
        months.put("Apr", "04");
        months.put("May", "05");
        months.put("Jun", "06");
        months.put("Jul", "07");
        months.put("Aug", "08");
        months.put("Sep", "09");
        months.put("Oct", "10");
        months.put("Nov", "11");
        months.put("Dec", "12");

        if (months.containsKey(month)) {
            return months.get(month);
        } else {
            return null;
        }
    }
}
