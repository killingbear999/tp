package seedu.duke;

public class ExitCommand {
    
    private String description;
    
    public ExitCommand(String description) {
        this.description = description;
    }
    
    public boolean isExit() {
        if (description.startsWith("logout")) {
            return true;
        }
        return false;
    }
}
