package seedu.duke;

public class ClearCommand {

    private boolean isClearAll = false;
    private int clearIndex;

    public ClearCommand(boolean isClearAll, int clearIndex) {
        this.isClearAll = isClearAll;
        if(isClearAll == false) {
            this.clearIndex = clearIndex;
        }
    }
    public void execute(SpendingList spendingList, Ui ui) {
        if(!isClearAll) {
            ui.printClearIndex(spendingList.getItem(clearIndex - 1));
            spendingList.deleteItem(clearIndex - 1);
        } else {
            int totalItems = spendingList.getTotalItems();
            while(totalItems > 0) {
                spendingList.deleteItem(totalItems - 1);
                totalItems -= 1;
            }
            ui.printClearAll();
        }
    }
}
