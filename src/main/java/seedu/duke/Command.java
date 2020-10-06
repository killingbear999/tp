package seedu.duke;

public abstract class Command {
    public abstract void execute(SpendingList spendingList, Ui ui, Storage storage);
    public abstract boolean isExit();
}
