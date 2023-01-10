package com.company;

public interface Attendance {
    String attendanceSQL = "SELECT a.student_id AS StudentID,\n" +
            "       m.module_name AS Module,\n" +
            "       a.date AS Date\n" +
            "FROM attendance a\n" +
            "LEFT JOIN module m ON a.module_id = m.module_id\n";
}
