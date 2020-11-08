package seedu.duke.command;

import seedu.duke.data.Data;
import seedu.duke.ui.Ui;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReminderCommand extends Command {
    private LocalDate startWeek;
    private WarnCommand warn;
    private ArrayList<String> week = new ArrayList<>();

    public ReminderCommand() {
        saveDatesToList();
        warn = new WarnCommand();
    }

    @Override
    public void execute(Data data, Ui ui) {
        double amountRemained = 0;
        if (data.budget.hasBudget) {
            warn.execute(data, ui);
            amountRemained = findRemainingAmount(data);
        }

        double totalAmountSpent = 0;
        for (String i: week) {
            totalAmountSpent += data.spendingList.getSpendingAmountTime(i);
        }

        ui.printReminderMessage(totalAmountSpent, amountRemained, startWeek.toString());
    }

    private LocalDate startOfWeek() {
        LocalDate today = LocalDate.now();
        DayOfWeek day = today.getDayOfWeek();
        switch (day) {
        case MONDAY:
            startWeek = today;
            break;
        case TUESDAY:
            startWeek = today.minusDays(1);
            break;
        case WEDNESDAY:
            startWeek = today.minusDays(2);
            break;
        case THURSDAY:
            startWeek = today.minusDays(3);
            break;
        case FRIDAY:
            startWeek = today.minusDays(4);
            break;
        case SATURDAY:
            startWeek = today.minusDays(5);
            break;
        case SUNDAY:
            startWeek = today.minusDays(6);
            break;
        default:
            break;
        }

        return startWeek;
    }

    private void saveDatesToList() {
        LocalDate startDay = startOfWeek();
        for (int i = 0; i < 7; i++) {
            week.add(startDay.minusDays(-i).toString());
        }
    }

    public double findRemainingAmount(Data data) {
        double budgetLimit = data.budget.getBudgetLimit();
        double currentAmount = data.spendingList.getCurrentAmount(data);
        return budgetLimit - currentAmount;
    }
}
