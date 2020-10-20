package seedu.duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConvertCommandTest {
    @Test
    public void identifyCurrencySgdUsd() {
        String expectedCurrency = "SGD USD";
        String description = "-d SGD -d USD";
        ConvertCommand convertCommand = new ConvertCommand(description);
        assertEquals(expectedCurrency, convertCommand.identifyCurrency(description));
    }
}