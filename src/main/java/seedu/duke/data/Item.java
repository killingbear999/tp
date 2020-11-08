package seedu.duke.data;

import java.time.LocalDate;

//@@author pinfang
public class Item {
    private String description;
    private double amount;
    private String symbol;
    private String date;
    private String category = "Other";

    public Item(String description, String symbol, double amount) {
        this.description = description;
        this.symbol = symbol;
        this.amount = amount;
        this.date = currentDate();
    }

    public Item(String description, String symbol, double amount, String category) {
        this.description = description;
        this.symbol = symbol;
        this.amount = amount;
        this.date = currentDate();
        this.category = Category.categoryName(category);
    }

    protected String currentDate() {
        LocalDate date = LocalDate.now();
        return date.toString();
    }

    public void editDescription(String description) {
        this.description = description;
    }

    public void editAmount(double amount) {
        this.amount = amount;
    }

    public void editSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void editCategory(String category) {
        this.category = Category.categoryName(category);
    }

    public String getSymbol() {
        return symbol;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }
    
    public void editDate(String specificDate) {
        this.date = specificDate;
    }

    public String toString() {
        return date + " [" + category + "] " + description + " " + symbol + " " + String.format("%.4f", amount);
    }
}
