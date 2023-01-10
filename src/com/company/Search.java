/*
 * Created by JFormDesigner on Tue Jan 25 18:20:04 ICT 2022
 */

package com.company;

import com.connection.JDBCConnection;
import net.proteanit.sql.DbUtils;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author Xuan Minh Nguyen
 */
public class Search extends JFrame {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    String searchBy;
    String searchOrder;
    String searchOrderType;

    String keyword;

    public Search() {
        connection = JDBCConnection.testConnection();
        initComponents();
    }

    private void cancel(ActionEvent e) {
        // close the frame
        setVisible(false);
    }

    private void showResultTable(String sql) {
        try {
            ResultSet rs = statement.executeQuery(sql);
            resultTabel.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private String getStudentOrderName(String studentOrder) {
        switch (studentOrder){
            case "ID":
                return "student_id";
            case "FirstName":
                return "first_name";
            case "LastName":
                return "last_name";
            case "Major":
                return "major";
            default:
                return null;
        }
    }

    private String getAttendanceOrderName(String attendanceOrder) {
        switch (attendanceOrder){
            case "ID":
                return "a.student_id";
            case "Module":
                return "m.module_name";
            case "Date":
                return "a.date";
            default:
                return null;
        }
    }

    private String getExamOrderName(String order) {
        switch (order) {
            case "ID":
                return "er.student_id";
            case "Module":
                return "m.module_name";
            case "ExamDate":
                return "m.exam_date";
            case "Attempt":
                return "er.attempt";
            default:
                return null;
        }
    }

        private void searchStudent(ActionEvent e) {
        keyword = keywordTextField.getText();
        searchBy = (String) studentComboBox.getSelectedItem();
        searchOrder = (String) orderStuComboBox.getSelectedItem();
        searchOrderType = (String) orderTypeStuComboBox.getSelectedItem();

        try {
            statement = connection.createStatement();

            String searchStudentSQL = "SELECT student_id AS StudentID,\n" +
                                        "first_name AS FirstName,\n" +
                                        "last_name AS LastName,\n" +
                                        "major AS Major\n" +
                                        "FROM student\n" +
                                        "WHERE " + getStudentOrderName(searchBy) + " LIKE '%" + keyword + "%' " +
                                        "ORDER BY " + getStudentOrderName(searchOrder) + " " + searchOrderType;
            showResultTable(searchStudentSQL);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }

    private void searchAttendance(ActionEvent e) {
        keyword = keywordTextField.getText();
        searchBy = (String) attendanceComboBox.getSelectedItem();
        searchOrder = (String) orderAttComboBox.getSelectedItem();
        searchOrderType = (String) orderTypeAttComboBox.getSelectedItem();

        try {
            statement = connection.createStatement();

            String searchAttendanceSQL = "SELECT a.student_id AS StudentID,\n" +
                                            "m.module_name AS Module,\n" +
                                            "a.date AS Date\n" +
                                            "FROM attendance a\n" +
                                            "LEFT JOIN module m ON a.module_id = m.module_id\n" +
                                            "WHERE " + getAttendanceOrderName(searchBy) + " LIKE '%" + keyword + "%' " +
                                            "ORDER BY " + getAttendanceOrderName(searchOrder) + " " + searchOrderType;
            showResultTable(searchAttendanceSQL);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }

    private void searchExam(ActionEvent e) {
        keyword = keywordTextField.getText();
        searchBy = (String) examComboBox.getSelectedItem();
        searchOrder = (String) orderExComboBox.getSelectedItem();
        searchOrderType = (String) orderTypeExComboBox.getSelectedItem();

        try {
            statement = connection.createStatement();

            String searchExamSQL = "SELECT er.student_id AS StudentID,\n" +
                                    "m.module_name AS Module,\n" +
                                    "m.exam_date AS ExamDate,\n" +
                                    "er.attempt AS Attempt\n" +
                                    "FROM exam_regis er\n" +
                                    "LEFT JOIN module m on er.module_id = m.module_id\n" +
                                    "WHERE " + getExamOrderName(searchBy) + " LIKE '%" + keyword + "%' " +
                                    "AND er.attempt IS NOT NULL ORDER BY " + getExamOrderName(searchOrder) + " " + searchOrderType;
            showResultTable(searchExamSQL);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        dialogPanel = new JPanel();
        keywordLabel = new JLabel();
        keywordTextField = new JTextField();
        innerpanel = new JPanel();
        resultScrollPane = new JScrollPane();
        resultTabel = new JTable();
        cancelButton = new JButton();
        studentPanel = new JPanel();
        searchStudentLabel = new JLabel();
        studentComboBox = new JComboBox<>();
        searchStudentButton = new JButton();
        orderStudentLabel = new JLabel();
        orderStuComboBox = new JComboBox<>();
        orderTypeStuComboBox = new JComboBox<>();
        attendancePanel = new JPanel();
        searchAttendanceLabel = new JLabel();
        attendanceComboBox = new JComboBox<>();
        searchAttendanceButton = new JButton();
        orderAttendanceLabel = new JLabel();
        orderAttComboBox = new JComboBox<>();
        orderTypeAttComboBox = new JComboBox<>();
        examPanel = new JPanel();
        searchExamLabel = new JLabel();
        examComboBox = new JComboBox<>();
        searchExamButton = new JButton();
        orderExamLabel = new JLabel();
        orderExComboBox = new JComboBox<>();
        orderTypeExComboBox = new JComboBox<>();
        resultLabel = new JLabel();
        backgroundLabel = new JLabel();

        //======== this ========
        setTitle("Search");
        setIconImage(new ImageIcon(getClass().getResource("/com/images/cmsLogo.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== dialogPanel ========
        {
            dialogPanel.setBorder(new TitledBorder("Delete Student"));
            dialogPanel.setLayout(null);

            //---- keywordLabel ----
            keywordLabel.setText("Enter Keyword");
            keywordLabel.setForeground(Color.white);
            keywordLabel.setFont(keywordLabel.getFont().deriveFont(Font.BOLD));
            dialogPanel.add(keywordLabel);
            keywordLabel.setBounds(15, 10, 105, keywordLabel.getPreferredSize().height);
            dialogPanel.add(keywordTextField);
            keywordTextField.setBounds(110, 10, 215, 20);

            //======== innerpanel ========
            {
                innerpanel.setLayout(null);

                //======== resultScrollPane ========
                {
                    resultScrollPane.setBorder(new TitledBorder("Result"));
                    resultScrollPane.setViewportView(resultTabel);
                }
                innerpanel.add(resultScrollPane);
                resultScrollPane.setBounds(5, 0, 285, 210);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < innerpanel.getComponentCount(); i++) {
                        Rectangle bounds = innerpanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = innerpanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    innerpanel.setMinimumSize(preferredSize);
                    innerpanel.setPreferredSize(preferredSize);
                }
            }
            dialogPanel.add(innerpanel);
            innerpanel.setBounds(335, 45, 295, 215);

            //---- cancelButton ----
            cancelButton.setText("Cancel");
            cancelButton.setIcon(new ImageIcon(getClass().getResource("/com/images/cancel.png")));
            cancelButton.setFont(cancelButton.getFont().deriveFont(cancelButton.getFont().getStyle() | Font.BOLD));
            cancelButton.addActionListener(e -> cancel(e));
            dialogPanel.add(cancelButton);
            cancelButton.setBounds(530, 270, 100, cancelButton.getPreferredSize().height);

            //======== studentPanel ========
            {
                studentPanel.setOpaque(false);
                studentPanel.setBorder(new TitledBorder(null, "Student", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.white));
                studentPanel.setLayout(null);

                //---- searchStudentLabel ----
                searchStudentLabel.setText("Search by");
                searchStudentLabel.setForeground(Color.white);
                searchStudentLabel.setFont(searchStudentLabel.getFont().deriveFont(searchStudentLabel.getFont().getStyle() | Font.BOLD));
                studentPanel.add(searchStudentLabel);
                searchStudentLabel.setBounds(10, 20, 65, searchStudentLabel.getPreferredSize().height);

                //---- studentComboBox ----
                studentComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                    "ID",
                    "FirstName",
                    "LastName",
                    "Major"
                }));
                studentPanel.add(studentComboBox);
                studentComboBox.setBounds(75, 20, 195, 20);

                //---- searchStudentButton ----
                searchStudentButton.setFont(searchStudentButton.getFont().deriveFont(searchStudentButton.getFont().getStyle() | Font.BOLD));
                searchStudentButton.setIcon(new ImageIcon(getClass().getResource("/com/images/check.png")));
                searchStudentButton.setToolTipText("Search Student");
                searchStudentButton.addActionListener(e -> searchStudent(e));
                studentPanel.add(searchStudentButton);
                searchStudentButton.setBounds(280, 35, 21, 21);

                //---- orderStudentLabel ----
                orderStudentLabel.setText("Order by");
                orderStudentLabel.setForeground(Color.white);
                orderStudentLabel.setFont(orderStudentLabel.getFont().deriveFont(orderStudentLabel.getFont().getStyle() | Font.BOLD));
                studentPanel.add(orderStudentLabel);
                orderStudentLabel.setBounds(10, 50, 65, 16);

                //---- orderStuComboBox ----
                orderStuComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                    "ID",
                    "FirstName",
                    "LastName",
                    "Major"
                }));
                studentPanel.add(orderStuComboBox);
                orderStuComboBox.setBounds(75, 50, 115, 20);

                //---- orderTypeStuComboBox ----
                orderTypeStuComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                    "ASC",
                    "DESC"
                }));
                studentPanel.add(orderTypeStuComboBox);
                orderTypeStuComboBox.setBounds(200, 50, 70, 20);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < studentPanel.getComponentCount(); i++) {
                        Rectangle bounds = studentPanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = studentPanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    studentPanel.setMinimumSize(preferredSize);
                    studentPanel.setPreferredSize(preferredSize);
                }
            }
            dialogPanel.add(studentPanel);
            studentPanel.setBounds(15, 35, 310, 85);

            //======== attendancePanel ========
            {
                attendancePanel.setOpaque(false);
                attendancePanel.setBorder(new TitledBorder(null, "Attendance", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.white));
                attendancePanel.setLayout(null);

                //---- searchAttendanceLabel ----
                searchAttendanceLabel.setText("Search by");
                searchAttendanceLabel.setForeground(Color.white);
                searchAttendanceLabel.setFont(searchAttendanceLabel.getFont().deriveFont(searchAttendanceLabel.getFont().getStyle() | Font.BOLD));
                attendancePanel.add(searchAttendanceLabel);
                searchAttendanceLabel.setBounds(10, 20, 70, 16);

                //---- attendanceComboBox ----
                attendanceComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                    "ID",
                    "Module",
                    "Date"
                }));
                attendancePanel.add(attendanceComboBox);
                attendanceComboBox.setBounds(75, 20, 195, 20);

                //---- searchAttendanceButton ----
                searchAttendanceButton.setFont(searchAttendanceButton.getFont().deriveFont(searchAttendanceButton.getFont().getStyle() | Font.BOLD));
                searchAttendanceButton.setIcon(new ImageIcon(getClass().getResource("/com/images/check.png")));
                searchAttendanceButton.setToolTipText("Search Attendance");
                searchAttendanceButton.addActionListener(e -> searchAttendance(e));
                attendancePanel.add(searchAttendanceButton);
                searchAttendanceButton.setBounds(280, 35, 21, 21);

                //---- orderAttendanceLabel ----
                orderAttendanceLabel.setText("Order by");
                orderAttendanceLabel.setForeground(Color.white);
                orderAttendanceLabel.setFont(orderAttendanceLabel.getFont().deriveFont(orderAttendanceLabel.getFont().getStyle() | Font.BOLD));
                attendancePanel.add(orderAttendanceLabel);
                orderAttendanceLabel.setBounds(10, 50, 70, 16);

                //---- orderAttComboBox ----
                orderAttComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                    "ID",
                    "Module",
                    "Date"
                }));
                attendancePanel.add(orderAttComboBox);
                orderAttComboBox.setBounds(75, 50, 115, 20);

                //---- orderTypeAttComboBox ----
                orderTypeAttComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                    "ASC",
                    "DESC"
                }));
                attendancePanel.add(orderTypeAttComboBox);
                orderTypeAttComboBox.setBounds(200, 50, 70, 20);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < attendancePanel.getComponentCount(); i++) {
                        Rectangle bounds = attendancePanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = attendancePanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    attendancePanel.setMinimumSize(preferredSize);
                    attendancePanel.setPreferredSize(preferredSize);
                }
            }
            dialogPanel.add(attendancePanel);
            attendancePanel.setBounds(15, 125, 310, 85);

            //======== examPanel ========
            {
                examPanel.setOpaque(false);
                examPanel.setBorder(new TitledBorder(null, "Exam", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.white));
                examPanel.setLayout(null);

                //---- searchExamLabel ----
                searchExamLabel.setText("Search by");
                searchExamLabel.setForeground(Color.white);
                searchExamLabel.setFont(searchExamLabel.getFont().deriveFont(searchExamLabel.getFont().getStyle() | Font.BOLD));
                examPanel.add(searchExamLabel);
                searchExamLabel.setBounds(10, 25, 65, 16);

                //---- examComboBox ----
                examComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                    "ID",
                    "Module",
                    "ExamDate",
                    "Attempt"
                }));
                examPanel.add(examComboBox);
                examComboBox.setBounds(75, 25, 195, 20);

                //---- searchExamButton ----
                searchExamButton.setFont(searchExamButton.getFont().deriveFont(searchExamButton.getFont().getStyle() | Font.BOLD));
                searchExamButton.setIcon(new ImageIcon(getClass().getResource("/com/images/check.png")));
                searchExamButton.setToolTipText("Search Exam");
                searchExamButton.addActionListener(e -> searchExam(e));
                examPanel.add(searchExamButton);
                searchExamButton.setBounds(280, 40, 21, 21);

                //---- orderExamLabel ----
                orderExamLabel.setText("Order by");
                orderExamLabel.setForeground(Color.white);
                orderExamLabel.setFont(orderExamLabel.getFont().deriveFont(orderExamLabel.getFont().getStyle() | Font.BOLD));
                examPanel.add(orderExamLabel);
                orderExamLabel.setBounds(10, 55, 65, 16);

                //---- orderExComboBox ----
                orderExComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                    "ID",
                    "Module",
                    "ExamDate",
                    "Attempt"
                }));
                examPanel.add(orderExComboBox);
                orderExComboBox.setBounds(75, 55, 115, 20);

                //---- orderTypeExComboBox ----
                orderTypeExComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                    "ASC",
                    "DESC"
                }));
                examPanel.add(orderTypeExComboBox);
                orderTypeExComboBox.setBounds(200, 55, 70, 20);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < examPanel.getComponentCount(); i++) {
                        Rectangle bounds = examPanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = examPanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    examPanel.setMinimumSize(preferredSize);
                    examPanel.setPreferredSize(preferredSize);
                }
            }
            dialogPanel.add(examPanel);
            examPanel.setBounds(15, 215, 310, 85);

            //---- resultLabel ----
            resultLabel.setText("Result");
            resultLabel.setForeground(Color.white);
            resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
            resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
            dialogPanel.add(resultLabel);
            resultLabel.setBounds(435, 20, 105, 16);

            //---- backgroundLabel ----
            backgroundLabel.setText("text");
            backgroundLabel.setIcon(new ImageIcon(getClass().getResource("/com/images/backGround4.png")));
            backgroundLabel.setVerticalAlignment(SwingConstants.TOP);
            dialogPanel.add(backgroundLabel);
            backgroundLabel.setBounds(0, 0, 645, 315);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < dialogPanel.getComponentCount(); i++) {
                    Rectangle bounds = dialogPanel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = dialogPanel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                dialogPanel.setMinimumSize(preferredSize);
                dialogPanel.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(dialogPanel);
        dialogPanel.setBounds(0, 0, 645, 310);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel dialogPanel;
    private JLabel keywordLabel;
    private JTextField keywordTextField;
    private JPanel innerpanel;
    private JScrollPane resultScrollPane;
    private JTable resultTabel;
    private JButton cancelButton;
    private JPanel studentPanel;
    private JLabel searchStudentLabel;
    private JComboBox<String> studentComboBox;
    private JButton searchStudentButton;
    private JLabel orderStudentLabel;
    private JComboBox<String> orderStuComboBox;
    private JComboBox<String> orderTypeStuComboBox;
    private JPanel attendancePanel;
    private JLabel searchAttendanceLabel;
    private JComboBox<String> attendanceComboBox;
    private JButton searchAttendanceButton;
    private JLabel orderAttendanceLabel;
    private JComboBox<String> orderAttComboBox;
    private JComboBox<String> orderTypeAttComboBox;
    private JPanel examPanel;
    private JLabel searchExamLabel;
    private JComboBox<String> examComboBox;
    private JButton searchExamButton;
    private JLabel orderExamLabel;
    private JComboBox<String> orderExComboBox;
    private JComboBox<String> orderTypeExComboBox;
    private JLabel resultLabel;
    private JLabel backgroundLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
