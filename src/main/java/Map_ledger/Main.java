package Map_ledger;

import java.util.*;


public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final AccountBook accountBook = new AccountBook();

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int menu = Integer.parseInt(sc.nextLine());

            switch (menu) {
                case 1 -> addExpense();
                case 2 -> printExpenses();
                case 3 -> deleteExpense();
                case 4 -> printTotalExpense();
                case 5 -> printCategories();
                case 6 -> printTotalByCategories();
                case 0 -> {
                    System.out.println("Exiting the program.");
                    return;
                }
                default -> System.out.println("Invalid menu.");
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("=== Account Book ===");
        System.out.println("1. Add");
        System.out.println("2. List");
        System.out.println("3. Delete");
        System.out.println("4. Total");
        System.out.println("5. Categories");
        System.out.println("6. Total by category");
        System.out.println("0. Exit");
        System.out.print("Select: ");
    }

    private static void addExpense() {
        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Category: ");
        String category = sc.nextLine();

        System.out.print("Amount: ");
        int amount = Integer.parseInt(sc.nextLine());

        try {
            Expense expense = new Expense(name, category, amount);
            accountBook.add(expense);
            System.out.println("Added: " + expense.name() + " / " + expense.category() + " / " + expense.amount());
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void printExpenses() {
        List<Expense> expenses = accountBook.findAll();
        if (expenses.isEmpty()) {
            System.out.println("No expenses.");
            return;
        }

        System.out.printf("%-4s %-15s %-10s %10s%n", "No", "Name", "Category", "Amount");
        System.out.println("-".repeat(42));

        for (int i = 0; i < expenses.size(); i++) {
            Expense e = expenses.get(i);
            System.out.printf("%-4d %-15s %-10s %,10d%n",
                    i + 1, e.name(), e.category(), e.amount());
        }

        System.out.println("-".repeat(42));
        System.out.printf("%-31s %,10d%n", "Total", accountBook.total());
    }

    private static void deleteExpense() {
        System.out.print("Number to delete: ");
        int number = Integer.parseInt(sc.nextLine());
        try {
            Expense removed = accountBook.delete(number);
            System.out.println("Deleted: " + removed.name());
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void printTotalExpense() { System.out.println("Total: " + accountBook.total()); }

    private static void printCategories() {
        Set<String> categories = accountBook.categories();
        if (categories.isEmpty()) {
            System.out.println("No entries.");
            return;
        }
        System.out.println("Categories: " + String.join(", ", categories));
    }

    private static void printTotalByCategories() {
        Map<String, Integer> totalByCategory = accountBook.totalByCategory();
        if (totalByCategory.isEmpty()) {
            System.out.println("No entries.");
            return;
        }
        for (Map.Entry<String, Integer> entry : totalByCategory.entrySet()) { System.out.println(entry.getKey() + ": " + entry.getValue()); }
    }
}
