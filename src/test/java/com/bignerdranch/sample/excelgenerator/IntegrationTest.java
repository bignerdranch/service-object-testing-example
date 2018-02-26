package com.bignerdranch.sample.excelgenerator;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {
    @Test
    public void doSpreadsheetRequest() throws Exception {
        PrepareSpreadsheet prepareSpreadsheet = new PrepareSpreadsheet();

        String departmentId = "ABC123";
        int year = 2017;
        int month = 1;
        int day = 1;

        Workbook workbook = prepareSpreadsheet.call(departmentId, year, month, day);

        assertEquals("Procurement", getStringValueAtCell(workbook, 0, 0, 1));
        assertEquals("Simone", getStringValueAtCell(workbook, 0, 3, 0));
        assertEquals("Robertson", getStringValueAtCell(workbook, 0, 3, 1));
    }

    private String getStringValueAtCell(Workbook workbook, int sheet, int row, int col) {
        return workbook.getSheetAt(sheet).getRow(row).getCell(col).getStringCellValue();
    }
}
