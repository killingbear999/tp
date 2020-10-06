package seedu.duke;

import java.util.ArrayList;
import java.util.List;

public class SpendingList {
    private List<Item> spendingList;

    public SpendingList() {
        spendingList = new ArrayList<>();
    }

    public SpendingList(List<Item> spendingList) {
        this.spendingList = spendingList;
    }

    public void addItem(String description, double amount) {
        Item item = new Item(description, amount);
        spendingList.add(item);
    }

    public void deleteItem(int index) {
        spendingList.remove(index);
    }

    public void clearAllItems() {
        spendingList.clear();
    }

    public Item getItem(int index) {
        return spendingList.get(index);
    }

    public int getListSize() {
        return spendingList.size();
    }

    public List<Item> getSpendingList() {
        return spendingList;
    }

//    public void updateSpendingList() {
//        convertCommand convertCommand = new ConvertCommand(description);
//        spendingList = convertCommand.updateSpendingList();
//    }
}
