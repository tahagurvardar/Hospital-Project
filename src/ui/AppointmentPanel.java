package ui;

import dao.AppointmentDAO;
import model.Appointment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class AppointmentPanel extends JPanel {

    private JTextField patientNameField;
    private JTextField doctorNameField;
    private JTextField dateField;
    private JTextField timeField;
    private JComboBox<String> statusBox;

    private JTable appointmentTable;
    private DefaultTableModel tableModel;

    private AppointmentDAO appointmentDAO = new AppointmentDAO();

    public AppointmentPanel() {

        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Appointment Form"));

        formPanel.add(new JLabel("Patient Name:"));
        patientNameField = new JTextField();
        formPanel.add(patientNameField);

        formPanel.add(new JLabel("Doctor Name:"));
        doctorNameField = new JTextField();
        formPanel.add(doctorNameField);

        formPanel.add(new JLabel("Date:"));
        dateField = new JTextField("2026-05-16");
        formPanel.add(dateField);

        formPanel.add(new JLabel("Time:"));
        timeField = new JTextField("14:00");
        formPanel.add(timeField);

        formPanel.add(new JLabel("Status:"));
        statusBox = new JComboBox<>(new String[]{"Scheduled", "Completed", "Cancelled"});
        formPanel.add(statusBox);

        JButton addButton = new JButton("Add Appointment");
        JButton clearButton = new JButton("Clear");

        formPanel.add(addButton);
        formPanel.add(clearButton);

        add(formPanel, BorderLayout.WEST);

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{
                "ID", "Patient", "Doctor", "Date", "Time", "Status"
        });

        appointmentTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(appointmentTable);

        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton refreshButton = new JButton("Refresh");
        JButton deleteButton = new JButton("Delete Selected");

        buttonPanel.add(refreshButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        loadAppointments();

        addButton.addActionListener(e -> addAppointment());
        clearButton.addActionListener(e -> clearFields());
        refreshButton.addActionListener(e -> loadAppointments());
        deleteButton.addActionListener(e -> deleteSelectedAppointment());
    }

    private void addAppointment() {

        try {

            String patientName = patientNameField.getText();
            String doctorName = doctorNameField.getText();
            String date = dateField.getText();
            String time = timeField.getText();
            String status = statusBox.getSelectedItem().toString();

            boolean conflict = appointmentDAO.isAppointmentConflict(
                    doctorName,
                    date,
                    time
            );

            if (conflict) {

                JOptionPane.showMessageDialog(
                        this,
                        "This doctor already has an appointment at this date and time!"
                );

                return;
            }

            Appointment appointment = new Appointment(
                    patientName,
                    doctorName,
                    date,
                    time,
                    status
            );

            appointmentDAO.addAppointment(appointment);

            JOptionPane.showMessageDialog(this, "Appointment added successfully!");

            clearFields();
            loadAppointments();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "Please enter valid appointment information.");
        }
    }

    private void loadAppointments() {

        tableModel.setRowCount(0);

        ArrayList<Appointment> appointments = appointmentDAO.getAppointments();

        for (Appointment appointment : appointments) {

            tableModel.addRow(new Object[]{
                    appointment.getId(),
                    appointment.getPatientName(),
                    appointment.getDoctorName(),
                    appointment.getDate(),
                    appointment.getTime(),
                    appointment.getStatus()
            });
        }
    }

    private void deleteSelectedAppointment() {

        int selectedRow = appointmentTable.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(this, "Please select an appointment from the table.");
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);

        appointmentDAO.deleteAppointment(id);

        JOptionPane.showMessageDialog(this, "Appointment deleted successfully!");

        loadAppointments();
    }

    private void clearFields() {

        patientNameField.setText("");
        doctorNameField.setText("");
        dateField.setText("2026-05-16");
        timeField.setText("14:00");
        statusBox.setSelectedIndex(0);
        appointmentTable.clearSelection();
    }
}