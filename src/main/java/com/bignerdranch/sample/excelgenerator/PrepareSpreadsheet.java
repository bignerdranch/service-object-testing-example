package com.bignerdranch.sample.excelgenerator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.util.Map;

public class PrepareSpreadsheet {
    Cache cache = new Cache();
    DataSource dataSource = new HardCodedDataSource();
    ExcelRenderer excelRenderer = new JxlsRenderer();

    public Workbook call(String departmentId, int year, int month, int day) throws IOException, InvalidFormatException {
        Workbook workbook = cache.getDepartmentSpreadsheet(departmentId, year, month, day);

        if (workbook == null) {
            Map departmentData = dataSource.getDepartmentData(departmentId, year, month, day);
            workbook = excelRenderer.render(departmentData);
            cache.putDepartmentSpreadsheet(departmentId, year, month, day, workbook);
        }

        return workbook;
    }
}
