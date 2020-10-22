package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.SpendingList;
import seedu.duke.Ui;
import seedu.duke.category.Item;

import java.util.ArrayList;
import java.util.Arrays;

class DrawCommandTest {
    public static ArrayList<Item> initList(Item... items) {
        return new ArrayList<>(Arrays.asList(items));
    }

    public static SpendingList initSpendingList(Item... items) {
        return new SpendingList(initList(items));
    }

    SpendingList spendingList = initSpendingList(
            new Item("Item0", "USD", 1.5),
            new Item("Item1", "USD", 233, "Food"),
            new Item("Item2", "USD", 13.14, "Education"),
            new Item("Item3", "USD", 50.0, "Education")
    );

    Ui ui = new Ui();

    @Test
    void execute() {
        spendingList.getItem(0).editDate("2020-09-11");
        spendingList.getItem(1).editDate("2019-09-11");
        spendingList.getItem(2).editDate("2020-08-11");
        spendingList.getItem(3).editDate("2020-08-12");
        try {
            new DrawCommand("2020").execute(spendingList, ui);
        } catch (Exception e) {
            assert false;
        }
        assert true;
    }
}