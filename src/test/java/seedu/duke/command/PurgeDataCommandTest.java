package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.data.Data;
import seedu.duke.data.RepaymentList;
import seedu.duke.exceptions.InvalidClearBudgetException;
import seedu.duke.exceptions.InvalidClearRepaymentException;
import seedu.duke.exceptions.InvalidClearSpendingException;
import seedu.duke.ui.Ui;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PurgeDataCommandTest {
    @Test
    void execute() throws IOException, InvalidClearRepaymentException, InvalidClearSpendingException,
            InvalidClearBudgetException {
        Data data = new Data(null, null, null);
        data.spendingList.addItem("buy chicken rice", "$", 3.0);
        data.spendingList.addItem("buy sushi", "$", 5.1);
        data.budget.addBudget("SGD", 100);
        data.repaymentList.addItem("John","USD",123,"2020-02-20");
        data.repaymentList = new RepaymentList();
        data.repaymentList.addItem("John","USD",123,"2020-02-20");
        assertEquals(1,data.repaymentList.getListSize());
        PurgeDataCommand purgeDataCommand = new PurgeDataCommand();
        Ui ui = new Ui();
        purgeDataCommand.execute(data, ui);
        assertFalse(data.budget.hasBudget);
        assertEquals(0, data.spendingList.getListSize());
        assertEquals(0, data.repaymentList.getListSize());
    }
}