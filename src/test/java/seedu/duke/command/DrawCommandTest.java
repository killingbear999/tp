package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.data.Data;
import seedu.duke.data.Item;
import seedu.duke.data.SpendingList;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;

//@@author Wu-Haitao
class DrawCommandTest {
    SpendingList spendingList;
    Ui ui = new Ui();

    private ArrayList<Item> initList(Item... items) {
        return new ArrayList<>(Arrays.asList(items));
    }

    private void initSpendingList() {
        Item[] items = new Item[]{new Item("Item0", "USD", 1.5),
            new Item("Item1", "USD", 21, "Food"),
            new Item("Item2", "USD", 13.14, "Education"),
            new Item("Item3", "USD", 12, "Transportation"),
            new Item("Item4", "USD", 28.0, "Food"),
            new Item("Item5", "USD", 156.7, "Entertainment"),
            new Item("Item6", "USD", 96, "Health"),
            new Item("Item7", "USD", 1.2, "Education")};
        this.spendingList =  new SpendingList(initList(items));
        this.spendingList.getItem(0).editDate("2020-09-11");
        this.spendingList.getItem(1).editDate("2020-09-11");
        this.spendingList.getItem(2).editDate("2020-08-11");
        this.spendingList.getItem(3).editDate("2020-08-12");
        this.spendingList.getItem(4).editDate("2020-12-14");
        this.spendingList.getItem(5).editDate("2020-01-14");
        this.spendingList.getItem(6).editDate("2020-03-23");
        this.spendingList.getItem(7).editDate("2020-05-01");
    }

    @Test
    void execute() {
        initSpendingList();
        try {
            Data data = new Data(null, null, null);
            new DrawCommand("2020", null, false).execute(data, ui);
        } catch (Exception e) {
            assert false;
        }
        assert true;
    }
}