package seedu.duke.parser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import seedu.duke.command.Command;
import seedu.duke.command.DateCommand;
import seedu.duke.exceptions.InvalidCommandException;
import seedu.duke.exceptions.InvalidFormatException;

import java.lang.reflect.InvocationTargetException;

public class DateParser<T extends DateCommand> extends Parser {
    private final Class<T> command;

    public DateParser(Class<T> command) {
        super();
        addAllOption();
        this.command = command;
    }

    @Override
    public Command parse(String[] args) throws ParseException, InvalidCommandException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException, InvalidFormatException {
        CommandLine line = getCommandLine(args, true);

        if (parseAllOption(line)) {
            parseNoArgs(line);
            return command.getDeclaredConstructor().newInstance();
        }

        YearMonth ym = parseDateArgs(line);
        return command.getDeclaredConstructor(String.class, String.class).newInstance(ym.year, ym.month);
    }
}
