package seedu.duke;

import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private Scanner in;
    private PrintStream out;
    private static final String SEPARATE_LINE_CHAR = "-";
    private static final int SEPARATE_LINE_LENGTH = 40;
    private static final String LOGO = "  _____         __ _      ___              \n" +
            " / ___/__ ___  / /| | /| / (_)__ ___       \n" +
            "/ /__/ -_) _ \\/ __/ |/ |/ / (_-</ -_)      \n" +
            "\\___/\\__/_//_/\\__/|__/|__/_/___/\\__/       \n" +
            "  / _ \\___  / / /__ ____| | /| / (_)__ ___ \n" +
            " / // / _ \\/ / / _ `/ __/ |/ |/ / (_-</ -_)\n" +
            "/____/\\___/_/_/\\_,_/_/  |__/|__/_/___/\\__/ \n" +
            "                                          ";
    private static final String HELP = "";

    public Ui() {
        this(new Scanner(System.in), System.out);
    }

    public Ui(Scanner in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    public String getUserInput() {
        return in.nextLine();
    }

    public void printMessage(String message) {
        out.println(message);
        drawSeparateLine();
    }

    public void printWelcomeMessage() {
        drawSeparateLine();
        out.println(LOGO);
        drawSeparateLine();
    }

    public void printWelcomeMessage(String filePath) {
        printWelcomeMessage();
        out.println("Local file path:");
        out.println(filePath);
        drawSeparateLine();
    }

    private void drawSeparateLine() {
        out.println(SEPARATE_LINE_CHAR.repeat(SEPARATE_LINE_LENGTH));
    }

    public void printSpendingList(SpendingList spendingList) {
        for (int i = 0; i < spendingList.getListSize(); i++) {
            out.println(spendingList.getItem(i));
        }
        drawSeparateLine();
    }

    public void printHelp() {
        out.println(HELP);
        drawSeparateLine();
    }

    public void printClearIndex(Item item) {
        out.println("You've deleted the record:");
        out.print(item);
        drawSeparateLine();
    }

    public void printClearAll() {
        out.println("You've deleted all the records.");
        drawSeparateLine();
    }

    public void printAdd(SpendingList spendingList) {
        out.println("You've added the record:");
        out.println(spendingList.getItem(spendingList.getListSize() - 1));
        drawSeparateLine();
    }
}
