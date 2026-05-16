package ui;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import util.LanguageManager;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame(String role) {

        setTitle(LanguageManager.get("app_title") + " - " + role);
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton themeButton = new JButton(LanguageManager.get("theme"));

        themeButton.addActionListener(e -> {

            try {

                if (UIManager.getLookAndFeel().getName().contains("Dark")) {

                    UIManager.setLookAndFeel(new FlatLightLaf());

                } else {

                    UIManager.setLookAndFeel(new FlatDarkLaf());
                }

                SwingUtilities.updateComponentTreeUI(this);

            } catch (Exception ex) {

                System.out.println(ex.getMessage());
            }
        });

        topPanel.add(themeButton);

        JTabbedPane tabs = new JTabbedPane();

        if (role.equals("Admin")) {

            tabs.addTab(LanguageManager.get("dashboard"), new DashboardPanel());
            tabs.addTab(LanguageManager.get("patients"), new PatientPanel());
            tabs.addTab(LanguageManager.get("doctors"), new DoctorPanel());
            tabs.addTab(LanguageManager.get("appointments"), new AppointmentPanel());

        } else if (role.equals("Doctor")) {

            tabs.addTab(LanguageManager.get("dashboard"), new DashboardPanel());
            tabs.addTab(LanguageManager.get("appointments"), new AppointmentPanel());

        } else if (role.equals("Receptionist")) {

            tabs.addTab(LanguageManager.get("dashboard"), new DashboardPanel());
            tabs.addTab(LanguageManager.get("patients"), new PatientPanel());
            tabs.addTab(LanguageManager.get("appointments"), new AppointmentPanel());
        }

        add(topPanel, BorderLayout.NORTH);
        add(tabs, BorderLayout.CENTER);
    }
}