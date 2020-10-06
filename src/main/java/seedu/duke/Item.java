package seedu.duke;

import java.time.LocalDate;

public class Item {
    protected String description;
    protected double amount;

    public Item(String description, double amount) {
        this.description = description;
        this.amount = amount;
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

    public double getAmount() {
        return amount;
    }

    public String getYearMonth() {
        return currentDate().substring(0, 7);
    }

    public String toString() {
        return currentDate() + " " + description + " $" + amount;
    }
}
