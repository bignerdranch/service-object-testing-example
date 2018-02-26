package com.bignerdranch.sample.excelgenerator;

import org.apache.poi.ss.usermodel.Workbook;
import spark.Route;

public class SpreadsheetController {
    PrepareSpreadsheet prepareSpreadsheet = new PrepareSpreadsheet();

    public Route show = (request, response) -> {
        String departmentId = request.queryParams("department_id");
        int year = Integer.parseInt(request.queryParams("year"));
        int month = Integer.parseInt(request.queryParams("month"));
        int day = Integer.parseInt(request.queryParams("day"));

        Workbook workbook = prepareSpreadsheet.call(departmentId, year, month, day);

        response.type("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.header("CONTENT-DISPOSITION", "attachment; filename=department-report.xlsx");
        workbook.write(response.raw().getOutputStream());

        return response.raw();
    };
}
