package monthlyReport;

public class MonthlyRecord {
    private String itemName;
    private boolean isExpense;
    private int quantity;
    private double sumOfOne;

    public MonthlyRecord(String itemName, boolean isExpense, int quantity, double sumOfOne) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String item_name) {
        this.itemName = item_name;
    }

    public boolean isExpense() {
        return isExpense;
    }

    public void setIsExpense(boolean is_expense) {
        this.isExpense = is_expense;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSumOfOne() {
        return sumOfOne;
    }

    public void setSumOfOne(double sum_of_one) {
        this.sumOfOne = sum_of_one;
    }

    @Override
    public String toString() {
        return "MonthlyReportRecord{" +
                "item_name='" + itemName + '\'' +
                ", is_expense=" + isExpense +
                ", quantity=" + quantity +
                ", sum_of_one=" + sumOfOne +
                '}';
    }
}
