package seedu.duke.command;

import seedu.duke.data.Data;
import seedu.duke.ui.Ui;

//@@author killingbear999
public class WarnCommand extends Command {
    private double budgetLimit;
    private double budgetThreshold;
    private String outputCurrency;
    private final double threshold = 0.9;
    
    public WarnCommand() {
    }
    
    public void execute(Data data, Ui ui) {
        outputCurrency = data.budget.getCurrency();
        budgetLimit = data.budget.getBudgetLimit();
        budgetThreshold = budgetLimit * threshold;
        double currentAmount = data.spendingList.getCurrentAmount(data);
        if (currentAmount >= budgetThreshold && currentAmount < budgetLimit) {
            double amountRemaining = budgetLimit - currentAmount;
            ui.printApproachingWarningMessage(outputCurrency, amountRemaining);
        } else if (currentAmount >= budgetLimit) {
            ui.printExceedingWarningMessage();
        }
    }
}
