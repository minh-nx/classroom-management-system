/*
 * Created by JFormDesigner on Tue Jan 04 13:48:05 ICT 2022
 */

package com.company.edit;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import com.company.add.AddAttendance;
import com.connection.JDBCConnection;
import net.miginfocom.swing.*;

/**
 * @author Xuan Minh Nguyen
 */
public class EditStudent extends JFrame {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    AddAttendance addAttendance = new AddAttendance();
    private JTextField[] studentTextField = new JTextField[4];
    private String[] studentLabel = {"Student ID", "First Name", "Last Name", "Module"};

    boolean isMissing;
    String missingTextField;

    public EditStudent() {
        initComponents();
        connection = JDBCConnection.testConnection();
    }

    private void checkStudentAction(int studentId) {
        try {
            statement = connection.createStatement();
            String checkStudentSQL = "SELECT * FROM student WHERE student_id=" + studentId;
            resultSet = statement.executeQuery(checkStudentSQL);

            // check whether student is in the database then update the textFields with student's record,
            // or clear all if not
            if (resultSet.next()) {
                // set all textFields to student's record
                firstNameTextField.setText(resultSet.getString("first_name"));
                lastNameTextField.setText(resultSet.getString("last_name"));
                majorComboBox.setSelectedItem(resultSet.getString("major"));
            } else {
                JOptionPane.showMessageDialog(this, "Student not found!", "Warning", JOptionPane.WARNING_MESSAGE);
                // clear all textFields
                firstNameTextField.setText("");
                lastNameTextField.setText("");
                majorComboBox.setSelectedItem("");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void checkStudent(ActionEvent e) {
        int studentId = 0;
        try {
            studentId = Integer.parseInt(idTextField.getText());
            checkStudentAction(studentId);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Missing information or invalid format for Student ID!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void updateData() {
        // add textField into JTextField array
        studentTextField[0] = idTextField;
        studentTextField[1] = firstNameTextField;
        studentTextField[2] = lastNameTextField;
        studentTextField[3] = moduleTextField;

        isMissing = addAttendance.isMissing(studentTextField, studentLabel);
        missingTextField = addAttendance.getMissingTextField();
    }

    private void updateStudent(ActionEvent e) {
        updateData();

        int studentId = Integer.parseInt(idTextField.getText());
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String major = (String) majorComboBox.getSelectedItem();
        String module = moduleTextField.getText();

        try {
            if (!isMissing) {
                statement = connection.createStatement();
                // update student's record
                String updateStudentSQL = "UPDATE student SET student_id=" + studentId + ", first_name='" + firstName +
                        "', last_name='" + lastName + "', major='" + major + "' WHERE student_id=" + studentId;
                statement.executeUpdate(updateStudentSQL);
                JOptionPane.showMessageDialog(this, "Student's record updated!", "Warning", JOptionPane.WARNING_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(this, missingTextField + " is not filled\nPlease complete all information!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }

    private void removeStudent(ActionEvent e) {
        updateData();
        try {
            if (!isMissing) {
                int studentId = Integer.parseInt(idTextField.getText());
                checkStudentAction(studentId);
                String deleteStudentSQL = "DELETE FROM student WHERE student_id=" + studentId;
                String deleteModuleSQL = "DELETE er FROM exam_regis er\n" +
                        "WHERE er.student_id=" + studentId + " AND er.module_id IN (\n" +
                        "SELECT m.module_id FROM module m WHERE module_name='" + moduleTextField.getText() + "')";

                // set a default icon, custom title dialog
                Object[] options = {"Yes, remove Student",
                        "Not quite, remove Module",
                        "No, abort abort"};
                int n = JOptionPane.showOptionDialog(this,
                        "Are you sure?\nStudent and all of their records will be permanently deleted.\n" +
                                "Accept the consequences and proceed?",
                        "Warning",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        options,
                        options[2]);
                if (n == JOptionPane.YES_OPTION) {
                    statement.executeUpdate(deleteStudentSQL);
                    JOptionPane.showMessageDialog(this, "Student is successfully removed!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else if (n == JOptionPane.NO_OPTION) {
                    statement.executeUpdate(deleteModuleSQL);
                    JOptionPane.showMessageDialog(this, "Module is successfully removed from " + studentId + "!",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, missingTextField + " is not filled\nPlease complete all information!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void cancel(ActionEvent e) {
        setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        contentPanel = new JPanel();
        idLabel = new JLabel();
        idTextField = new JTextField();
        checkButton = new JButton();
        firstNameLabel = new JLabel();
        firstNameTextField = new JTextField();
        lastNameLabel = new JLabel();
        lastNameTextField = new JTextField();
        majorLabel = new JLabel();
        majorComboBox = new JComboBox<>();
        moduleLabel = new JLabel();
        moduleTextField = new JTextField();
        updateButton2 = new JButton();
        updateButton = new JButton();
        cancelButton = new JButton();
        backgroundLabel = new JLabel();

        //======== this ========
        setTitle("Edit Student");
        setIconImage(new ImageIcon(getClass().getResource("/com/images/logoLabel.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "insets 0,hidemode 3",
            // columns
            "[grow,fill]",
            // rows
            "[grow,fill]"));

        //======== contentPanel ========
        {
            contentPanel.setLayout(null);

            //---- idLabel ----
            idLabel.setText("Student ID");
            idLabel.setForeground(Color.white);
            idLabel.setFont(idLabel.getFont().deriveFont(Font.BOLD));
            contentPanel.add(idLabel);
            idLabel.setBounds(10, 20, 78, 16);
            contentPanel.add(idTextField);
            idTextField.setBounds(95, 15, 265, idTextField.getPreferredSize().height);

            //---- checkButton ----
            checkButton.setFont(checkButton.getFont().deriveFont(checkButton.getFont().getStyle() | Font.BOLD));
            checkButton.setIcon(new ImageIcon(getClass().getResource("/com/images/check.png")));
            checkButton.setToolTipText("Check Student");
            checkButton.addActionListener(e -> checkStudent(e));
            contentPanel.add(checkButton);
            checkButton.setBounds(365, 15, 21, 21);

            //---- firstNameLabel ----
            firstNameLabel.setText("First Name");
            firstNameLabel.setFont(firstNameLabel.getFont().deriveFont(firstNameLabel.getFont().getStyle() | Font.BOLD));
            firstNameLabel.setForeground(Color.white);
            contentPanel.add(firstNameLabel);
            firstNameLabel.setBounds(10, 60, 78, 16);
            contentPanel.add(firstNameTextField);
            firstNameTextField.setBounds(95, 52, 291, firstNameTextField.getPreferredSize().height);

            //---- lastNameLabel ----
            lastNameLabel.setText("Last Name");
            lastNameLabel.setForeground(Color.white);
            lastNameLabel.setFont(lastNameLabel.getFont().deriveFont(lastNameLabel.getFont().getStyle() | Font.BOLD));
            contentPanel.add(lastNameLabel);
            lastNameLabel.setBounds(10, 95, 78, 16);
            contentPanel.add(lastNameTextField);
            lastNameTextField.setBounds(95, 89, 291, lastNameTextField.getPreferredSize().height);

            //---- majorLabel ----
            majorLabel.setText("Major");
            majorLabel.setForeground(Color.white);
            majorLabel.setFont(majorLabel.getFont().deriveFont(majorLabel.getFont().getStyle() | Font.BOLD));
            contentPanel.add(majorLabel);
            majorLabel.setBounds(10, 135, 78, 16);

            //---- majorComboBox ----
            majorComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                "CSE",
                "BBA",
                "BFA",
                "ARC",
                "MEN",
                "BFA",
                "BCE",
                "ECE"
            }));
            contentPanel.add(majorComboBox);
            majorComboBox.setBounds(95, 126, 291, majorComboBox.getPreferredSize().height);

            //---- moduleLabel ----
            moduleLabel.setText("Module");
            moduleLabel.setFont(moduleLabel.getFont().deriveFont(moduleLabel.getFont().getStyle() | Font.BOLD));
            moduleLabel.setForeground(Color.white);
            contentPanel.add(moduleLabel);
            moduleLabel.setBounds(10, 170, 78, 16);
            contentPanel.add(moduleTextField);
            moduleTextField.setBounds(95, 163, 291, moduleTextField.getPreferredSize().height);

            //---- updateButton2 ----
            updateButton2.setText("Update");
            updateButton2.setIcon(new ImageIcon(getClass().getResource("/com/images/update.png")));
            updateButton2.setFont(updateButton2.getFont().deriveFont(updateButton2.getFont().getStyle() | Font.BOLD));
            updateButton2.addActionListener(e -> updateStudent(e));
            contentPanel.add(updateButton2);
            updateButton2.setBounds(25, 220, 105, updateButton2.getPreferredSize().height);

            //---- updateButton ----
            updateButton.setText("Remove");
            updateButton.setIcon(new ImageIcon(getClass().getResource("/com/images/delete.png")));
            updateButton.setFont(updateButton.getFont().deriveFont(updateButton.getFont().getStyle() | Font.BOLD));
            updateButton.addActionListener(e -> removeStudent(e));
            contentPanel.add(updateButton);
            updateButton.setBounds(155, 220, 105, updateButton.getPreferredSize().height);

            //---- cancelButton ----
            cancelButton.setText("Cancel");
            cancelButton.setIcon(new ImageIcon(getClass().getResource("/com/images/cancel.png")));
            cancelButton.setFont(cancelButton.getFont().deriveFont(cancelButton.getFont().getStyle() | Font.BOLD));
            cancelButton.addActionListener(e -> cancel(e));
            contentPanel.add(cancelButton);
            cancelButton.setBounds(280, 220, 105, cancelButton.getPreferredSize().height);

            //---- backgroundLabel ----
            backgroundLabel.setText("text");
            backgroundLabel.setIcon(new ImageIcon(getClass().getResource("/com/images/backGround4.png")));
            backgroundLabel.setHorizontalAlignment(SwingConstants.LEFT);
            contentPanel.add(backgroundLabel);
            backgroundLabel.setBounds(0, 0, 400, 270);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < contentPanel.getComponentCount(); i++) {
                    Rectangle bounds = contentPanel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = contentPanel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                contentPanel.setMinimumSize(preferredSize);
                contentPanel.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(contentPanel, "cell 0 0");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel contentPanel;
    private JLabel idLabel;
    private JTextField idTextField;
    private JButton checkButton;
    private JLabel firstNameLabel;
    private JTextField firstNameTextField;
    private JLabel lastNameLabel;
    private JTextField lastNameTextField;
    private JLabel majorLabel;
    private JComboBox<String> majorComboBox;
    private JLabel moduleLabel;
    private JTextField moduleTextField;
    private JButton updateButton2;
    private JButton updateButton;
    private JButton cancelButton;
    private JLabel backgroundLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
