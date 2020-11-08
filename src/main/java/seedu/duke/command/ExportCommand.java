package seedu.duke.command;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import seedu.duke.data.Data;
import seedu.duke.data.Item;
import seedu.duke.data.SpendingList;
import seedu.duke.ui.Ui;
import seedu.duke.utilities.FileExplorer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

//@@author Wu-Haitao
public class ExportCommand extends Command {
    private final String filePath;
    private final FileExplorer fileExplorer;
    private final boolean isOpening;
    private boolean isValidPath = true;

    public ExportCommand(String filePath) {
        File folder = new File(filePath);
        if (!folder.isDirectory()) {
            isValidPath = false;
        }
        this.filePath = filePath + "Records.xlsx";
        fileExplorer = new FileExplorer(this.filePath);
        this.isOpening = true;
    }

    public ExportCommand(String filePath, boolean isOpening) {
        File folder = new File(filePath);
        if (!folder.isDirectory()) {
            isValidPath = false;
        }
        this.filePath = filePath + "Records.xlsx";
        fileExplorer = new FileExplorer(this.filePath);
        this.isOpening = isOpening;
    }

    @Override
    public void execute(Data data, Ui ui) {
        if (!isValidPath) {
            ui.printExportMessage(false);
            return;
        }
        exportToExcel(data.spendingList);
        if (isOpening) {
            try {
                fileExplorer.openFile();
            } catch (IOException e) {
                ui.printOpenFileFailedMessage();
            }
        }
        ui.printExportMessage(true);
    }

    private void exportToExcel(SpendingList list) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("sheet0");
        sheet.setDefaultColumnWidth(15);
        Row row = sheet.createRow(0);
        printHeaders(workbook, row);
        for (int i = 0; i < list.getListSize(); i++) {
            row = sheet.createRow(i + 1);
            Item item = list.getItem(i);
            row.createCell(0).setCellValue(item.getDescription());
            row.createCell(1).setCellValue(item.getSymbol());
            row.createCell(2).setCellValue(item.getAmount());
            row.createCell(3).setCellValue(item.getDate());
            row.createCell(4).setCellValue(item.getCategory());
        }
        try {
            FileOutputStream output = new FileOutputStream(filePath);
            workbook.write(output);
            output.flush();
            output.close();
        } catch (Exception e) {
            assert false : "Failed to create Excel file";
        }
    }

    private void printHeaders(Workbook workbook, Row row) {
        Cell[] cells = new Cell[5];
        CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        cellStyle.setFont(font);
        for (int i = 0; i < 5; i++) {
            cells[i] = row.createCell(i);
            cells[i].setCellStyle(cellStyle);
        }
        cells[0].setCellValue("Description");
        cells[1].setCellValue("Currency");
        cells[2].setCellValue("Amount");
        cells[3].setCellValue("Date");
        cells[4].setCellValue("Category");
    }
}
