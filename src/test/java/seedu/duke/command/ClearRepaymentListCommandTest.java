package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.data.Data;
import seedu.duke.exceptions.InvalidClearRepaymentException;
import seedu.duke.exceptions.InvalidNumberException;
import seedu.duke.ui.Ui;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClearRepaymentListCommandTest {
    @Test
    void execute_clearIndex() throws IOException, InvalidClearRepaymentException, InvalidNumberException {
        Data data = new Data(null, null, null);
        data.repaymentList.addItem("Ben", "USD", 20.00, "10 Nov 2020");
        Ui ui = new Ui();
        assertThrows(InvalidNumberException.class, () -> new ClearRepaymentListCommand(false, 3).execute(data, ui));
        ClearRepaymentListCommand c = new ClearRepaymentListCommand(false, 1);
        c.execute(data, ui);
        assertEquals(0, data.repaymentList.getListSize());
        assertThrows(InvalidClearRepaymentException.class, () -> c.execute(data, ui));
    }

    @Test
    void execute_clearAll() throws IOException, InvalidClearRepaymentException, InvalidNumberException {
        Data data = new Data(null, null, null);
        data.repaymentList.addItem("Ben", "USD", 20.00, "10 Nov 2020");
        Ui ui = new Ui();
        ClearRepaymentListCommand c = new ClearRepaymentListCommand(true, 0);
        c.execute(data, ui);
        assertEquals(0, data.repaymentList.getListSize());
        assertThrows(InvalidClearRepaymentException.class, () -> c.execute(data, ui));
    }
}
