/*
 * Created by JFormDesigner on Sat Jan 01 15:32:48 ICT 2022
 */

package com.company;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.*;
import net.miginfocom.swing.*;

/**
 * @author Xuan Minh Nguyen
 */
public class About extends JFrame {
    public About() {
        initComponents();
    }

    private void cancel(ActionEvent e) {
        setVisible(false);
    }

    private static void open(URI uri) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(uri);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    private void help(ActionEvent e) throws URISyntaxException {
        final URI url = new URI("https://docs.google.com/document/d/10Plshsw-PM4E9ssOqAgAB6wm6Nn5yWGK/edit?usp=sharing&ouid=108012000234673193060&rtpof=true&sd=true");
        open(url);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        contentPanel = new JPanel();
        label2 = new JLabel();
        infoLabel = new JLabel();
        logoLabel = new JLabel();
        okButton = new JButton();
        okButton2 = new JButton();
        backgroundLabel = new JLabel();

        //======== this ========
        setTitle("About CMS");
        setIconImage(new ImageIcon(getClass().getResource("/com/images/cmsLogo.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== contentPanel ========
        {
            contentPanel.setLayout(null);

            //---- label2 ----
            label2.setText("A Java OOP module Project");
            label2.setForeground(Color.white);
            contentPanel.add(label2);
            label2.setBounds(new Rectangle(new Point(135, 85), label2.getPreferredSize()));

            //---- infoLabel ----
            infoLabel.setText("Classroom Management System");
            infoLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
            infoLabel.setForeground(Color.white);
            contentPanel.add(infoLabel);
            infoLabel.setBounds(135, 45, 240, infoLabel.getPreferredSize().height);

            //---- logoLabel ----
            logoLabel.setIcon(new ImageIcon(getClass().getResource("/com/images/logoLabel.png")));
            contentPanel.add(logoLabel);
            logoLabel.setBounds(new Rectangle(new Point(40, 55), logoLabel.getPreferredSize()));

            //---- okButton ----
            okButton.setText("OK");
            okButton.addActionListener(e -> cancel(e));
            contentPanel.add(okButton);
            okButton.setBounds(295, 190, 68, okButton.getPreferredSize().height);

            //---- okButton2 ----
            okButton2.setText("Help");
            okButton2.addActionListener(e -> {
                try {
                    help(e);
                } catch (URISyntaxException ex) {
                    ex.printStackTrace();
                }
            });
            contentPanel.add(okButton2);
            okButton2.setBounds(205, 190, 68, okButton2.getPreferredSize().height);

            //---- backgroundLabel ----
            backgroundLabel.setText("text");
            backgroundLabel.setIcon(new ImageIcon(getClass().getResource("/com/images/background.png")));
            contentPanel.add(backgroundLabel);
            backgroundLabel.setBounds(0, 0, 390, 245);

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
        contentPane.add(contentPanel, BorderLayout.NORTH);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        pack();
        setLocationRelativeTo(null);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel contentPanel;
    private JLabel label2;
    private JLabel infoLabel;
    private JLabel logoLabel;
    private JButton okButton;
    private JButton okButton2;
    private JLabel backgroundLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
