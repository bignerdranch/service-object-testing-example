package com.bignerdranch.sample.excelgenerator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import java.io.*;
import java.util.Map;

public class JxlsRenderer implements ExcelRenderer {
    private static final String TEMPLATE_FILE_PATH = "src/main/resources/template.xlsx";

    @Override
    public Workbook render(Map departmentData) throws IOException, InvalidFormatException {
        try (InputStream inputStream = templateStream()) {
            try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
                transform(inputStream, departmentData, os);
                try (ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray())) {
                    ZipSecureFile.setMinInflateRatio(0.0d);
                    return WorkbookFactory.create(is);
                }
            }
        }
    }

    private void transform(InputStream inputStream, Map departmentData, OutputStream
            outputStream) throws IOException {
        Context context = new Context();
        context.putVar("departmentName", departmentData.get("departmentName"));
        context.putVar("employees", departmentData.get("employees"));
        JxlsHelper.getInstance().processTemplate(inputStream, outputStream, context);
    }

    private InputStream templateStream() throws IOException {
        return new FileInputStream(TEMPLATE_FILE_PATH);
    }
}
