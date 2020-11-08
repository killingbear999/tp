package seedu.duke.exceptions;

//@@author xieyaoyue
public class InvalidFormatException extends DukeException {
    @Override
    public String toString() {
        return "Sorry, your command is in the wrong format.";
    }
}
