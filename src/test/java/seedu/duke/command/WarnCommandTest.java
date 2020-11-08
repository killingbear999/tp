package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.data.Data;
import seedu.duke.data.Item;
import seedu.duke.data.SpendingList;
import seedu.duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

//@@author killingbear999
public class WarnCommandTest {
    public static ArrayList<Item> initList(Item... items) {
        return new ArrayList<>(Arrays.asList(items));
    }
    
    public static SpendingList initSpendingList(Item... items) {
        return new SpendingList(initList(items));
    }
    
    SpendingList realList = initSpendingList(
            new Item("sushi", "S$", 10.5),
            new Item("bubble tea", "S$", 4.0),
            new Item("medicine", "S$", 5.0)
    );
    
    @Test
    public void approachLimit() throws IOException {
        Ui ui = new Ui();
        Data data = new Data(null, null, null);
        data.budget.addBudget("SGD", 20.0);
        WarnCommand approachingLimit = new WarnCommand();
        approachingLimit.execute(data, ui);
    }
    
    @Test
    public void exceedLimit() throws IOException {
        Ui ui = new Ui();
        Data data = new Data(null, null, null);
        data.budget.addBudget("SGD", 10.0);
        WarnCommand exceedingLimit = new WarnCommand();
        exceedingLimit.execute(data, ui);
    }
}
