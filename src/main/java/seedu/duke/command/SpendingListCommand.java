package seedu.duke.command;

import seedu.duke.data.Data;
import seedu.duke.ui.Ui;

public class SpendingListCommand extends DateCommand {
    public SpendingListCommand() {
        // TODO : accept all time
    }

    public SpendingListCommand(String year, String month) {
        // TODO : accept year and month (possibly null)
        // TODO : accept category
    }

    @Override
    public void execute(Data data, Ui ui) {
        ui.printSpendingList(data.spendingList);
    }
}
