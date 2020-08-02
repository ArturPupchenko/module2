package ua.com.alevel.nix.module2.utilclasses;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatesUtil {

////    1. Дан список дат (строковая запись) в форматах типа “2020/04/05”, “05/04/2020”, “04-05-2020”
////    (все даты в примере - 5е апреля 2020) Вернуть список дат (строковая запись) в формате “20200405”.
////    Даты с неверным форматом - игнорировать.


    public static List<String> defaultDatesInputAsStringList = new ArrayList<>(Arrays.asList(
            "2001/04/05",
            "05/04/2002",
            "04-05-2003",
            "20040405",
            "",
            "10",
            "1010101010",
            "111-111-111",
            "as54a84",
            "sadsa"));

    public static List<String> defaultValidDateFormats = new ArrayList<>(Arrays.asList(
            "yyyy/MM/dd",
            "dd/MM/yyyy",
            "MM-dd-yyyy",
            "yyyyMMdd"));


    public static String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }


    public static List<String> makeDateList(List<String> datesInputAsStringList, List<String> validDateFormats) {
        List<String> datesList = new ArrayList<>();
        for (String date : datesInputAsStringList) {
            String tempDate = isValidDate(date, validDateFormats);
            if (tempDate != null)
                datesList.add(tempDate);
        }
        return datesList;

    }


    public static String isValidDate(String dateToCheck, List<String> validDateFormats) {

        int dateFormatsLength = validDateFormats.size();
        for (String datePattern : validDateFormats) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
            dateFormatsLength--;

            try {
                LocalDate dt = formatter.parse(dateToCheck, LocalDate::from);
                String date = formatDate(dt);
                return date;
            } catch (DateTimeParseException e) {
                if (dateFormatsLength == 0)
                    return null;
            }
        }
        return null;
    }
}


