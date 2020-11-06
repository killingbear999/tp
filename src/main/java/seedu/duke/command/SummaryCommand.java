package seedu.duke.command;

import seedu.duke.data.RepaymentList;
import seedu.duke.data.SpendingList;
import seedu.duke.exceptions.InvalidYearException;
import seedu.duke.ui.Ui;
import seedu.duke.data.Category;
import seedu.duke.exceptions.InvalidMonthException;
import seedu.duke.utilities.DateFormatter;

import java.util.logging.Level;
import java.util.logging.Logger;

//@@author pinfang
public class SummaryCommand extends DateCommand {
    private String period;
    private boolean isValidMonth = true;
    private boolean isValidYear = true;
    private static final Logger logger = Logger.getLogger("SummaryCommand");
    private final DateFormatter dateFormatter = new DateFormatter();

    //@@author pinfang
    public SummaryCommand(String year, String month) {
        String monthFormat = dateFormatter.changeMonthFormat(month);
        isValidYear = hasValidYear(year);
        if (monthFormat == null && month != null) {
            isValidMonth = false;
            period = year;
        } else {
            period = year + "-" + monthFormat;
        }
    }

    public SummaryCommand() {
        period = "-";
    }

    @Override
    public void execute(SpendingList spendingList, RepaymentList repaymentList, Ui ui)
            throws InvalidMonthException, InvalidYearException {
        logger.log(Level.FINE, "going to start processing");
        if (!isValidMonth) {
            throw new InvalidMonthException();
        }
        if (!isValidYear) {
            throw new InvalidYearException();
        }
        double amountSpent = spendingList.getSpendingAmountTime(period);
        logger.log(Level.FINE, "end of processing");
        ui.printSummaryMessage(amountSpent);
        for (Category c : Category.values()) {
            double categoryAmountSpent = spendingList.getSpendingAmountCategory(c.toString(), period);
            ui.printSummaryCategory(c.name(), categoryAmountSpent);
        }
    }

    private boolean hasValidYear(String year) {
        try {
            Integer.parseInt(year);
        } catch (NumberFormatException e) {
            return false;
        }
        return year.length() == 4;
    }
}
