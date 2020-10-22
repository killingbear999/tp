package seedu.duke.command;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import seedu.duke.SpendingList;
import seedu.duke.Ui;
import seedu.duke.category.Item;
import seedu.duke.exceptions.InvalidCommandException;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExportCommand extends Command {
    private String filePath;

    public ExportCommand(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void execute(SpendingList spendingList, Ui ui) throws IOException {
        exportToExcel(spendingList);
        ui.printExportMessage();
    }

    private void exportToExcel(SpendingList list) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("sheet0");
        sheet.setDefaultColumnWidth(15);
        Row row = sheet.createRow(0);
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
            FileOutputStream output = new FileOutputStream(filePath + "Records.xlsx");
            workbook.write(output);
            output.flush();
            output.close();
        } catch (Exception e) {
            throw new IOException();
        }
    }
}
