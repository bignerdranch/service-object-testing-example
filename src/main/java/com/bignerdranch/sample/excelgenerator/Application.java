package com.bignerdranch.sample.excelgenerator;

import static spark.Spark.get;
import static spark.Spark.port;

public class Application {
    public static void main(String[] arg) {
        port(8080);
        SpreadsheetController controller = new SpreadsheetController();
        get("spreadsheet", controller.show);
    }
}
