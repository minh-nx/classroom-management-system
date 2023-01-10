/*
 * Created by JFormDesigner on Sat Jan 01 15:31:40 ICT 2022
 */

package com.company.add;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;

import com.company.edit.EditStudent;
import com.connection.JDBCConnection;
import net.miginfocom.swing.*;
import net.proteanit.sql.DbUtils;

/**
 * @author Xuan Minh Nguyen
 */
public class AddAttendance extends JFrame {
    public int getStudentId() {
        int studentId = 0;
        try {
            studentId = Integer.parseInt(idTextField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Missing information or invalid format for Student ID!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        return studentId;
    }
    // declare new instance of JTextField string with 3 elements,
    // equals to 3 JTextFields of the addAttendance Frame
    private JTextField[] attendanceTextField = new JTextField[3];
    private String[] attendanceLabel = {"Student ID", "Module", "Date"};

    public String getMissingTextField() {
        return missingTextField;
    }

    private String missingTextField;
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public AddAttendance() {
        initComponents();
        setPreferredSize(getMinimumSize());
        pack();
        connection = JDBCConnection.testConnection();
    }

    // return true if there is an unfilled textField, return false if all textFields is filled
    public boolean isMissing(JTextField[] infoTxtFld, String[] infoLabel) {
        for (int i = 0; i < infoTxtFld.length; i++)
            // check whether a textField is blank or not
            if (infoTxtFld[i].getText().equals("")) {
                missingTextField = infoLabel[i];
                return true;
            }
        return false;
    }

    private void addAttendance(ActionEvent e) {
        // opt out majorComboBox as its always has a value,
        // and it's not a JTextField
        attendanceTextField[0] = idTextField;
        attendanceTextField[1] = moduleTextField;
        attendanceTextField[2] = dateTextField;

        int studentId = 0;
        try {
            studentId = Integer.parseInt(idTextField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Missing information or invalid format for Student ID!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        String module = moduleTextField.getText();
        String date = dateTextField.getText();

        /**
         * insert a new attendance if there is no unfilled textField, and popup a message;
         * display a warning message if there is an unfilled textField; popup a custom text button to offer
         * adding a new student if the program encounters an integrity error, related to student_id_pk;
         */
        try {
            if (!isMissing(attendanceTextField, attendanceLabel)) {
                statement = connection.createStatement();
                String addAttendanceSQL = "INSERT INTO attendance\n" +
                                            "SELECT s.student_id, m.module_id, '" + date + "'\n" +
                                            "FROM student s\n" +
                                            "JOIN module m\n" +
                                            "WHERE  s.student_id=" + getStudentId() + " AND m.module_name='" + module + "'";
                statement.executeUpdate(addAttendanceSQL);
                JOptionPane.showMessageDialog(this, "Attendance added!");
                checkStudentAction();
            } else {
                JOptionPane.showMessageDialog(this, missingTextField + " is not filled\nPlease complete all information!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            // create a custom button text
            Object[] options = {"Yes, of course",
                                "No, abort"};
            int n = JOptionPane.showOptionDialog(this,
                    "Invalid information!\nStudent's already attended " + module +
                            " on this date or Module not found!\n" +
                            "Do you want to add " + module + " module to Student with ID " + getStudentId() + "?",
                    "Warning",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,     //do not use a custom Icon
                    options,  //the titles of buttons
                    options[0]); //default button title
            // register a new student if user accepts the option, abort if user refuses
            if (!(n == JOptionPane.CLOSED_OPTION || n == JOptionPane.NO_OPTION)){
                setVisible(false);
                EditStudent editStudent = new EditStudent();
                // show registerStudent frame
                editStudent.setVisible(true);
            }
        } catch (SQLException exp) {
            JOptionPane.showMessageDialog(this, "Invalid Information Format!", "Warning", JOptionPane.WARNING_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Student ID!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void cancel(ActionEvent e) {
        // close frame
        setVisible(false);
    }

    private void checkStudentAction() {
        String studentSQL = "SELECT student_id AS StudentID,\n" +
                            "       first_name AS FirstName,\n" +
                            "       last_name AS LastName,\n" +
                            "       major AS Major\n" +
                            "FROM student\n" +
                            "WHERE student_id = " + Integer.parseInt(idTextField.getText());

        String attendanceSQL =  "SELECT a.student_id AS StudentID,\n" +
                                "       m.module_name AS Module,\n" +
                                "       a.date AS Date\n" +
                                "FROM attendance a\n" +
                                "LEFT JOIN module m ON a.module_id = m.module_id\n" +
                                "WHERE a.student_id =" + getStudentId();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(studentSQL);

            /**
             *  display Student records on the studentTable if his/her corresponding
             *  student_id is found, popup a warning message if student_id not found
             */
            if (resultSet.next()) {
                ResultSet rss = statement.executeQuery(studentSQL);
                // show student records in studentTabel
                studentTabel.setModel(DbUtils.resultSetToTableModel(rss));
                ResultSet rsa = statement.executeQuery(attendanceSQL);
                // show attendance records in attendanceTabel
                attendanceTabel.setModel(DbUtils.resultSetToTableModel(rsa));
            } else {
                JOptionPane.showMessageDialog(this, "Student not found!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }

    private void checkStudent(ActionEvent e) {
        checkStudentAction();
    };

    public JTextField getIdTextField() {
        return idTextField;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        dialogPanel = new JPanel();
        idLabel = new JLabel();
        idTextField = new JTextField();
        checkButton = new JButton();
        moduleLabel = new JLabel();
        moduleTextField = new JTextField();
        dateLabel = new JLabel();
        dateTextField = new JTextField();
        innerpanel = new JPanel();
        studentScrollPane = new JScrollPane();
        studentTabel = new JTable();
        attendanceScrollPane = new JScrollPane();
        attendanceTabel = new JTable();
        addButton = new JButton();
        cancelButton = new JButton();
        backgroundImageLabel = new JLabel();

        //======== this ========
        setTitle("Add Attendance");
        setIconImage(new ImageIcon(getClass().getResource("/com/images/cmsLogo.png")).getImage());
        setAlwaysOnTop(true);
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "insets 0,hidemode 3",
            // columns
            "[grow,fill]",
            // rows
            "[109,grow,fill]" +
            "[]"));

        //======== dialogPanel ========
        {
            dialogPanel.setLayout(null);

            //---- idLabel ----
            idLabel.setText("Student ID");
            idLabel.setForeground(Color.white);
            idLabel.setFont(idLabel.getFont().deriveFont(Font.BOLD));
            dialogPanel.add(idLabel);
            idLabel.setBounds(10, 18, 78, idLabel.getPreferredSize().height);
            dialogPanel.add(idTextField);
            idTextField.setBounds(96, 11, 264, idTextField.getPreferredSize().height);

            //---- checkButton ----
            checkButton.setFont(checkButton.getFont().deriveFont(checkButton.getFont().getStyle() | Font.BOLD));
            checkButton.setIcon(new ImageIcon(getClass().getResource("/com/images/check.png")));
            checkButton.setToolTipText("Check Student");
            checkButton.addActionListener(e -> checkStudent(e));
            dialogPanel.add(checkButton);
            checkButton.setBounds(365, 10, 21, 21);

            //---- moduleLabel ----
            moduleLabel.setText("Module");
            moduleLabel.setFont(moduleLabel.getFont().deriveFont(moduleLabel.getFont().getStyle() | Font.BOLD));
            moduleLabel.setForeground(Color.white);
            dialogPanel.add(moduleLabel);
            moduleLabel.setBounds(10, 50, 78, moduleLabel.getPreferredSize().height);
            dialogPanel.add(moduleTextField);
            moduleTextField.setBounds(96, 45, 291, moduleTextField.getPreferredSize().height);

            //---- dateLabel ----
            dateLabel.setText("Date");
            dateLabel.setForeground(Color.white);
            dateLabel.setFont(dateLabel.getFont().deriveFont(dateLabel.getFont().getStyle() | Font.BOLD));
            dialogPanel.add(dateLabel);
            dateLabel.setBounds(10, 85, 78, dateLabel.getPreferredSize().height);
            dialogPanel.add(dateTextField);
            dateTextField.setBounds(96, 79, 291, dateTextField.getPreferredSize().height);

            //======== innerpanel ========
            {
                innerpanel.setLayout(null);

                //======== studentScrollPane ========
                {
                    studentScrollPane.setBorder(new TitledBorder("Student"));
                    studentScrollPane.setViewportView(studentTabel);
                }
                innerpanel.add(studentScrollPane);
                studentScrollPane.setBounds(10, 5, 355, 65);

                //======== attendanceScrollPane ========
                {
                    attendanceScrollPane.setBorder(new TitledBorder("Attendance"));
                    attendanceScrollPane.setViewportView(attendanceTabel);
                }
                innerpanel.add(attendanceScrollPane);
                attendanceScrollPane.setBounds(10, 75, 355, 95);

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
            innerpanel.setBounds(10, 120, 375, 180);

            //---- addButton ----
            addButton.setText("Add");
            addButton.setFont(addButton.getFont().deriveFont(addButton.getFont().getStyle() | Font.BOLD));
            addButton.setIcon(new ImageIcon(getClass().getResource("/com/images/add.png")));
            addButton.addActionListener(e -> addAttendance(e));
            dialogPanel.add(addButton);
            addButton.setBounds(155, 310, 100, addButton.getPreferredSize().height);

            //---- cancelButton ----
            cancelButton.setText("Cancel");
            cancelButton.setFont(cancelButton.getFont().deriveFont(cancelButton.getFont().getStyle() | Font.BOLD));
            cancelButton.setIcon(new ImageIcon(getClass().getResource("/com/images/cancel.png")));
            cancelButton.addActionListener(e -> cancel(e));
            dialogPanel.add(cancelButton);
            cancelButton.setBounds(285, 310, 100, cancelButton.getPreferredSize().height);

            //---- backgroundImageLabel ----
            backgroundImageLabel.setText("text");
            backgroundImageLabel.setIcon(new ImageIcon(getClass().getResource("/com/images/background.png")));
            backgroundImageLabel.setVerticalAlignment(SwingConstants.BOTTOM);
            dialogPanel.add(backgroundImageLabel);
            backgroundImageLabel.setBounds(0, 0, 400, 355);

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
        contentPane.add(dialogPanel, "cell 0 0 1 2,gapx null 0");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel dialogPanel;
    private JLabel idLabel;
    private JTextField idTextField;
    private JButton checkButton;
    private JLabel moduleLabel;
    private JTextField moduleTextField;
    private JLabel dateLabel;
    private JTextField dateTextField;
    private JPanel innerpanel;
    private JScrollPane studentScrollPane;
    private JTable studentTabel;
    private JScrollPane attendanceScrollPane;
    private JTable attendanceTabel;
    private JButton addButton;
    private JButton cancelButton;
    private JLabel backgroundImageLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
