/*
 * Created by JFormDesigner on Tue Jan 04 04:06:30 ICT 2022
 */

package com.company.add;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import com.company.edit.EditStudent;
import com.connection.JDBCConnection;
import net.miginfocom.swing.*;

/**
 * @author Xuan Minh Nguyen
 */
public class AddStudent extends JFrame {
    Connection connection = null;
    Statement statement = null;

    AddAttendance addAttendance = new AddAttendance();
    private JTextField[] studentTextField = new JTextField[4];
    private String[] studentLabel = {"Student ID", "First Name", "Last Name", "Module"};

    public AddStudent() {
        initComponents();
        connection = JDBCConnection.testConnection();
    }

    private void cancel(ActionEvent e) {
        setVisible(false);
    }

    private void addStudent(ActionEvent e) {
        studentTextField[0] = idTextField;
        studentTextField[1] = firstNameTextField;
        studentTextField[2] = lastNameTextField;
        studentTextField[3] = moduleTextField;

        int studentId = 0;
        try {
            studentId = Integer.parseInt(idTextField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Format for Student ID!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String major = (String) majorComboBox.getSelectedItem();
        String module = moduleTextField.getText();
        int moduleId = 0;

        boolean isMissing = addAttendance.isMissing(studentTextField, studentLabel);
        String missingTextField = addAttendance.getMissingTextField();

        // add a new module for that student
        String addModuleSQL = "INSERT INTO exam_regis\n" +
                "    SELECT s.student_id, m.module_id, null\n" +
                "    FROM student s\n" +
                "    JOIN module m\n" +
                "    WHERE s.student_id=" + studentId + " AND m.module_name='" + module + "'";

        try {
            if (!isMissing) {
                statement = connection.createStatement();
                String addStudentSQL = "INSERT INTO student VALUES(" + studentId + ", '" + firstName +
                                                    "', '" + lastName + "', '" + major + "')";
                statement.executeUpdate(addStudentSQL);
                statement.executeUpdate(addModuleSQL);

                JOptionPane.showMessageDialog(this, "Student added!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, missingTextField + " is not filled\nPlease complete all information!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            // create default icon, custom title
            int n = JOptionPane.showConfirmDialog(
                    this,
                    "Student is already in the database or Invalid information!\n" +
                            "Do you want to add " + module + " module to " + studentId + " ?",
                    "Confirm Add Module",
                    JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                try {
                    statement.executeUpdate(addModuleSQL);
                    JOptionPane.showMessageDialog(this, "Module added!");
                } catch (SQLIntegrityConstraintViolationException se) {
                    JOptionPane.showMessageDialog(this, "Student is already taken " + module + " module!");
                } catch (SQLException exc) {
                    JOptionPane.showMessageDialog(this, exc);
                }
            } else {
                setVisible(false);
            }
        } catch (SQLException exp) {
            JOptionPane.showMessageDialog(this, exp);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        contentPanel = new JPanel();
        idLabel = new JLabel();
        idTextField = new JTextField();
        firstNameLabel = new JLabel();
        firstNameTextField = new JTextField();
        lastNameLabel = new JLabel();
        lastNameTextField = new JTextField();
        majorLabel = new JLabel();
        majorComboBox = new JComboBox<>();
        moduleLabel = new JLabel();
        moduleTextField = new JTextField();
        registerButton = new JButton();
        cancelButton = new JButton();
        backgroundLabel = new JLabel();

        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/com/images/cmsLogo.png")).getImage());
        setTitle("Add Student");
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "insets 0,hidemode 3,gap 0 0",
            // columns
            "[grow,fill]",
            // rows
            "[fill]" +
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
            idTextField.setBounds(95, 15, 291, 30);

            //---- firstNameLabel ----
            firstNameLabel.setText("First Name");
            firstNameLabel.setFont(firstNameLabel.getFont().deriveFont(firstNameLabel.getFont().getStyle() | Font.BOLD));
            firstNameLabel.setForeground(Color.white);
            contentPanel.add(firstNameLabel);
            firstNameLabel.setBounds(10, 60, 78, 16);
            contentPanel.add(firstNameTextField);
            firstNameTextField.setBounds(95, 52, 291, 30);

            //---- lastNameLabel ----
            lastNameLabel.setText("Last Name");
            lastNameLabel.setForeground(Color.white);
            lastNameLabel.setFont(lastNameLabel.getFont().deriveFont(lastNameLabel.getFont().getStyle() | Font.BOLD));
            contentPanel.add(lastNameLabel);
            lastNameLabel.setBounds(10, 95, 78, 16);
            contentPanel.add(lastNameTextField);
            lastNameTextField.setBounds(95, 89, 291, 30);

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
            majorComboBox.setBounds(95, 126, 291, 30);

            //---- moduleLabel ----
            moduleLabel.setText("Module");
            moduleLabel.setFont(moduleLabel.getFont().deriveFont(moduleLabel.getFont().getStyle() | Font.BOLD));
            moduleLabel.setForeground(Color.white);
            contentPanel.add(moduleLabel);
            moduleLabel.setBounds(10, 170, 78, 16);
            contentPanel.add(moduleTextField);
            moduleTextField.setBounds(95, 163, 291, 30);

            //---- registerButton ----
            registerButton.setText("Add");
            registerButton.setIcon(new ImageIcon(getClass().getResource("/com/images/add.png")));
            registerButton.addActionListener(e -> addStudent(e));
            contentPanel.add(registerButton);
            registerButton.setBounds(155, 220, 105, registerButton.getPreferredSize().height);

            //---- cancelButton ----
            cancelButton.setText("Cancel");
            cancelButton.setIcon(new ImageIcon(getClass().getResource("/com/images/cancel.png")));
            cancelButton.addActionListener(e -> cancel(e));
            contentPanel.add(cancelButton);
            cancelButton.setBounds(280, 220, 100, cancelButton.getPreferredSize().height);

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
        contentPane.add(contentPanel, "cell 0 0 1 2");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel contentPanel;
    private JLabel idLabel;
    private JTextField idTextField;
    private JLabel firstNameLabel;
    private JTextField firstNameTextField;
    private JLabel lastNameLabel;
    private JTextField lastNameTextField;
    private JLabel majorLabel;
    private JComboBox<String> majorComboBox;
    private JLabel moduleLabel;
    private JTextField moduleTextField;
    private JButton registerButton;
    private JButton cancelButton;
    private JLabel backgroundLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
