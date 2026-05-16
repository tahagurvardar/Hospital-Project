package ui;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() {

        setTitle(
                "Hospital Management System"
        );

        setSize(
                1000,
                600
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        JTabbedPane tabs =
                new JTabbedPane();

        tabs.addTab(
                "Dashboard",
                new DashboardPanel()
        );

        tabs.addTab(
                "Patients",
                new PatientPanel()
        );

        tabs.addTab(
                "Doctors",
                new DoctorPanel()
        );

        add(tabs);

        tabs.addTab(
                "Appointments",
                new AppointmentPanel()
        );
    }
}