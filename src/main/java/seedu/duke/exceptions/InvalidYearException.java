package seedu.duke.exceptions;

public class InvalidYearException extends DukeException {
    @Override
    public String toString() {
        return "Sorry, your year input is invalid. Please re-enter the year.";
    }
}
