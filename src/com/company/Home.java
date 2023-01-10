/*
 * Created by JFormDesigner on Sun Jan 02 01:23:07 ICT 2022
 */

package com.company;

import com.company.add.AddAttendance;
import com.company.add.AddStudent;
import com.company.add.RegisterExam;
import com.company.edit.EditAttendance;
import com.company.edit.EditStudent;
import com.company.remove.RemoveStudent;
import com.company.view.ViewAttendance;
import com.company.view.ViewStudent;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

/**
 * @author Xuan Minh Nguyen
 */
public class Home extends JFrame {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    Login login = new Login();

    AddStudent addStudent = new AddStudent();
    EditStudent editStudent = new EditStudent();
    ViewStudent viewStudent = new ViewStudent();
    RemoveStudent removeStudent = new RemoveStudent();

    AddAttendance addAttendance = new AddAttendance();
    EditAttendance editAttendance = new EditAttendance();
    ViewAttendance viewAttendance = new ViewAttendance();

    RegisterExam registerExam = new RegisterExam();

    Search search = new Search();
    About about = new About();

    public Home() throws SQLException {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void aboutMenuItem(ActionEvent e) {
        about.setVisible(true);
    }

    private void addAttendance(ActionEvent e) {
        addAttendance.setVisible(true);
    }

    private void closeMenuItem(ActionEvent e) {
        System.exit(0);
    }

    private void registerStudent(ActionEvent e) {
        addStudent.setVisible(true);
    }

    private void viewStudent(ActionEvent e) {
        viewStudent.setVisible(true);
    }

    private void viewAttendance(ActionEvent e) {
        viewAttendance.setVisible(true);
    }

    private void editStudent(ActionEvent e) {
        editStudent.setVisible(true);
    }

    private void addStudentButton(ActionEvent e) {
        addStudent.setVisible(true);
    }

    private void exitApplication(ActionEvent e) {
        System.exit(0);
    }

    private void editStudentToolbar(ActionEvent e) {
        editStudent.setVisible(true);
    }

    private void viewStudentButton(ActionEvent e) {
        viewStudent.setVisible(true);
    }

    private void addAttendanceButton(ActionEvent e) {
        addAttendance.setVisible(true);
    }

    private void editStudentButton(ActionEvent e) {
        editStudent.setVisible(true);
    }

    private void editAttendanceButton(ActionEvent e) {
        editAttendance.setVisible(true);
    }

    private void viewAttendanceButton(ActionEvent e) {
        viewAttendance.setVisible(true);
    }

    private void infoButton(ActionEvent e) {
        about.setVisible(true);
    }

    private void exitButton(ActionEvent e) {
        System.exit(0);
    }

    private void logoutMenuItem(ActionEvent e) {
        setVisible(false);
        login.setVisible(true);
    }

    private void removeStudent(ActionEvent e) {
        removeStudent.setVisible(true);
    }

    private void search(ActionEvent e) {
        search.setVisible(true);
    }

    private void searchStudent(ActionEvent e) {
        search.setVisible(true);
    }

    private void editAttendance(ActionEvent e) {
        editAttendance.setVisible(true);
    }

    private void searchAttendance(ActionEvent e) {
        search.setVisible(true);
    }

    private void registerExam(ActionEvent e) {
        registerExam.setVisible(true);
    }

    private void examRegister(ActionEvent e) {
        registerExam.setVisible(true);
    }

    private void removeAtttendance(ActionEvent e) {
        editAttendance.setVisible(true);
    }

    private void initComponents() throws SQLException {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar = new JMenuBar();
        fileMenu = new JMenu();
        logoutMenuItem = new JMenuItem();
        closeMenuItem = new JMenuItem();
        studentMenu = new JMenu();
        addStudentMenuItem = new JMenuItem();
        editStudentMenuItem = new JMenuItem();
        viewStudentMenuItem = new JMenuItem();
        menuItem1 = new JMenuItem();
        attendanceMenu = new JMenu();
        addAttendanceMenuItem = new JMenuItem();
        menuItem2 = new JMenuItem();
        viewAttendanceMenuItem = new JMenuItem();
        menuItem3 = new JMenuItem();
        examMenu = new JMenu();
        registerExamMenuItem = new JMenuItem();
        helpMenu = new JMenu();
        aboutMenuItem = new JMenuItem();
        hSpacer = new JPanel(null);
        contentPanel = new JPanel();
        toolBar1 = new JToolBar();
        addStudentButton = new JButton();
        editStudentButton = new JButton();
        viewStudentButton = new JButton();
        removeStudentButton = new JButton();
        addAttendanceButton = new JButton();
        editAttendanceButton = new JButton();
        viewAttendanceButton = new JButton();
        removeAtttendanceButton = new JButton();
        examRegisterButton = new JButton();
        searchButton = new JButton();
        infoButton = new JButton();
        exitButton = new JButton();

        //======== this ========
        setTitle("Classroom Management System");
        setIconImage(new ImageIcon(getClass().getResource("/com/images/cmsLogo.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== menuBar ========
        {

            //======== fileMenu ========
            {
                fileMenu.setText("File");

                //---- logoutMenuItem ----
                logoutMenuItem.setText("Log Out");
                logoutMenuItem.addActionListener(e -> logoutMenuItem(e));
                fileMenu.add(logoutMenuItem);

                //---- closeMenuItem ----
                closeMenuItem.setText("Close");
                closeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_DOWN_MASK));
                closeMenuItem.addActionListener(e -> closeMenuItem(e));
                fileMenu.add(closeMenuItem);
            }
            menuBar.add(fileMenu);

            //======== studentMenu ========
            {
                studentMenu.setText("Student");

                //---- addStudentMenuItem ----
                addStudentMenuItem.setText("Add Student");
                addStudentMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
                addStudentMenuItem.addActionListener(e -> registerStudent(e));
                studentMenu.add(addStudentMenuItem);

                //---- editStudentMenuItem ----
                editStudentMenuItem.setText("Edit Student");
                editStudentMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK));
                editStudentMenuItem.addActionListener(e -> editStudent(e));
                studentMenu.add(editStudentMenuItem);

                //---- viewStudentMenuItem ----
                viewStudentMenuItem.setText("View Student");
                viewStudentMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
                viewStudentMenuItem.addActionListener(e -> viewStudent(e));
                studentMenu.add(viewStudentMenuItem);

                //---- menuItem1 ----
                menuItem1.setText("Search Student");
                menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
                menuItem1.addActionListener(e -> searchStudent(e));
                studentMenu.add(menuItem1);
            }
            menuBar.add(studentMenu);

            //======== attendanceMenu ========
            {
                attendanceMenu.setText("Attendance");

                //---- addAttendanceMenuItem ----
                addAttendanceMenuItem.setText("Add Attendance");
                addAttendanceMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.ALT_DOWN_MASK));
                addAttendanceMenuItem.addActionListener(e -> addAttendance(e));
                attendanceMenu.add(addAttendanceMenuItem);

                //---- menuItem2 ----
                menuItem2.setText("Edit Attendance");
                menuItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.ALT_DOWN_MASK));
                menuItem2.addActionListener(e -> editAttendance(e));
                attendanceMenu.add(menuItem2);

                //---- viewAttendanceMenuItem ----
                viewAttendanceMenuItem.setText("View Attendance");
                viewAttendanceMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.ALT_DOWN_MASK));
                viewAttendanceMenuItem.addActionListener(e -> viewAttendance(e));
                attendanceMenu.add(viewAttendanceMenuItem);

                //---- menuItem3 ----
                menuItem3.setText("Search Attendance");
                menuItem3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.ALT_DOWN_MASK));
                menuItem3.addActionListener(e -> searchAttendance(e));
                attendanceMenu.add(menuItem3);
            }
            menuBar.add(attendanceMenu);

            //======== examMenu ========
            {
                examMenu.setText("Exam");

                //---- registerExamMenuItem ----
                registerExamMenuItem.setText("Register Exam");
                registerExamMenuItem.addActionListener(e -> registerExam(e));
                examMenu.add(registerExamMenuItem);
            }
            menuBar.add(examMenu);

            //======== helpMenu ========
            {
                helpMenu.setText("Help");

                //---- aboutMenuItem ----
                aboutMenuItem.setText("About");
                aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_DOWN_MASK));
                aboutMenuItem.addActionListener(e -> aboutMenuItem(e));
                helpMenu.add(aboutMenuItem);
            }
            menuBar.add(helpMenu);
            menuBar.add(hSpacer);
        }
        setJMenuBar(menuBar);

        //======== contentPanel ========
        {
            contentPanel.setLayout(null);

            //======== toolBar1 ========
            {

                //---- addStudentButton ----
                addStudentButton.setIcon(new ImageIcon(getClass().getResource("/com/images/addStudent1.png")));
                addStudentButton.setToolTipText("Add Student");
                addStudentButton.addActionListener(e -> addStudentButton(e));
                toolBar1.add(addStudentButton);

                //---- editStudentButton ----
                editStudentButton.setIcon(new ImageIcon(getClass().getResource("/com/images/editStudent.png")));
                editStudentButton.setToolTipText("Edit Student");
                editStudentButton.addActionListener(e -> editStudentButton(e));
                toolBar1.add(editStudentButton);

                //---- viewStudentButton ----
                viewStudentButton.setIcon(new ImageIcon(getClass().getResource("/com/images/viewStudent.png")));
                viewStudentButton.setToolTipText("View Student");
                viewStudentButton.addActionListener(e -> viewStudentButton(e));
                toolBar1.add(viewStudentButton);

                //---- removeStudentButton ----
                removeStudentButton.setIcon(new ImageIcon(getClass().getResource("/com/images/trash.png")));
                removeStudentButton.setToolTipText("Delete Student");
                removeStudentButton.addActionListener(e -> removeStudent(e));
                toolBar1.add(removeStudentButton);
                toolBar1.addSeparator();

                //---- addAttendanceButton ----
                addAttendanceButton.setIcon(new ImageIcon(getClass().getResource("/com/images/addAttendance1.png")));
                addAttendanceButton.setToolTipText("Add Attendance");
                addAttendanceButton.addActionListener(e -> addAttendanceButton(e));
                toolBar1.add(addAttendanceButton);

                //---- editAttendanceButton ----
                editAttendanceButton.setIcon(new ImageIcon(getClass().getResource("/com/images/editAttendance1.png")));
                editAttendanceButton.setToolTipText("Edit Attendance");
                editAttendanceButton.addActionListener(e -> editAttendanceButton(e));
                toolBar1.add(editAttendanceButton);

                //---- viewAttendanceButton ----
                viewAttendanceButton.setIcon(new ImageIcon(getClass().getResource("/com/images/viewAttendance1.png")));
                viewAttendanceButton.setToolTipText("View Attendance");
                viewAttendanceButton.addActionListener(e -> viewAttendanceButton(e));
                toolBar1.add(viewAttendanceButton);

                //---- removeAtttendanceButton ----
                removeAtttendanceButton.setIcon(new ImageIcon(getClass().getResource("/com/images/trash.png")));
                removeAtttendanceButton.setToolTipText("Delete Attendance");
                removeAtttendanceButton.addActionListener(e -> removeAtttendance(e));
                toolBar1.add(removeAtttendanceButton);
                toolBar1.addSeparator();

                //---- examRegisterButton ----
                examRegisterButton.setIcon(new ImageIcon(getClass().getResource("/com/images/list.png")));
                examRegisterButton.setToolTipText("Register Exam");
                examRegisterButton.addActionListener(e -> examRegister(e));
                toolBar1.add(examRegisterButton);
                toolBar1.addSeparator();

                //---- searchButton ----
                searchButton.setIcon(new ImageIcon(getClass().getResource("/com/images/search.png")));
                searchButton.setToolTipText("Search");
                searchButton.addActionListener(e -> search(e));
                toolBar1.add(searchButton);

                //---- infoButton ----
                infoButton.setIcon(new ImageIcon(getClass().getResource("/com/images/info.png")));
                infoButton.setToolTipText("About");
                infoButton.addActionListener(e -> infoButton(e));
                toolBar1.add(infoButton);

                //---- exitButton ----
                exitButton.setIcon(new ImageIcon(getClass().getResource("/com/images/exitButton.png")));
                exitButton.setToolTipText("Exit");
                exitButton.addActionListener(e -> exitButton(e));
                toolBar1.add(exitButton);
            }
            contentPanel.add(toolBar1);
            toolBar1.setBounds(new Rectangle(new Point(0, 0), toolBar1.getPreferredSize()));

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
        contentPanel.setBounds(0, 0, 440, 265);

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
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem logoutMenuItem;
    private JMenuItem closeMenuItem;
    private JMenu studentMenu;
    private JMenuItem addStudentMenuItem;
    private JMenuItem editStudentMenuItem;
    private JMenuItem viewStudentMenuItem;
    private JMenuItem menuItem1;
    private JMenu attendanceMenu;
    private JMenuItem addAttendanceMenuItem;
    private JMenuItem menuItem2;
    private JMenuItem viewAttendanceMenuItem;
    private JMenuItem menuItem3;
    private JMenu examMenu;
    private JMenuItem registerExamMenuItem;
    private JMenu helpMenu;
    private JMenuItem aboutMenuItem;
    private JPanel hSpacer;
    private JPanel contentPanel;
    private JToolBar toolBar1;
    private JButton addStudentButton;
    private JButton editStudentButton;
    private JButton viewStudentButton;
    private JButton removeStudentButton;
    private JButton addAttendanceButton;
    private JButton editAttendanceButton;
    private JButton viewAttendanceButton;
    private JButton removeAtttendanceButton;
    private JButton examRegisterButton;
    private JButton searchButton;
    private JButton infoButton;
    private JButton exitButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
