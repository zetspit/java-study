package Map_ledger;

import java.util.*;

public class AccountBook {
    private final List<Expense> expenses = new ArrayList<>();

    public void add(Expense expense) {
        expenses.add(expense);
    }

    public Expense delete(int number) {
        if (number > expenses.size() || number < 1) {
            throw new IllegalArgumentException("No expenses.");
        }
        return expenses.remove(number - 1);
    }

    public List<Expense> findAll() {
        return List.copyOf(expenses);
    }
    public int total() {
        int total = 0;
        for (Expense e : expenses) total += e.amount();
        return total;
    }

    public Set<String> categories() {
        Set<String> categories = new LinkedHashSet<>();
        for (Expense e : expenses) categories.add(e.category());
        return categories;
    }

    public Map<String, Integer> totalByCategory() {
        Map<String, Integer> totalByCategory = new TreeMap<>();
        for (Expense e : expenses) totalByCategory.merge(e.category(), e.amount(), Integer::sum);
        return totalByCategory;
    }
}