package seedu.duke.command;

import org.junit.jupiter.api.Test;

import seedu.duke.data.Data;
import seedu.duke.data.SpendingList;
import seedu.duke.exceptions.InvalidYearException;
import seedu.duke.ui.Ui;
import seedu.duke.data.Item;
import seedu.duke.exceptions.InvalidMonthException;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SummaryCommandTest {
    public static ArrayList<Item> initList(Item... items) {
        return new ArrayList<>(Arrays.asList(items));
    }

    public static SpendingList initSpendingList(Item... items) {
        return new SpendingList(initList(items));
    }

    SpendingList spendingList = initSpendingList(
        new Item("noodle", "S$", 1.5),
        new Item("fish", "S$", 10),
        new Item("books", "S$", 8.9)
    );

    @Test
    public void badSummary() {
        Ui ui = new Ui();
        SummaryCommand invalidSummaryMonth = new SummaryCommand("2020", "fdj");
        try {
            Data data = new Data(null, null, null);
            invalidSummaryMonth.execute(data, ui);
        } catch (InvalidMonthException e) {
            assertEquals("Sorry, your month input is invalid. Please re-enter the month.", e.toString());
        } catch (InvalidYearException e) {
            assertEquals("Sorry, your year input is invalid. Please re-enter the year.", e.toString());
        }
    }

    @Test
    public void goodSummary() throws InvalidMonthException, InvalidYearException {
        Ui ui = new Ui();
        SummaryCommand validSummaryMonth = new SummaryCommand("2020", "Jan");
        SummaryCommand summaryYear = new SummaryCommand("2020", null);
        SummaryCommand summary = new SummaryCommand();
        Data data = new Data(null, null, null);
        validSummaryMonth.execute(data, ui);
        summaryYear.execute(data, ui);
        summary.execute(data, ui);
    }
}
