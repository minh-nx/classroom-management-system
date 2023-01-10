/*
 * Created by JFormDesigner on Wed Jan 05 16:06:31 ICT 2022
 */

package com.company.edit;

import com.company.Attendance;
import com.company.add.AddAttendance;
import com.connection.JDBCConnection;
import net.proteanit.sql.DbUtils;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.TableModel;

/**
 * @author Xuan Minh Nguyen
 */
public class EditAttendance extends JFrame implements Attendance {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    AddAttendance addAttendance = new AddAttendance();
    private JTextField[] attedanceTextFields = new JTextField[3];
    private String[] attendanceLabels = {"Student ID", "Module", "Date"};

    boolean isMissing;
    String missingTextField;

    public EditAttendance() {
        initComponents();
        connection = JDBCConnection.testConnection();
    }

    private void cancel(ActionEvent e) {
        // close the frame
        setVisible(false);
    }

    private void updateData() {
        // add textField into JTextField array
        attedanceTextFields[0] = idTextField;
        attedanceTextFields[1] = moduleTextField;
        attedanceTextFields[2] = dateTextField;

        isMissing = addAttendance.isMissing(attedanceTextFields, attendanceLabels);
        missingTextField = addAttendance.getMissingTextField();
    }

    private void removeAttendance(ActionEvent e) {
        try {
            statement = connection.createStatement();
            String removeAttendance = "DELETE FROM attendance\n" +
                                    "WHERE student_id = " + idTextField.getText() + " AND\n" +
                                    "module_id = (\n" +
                                    "SELECT module_id FROM module WHERE module_name = '" + moduleTextField.getText() +
                                    "') AND date = '" + dateTextField.getText() + "'";

            // create default icon, custom title dialog
            int n = JOptionPane.showConfirmDialog(
                    this,
                    "Confirm remove Attendance?\n" +
                    "This action cannot be undone, proceed?",
                    "Confirm Remove",
                    JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                statement.executeUpdate(removeAttendance);
                JOptionPane.showMessageDialog(this, "Attendance deleted!");
                showAttendanceTable(getCheckAttendance(idTextField));
            } else {
                setVisible(false);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }

    private String getCheckAttendance(JTextField id) {
        String checkAttendanceSQL = attendanceSQL + "WHERE a.student_id =" + id.getText();
        return checkAttendanceSQL;
    }

    private void updateAttendance(ActionEvent e) {
        updateData();

        int studentId = Integer.parseInt(idTextField.getText());
        String module = moduleTextField.getText();
        String date = dateTextField.getText();

        try {
            if (!isMissing) {
                statement = connection.createStatement();
                String addAttendanceSQL = "INSERT INTO attendance\n" +
                        "SELECT s.student_id, m.module_id, '" + date + "'\n" +
                        "FROM student s\n" +
                        "JOIN module m\n" +
                        "WHERE  s.student_id=" + studentId + " AND m.module_name='" + module + "'";
                statement.executeUpdate(addAttendanceSQL);
                JOptionPane.showMessageDialog(this, "Attendance added!");
                showAttendanceTable(getCheckAttendance(idTextField));
            } else {
                JOptionPane.showMessageDialog(this, missingTextField + " is not filled\nPlease complete all information!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(this, ex);
//            JOptionPane.showMessageDialog(this, "Student's already attended " + module + " module", "Warning", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }

    private void attendanceTabelDisplayMouseClicked(MouseEvent e) {
        int s = attendanceTabel.getSelectedRow();
        TableModel model = attendanceTabel.getModel();
        idTextField.setText(model.getValueAt(s, 0).toString());
        moduleTextField.setText(model.getValueAt(s, 1).toString());
        dateTextField.setText(model.getValueAt(s, 2).toString());
    }

    private void showAttendanceTable(String sql) {
        try {
            ResultSet rs = statement.executeQuery(sql);
            attendanceTabel.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void checkStudent(ActionEvent e) {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(getCheckAttendance(idTextField));
            if (resultSet.next()) {
                showAttendanceTable(getCheckAttendance(idTextField));
            } else {
                JOptionPane.showMessageDialog(this, "Student not exists", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        contenPane = new JPanel();
        idLabel = new JLabel();
        idTextField = new JTextField();
        checkButton = new JButton();
        moduleLabel = new JLabel();
        moduleTextField = new JTextField();
        dateLabel = new JLabel();
        dateTextField = new JTextField();
        innerPane = new JPanel();
        attendanceScrollPane = new JScrollPane();
        attendanceTabel = new JTable();
        updateButton = new JButton();
        deleteButton = new JButton();
        cancelButton = new JButton();
        backgroundImageLabel = new JLabel();

        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/com/images/cmsLogo.png")).getImage());
        setTitle("Edit Attendance");
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== contenPane ========
        {
            contenPane.setLayout(null);

            //---- idLabel ----
            idLabel.setText("Student ID");
            idLabel.setForeground(Color.white);
            idLabel.setFont(idLabel.getFont().deriveFont(Font.BOLD));
            contenPane.add(idLabel);
            idLabel.setBounds(15, 16, 78, idLabel.getPreferredSize().height);
            contenPane.add(idTextField);
            idTextField.setBounds(100, 10, 260, idTextField.getPreferredSize().height);

            //---- checkButton ----
            checkButton.setFont(checkButton.getFont().deriveFont(checkButton.getFont().getStyle() | Font.BOLD));
            checkButton.setIcon(new ImageIcon(getClass().getResource("/com/images/check.png")));
            checkButton.setToolTipText("Check Student");
            checkButton.addActionListener(e -> checkStudent(e));
            contenPane.add(checkButton);
            checkButton.setBounds(370, 10, 21, 21);

            //---- moduleLabel ----
            moduleLabel.setText("Module");
            moduleLabel.setFont(moduleLabel.getFont().deriveFont(moduleLabel.getFont().getStyle() | Font.BOLD));
            moduleLabel.setForeground(Color.white);
            contenPane.add(moduleLabel);
            moduleLabel.setBounds(15, 50, 78, moduleLabel.getPreferredSize().height);
            contenPane.add(moduleTextField);
            moduleTextField.setBounds(100, 45, 291, moduleTextField.getPreferredSize().height);

            //---- dateLabel ----
            dateLabel.setText("Date");
            dateLabel.setForeground(Color.white);
            dateLabel.setFont(dateLabel.getFont().deriveFont(dateLabel.getFont().getStyle() | Font.BOLD));
            contenPane.add(dateLabel);
            dateLabel.setBounds(15, 85, 78, dateLabel.getPreferredSize().height);
            contenPane.add(dateTextField);
            dateTextField.setBounds(100, 80, 291, dateTextField.getPreferredSize().height);

            //======== innerPane ========
            {
                innerPane.setLayout(null);

                //======== attendanceScrollPane ========
                {
                    attendanceScrollPane.setBorder(new TitledBorder("Attendance"));

                    //---- attendanceTabel ----
                    attendanceTabel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            attendanceTabelDisplayMouseClicked(e);
                        }
                    });
                    attendanceScrollPane.setViewportView(attendanceTabel);
                }
                innerPane.add(attendanceScrollPane);
                attendanceScrollPane.setBounds(0, 0, 380, 95);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < innerPane.getComponentCount(); i++) {
                        Rectangle bounds = innerPane.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = innerPane.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    innerPane.setMinimumSize(preferredSize);
                    innerPane.setPreferredSize(preferredSize);
                }
            }
            contenPane.add(innerPane);
            innerPane.setBounds(new Rectangle(new Point(10, 120), innerPane.getPreferredSize()));

            //---- updateButton ----
            updateButton.setText("Update");
            updateButton.setIcon(new ImageIcon(getClass().getResource("/com/images/update.png")));
            updateButton.setFont(updateButton.getFont().deriveFont(updateButton.getFont().getStyle() | Font.BOLD));
            updateButton.addActionListener(e -> updateAttendance(e));
            contenPane.add(updateButton);
            updateButton.setBounds(25, 230, 105, updateButton.getPreferredSize().height);

            //---- deleteButton ----
            deleteButton.setText("Remove");
            deleteButton.setIcon(new ImageIcon(getClass().getResource("/com/images/delete.png")));
            deleteButton.setFont(deleteButton.getFont().deriveFont(deleteButton.getFont().getStyle() | Font.BOLD));
            deleteButton.addActionListener(e -> removeAttendance(e));
            contenPane.add(deleteButton);
            deleteButton.setBounds(155, 230, 105, deleteButton.getPreferredSize().height);

            //---- cancelButton ----
            cancelButton.setText("Cancel");
            cancelButton.setIcon(new ImageIcon(getClass().getResource("/com/images/cancel.png")));
            cancelButton.setFont(cancelButton.getFont().deriveFont(cancelButton.getFont().getStyle() | Font.BOLD));
            cancelButton.addActionListener(e -> cancel(e));
            contenPane.add(cancelButton);
            cancelButton.setBounds(280, 230, 100, cancelButton.getPreferredSize().height);

            //---- backgroundImageLabel ----
            backgroundImageLabel.setText("text");
            backgroundImageLabel.setIcon(new ImageIcon(getClass().getResource("/com/images/background.png")));
            backgroundImageLabel.setVerticalAlignment(SwingConstants.BOTTOM);
            backgroundImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            contenPane.add(backgroundImageLabel);
            backgroundImageLabel.setBounds(0, 0, 410, 280);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < contenPane.getComponentCount(); i++) {
                    Rectangle bounds = contenPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = contenPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                contenPane.setMinimumSize(preferredSize);
                contenPane.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(contenPane);
        contenPane.setBounds(0, 0, 400, 270);

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
    private JPanel contenPane;
    private JLabel idLabel;
    private JTextField idTextField;
    private JButton checkButton;
    private JLabel moduleLabel;
    private JTextField moduleTextField;
    private JLabel dateLabel;
    private JTextField dateTextField;
    private JPanel innerPane;
    private JScrollPane attendanceScrollPane;
    private JTable attendanceTabel;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton cancelButton;
    private JLabel backgroundImageLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
