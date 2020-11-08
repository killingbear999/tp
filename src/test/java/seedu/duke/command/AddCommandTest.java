package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.data.Data;
import seedu.duke.exceptions.InvalidAmountException;
import seedu.duke.exceptions.InvalidInputCurrencyException;
import seedu.duke.exceptions.InvalidNameException;
import seedu.duke.ui.Ui;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddCommandTest {

    @Test
    void execute() throws IOException, InvalidInputCurrencyException, InvalidAmountException, InvalidNameException {
        AddCommand addCommand = new AddCommand("sushi", "SGD", 3.0, null);
        Ui ui = new Ui();
        Data data = new Data(null, null, null);
        addCommand.execute(data, ui);
        assertEquals("sushi", data.spendingList.getItem(0).getDescription());
        assertEquals("SGD", data.spendingList.getItem(0).getSymbol());
        assertEquals(3.0, data.spendingList.getItem(0).getAmount());
    }
}