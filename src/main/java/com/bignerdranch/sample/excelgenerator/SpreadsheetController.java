package com.bignerdranch.sample.excelgenerator;

import org.apache.poi.ss.usermodel.Workbook;
import spark.Route;

import java.util.Map;

public class SpreadsheetController {
    Cache cache = new Cache();
    DataSource dataSource = new HardCodedDataSource();
    ExcelRenderer excelRenderer = new JxlsRenderer();

    public Route show = (request, response) -> {
        String departmentId = request.queryParams("department_id");
        int year = Integer.parseInt(request.queryParams("year"));
        int month = Integer.parseInt(request.queryParams("month"));
        int day = Integer.parseInt(request.queryParams("day"));

        Workbook workbook = cache.getDepartmentSpreadsheet(departmentId, year, month, day);
        if (workbook == null) {
            Map departmentData = dataSource.getDepartmentData(departmentId, year, month, day);
            workbook = excelRenderer.render(departmentData);
            cache.putDepartmentSpreadsheet(departmentId, year, month, day, workbook);
        }

        response.type("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.header("CONTENT-DISPOSITION", "attachment; filename=department-report.xlsx");
        workbook.write(response.raw().getOutputStream());

        return response.raw();
    };
}
