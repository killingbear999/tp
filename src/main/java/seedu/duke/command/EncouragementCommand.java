package seedu.duke.command;

import seedu.duke.data.Data;
import seedu.duke.ui.Ui;

public class EncouragementCommand extends Command {

    @Override
    public void execute(Data data, Ui ui) {
        ui.printEncouragementMessage();
    }
}
