package yearlyReport;

import java.util.ArrayList;
import java.util.List;

public class YearlyReportsStat {

    private final String JANUARY_KEY = "01";
    private final String FEBRUARY_KEY = "02";
    private final String MARCH_KEY = "03";

    public void printYearlyStat(List<YearlyRecord> yearlyRecordList) {
        List<YearlyRecord> listOfExpenses = new ArrayList<>();
        List<YearlyRecord> listOfEarnings = new ArrayList<>();

        for(YearlyRecord record : yearlyRecordList) {
            if(record.isExpense()) {
                listOfExpenses.add(record);
            }
            else {
                listOfEarnings.add(record);
            }
        }

        System.out.println("Статистика за 2021:" + "\n");

        System.out.println("Прибыль за Январь: " + calculateMonthProfit(yearlyRecordList, JANUARY_KEY));
        System.out.println("Прибыль за Февраль: " + calculateMonthProfit(yearlyRecordList, FEBRUARY_KEY));
        System.out.println("Прибыль за Март: " + calculateMonthProfit(yearlyRecordList, MARCH_KEY));
        System.out.println();

        System.out.println("Средний доход за 2021: " + calculateAverageEarning(yearlyRecordList));
        System.out.println("Средний расход за 2021: " + calculateAverageExpense(yearlyRecordList));
        System.out.println();

        System.out.println("|---------------------------------------------|");

    }

    private double calculateMonthProfit(List<YearlyRecord> yearlyRecordList, String month) {

        double result = 0.0;
        double expense = 0.0;
        double earning = 0.0;

        for(YearlyRecord record : yearlyRecordList) {
            if (month.equals(record.getMonth())) {
                if(record.isExpense()) {
                    expense = record.getAmount();
                } else {
                    earning = record.getAmount();
                }
            }
        }

        result = earning - expense;
        return result;

    }

    private double calculateAverageExpense(List<YearlyRecord> yearlyRecordList) {

        double avgExpense = 0.0;
        double sum = 0.0;
        int count = 0;

        for(YearlyRecord record : yearlyRecordList) {
            if(record.isExpense()) {
                sum+= record.getAmount();
                count++;
            }
        }

        avgExpense = sum / count;
        return avgExpense;
    }

    private double calculateAverageEarning(List<YearlyRecord> yearlyRecordList) {

        double avgEarning = 0.0;
        double sum = 0.0;
        int count = 0;

        for(YearlyRecord record : yearlyRecordList) {
            if(!record.isExpense()) {
                sum+= record.getAmount();
                count++;
            }
        }

        avgEarning = sum / count;
        return avgEarning;
    }


   /* private YearlyRecord getBiggestExpense(List<YearlyRecord> yearlyReportList) {

        double biggestExpense = 0.0;
        YearlyRecord biggestRecord = null;

        for(YearlyRecord record : yearlyReportList) {
            double expense = record.getAmount();
            if(expense > biggestExpense) {
                biggestExpense = expense;
                biggestRecord = record;
            }
        }
        return biggestRecord;
    }

    private YearlyRecord getBiggestEarning(List<YearlyRecord> yearlyReportList) {

        double biggestEarning = 0.0;
        YearlyRecord biggestRecord = null;

        for(YearlyRecord record : yearlyReportList) {
            double earning = record.getAmount();
            if(earning > biggestEarning) {
                biggestEarning = earning;
                biggestRecord = record;
            }
        }
        return biggestRecord;
    }

    */
}
