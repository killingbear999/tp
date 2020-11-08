package seedu.duke.command;

import seedu.duke.data.Category;
import seedu.duke.data.Data;
import seedu.duke.exceptions.InvalidMonthException;
import seedu.duke.exceptions.InvalidYearException;
import seedu.duke.ui.Ui;
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
        System.out.println(year + month);
        if (monthFormat == null && month != null) {
            isValidMonth = false;
            period = year;
        } else if (isValidYear && month == null) {
            period = year;
        } else {
            period = year + "-" + monthFormat;
        }
    }

    public SummaryCommand() {
        period = "-";
    }

    @Override
    public void execute(Data data, Ui ui)
            throws InvalidMonthException, InvalidYearException {
        logger.log(Level.FINE, "going to start processing");
        if (!isValidMonth) {
            throw new InvalidMonthException();
        }
        if (!isValidYear) {
            throw new InvalidYearException();
        }
        double amountSpent = data.spendingList.getSpendingAmountTime(period);
        logger.log(Level.FINE, "end of processing");
        ui.printSummaryMessage(amountSpent);
        for (Category c : Category.values()) {
            double categoryAmountSpent = data.spendingList.getSpendingAmountCategory(c.toString(), period);
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
