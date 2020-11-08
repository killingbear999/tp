package seedu.duke.command;

import seedu.duke.data.Data;
import seedu.duke.exceptions.InvalidClearBudgetException;
import seedu.duke.ui.Ui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClearBudgetCommand extends Command {
    private static Logger logger = Logger.getLogger("ClearBudgetCommand");

    @Override
    public void execute(Data data, Ui ui) throws
        InvalidClearBudgetException, IOException {
        if (!data.budget.hasBudget) {
            throw new InvalidClearBudgetException();
        }
        logger.log(Level.FINE, "going to clear budget");
        data.budget.clearBudget();
        ui.printClearBudget();
    }
}
