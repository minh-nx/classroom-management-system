/*
 * Created by JFormDesigner on Sat Jan 01 14:12:48 ICT 2022
 */

package com.company;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

import com.connection.JDBCConnection;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class Login extends JFrame {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public Login() {
        initComponents();
        connection = JDBCConnection.testConnection();
    }

    private void cancel(ActionEvent e) {
        System.exit(0);
    }

    private void login(ActionEvent e) {
        try {
            statement = connection.createStatement();
            String user = userTextField.getText();
            String password = passwordField.getText();

            String sql = "SELECT * FROM teacher WHERE name='"
                        + user + "' && password='" + password + "'";
            resultSet = statement.executeQuery(sql);

            //  if user or password is correct, close the login page
            //  and open the home page.
            //  else, return error message
            if (resultSet.next()) {
                setVisible(false);
                Home home = new Home();
                home.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Login failed!\nInvalid User Name or Password.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void showPassword(ActionEvent e) {
        if (showPasswordcheckBox.isSelected()) {
            passwordField.setEchoChar((char)0);
        } else {
            passwordField.setEchoChar('\u2022');
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        contentPanel = new JPanel();
        userLabel = new JLabel();
        userTextField = new JTextField();
        passwordLabel = new JLabel();
        passwordField = new JPasswordField();
        showPasswordcheckBox = new JCheckBox();
        loginButton = new JButton();
        cancelButton = new JButton();
        backgroundLabel = new JLabel();

        //======== this ========
        setTitle("Login");
        setIconImage(new ImageIcon(getClass().getResource("/com/images/cmsLogo.png")).getImage());
        setFont(new Font("JetBrains Mono ExtraBold", Font.PLAIN, 12));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "insets 0,hidemode 3",
            // columns
            "[fill]",
            // rows
            "[fill]" +
            "[]"));

        //======== contentPanel ========
        {
            contentPanel.setLayout(null);

            //---- userLabel ----
            userLabel.setText("User Name");
            userLabel.setIcon(null);
            userLabel.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
            userLabel.setForeground(Color.white);
            contentPanel.add(userLabel);
            userLabel.setBounds(new Rectangle(new Point(11, 11), userLabel.getPreferredSize()));
            contentPanel.add(userTextField);
            userTextField.setBounds(10, 35, 345, userTextField.getPreferredSize().height);

            //---- passwordLabel ----
            passwordLabel.setText("Password");
            passwordLabel.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
            passwordLabel.setForeground(Color.white);
            contentPanel.add(passwordLabel);
            passwordLabel.setBounds(11, 72, 65, passwordLabel.getPreferredSize().height);
            contentPanel.add(passwordField);
            passwordField.setBounds(10, 96, 345, passwordField.getPreferredSize().height);

            //---- showPasswordcheckBox ----
            showPasswordcheckBox.setIcon(new ImageIcon(getClass().getResource("/com/images/showPassword.png")));
            showPasswordcheckBox.setSelectedIcon(new ImageIcon(getClass().getResource("/com/images/hidePassword.png")));
            showPasswordcheckBox.setText("Show Password");
            showPasswordcheckBox.setBackground(new Color(20, 14, 44));
            showPasswordcheckBox.setForeground(Color.white);
            showPasswordcheckBox.setFont(showPasswordcheckBox.getFont().deriveFont(showPasswordcheckBox.getFont().getStyle() | Font.BOLD));
            showPasswordcheckBox.addActionListener(e -> showPassword(e));
            contentPanel.add(showPasswordcheckBox);
            showPasswordcheckBox.setBounds(5, 130, 135, 25);

            //---- loginButton ----
            loginButton.setText("Login");
            loginButton.setIcon(new ImageIcon(getClass().getResource("/com/images/Login-icon.png")));
            loginButton.setFont(loginButton.getFont().deriveFont(loginButton.getFont().getStyle() | Font.BOLD));
            loginButton.setSelectedIcon(null);
            loginButton.addActionListener(e -> login(e));
            contentPanel.add(loginButton);
            loginButton.setBounds(125, 180, 100, loginButton.getPreferredSize().height);

            //---- cancelButton ----
            cancelButton.setText("Cancel");
            cancelButton.setIcon(new ImageIcon(getClass().getResource("/com/images/cancel1.png")));
            cancelButton.setFont(cancelButton.getFont().deriveFont(cancelButton.getFont().getStyle() | Font.BOLD));
            cancelButton.addActionListener(e -> cancel(e));
            contentPanel.add(cancelButton);
            cancelButton.setBounds(255, 180, 100, cancelButton.getPreferredSize().height);

            //---- backgroundLabel ----
            backgroundLabel.setText("text");
            backgroundLabel.setIcon(new ImageIcon(getClass().getResource("/com/images/backGround4.png")));
            backgroundLabel.setHorizontalAlignment(SwingConstants.CENTER);
            contentPanel.add(backgroundLabel);
            backgroundLabel.setBounds(0, -30, 370, 255);

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
    private JLabel userLabel;
    private JTextField userTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JCheckBox showPasswordcheckBox;
    private JButton loginButton;
    private JButton cancelButton;
    private JLabel backgroundLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
