/*
 * Created by JFormDesigner on Tue Jan 25 08:25:18 ICT 2022
 */

package com.company.add;

import com.connection.JDBCConnection;
import net.miginfocom.swing.*;
import net.proteanit.sql.DbUtils;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author Xuan Minh Nguyen
 */
public class RegisterExam extends JFrame {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public RegisterExam() {
        connection = JDBCConnection.testConnection();
        initComponents();
    }

    private void showExamTable() {
        String showExamRegistration = "SELECT s.student_id AS \"ID\",\n" +
                "       concat(s.first_name, ' ', s.last_name) AS \"Name\",\n" +
                "       m.module_name AS \"Module\",\n" +
                "       m.exam_date AS \"Exam Date\",\n" +
                "       er.attempt AS \"Attempt\"\n" +
                "FROM student s\n" +
                "JOIN exam_regis er on s.student_id = er.student_id\n" +
                "JOIN module m on er.module_id = m.module_id\n" +
                "WHERE s.student_id = " + idTextField.getText() + " AND er.attempt >= 1;";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(showExamRegistration);
            if (resultSet.next()) {
                // show exam registrations in examTable
                ResultSet rs = statement.executeQuery(showExamRegistration);
                examTabel.setModel(DbUtils.resultSetToTableModel(rs));
            } else {
                JOptionPane.showMessageDialog(this, "Student haven't registered for any exam!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }

    private void check(ActionEvent e) {
        showExamTable();
    }

    private void cancel(ActionEvent e) {
        // close the frame
        setVisible(false);
    }

    private void addExamRegistration(ActionEvent e) {
        String attempt = attemptTextField.getText();
        String module = moduleTextField.getText();
        try {
            statement = connection.createStatement();
            String addExamRegistration = "UPDATE exam_regis\n" +
                    "SET attempt = " + attempt + "\n" +
                    "WHERE student_id = " + idTextField.getText() + " AND module_id = (\n" +
                    "    SELECT module_id\n" +
                    "    FROM module \n" +
                    "    WHERE module_name = '" + module + "')";
            statement.executeUpdate(addExamRegistration);
            JOptionPane.showMessageDialog(this, "Exam registered!");
            showExamTable();
        } catch (SQLIntegrityConstraintViolationException exp) {
            JOptionPane.showMessageDialog(this, "Invalid information!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Information Format!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        dialogPane = new JPanel();
        idLabel = new JLabel();
        idTextField = new JTextField();
        checkButton = new JButton();
        moduleLabel = new JLabel();
        moduleTextField = new JTextField();
        attemptLabel = new JLabel();
        attemptTextField = new JTextField();
        innerpanel = new JPanel();
        studentScrollPane = new JScrollPane();
        examTabel = new JTable();
        addButton = new JButton();
        cancelButton = new JButton();
        backgroundImageLabel = new JLabel();

        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/com/images/cmsLogo.png")).getImage());
        setTitle("Register Exam");
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "insets 0,hidemode 3",
            // columns
            "[grow,fill]",
            // rows
            "[grow,fill]"));

        //======== dialogPane ========
        {
            dialogPane.setLayout(null);

            //---- idLabel ----
            idLabel.setText("Student ID");
            idLabel.setForeground(Color.white);
            idLabel.setFont(idLabel.getFont().deriveFont(Font.BOLD));
            dialogPane.add(idLabel);
            idLabel.setBounds(10, 15, 78, idLabel.getPreferredSize().height);
            dialogPane.add(idTextField);
            idTextField.setBounds(95, 10, 300, idTextField.getPreferredSize().height);

            //---- checkButton ----
            checkButton.setFont(checkButton.getFont().deriveFont(checkButton.getFont().getStyle() | Font.BOLD));
            checkButton.setIcon(new ImageIcon(getClass().getResource("/com/images/check.png")));
            checkButton.setToolTipText("Check Student");
            checkButton.addActionListener(e -> check(e));
            dialogPane.add(checkButton);
            checkButton.setBounds(405, 10, 21, 21);

            //---- moduleLabel ----
            moduleLabel.setText("Module");
            moduleLabel.setFont(moduleLabel.getFont().deriveFont(moduleLabel.getFont().getStyle() | Font.BOLD));
            moduleLabel.setForeground(Color.white);
            dialogPane.add(moduleLabel);
            moduleLabel.setBounds(10, 50, 78, moduleLabel.getPreferredSize().height);
            dialogPane.add(moduleTextField);
            moduleTextField.setBounds(95, 45, 330, moduleTextField.getPreferredSize().height);

            //---- attemptLabel ----
            attemptLabel.setText("Attempt");
            attemptLabel.setForeground(Color.white);
            attemptLabel.setFont(attemptLabel.getFont().deriveFont(attemptLabel.getFont().getStyle() | Font.BOLD));
            dialogPane.add(attemptLabel);
            attemptLabel.setBounds(10, 85, 78, attemptLabel.getPreferredSize().height);
            dialogPane.add(attemptTextField);
            attemptTextField.setBounds(95, 80, 330, attemptTextField.getPreferredSize().height);

            //======== innerpanel ========
            {
                innerpanel.setLayout(null);

                //======== studentScrollPane ========
                {
                    studentScrollPane.setBorder(new TitledBorder("Exam Registration"));
                    studentScrollPane.setViewportView(examTabel);
                }
                innerpanel.add(studentScrollPane);
                studentScrollPane.setBounds(10, 5, 395, 85);

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
            dialogPane.add(innerpanel);
            innerpanel.setBounds(10, 120, 415, 100);

            //---- addButton ----
            addButton.setText("Add");
            addButton.setFont(addButton.getFont().deriveFont(addButton.getFont().getStyle() | Font.BOLD));
            addButton.setIcon(new ImageIcon(getClass().getResource("/com/images/add.png")));
            addButton.addActionListener(e -> addExamRegistration(e));
            dialogPane.add(addButton);
            addButton.setBounds(195, 235, 100, addButton.getPreferredSize().height);

            //---- cancelButton ----
            cancelButton.setText("Cancel");
            cancelButton.setFont(cancelButton.getFont().deriveFont(cancelButton.getFont().getStyle() | Font.BOLD));
            cancelButton.setIcon(new ImageIcon(getClass().getResource("/com/images/cancel.png")));
            cancelButton.addActionListener(e -> cancel(e));
            dialogPane.add(cancelButton);
            cancelButton.setBounds(325, 235, 100, cancelButton.getPreferredSize().height);

            //---- backgroundImageLabel ----
            backgroundImageLabel.setText("text");
            backgroundImageLabel.setIcon(new ImageIcon(getClass().getResource("/com/images/background.png")));
            backgroundImageLabel.setVerticalAlignment(SwingConstants.TOP);
            backgroundImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            backgroundImageLabel.setLabelFor(idTextField);
            dialogPane.add(backgroundImageLabel);
            backgroundImageLabel.setBounds(0, 0, 440, 275);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < dialogPane.getComponentCount(); i++) {
                    Rectangle bounds = dialogPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = dialogPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                dialogPane.setMinimumSize(preferredSize);
                dialogPane.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(dialogPane, "cell 0 0,align leading top,grow 0 0");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel dialogPane;
    private JLabel idLabel;
    private JTextField idTextField;
    private JButton checkButton;
    private JLabel moduleLabel;
    private JTextField moduleTextField;
    private JLabel attemptLabel;
    private JTextField attemptTextField;
    private JPanel innerpanel;
    private JScrollPane studentScrollPane;
    private JTable examTabel;
    private JButton addButton;
    private JButton cancelButton;
    private JLabel backgroundImageLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
