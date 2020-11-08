package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.data.Data;
import seedu.duke.exceptions.InvalidClearBudgetException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


//@@author xieyaoyue
class ClearBudgetCommandTest {

    @Test
    void execute() throws IOException {
        Data data = new Data(null, null, null);
        data.budget.addBudget("SGD", 100);
        assertTrue(data.budget.hasBudget);
        data.budget.clearBudget();
        assertFalse(data.budget.hasBudget);
        ClearBudgetCommand c = new ClearBudgetCommand();
        assertThrows(InvalidClearBudgetException.class, () -> c.execute(data, null));

    }
}
