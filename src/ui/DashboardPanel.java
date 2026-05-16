package ui;

import dao.AppointmentDAO;
import dao.DoctorDAO;
import dao.PatientDAO;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {

    private PatientDAO patientDAO = new PatientDAO();
    private DoctorDAO doctorDAO = new DoctorDAO();
    private AppointmentDAO appointmentDAO = new AppointmentDAO();

    private JLabel patientCountLabel;
    private JLabel doctorCountLabel;
    private JLabel appointmentCountLabel;

    public DashboardPanel() {

        setLayout(new BorderLayout(20, 20));

        JLabel titleLabel = new JLabel("Hospital Dashboard", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));

        add(titleLabel, BorderLayout.NORTH);

        JPanel cardPanel = new JPanel(new GridLayout(1, 3, 20, 20));
        cardPanel.setBorder(BorderFactory.createEmptyBorder(60, 40, 60, 40));

        patientCountLabel = createCard("Total Patients", "0");
        doctorCountLabel = createCard("Total Doctors", "0");
        appointmentCountLabel = createCard("Total Appointments", "0");

        cardPanel.add(patientCountLabel);
        cardPanel.add(doctorCountLabel);
        cardPanel.add(appointmentCountLabel);

        add(cardPanel, BorderLayout.CENTER);

        JButton refreshButton = new JButton("Refresh Dashboard");

        refreshButton.addActionListener(e -> loadDashboard());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(refreshButton);

        add(bottomPanel, BorderLayout.SOUTH);

        loadDashboard();
    }

    private JLabel createCard(String title, String value) {

        JLabel label = new JLabel(
                "<html><center>" + title + "<br><br><b style='font-size:28px;'>" + value + "</b></center></html>",
                JLabel.CENTER
        );

        label.setFont(new Font("Arial", Font.PLAIN, 20));
        label.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        label.setOpaque(true);
        label.setBackground(new Color(245, 245, 245));

        return label;
    }

    private void loadDashboard() {

        int patientCount = patientDAO.getPatientCount();
        int doctorCount = doctorDAO.getDoctorCount();
        int appointmentCount = appointmentDAO.getAppointmentCount();

        patientCountLabel.setText(
                "<html><center>Total Patients<br><br><b style='font-size:28px;'>" + patientCount + "</b></center></html>"
        );

        doctorCountLabel.setText(
                "<html><center>Total Doctors<br><br><b style='font-size:28px;'>" + doctorCount + "</b></center></html>"
        );

        appointmentCountLabel.setText(
                "<html><center>Total Appointments<br><br><b style='font-size:28px;'>" + appointmentCount + "</b></center></html>"
        );
    }
}