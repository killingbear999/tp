package seedu.duke.command;

import seedu.duke.SpendingList;
import seedu.duke.Ui;
import seedu.duke.exceptions.InvalidMonthException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author pinfang
public class SummaryCommand extends Command {
    private String year;
    private String month;
    private String period;
    private boolean isValidMonth = true;
    private static final Logger logger = Logger.getLogger("SummaryCommand");
    private final DateFormatter dateFormatter = new DateFormatter();

    //@@author pinfang
    public SummaryCommand(String year, String month) {
        this.year = year;
        this.month = dateFormatter.changeMonthFormat(month);
        period = this.year + "-" + this.month;
    }

    public SummaryCommand(String year) {
        this.year = year;
        period = year;
    }

    public SummaryCommand() {
        this.year = dateFormatter.getCurrentYear();
        this.month = dateFormatter.getCurrentMonth();
        period = year + "-" + month;
    }

    @Override
    public void execute(SpendingList spendingList, Ui ui) throws InvalidMonthException {
        if (month == null) {
            isValidMonth = false;
        }
        if (isValidMonth) {
            logger.log(Level.FINE, "going to start processing");
            double amountSpent = spendingList.getSpendingAmount(period);
            logger.log(Level.FINE, "end of processing");
            ui.printSummaryMessage(amountSpent);
        } else {
            throw new InvalidMonthException();
        }
    }
}
