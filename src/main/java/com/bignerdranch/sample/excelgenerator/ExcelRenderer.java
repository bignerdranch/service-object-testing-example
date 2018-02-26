package com.bignerdranch.sample.excelgenerator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.util.Map;

public interface ExcelRenderer {
    Workbook render(Map departmentData) throws IOException, InvalidFormatException;
}
