package monthlyReport;

import util.MonthConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MonthlyReportsStat {

    public void printMonthlyStat(List<MonthlyRecord> monthlyRecordList, String monthKey) {
        List<MonthlyRecord> listOfExpenses = new ArrayList<>();
        List<MonthlyRecord> listOfEarnings = new ArrayList<>();

        for(MonthlyRecord record : monthlyRecordList) {
            if(record.isExpense()) {
                listOfExpenses.add(record);
            }
            else {
                listOfEarnings.add(record);
            }
        }

        MonthlyRecord biggestExpense = getBiggestExpense(listOfExpenses);
        MonthlyRecord biggestEarning = getBiggestEarning(listOfEarnings);

        System.out.println("Самая большая трата за " + MonthConverter.convertMonth(monthKey));
        System.out.println("Название товара: " + biggestExpense.getItemName());
        System.out.println("Сумма дохода: " + (biggestExpense.getQuantity() * biggestExpense.getSumOfOne()));
        System.out.println();

        System.out.println("Самый большой доход за " + MonthConverter.convertMonth(monthKey));
        System.out.println("Название товара: " + biggestEarning.getItemName());
        System.out.println("Сумма дохода: " + (biggestEarning.getQuantity() * biggestEarning.getSumOfOne()));
        System.out.println("|---------------------------------------------|");

    }


    private MonthlyRecord getBiggestExpense(List<MonthlyRecord> monthlyReportList) {

        double biggestExpense = 0.0;
        MonthlyRecord biggestRecord = null;

        for(MonthlyRecord record : monthlyReportList) {
            double expense = record.getQuantity() * record.getSumOfOne();
            if(expense > biggestExpense) {
                biggestExpense = expense;
                biggestRecord = record;
            }
        }
        return biggestRecord;
    }

    private MonthlyRecord getBiggestEarning(List<MonthlyRecord> monthlyReportList) {

        double biggestEarning = 0.0;
        MonthlyRecord biggestRecord = null;

        for(MonthlyRecord record : monthlyReportList) {
            double earning = record.getQuantity() * record.getSumOfOne();
            if(earning > biggestEarning) {
                biggestEarning = earning;
                biggestRecord = record;
            }
        }
        return biggestRecord;
    }
}
