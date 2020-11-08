package seedu.duke.command;

import seedu.duke.data.Data;
import seedu.duke.data.Item;
import seedu.duke.exceptions.EmptyListException;
import seedu.duke.exceptions.InvalidCurrencyException;
import seedu.duke.exceptions.InvalidInputCurrencyException;
import seedu.duke.exceptions.InvalidOutputCurrencyException;
import seedu.duke.ui.Ui;
import seedu.duke.utilities.DecimalFormatter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author killingbear999
public class ConvertCommand extends Command {
    private String currencies;
    private String outputCurrency;
    private String inputCurrency;
    private double exchangeRate = 1.0;
    public static ArrayList<Item> newSpendingList = new ArrayList<>();
    private static Logger logger = Logger.getLogger("ConvertCommand");

    /** SGD to USD; USD to SGD; SGD to CNY; CNY to SGD. */
    private final String[][] exchangeRates = {
            {"SGDUSD", "USDSGD", "SGDCNY", "CNYSGD"},
            {"0.74", "1.35135", "5.00", "0.20"},
    };


    public ConvertCommand() {
    }

    public ConvertCommand(String source, String target) {
        this.inputCurrency = source;
        this.outputCurrency = target;
    }

    private String identifyCurrency() {
        return inputCurrency + outputCurrency;
    }

    private void findExchangeRate() {
        for (int i = 0; i < 4; i++) {
            if (exchangeRates[0][i].equals(currencies)) {
                exchangeRate = Double.parseDouble(exchangeRates[1][i]);
                assert exchangeRate == Double.parseDouble(exchangeRates[1][i]) : "Incorrect exchange rate";
                break;
            }
        }
    }
    
    private boolean isValid() {
        for (int i = 0; i < 4; i++) {
            if (exchangeRates[0][i].equals(currencies)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void execute(Data data, Ui ui) throws IOException,
            InvalidInputCurrencyException, InvalidOutputCurrencyException, InvalidCurrencyException,
            EmptyListException {
        if (outputCurrency.equals("SGD") || outputCurrency.equals("USD") || outputCurrency.equals("CNY")) {
            if (inputCurrency.equals("SGD") || inputCurrency.equals("USD") || inputCurrency.equals("CNY")) {
                convert(data, ui);
            } else {
                throw new InvalidInputCurrencyException();
            }
        } else {
            throw new InvalidOutputCurrencyException();
        }
    }
    
    private void convert(Data data, Ui ui) throws IOException, InvalidCurrencyException,
            EmptyListException {
        int size = data.spendingList.getListSize();
        if (size == 0) {
            throw new EmptyListException();
        }
        String defaultCurrency = data.spendingList.getItem(0).getSymbol();
        if (inputCurrency.equals(defaultCurrency)) {
            logger.log(Level.FINE, "going to start processing");
            newSpendingList = data.spendingList.getSpendingList();
            currencies = identifyCurrency();
            findExchangeRate();
            updateList();
            if (isValid()) {
                ui.printConvertCurrency(outputCurrency);
            } else {
                throw new InvalidCurrencyException();
            }
            data.spendingList.updateSpendingList();
            updateBudgetList(data);
            logger.log(Level.FINE, "end of processing");
        } else {
            ui.printInvalidConversion(defaultCurrency);
        }
    }
    
    private void updateList() {
        for (Item item : newSpendingList) {
            if (!item.getSymbol().equals(outputCurrency)) {
                updateNewAmount(item);
                updateCurrency(item);
            }
        }
    }
    
    private void updateNewAmount(Item currentString) {
        double amount = currentString.getAmount();
        amount = amount * exchangeRate;
        DecimalFormatter decimalFormatter = new DecimalFormatter();
        amount = decimalFormatter.convert(amount);
        currentString.editAmount(amount);
    }

    private void updateCurrency(Item currentString) {
        switch (currencies) {
        case "SGDUSD":
            currentString.editSymbol("USD");
            break;
        case "USDSGD":
            currentString.editSymbol("SGD");
            break;
        case "SGDCNY":
            currentString.editSymbol("CNY");
            break;
        case "CNYSGD":
            currentString.editSymbol("SGD");
            break;
        default:
        }
    }
    
    public void updateBudgetList(Data data) throws IOException {
        if (data.budget.getCurrency().equals(outputCurrency)) {
            double budgetLimit = data.budget.getBudgetLimit();
            double newBudgetLimit = budgetLimit * exchangeRate;
            data.budget.updateBudget(outputCurrency, newBudgetLimit);
        }
    }

    public ArrayList<Item> updateSpendingList() {
        return newSpendingList;
    }
}
