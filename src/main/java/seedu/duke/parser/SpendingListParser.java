package seedu.duke.parser;

import org.apache.commons.cli.ParseException;
import seedu.duke.command.Command;
import seedu.duke.command.SpendingListCommand;
import seedu.duke.exceptions.InvalidCommandException;
import seedu.duke.exceptions.InvalidFormatException;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class SpendingListParser extends DateParser<SpendingListCommand> {
    public SpendingListParser() {
        super(SpendingListCommand.class);
        addCategoryOption(false);
    }

    @Override
    public Command parse(String[] args) throws ParseException, InvalidCommandException, InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException, InvalidFormatException {
        if (args.length < 1 || !args[0].equals("list")) {
            throw new InvalidCommandException();
        }

        String[] opts = Arrays.copyOfRange(args, 1, args.length);
        return super.parse(opts);
        // TODO : list by category
    }
}
