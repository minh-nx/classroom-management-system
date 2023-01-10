/*
 * Created by JFormDesigner on Tue Jan 04 03:42:32 ICT 2022
 */

package com.company.view;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.border.*;

import com.company.add.AddStudent;
import com.connection.JDBCConnection;
import net.miginfocom.swing.*;
import net.proteanit.sql.DbUtils;

/**
 * @author Xuan Minh Nguyen
 */
public class ViewStudent extends JFrame {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public ViewStudent() {
        initComponents();
        connection = JDBCConnection.testConnection();
        showStudent();
    }

    private void cancel(ActionEvent e) {
        setVisible(false);
    }

    public void showStudent() {
        try {
            statement = connection.createStatement();
            String sql = "SELECT * FROM student";
            ResultSet resultSet = statement.executeQuery(sql);
            studentTabel.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }

    private void addStudent(ActionEvent e) {
        setVisible(false);
        AddStudent addStudent = new AddStudent();
        addStudent.setVisible(true);
    }

    private void refresh(ActionEvent e) {
        showStudent();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        contentPanel = new JPanel();
        scrollPane1 = new JScrollPane();
        studentTabel = new JTable();
        addButton = new JButton();
        refreshButton = new JButton();
        cancelButton = new JButton();
        backgroundLabel = new JLabel();

        //======== this ========
        setTitle("View Student");
        setIconImage(new ImageIcon(getClass().getResource("/com/images/cmsLogo.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "insets 0,hidemode 3",
            // columns
            "[grow,fill]",
            // rows
            "[grow,fill]0" +
            "[]0" +
            "[]0" +
            "[]"));

        //======== contentPanel ========
        {
            contentPanel.setLayout(null);

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(studentTabel);
            }
            contentPanel.add(scrollPane1);
            scrollPane1.setBounds(15, 25, 370, 175);

            //---- addButton ----
            addButton.setText("Add");
            addButton.setFont(addButton.getFont().deriveFont(addButton.getFont().getStyle() | Font.BOLD));
            addButton.setIcon(new ImageIcon(getClass().getResource("/com/images/add.png")));
            addButton.addActionListener(e -> addStudent(e));
            contentPanel.add(addButton);
            addButton.setBounds(15, 207, 100, addButton.getPreferredSize().height);

            //---- refreshButton ----
            refreshButton.setText("Refresh");
            refreshButton.setFont(refreshButton.getFont().deriveFont(refreshButton.getFont().getStyle() | Font.BOLD));
            refreshButton.setIcon(new ImageIcon(getClass().getResource("/com/images/refresh1.png")));
            refreshButton.addActionListener(e -> refresh(e));
            contentPanel.add(refreshButton);
            refreshButton.setBounds(150, 207, 100, refreshButton.getPreferredSize().height);

            //---- cancelButton ----
            cancelButton.setText("Back");
            cancelButton.setIcon(new ImageIcon(getClass().getResource("/com/images/backIcon.png")));
            cancelButton.addActionListener(e -> cancel(e));
            contentPanel.add(cancelButton);
            cancelButton.setBounds(285, 207, 100, cancelButton.getPreferredSize().height);

            //---- backgroundLabel ----
            backgroundLabel.setText("text");
            backgroundLabel.setIcon(new ImageIcon(getClass().getResource("/com/images/backGround4.png")));
            backgroundLabel.setVerticalAlignment(SwingConstants.BOTTOM);
            backgroundLabel.setBorder(new TitledBorder(null, "Student", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.BOLD, 15), Color.white));
            contentPanel.add(backgroundLabel);
            backgroundLabel.setBounds(0, 0, 405, 250);

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
        contentPane.add(contentPanel, "cell 0 1");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel contentPanel;
    private JScrollPane scrollPane1;
    private JTable studentTabel;
    private JButton addButton;
    private JButton refreshButton;
    private JButton cancelButton;
    private JLabel backgroundLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
