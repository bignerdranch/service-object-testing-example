package com.bignerdranch.sample.excelgenerator;

import java.util.Map;

public interface DataSource {
    Map getDepartmentData(String departmentId, int year, int month, int day);
}
