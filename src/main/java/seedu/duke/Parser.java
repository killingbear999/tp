package seedu.duke;

import seedu.duke.command.SpendingListCommand;
import seedu.duke.command.SetBudgetCommand;
import seedu.duke.command.ConvertCommand;
import seedu.duke.command.EditCommand;
import seedu.duke.command.AddCommand;
import seedu.duke.command.ClearListCommand;
import seedu.duke.command.Command;
import seedu.duke.command.ConvertCommand;
import seedu.duke.command.DrawCommand;
import seedu.duke.command.EditCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.ExportCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.RepayCommand;
import seedu.duke.command.SetBudgetCommand;
import seedu.duke.command.SummaryCommand;
import seedu.duke.command.ViewCommand;
import seedu.duke.command.RepaymentListCommand;
import seedu.duke.exceptions.InvalidCommandException;

public class Parser {
    private enum CommandPattern {
        HELP("^help$", "help"),
        CLEAR_ALL("^clear\\s*-all$", "clearAll"),
        CLEAR_INDEX("^clear\\s*\\d+$", "clear"),
        ADD("^add\\s*-d.+-s\\s*.\\d+([.]\\d*)?$", "add"),
        REPAY("^repay\\s*-d.+-s.+-t\\s*.\\d+([.]\\d*)?\\s*.$", "repay"),
        EDIT("^edit\\s*\\d+\\s*-d.+\\s*-s\\s*.\\d+([.]\\d*)?$", "edit"),
        SPENDINGLIST("^spending list$","spending list"),
        REPAYMENTLIST("^repayment list$","repayment list"),
        SET("^set\\s*-s.+\\d+([.]\\d*)?$", "set"),
        VIEW("^view$", "view"),
        LOGOUT("^logout$", "logout"),
        CONVERT("^convert\\s*-d.+\\s*-d.+$", "convert"),
        SUMMARY("^summary$", "summary"),
        SUMMARY_YEAR("^summary\\s*\\d{4}$", "summaryYear"),
        SUMMARY_YEAR_MONTH("^summary\\s*\\d{4}\\s*[a-zA-Z]{3}$", "summaryYearMonth"),
        EXPORT("^export.*$", "export"),
        DRAW("^draw$", "draw"),
        DRAW_YEAR("^draw\\s*\\d{4}$", "drawYear"),
        DRAW_YEAR_MONTH("^draw\\s*\\d{4}\\s*[a-zA-Z]{3}$", "drawYearMonth");

        private final String pattern;
        private final String action;
        CommandPattern(String pattern, String action) {
            this.pattern = pattern;
            this.action = action;
        }
    }

    private static String getAction(String userInput) throws InvalidCommandException {
        for (CommandPattern command: CommandPattern.values()) {
            if (userInput.matches(command.pattern)) {
                return command.action;
            }
        }
        throw new InvalidCommandException();
    }

    private static Command getAddCommand(String commandParameters) {
        int descriptionBeginIndex = commandParameters.indexOf("-d");
        int spendingBeginIndex = commandParameters.indexOf("-s");
        String description = commandParameters.substring(descriptionBeginIndex + "-d".length(),
                spendingBeginIndex).strip();
        String spending = commandParameters.substring(spendingBeginIndex + "-s".length()).strip();
        String symbol = spending.substring(0, 1);
        double amount = Double.parseDouble(spending.substring(1));
        return new AddCommand(description, symbol, amount, ""); // need to modify
    }
    
    private static Command getEditCommand(String commandParameters) {
        int categoryBeginIndex = commandParameters.indexOf("-c");
        int descriptionBeginIndex = commandParameters.indexOf("-d");
        int spendingBeginIndex = commandParameters.indexOf("-s");
        int number = Integer.parseInt(commandParameters.substring(0, categoryBeginIndex).strip()) - 1;
        String category = commandParameters.substring(categoryBeginIndex + "-c".length(),
                descriptionBeginIndex).strip();
        String description = commandParameters.substring(descriptionBeginIndex + "-d".length(),
                spendingBeginIndex).strip();
        String spending = commandParameters.substring(spendingBeginIndex + "-s".length()).strip();
        String symbol = spending.substring(0, 1);
        double amount = Double.parseDouble(spending.substring(1));
        return new EditCommand(number, description, symbol, amount, category);
    }
    
    public static Command parseCommand(String userInput) throws InvalidCommandException {
        userInput = userInput.strip();
        String action = getAction(userInput);
        int parameterStartIndex;
        for (parameterStartIndex = 0; parameterStartIndex < action.length(); parameterStartIndex++) {
            if (Character.isUpperCase(action.charAt(parameterStartIndex))) {
                break;
            }
        }
        String commandParameters = userInput.substring(parameterStartIndex).strip();
        switch (action) {
        case "add":
            Command newAddCommand = getAddCommand(commandParameters);
            assert newAddCommand instanceof AddCommand : "Getting new add command failed.";
            return newAddCommand;
        case "help": return new HelpCommand();
        case "clear": return new ClearListCommand(false, Integer.parseInt(commandParameters));
        case "clearAll": return new ClearListCommand(true, 0);
        case "convert": return new ConvertCommand(commandParameters);
        case "summary": return new SummaryCommand();
        case "summaryYear": return new SummaryCommand(commandParameters);
        case "summaryYearMonth": return new SummaryCommand(commandParameters.substring(0, 4),
                commandParameters.substring(4).strip());
        case "logout": return new ExitCommand();
        case "edit":
            Command newEditCommand = getEditCommand(commandParameters);
            assert newEditCommand instanceof EditCommand : "Getting new edit command failed.";
            return newEditCommand;
        case "spending list": return new SpendingListCommand();
        case "repayment list": return new RepaymentListCommand();
        case "set": return new SetBudgetCommand(commandParameters);
        case "repay": return new RepayCommand(commandParameters);
        case "export": return new ExportCommand(commandParameters);
        case "draw": return new DrawCommand();
        case "drawYear": return new DrawCommand(commandParameters);
        case "drawYearMonth": return new DrawCommand(commandParameters.substring(0, 4),
                commandParameters.substring(4).strip());
        case "view": return new ViewCommand();
        default: throw new InvalidCommandException();
        }
    }
}
