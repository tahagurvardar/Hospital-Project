package ui;

import util.ReportGenerator;
import dao.AppointmentDAO;
import model.Appointment;
import org.jdatepicker.impl.*;
import java.util.Properties;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class AppointmentPanel extends JPanel {

    private JTextField patientNameField;
    private JTextField doctorNameField;
    private JDatePickerImpl datePicker;
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

        Properties p = new Properties();

        p.put("text.today","Today");
        p.put("text.month","Month");
        p.put("text.year","Year");

        UtilDateModel model = new UtilDateModel();

        JDatePanelImpl datePanel = new JDatePanelImpl(model,p);

        datePicker = new JDatePickerImpl(
                datePanel,
                new DateLabelFormatter()
        );

        formPanel.add(datePicker);

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
        JButton updateButton = new JButton("Update Selected");
        JButton deleteButton = new JButton("Delete Selected");
        JButton exportButton = new JButton("Export PDF");
        JButton emailButton = new JButton("Email Preview");

        buttonPanel.add(refreshButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(exportButton);
        buttonPanel.add(emailButton);

        add(buttonPanel, BorderLayout.SOUTH);

        loadAppointments();

        addButton.addActionListener(e -> addAppointment());
        clearButton.addActionListener(e -> clearFields());
        refreshButton.addActionListener(e -> loadAppointments());
        updateButton.addActionListener(e -> updateSelectedAppointment());
        deleteButton.addActionListener(e -> deleteSelectedAppointment());
        exportButton.addActionListener(e -> exportPDF());
        emailButton.addActionListener(e -> emailPreview());


        appointmentTable.getSelectionModel().addListSelectionListener(
                e -> fillFieldsFromSelectedRow()
        );
    }

    private void addAppointment() {

        try {

            String patientName = patientNameField.getText();
            String doctorName = doctorNameField.getText();
            String date = datePicker.getJFormattedTextField().getText();
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

    private void updateSelectedAppointment() {

        int selectedRow = appointmentTable.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(this, "Please select an appointment from the table.");
            return;
        }

        try {

            int id = (int) tableModel.getValueAt(selectedRow, 0);

            String patientName = patientNameField.getText();
            String doctorName = doctorNameField.getText();
            String date = datePicker.getJFormattedTextField().getText();
            String time = timeField.getText();
            String status = statusBox.getSelectedItem().toString();

            Appointment appointment = new Appointment(
                    id,
                    patientName,
                    doctorName,
                    date,
                    time,
                    status
            );

            appointmentDAO.updateAppointment(appointment);

            JOptionPane.showMessageDialog(this, "Appointment updated successfully!");

            clearFields();
            loadAppointments();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "Please enter valid appointment information.");
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

        clearFields();
        loadAppointments();
    }

    private void fillFieldsFromSelectedRow() {

        int selectedRow = appointmentTable.getSelectedRow();

        if (selectedRow != -1) {

            patientNameField.setText(tableModel.getValueAt(selectedRow, 1).toString());
            doctorNameField.setText(tableModel.getValueAt(selectedRow, 2).toString());
            datePicker.getJFormattedTextField().setText(tableModel.getValueAt(selectedRow, 3).toString());
            timeField.setText(tableModel.getValueAt(selectedRow, 4).toString());
            statusBox.setSelectedItem(tableModel.getValueAt(selectedRow, 5).toString());
        }
    }

    private void clearFields() {

        patientNameField.setText("");
        doctorNameField.setText("");
        datePicker.getJFormattedTextField().setText("");
        timeField.setText("14:00");
        statusBox.setSelectedIndex(0);
        appointmentTable.clearSelection();
    }
    private void exportPDF() {

        ArrayList<Appointment> appointments = appointmentDAO.getAppointments();

        ReportGenerator.exportAppointmentsToPDF(appointments);

        JOptionPane.showMessageDialog(this, "PDF report created successfully!");
    }
    private void emailPreview() {

        int selectedRow = appointmentTable.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(this, "Please select an appointment from the table.");
            return;
        }

        String patientName = tableModel.getValueAt(selectedRow, 1).toString();
        String doctorName = tableModel.getValueAt(selectedRow, 2).toString();
        String date = tableModel.getValueAt(selectedRow, 3).toString();
        String time = tableModel.getValueAt(selectedRow, 4).toString();
        String status = tableModel.getValueAt(selectedRow, 5).toString();

        String message =
                "Subject: Appointment Notification\n\n" +
                        "Dear " + patientName + ",\n\n" +
                        "Your hospital appointment details are below:\n\n" +
                        "Doctor: " + doctorName + "\n" +
                        "Date: " + date + "\n" +
                        "Time: " + time + "\n" +
                        "Status: " + status + "\n\n" +
                        "Please be at the hospital on time.\n\n" +
                        "Best regards,\n" +
                        "Hospital Management System";

        JTextArea textArea = new JTextArea(message);
        textArea.setEditable(false);
        textArea.setRows(12);
        textArea.setColumns(40);

        JOptionPane.showMessageDialog(
                this,
                new JScrollPane(textArea),
                "Email Preview",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}