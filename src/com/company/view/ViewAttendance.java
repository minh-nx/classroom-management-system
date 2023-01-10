/*
 * Created by JFormDesigner on Wed Jan 05 14:52:41 ICT 2022
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

import com.company.Attendance;
import com.company.add.AddAttendance;
import com.connection.JDBCConnection;
import net.proteanit.sql.DbUtils;

/**
 * @author Xuan Minh Nguyen
 */
public class ViewAttendance extends JFrame implements Attendance {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public ViewAttendance() {
        initComponents();
        connection = JDBCConnection.testConnection();
        showAttendance();
    }

    private void cancel(ActionEvent e) {
        setVisible(false);
    }

    public void showAttendance() {
        try {
            statement = connection.createStatement();
            String viewAttendanceSQL = attendanceSQL;
            resultSet = statement.executeQuery(viewAttendanceSQL);
            attendanceTabel.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void addAttendance(ActionEvent e) {
        setVisible(false);
        AddAttendance addAttendance = new AddAttendance();
        addAttendance.setVisible(true);
    }

    private void refresh(ActionEvent e) {
        showAttendance();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        contentPanel = new JPanel();
        scrollPane1 = new JScrollPane();
        attendanceTabel = new JTable();
        addButton = new JButton();
        refreshButton = new JButton();
        cancelButton = new JButton();
        backgroundLabel = new JLabel();

        //======== this ========
        setTitle("View Attendance");
        setIconImage(new ImageIcon(getClass().getResource("/com/images/cmsLogo.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== contentPanel ========
        {
            contentPanel.setLayout(null);

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(attendanceTabel);
            }
            contentPanel.add(scrollPane1);
            scrollPane1.setBounds(15, 25, 370, 175);

            //---- addButton ----
            addButton.setText("Add");
            addButton.setFont(addButton.getFont().deriveFont(addButton.getFont().getStyle() | Font.BOLD));
            addButton.setIcon(new ImageIcon(getClass().getResource("/com/images/add.png")));
            addButton.addActionListener(e -> addAttendance(e));
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
            cancelButton.setFont(cancelButton.getFont().deriveFont(cancelButton.getFont().getStyle() | Font.BOLD));
            cancelButton.addActionListener(e -> cancel(e));
            contentPanel.add(cancelButton);
            cancelButton.setBounds(285, 207, 100, cancelButton.getPreferredSize().height);

            //---- backgroundLabel ----
            backgroundLabel.setText("text");
            backgroundLabel.setIcon(new ImageIcon(getClass().getResource("/com/images/backGround4.png")));
            backgroundLabel.setVerticalAlignment(SwingConstants.BOTTOM);
            backgroundLabel.setHorizontalAlignment(SwingConstants.CENTER);
            backgroundLabel.setBorder(new TitledBorder(null, "Attendance", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.BOLD, 15), Color.white));
            contentPanel.add(backgroundLabel);
            backgroundLabel.setBounds(0, 0, 410, 250);

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
        contentPane.add(contentPanel);
        contentPanel.setBounds(0, 0, 405, 250);

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
    private JPanel contentPanel;
    private JScrollPane scrollPane1;
    private JTable attendanceTabel;
    private JButton addButton;
    private JButton refreshButton;
    private JButton cancelButton;
    private JLabel backgroundLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
