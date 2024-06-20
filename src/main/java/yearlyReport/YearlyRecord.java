package yearlyReport;

public class YearlyRecord {
    private String month;
    private double amount;
    private boolean isExpense;

    public YearlyRecord(String month, double amount, boolean isExpense) {
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isExpense() {
        return isExpense;
    }

    public void setIsExpense(boolean is_expense) {
        this.isExpense = is_expense;
    }

    @Override
    public String toString() {
        return "YearlyReportRecord{" +
                "month='" + month + '\'' +
                ", amount=" + amount +
                ", is_expense=" + isExpense +
                '}';
    }
}
