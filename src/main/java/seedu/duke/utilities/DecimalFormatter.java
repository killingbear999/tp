package seedu.duke.utilities;

import java.text.DecimalFormat;

//@@author killingbear999
public class DecimalFormatter {
    
    public DecimalFormatter() {
    }
    
    public double convert(double amount) {
        DecimalFormat df = new DecimalFormat("0.0000");
        amount = Double.parseDouble(df.format(amount));
        return amount;
    }
}
