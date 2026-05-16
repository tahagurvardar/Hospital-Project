package ui;

import dao.AppointmentDAO;
import dao.DoctorDAO;
import dao.PatientDAO;
import util.LanguageManager;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {

    private PatientDAO patientDAO = new PatientDAO();
    private DoctorDAO doctorDAO = new DoctorDAO();
    private AppointmentDAO appointmentDAO = new AppointmentDAO();

    private JLabel patientCountLabel;
    private JLabel doctorCountLabel;
    private JLabel appointmentCountLabel;

    private JPanel chartContainer;

    public DashboardPanel() {

        setLayout(new BorderLayout(20, 20));

        JLabel titleLabel = new JLabel(LanguageManager.get("dashboard_title"), JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));

        add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout(20, 20));

        JPanel cardPanel = new JPanel(new GridLayout(1, 3, 20, 20));
        cardPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 20, 40));

        patientCountLabel = createCard(LanguageManager.get("total_patients"), "0");
        doctorCountLabel = createCard(LanguageManager.get("total_doctors"), "0");
        appointmentCountLabel = createCard(LanguageManager.get("total_appointments"), "0");

        cardPanel.add(patientCountLabel);
        cardPanel.add(doctorCountLabel);
        cardPanel.add(appointmentCountLabel);

        centerPanel.add(cardPanel, BorderLayout.NORTH);

        chartContainer = new JPanel(new BorderLayout());
        chartContainer.setBorder(BorderFactory.createEmptyBorder(10, 40, 20, 40));

        centerPanel.add(chartContainer, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        JButton refreshButton = new JButton(LanguageManager.get("refresh_dashboard"));

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
                "<html><center>" + LanguageManager.get("total_patients") + "<br><br><b style='font-size:28px;'>" + patientCount + "</b></center></html>"
        );

        doctorCountLabel.setText(
                "<html><center>" + LanguageManager.get("total_doctors") + "<br><br><b style='font-size:28px;'>" + doctorCount + "</b></center></html>"
        );

        appointmentCountLabel.setText(
                "<html><center>" + LanguageManager.get("total_appointments") + "<br><br><b style='font-size:28px;'>" + appointmentCount + "</b></center></html>"
        );

        loadChart(patientCount, doctorCount, appointmentCount);
    }

    private void loadChart(int patientCount, int doctorCount, int appointmentCount) {

        chartContainer.removeAll();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(patientCount, LanguageManager.get("count"), LanguageManager.get("patients"));
        dataset.addValue(doctorCount, LanguageManager.get("count"), LanguageManager.get("doctors"));
        dataset.addValue(appointmentCount, LanguageManager.get("count"), LanguageManager.get("appointments"));

        JFreeChart chart = ChartFactory.createBarChart(
                LanguageManager.get("records_chart"),
                LanguageManager.get("category"),
                LanguageManager.get("total_count"),
                dataset
        );

        ChartPanel chartPanel = new ChartPanel(chart);

        chartContainer.add(chartPanel, BorderLayout.CENTER);

        chartContainer.revalidate();
        chartContainer.repaint();
    }
}