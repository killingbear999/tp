package seedu.duke;

import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.Test;
import seedu.duke.command.AddCommand;
import seedu.duke.command.ClearRepaymentListCommand;
import seedu.duke.command.Command;
import seedu.duke.command.ConvertCommand;
import seedu.duke.command.DrawCommand;
import seedu.duke.command.EditCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.ExportCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.MultipleCommand;
import seedu.duke.command.PurgeDataCommand;
import seedu.duke.command.RepayCommand;
import seedu.duke.command.RepaymentListCommand;
import seedu.duke.command.SetBudgetCommand;
import seedu.duke.command.SpendingListCommand;
import seedu.duke.command.SummaryCommand;
import seedu.duke.exceptions.InvalidCommandException;
import seedu.duke.exceptions.InvalidFormatException;
import seedu.duke.exceptions.InvalidNumberException;
import seedu.duke.parser.Parser;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {
    static class Rule<T extends Throwable> {
        final String commandString;
        final Class<T> subclass;

        Rule(String command, Class<T> subclass) {
            this.commandString = command;
            this.subclass = subclass;
        }
    }

    @Test
    void allParserReturnClass() throws Exception {
        Rule[] rules = new Rule[]{
            new Rule("add -c Food -d Item 0 -s SGD 114.514", AddCommand.class),
            new Rule("add -d Chicken Rice 0 -s SGD 114.514 ", AddCommand.class),
            new Rule("edit 100 -s SGD 1.23 --description Chicken Rice -c Food", EditCommand.class),
            new Rule("edit 100 -s SGD 1.23 -c Food Stuff", EditCommand.class),
            new Rule("clear --spending 234", MultipleCommand.class),
            new Rule("clear -r 1 -b --spending 234", MultipleCommand.class),
            new Rule("clear -r 420", MultipleCommand.class),
            new Rule("convert -s SGD --target CNY", ConvertCommand.class),
            new Rule("draw ", DrawCommand.class),
            new Rule("draw 2020", DrawCommand.class),
            new Rule("export ./data", ExportCommand.class),
            new Rule("logout", ExitCommand.class),
            new Rule("purge data", PurgeDataCommand.class),
            new Rule("repay -s CAD 3.14 -t 2020-12-02 --description John", RepayCommand.class),
            new Rule("repayment list", RepaymentListCommand.class),
            new Rule("summary 2020 Jul", SummaryCommand.class),
            new Rule("summary --all", SummaryCommand.class),
            new Rule("spending list", SpendingListCommand.class),
            new Rule("spending list 2020", SpendingListCommand.class),
            new Rule("set --spending SGD 123.45", SetBudgetCommand.class),
            new Rule("help", HelpCommand.class)
        };
        for (Rule r : rules) {
            Command c;
            try {
                c = Parser.parseCommand(r.commandString);
            } catch (Exception e) {
                throw new Exception("Command: " + r.commandString, e);
            }
            assertTrue(r.subclass.isInstance(c), String.format("Expected %s, got %s", r.subclass, c.getClass()));
        }
    }

    @Test
    void exactNumberOfArgs() {
        String[] extraCommands = {
            "add extra -c Food -d Item 0 -s SGD 114.514",
            "clear -b extra -s 1 -r 2",
            "clear -s 1 2 -r 2",
            "convert -s SGD extra -t USD",
            "edit 100 200 -s SGD 1.23 --description Chicken Rice -c Food",
            "draw 2020 Jun 21",
            "draw incorrectText",
            "draw --all extra",
            "logout extra",
            "purge data text",
            "purge",
            "purge incorrectText",
            "purge data extra",
            "repay -s CAD 3.14 -t 2020-12-02 0100 --description John",
            "repayment",
            "repayment incorrectText",
            "repayment list extra",
            "summary incorrectText",
            "summary 2020 Jul 21",
            "summary --all extra",
            "spending",
            "spending incorrectText",
            "spending list extra",
            "spending list 2020 Jun 21",
            "spending list --all extra",
            "set --spending SGD 123.45 0",
            "set 0 -s SGD 1.23",
            "help me",
        };
        for (String c : extraCommands) {
            assertThrows(InvalidCommandException.class, () -> {
                Parser.parseCommand(c);
            }, "Incorrect command: " + c);
        }
    }

    @Test
    void addWithoutCategory() throws ParseException, InvalidCommandException, NoSuchMethodException,
            InstantiationException, IllegalAccessException, InvocationTargetException,
            java.text.ParseException, InvalidFormatException, InvalidNumberException {
        AddCommand c = (AddCommand) Parser.parseCommand("add -d Item 0 -s SGD 114.514 ");
        assertEquals(c.amount, 114.514);
        assertEquals(c.description, "Item 0");
        assertNull(c.category);
    }

    @Test
    void addShuffledArg() throws ParseException, InvalidCommandException, NoSuchMethodException,
            InstantiationException, IllegalAccessException, InvocationTargetException,
            java.text.ParseException, InvalidFormatException, InvalidNumberException {
        AddCommand c = (AddCommand) Parser.parseCommand("add -d Item 0 -s SGD 114.514 -c Food ");
        assertEquals(c.amount, 114.514);
        assertEquals(c.description, "Item 0");
        assertEquals(c.category, "Food");
    }

    @Test
    void editOneParam() throws ParseException, InvalidCommandException, NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException, java.text.ParseException, InvalidFormatException,
            InvalidNumberException {
        EditCommand c = (EditCommand) Parser.parseCommand("edit 987 --description fried rice");
        assertEquals(c.index, 986);
        assertEquals(c.description, "fried rice");
        assertNull(c.amount);
        assertNull(c.currency);
        assertNull(c.category);
    }

    @Test
    void clearIndex() throws ParseException, InvalidCommandException, NoSuchMethodException,
            InstantiationException, IllegalAccessException, java.text.ParseException,
            InvocationTargetException, InvalidFormatException, InvalidNumberException {
        MultipleCommand c = (MultipleCommand) Parser.parseCommand("clear --repayment 23");
        ClearRepaymentListCommand cl = (ClearRepaymentListCommand) c.commands.get(0);
        assertFalse(cl.isClearAll);
        assertEquals(cl.clearIndex, 23);
    }

    @Test
    void clearMultipleLists() throws NoSuchMethodException, ParseException, InvalidCommandException,
            InstantiationException, java.text.ParseException, IllegalAccessException,
            InvocationTargetException, InvalidFormatException, InvalidNumberException {
        MultipleCommand c = (MultipleCommand) Parser.parseCommand("clear --spending 234");
        assertEquals(1, c.commands.size());
        c = (MultipleCommand) Parser.parseCommand("clear -r 1 --spending 234");
        assertEquals(2, c.commands.size());
        c = (MultipleCommand) Parser.parseCommand("clear -r 1 -b --spending 234");
        assertEquals(3, c.commands.size());
    }

    @Test
    void convertMissingSource() {
        assertThrows(MissingOptionException.class, () -> {
            ConvertCommand c = (ConvertCommand) Parser.parseCommand("convert --target USD");
        });
    }
}