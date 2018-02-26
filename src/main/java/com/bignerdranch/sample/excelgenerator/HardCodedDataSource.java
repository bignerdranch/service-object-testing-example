package com.bignerdranch.sample.excelgenerator;

import java.util.*;

public class HardCodedDataSource implements DataSource {
    @Override
    public Map getDepartmentData(String departmentId, int year, int month, int day) {
        List<Map<String, Object>> employees = new ArrayList<>();
        Map<String, Object> employee;

        employee = new HashMap<>();
        employee.put("firstName", "Simone");
        employee.put("lastName", "Robertson");
        employees.add(employee);

        employee = new HashMap<>();
        employee.put("firstName", "Vincent");
        employee.put("lastName", "la Grande");
        employees.add(employee);

        Map<String, Object> data = new HashMap<>();
        data.put("departmentName", "Procurement");
        data.put("employees", employees);
        return data;
    }
}
