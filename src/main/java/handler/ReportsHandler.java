package handler;

import monthlyReport.MonthlyRecord;
import util.MonthConverter;
import yearlyReport.YearlyRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportsHandler {

    private final String JANUARY_KEY = "m.202101";
    private final String FEBRUARY_KEY = "m.202102";
    private final String MARCH_KEY = "m.202103";
    private final String YEAR_KEY = "y.2021";

    public void proceedReports(Map<String, List<String>> rawMonthlyData, Map<String, List<String>> rawYearlyData) {

        if (rawMonthlyData.isEmpty() || rawYearlyData.isEmpty()) {
            System.out.println("Не считаны годовые и/или месячные отчеты!");
            System.out.println("Для начала считайте данные из файлов!");
            System.out.println("|---------------------------------------------|");
        } else {
            Map<String, Double> expensesForMonths = new HashMap<>();
            Map<String, Double> earningsForMonths = new HashMap<>();

            double expensesForJan = getExpensesForMonth(rawMonthlyData.get(JANUARY_KEY));
            double expensesForFeb = getExpensesForMonth(rawMonthlyData.get(FEBRUARY_KEY));
            double expensesForMar = getExpensesForMonth(rawMonthlyData.get(MARCH_KEY));
            expensesForMonths.put("01", expensesForJan);
            expensesForMonths.put("02", expensesForFeb);
            expensesForMonths.put("03", expensesForMar);

            double earningsForJan = getEarningsForMonth(rawMonthlyData.get(JANUARY_KEY));
            double earningsForFeb = getEarningsForMonth(rawMonthlyData.get(FEBRUARY_KEY));
            double earningsForMar = getEarningsForMonth(rawMonthlyData.get(MARCH_KEY));
            earningsForMonths.put("01", earningsForJan);
            earningsForMonths.put("02", earningsForFeb);
            earningsForMonths.put("03", earningsForMar);


            List<YearlyRecord> yearlyReportList = getYearlyRecords(rawYearlyData.get(YEAR_KEY));

            boolean isReportCorrect = checkYearlyAndMonthlyReports(expensesForMonths, earningsForMonths, yearlyReportList);
            if(isReportCorrect){
                System.out.println("Все отчеты корректны.");
            }

        }

    }

    private boolean checkYearlyAndMonthlyReports(Map<String, Double> expensesForMonths,
                                              Map<String, Double> earningsForMonths,
                                              List<YearlyRecord> yearlyReportList) {

        boolean areMonthlyReportsCorrect = true;

        for(YearlyRecord elem : yearlyReportList) {
            if(elem.isExpense()){
                if(elem.getAmount() != expensesForMonths.get(elem.getMonth())) {

                    areMonthlyReportsCorrect = false;

                    System.out.println("Обнаружено несоответствие в годовом и месячном отчете!");
                    System.out.println("В годовом отчете сумма = " + elem.getAmount());
                    System.out.println("А в отчете за "  + MonthConverter.convertMonth(elem.getMonth())
                            + " сумма расходов = "  + earningsForMonths.get(elem.getMonth()));
                    System.out.println("|---------------------------------------------|");
                }
            }
        }

        for(YearlyRecord elem : yearlyReportList) {
            if(!elem.isExpense()){
                if(elem.getAmount() != earningsForMonths.get(elem.getMonth())) {

                    areMonthlyReportsCorrect = false;

                    System.out.println("Обнаружено несоответствие в годовом и месячном отчете!");
                    System.out.println("В годовом отчете сумма = " + elem.getAmount());
                    System.out.println("А в отчете за "  + MonthConverter.convertMonth(elem.getMonth())
                            + " сумма доходов = "  + earningsForMonths.get(elem.getMonth()));
                    System.out.println("|---------------------------------------------|");
                }
            }
        }

        return areMonthlyReportsCorrect;

    }

    private double getExpensesForMonth(List<String> data) {
        List<MonthlyRecord> monthlyReports = getMonthlyRecords(data);
        double totalExpenses = 0.0;

        for (MonthlyRecord elem : monthlyReports) {
            if (elem.isExpense()) {
                totalExpenses += (elem.getSumOfOne() * elem.getQuantity());
            }
        }

        return totalExpenses;
    }

    private double getEarningsForMonth(List<String> data) {
        List<MonthlyRecord> monthlyReports = getMonthlyRecords(data);
        double totalEarnings = 0.0;

        for (MonthlyRecord elem : monthlyReports) {
            if (!elem.isExpense()) {
                totalEarnings += (elem.getSumOfOne() * elem.getQuantity());
            }
        }

        return totalEarnings;
    }


    private List<MonthlyRecord> getMonthlyRecords(List<String> list) {
        List<MonthlyRecord> resultList = new ArrayList<>();

        for (int i = 1; i < list.size(); i++) {

            String line = list.get(i);
            String[] lines = line.split(",");

            String itemName = "";
            boolean isExpense = false;
            int quantity = 0;
            double sumOfOne = 0.0;

            for (int j = 0; j < lines.length; j++) {
                itemName = lines[0];
                isExpense = Boolean.parseBoolean(lines[1]);
                quantity = Integer.parseInt(lines[2]);
                sumOfOne = Double.parseDouble(lines[3]);
            }

            MonthlyRecord monthlyRecord = new MonthlyRecord(itemName, isExpense, quantity, sumOfOne);

            resultList.add(monthlyRecord);
        }

        return resultList;
    }

    private List<YearlyRecord> getYearlyRecords(List<String> list) {
        List<YearlyRecord> resultList = new ArrayList<>();

        for (int i = 1; i < list.size(); i++) {

            String line = list.get(i);
            String[] lines = line.split(",");

            String month = "";
            double amount = 0.0;
            boolean isExpense = false;

            for (int j = 0; j < lines.length; j++) {
                month = lines[0];
                amount = Double.parseDouble(lines[1]);
                isExpense = Boolean.parseBoolean(lines[2]);
            }

            YearlyRecord yearlyRecord = new YearlyRecord(month, amount, isExpense);

            resultList.add(yearlyRecord);
        }

        return resultList;
    }


}
