package ledger;

public class Expense {
    private final String date;
    private final String category;
    private final int amount;

    public Expense (String date, String category, int amount) {
        this.date = date;
        this.category = category;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("%-12s %-15s %,10d", date, category, amount);
    }

    public int getAmount() {
        return amount;
    }
}