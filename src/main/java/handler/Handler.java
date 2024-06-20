package handler;

import fileReader.CustomFileReader;
import monthlyReport.MonthlyRecord;
import monthlyReport.MonthlyReportsStat;
import yearlyReport.YearlyRecord;
import yearlyReport.YearlyReportsStat;

import java.util.*;

public class Handler {

     private ReportsHandler reportsHandler;

     private CustomFileReader customFileReader;

     private MonthlyReportsStat monthlyReportsStat;

     private YearlyReportsStat yearlyReportsStat;

     private Map<String, List<String>> rawMonthlyData;

     private Map<String, List<String>> rawYearlyData;


     private final String YEAR_KEY = "y.2021";

     public Handler() {

          customFileReader = new CustomFileReader();
          monthlyReportsStat = new MonthlyReportsStat();
          yearlyReportsStat = new YearlyReportsStat();
          rawMonthlyData = new HashMap<>();
          rawYearlyData = new HashMap<>();
          reportsHandler = new ReportsHandler();
     }

     public void handleCommand(int command) {
          switch (command) {
               case 1: {
                    for (int i = 1; i <= 3; i++) {
                         String key = "m.20210" + i;
                         List<String> value = customFileReader.readFileContents("m.20210" + i + ".csv");

                         rawMonthlyData.put(key, value);

                    }

                    System.out.println("Месячные отчеты успешно считаны!");
                    System.out.println("|---------------------------------------------|");
               }
               break;

               case 2: {
                    String key = "y.2021";
                    List<String> value = customFileReader.readFileContents("y.2021.csv");

                    rawYearlyData.put(key, value);

                    System.out.println("Годовой отчет спешно считан!");
                    System.out.println("|---------------------------------------------|");
               }
               break;

               case 3: {
                    reportsHandler.proceedReports(rawMonthlyData, rawYearlyData);
               }
               break;

               case 4: {
                    for (String key : rawMonthlyData.keySet()) {
                         List<MonthlyRecord> preparedList = getMonthlyRecords(rawMonthlyData.get(key));
                         monthlyReportsStat.printMonthlyStat(preparedList, key);
                    }
               }
               break;

               case 5: {
                    List<YearlyRecord> yearlyRecordList = getYearlyRecords(rawYearlyData.get(YEAR_KEY));
                    yearlyReportsStat.printYearlyStat(yearlyRecordList);
               }
               break;

               default: {
                    System.out.println("Нет такой команды!");;
               }
          }
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
