package Map_ledger;

import java.util.Locale;

public record Expense(String name, String category, int amount) {
    public Expense {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required.");
        }
        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("Category is required.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0.");
        }

        name = name.strip();
        category = category.strip().toLowerCase(Locale.ROOT);
    }
}
