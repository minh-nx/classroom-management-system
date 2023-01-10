/*
 * Created by JFormDesigner on Tue Jan 25 15:47:59 ICT 2022
 */

package com.company.remove;

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
public class RemoveStudent extends JFrame{
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public RemoveStudent() {
        connection = JDBCConnection.testConnection();
        initComponents();
    }

    private void showStudentTable(String sql) {
        try {
            ResultSet rs = statement.executeQuery(sql);
            studentTabel.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public String getCheckStudent(JTextField id) {
        String checkStudent = "SELECT student_id AS \"ID\",\n" +
                "first_name AS \"First Name\",\n" +
                "last_name AS \"Last Name\",\n" +
                "major AS \"Major\"\n" +
                "FROM student\n" +
                "WHERE student_id = " + id.getText();
        return checkStudent;
    }

    public void checkStudent() {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(getCheckStudent(idTextField));
            // check whether student exists or not
            if (resultSet.next()) {
                showStudentTable(getCheckStudent(idTextField));
            } else {
                JOptionPane.showMessageDialog(this, "Student not exists", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }

    private void check(ActionEvent e) {
        checkStudent();
    }

    private void removeStudent(ActionEvent e) {
        try {
            statement = connection.createStatement();
            String removeStudent = "DELETE FROM student\n" +
                    "WHERE student_id = " + idTextField.getText();
            // create default icon, custom title dialog
            int n = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure?\nStudent and all of their records will be permanently deleted.\n" +
                            "Accept the consequences and proceed?",
                    "Confirm Remove",
                    JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                statement.executeUpdate(removeStudent);
                JOptionPane.showMessageDialog(this, "Student has been removed!");
                showStudentTable(getCheckStudent(idTextField));
            } else {
                setVisible(false);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }

    private void cancel(ActionEvent e) {
        // close the frame
        setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        dialogPanel = new JPanel();
        idLabel = new JLabel();
        idTextField = new JTextField();
        checkButton = new JButton();
        innerpanel = new JPanel();
        studentScrollPane = new JScrollPane();
        studentTabel = new JTable();
        addButton = new JButton();
        cancelButton = new JButton();
        backgroundLabel = new JLabel();

        //======== this ========
        setTitle("Remove Student");
        setIconImage(new ImageIcon(getClass().getResource("/com/images/cmsLogo.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== dialogPanel ========
        {
            dialogPanel.setBorder(new TitledBorder("Delete Student"));
            dialogPanel.setLayout(null);

            //---- idLabel ----
            idLabel.setText("Student ID");
            idLabel.setForeground(Color.white);
            idLabel.setFont(idLabel.getFont().deriveFont(Font.BOLD));
            dialogPanel.add(idLabel);
            idLabel.setBounds(15, 10, 78, idLabel.getPreferredSize().height);
            dialogPanel.add(idTextField);
            idTextField.setBounds(85, 10, 280, 20);

            //---- checkButton ----
            checkButton.setFont(checkButton.getFont().deriveFont(checkButton.getFont().getStyle() | Font.BOLD));
            checkButton.setIcon(new ImageIcon(getClass().getResource("/com/images/check.png")));
            checkButton.setToolTipText("Check Student");
            checkButton.addActionListener(e -> check(e));
            dialogPanel.add(checkButton);
            checkButton.setBounds(370, 10, 21, 21);

            //======== innerpanel ========
            {
                innerpanel.setLayout(null);

                //======== studentScrollPane ========
                {
                    studentScrollPane.setBorder(new TitledBorder("Student"));
                    studentScrollPane.setViewportView(studentTabel);
                }
                innerpanel.add(studentScrollPane);
                studentScrollPane.setBounds(10, 5, 360, 80);

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
            innerpanel.setBounds(10, 40, 380, 90);

            //---- addButton ----
            addButton.setText("Remove");
            addButton.setFont(addButton.getFont().deriveFont(addButton.getFont().getStyle() | Font.BOLD));
            addButton.setIcon(new ImageIcon(getClass().getResource("/com/images/delete.png")));
            addButton.addActionListener(e -> removeStudent(e));
            dialogPanel.add(addButton);
            addButton.setBounds(125, 140, 120, addButton.getPreferredSize().height);

            //---- cancelButton ----
            cancelButton.setText("Cancel");
            cancelButton.setIcon(new ImageIcon(getClass().getResource("/com/images/cancel.png")));
            cancelButton.addActionListener(e -> cancel(e));
            dialogPanel.add(cancelButton);
            cancelButton.setBounds(270, 140, 120, cancelButton.getPreferredSize().height);

            //---- backgroundLabel ----
            backgroundLabel.setText("text");
            backgroundLabel.setIcon(new ImageIcon(getClass().getResource("/com/images/backGround4.png")));
            backgroundLabel.setVerticalAlignment(SwingConstants.TOP);
            dialogPanel.add(backgroundLabel);
            backgroundLabel.setBounds(0, 0, 405, 190);

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
        dialogPanel.setBounds(0, 0, 400, 185);

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
    private JLabel idLabel;
    private JTextField idTextField;
    private JButton checkButton;
    private JPanel innerpanel;
    private JScrollPane studentScrollPane;
    private JTable studentTabel;
    private JButton addButton;
    private JButton cancelButton;
    private JLabel backgroundLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
