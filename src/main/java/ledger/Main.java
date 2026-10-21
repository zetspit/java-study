package ledger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final List<Expense> list = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int menu = Integer.parseInt(sc.nextLine());

            switch (menu) {
                case 1 -> addExpense();
                case 2 -> printExpenses();
                case 3 -> deleteExpense();
                case 4 -> printTotal();
                case 0 -> {
                    System.out.println("Exiting the program.");
                    return;
                }
                default -> System.out.println("Invalid option. Please choose 0-4.");
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("===== Expense Ledger =====");
        System.out.println("1. Add expense");
        System.out.println("2. View expenses");
        System.out.println("3. Delete expense");
        System.out.println("4. Total amount");
        System.out.println("0. Exit");
        System.out.print("Select: ");
    }

    private static void addExpense() {
        System.out.print("Date (YYYY-MM-DD): ");
        String date = sc.nextLine();
        System.out.print("Category: ");
        String category = sc.nextLine();
        System.out.print("Amount: ");
        int amount = Integer.parseInt(sc.nextLine());

        list.add(new Expense(date, category, amount));
        System.out.println("Expense added.");
    }

    private static void printExpenses() {
        if (list.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }
        System.out.printf("%-4s %-12s %-15s %10s%n", "No.", "Date", "Category", "Amount");
        System.out.println("-".repeat(44));

        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%-4d %s%n", i + 1, list.get(i));
        }
    }

    private static void deleteExpense() {
        if (list.isEmpty()) {
            System.out.println("No expenses to delete.");
            return;
        }
        printExpenses();
        System.out.print("Enter the number to delete: ");
        int index = Integer.parseInt(sc.nextLine());

        if (index < 1 || index > list.size()) {
            System.out.println("Invalid number. Please enter 1-" + list.size() + ".");
            return;
        }
        Expense removed = list.remove(index - 1);
        System.out.println("Deleted: " + removed);
    }

    private static int calculateTotal() {
        int total = 0;
        for (Expense e : list) total += e.getAmount();
        return total;
    }

    private static void printTotal() {
        System.out.printf("Total amount: %,d%n", calculateTotal());
    }
}