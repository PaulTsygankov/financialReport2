package util;

public class MonthConverter {


    public static String convertMonth(String code){

        String month = switch (code) {
            case "01", "m.202101" -> "Январь";
            case "02", "m.202102" -> "Февраль";
            case "03", "m.202103" -> "Март";
            default -> "Ошибка! Неизвестный код.";
        };

        return month;
    }

}
