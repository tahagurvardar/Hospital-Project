package ui;

import dao.PatientDAO;
import model.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PatientPanel extends JPanel {

    private JTextField nameField;
    private JTextField ageField;
    private JTextField addressField;
    private JTextField paymentField;
    private JTextField searchField;

    private JTable patientTable;
    private DefaultTableModel tableModel;

    private PatientDAO patientDAO = new PatientDAO();

    public PatientPanel() {

        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Patient Management", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        topPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new FlowLayout());

        searchPanel.add(new JLabel("Search:"));
        searchField = new JTextField(20);
        searchPanel.add(searchField);

        JButton searchButton = new JButton("Search");
        JButton showAllButton = new JButton("Show All");

        searchPanel.add(searchButton);
        searchPanel.add(showAllButton);

        topPanel.add(searchPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Patient Form"));

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        formPanel.add(ageField);

        formPanel.add(new JLabel("Address:"));
        addressField = new JTextField();
        formPanel.add(addressField);

        formPanel.add(new JLabel("Payment:"));
        paymentField = new JTextField();
        formPanel.add(paymentField);

        JButton addButton = new JButton("Add Patient");
        JButton clearButton = new JButton("Clear");

        formPanel.add(addButton);
        formPanel.add(clearButton);

        add(formPanel, BorderLayout.WEST);

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"ID", "Name", "Age", "Address", "Payment"});

        patientTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(patientTable);

        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton refreshButton = new JButton("Refresh");
        JButton updateButton = new JButton("Update Selected");
        JButton deleteButton = new JButton("Delete Selected");

        buttonPanel.add(refreshButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        loadPatients();

        addButton.addActionListener(e -> addPatient());
        clearButton.addActionListener(e -> clearFields());
        refreshButton.addActionListener(e -> loadPatients());
        updateButton.addActionListener(e -> updateSelectedPatient());
        deleteButton.addActionListener(e -> deleteSelectedPatient());
        searchButton.addActionListener(e -> searchPatients());
        showAllButton.addActionListener(e -> loadPatients());

        patientTable.getSelectionModel().addListSelectionListener(e -> fillFieldsFromSelectedRow());
    }

    private void addPatient() {

        try {

            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String address = addressField.getText();
            double payment = Double.parseDouble(paymentField.getText());

            Patient patient = new Patient(name, age, address, payment);

            patientDAO.addPatient(patient);

            JOptionPane.showMessageDialog(this, "Patient added successfully!");

            clearFields();
            loadPatients();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "Please enter valid patient information.");
        }
    }

    private void loadPatients() {

        tableModel.setRowCount(0);

        ArrayList<Patient> patients = patientDAO.getPatientsList();

        for (Patient patient : patients) {

            tableModel.addRow(new Object[]{
                    patient.getId(),
                    patient.getName(),
                    patient.getAge(),
                    patient.getAddress(),
                    patient.getPayment()
            });
        }
    }

    private void searchPatients() {

        String keyword = searchField.getText();

        tableModel.setRowCount(0);

        ArrayList<Patient> patients = patientDAO.searchPatients(keyword);

        for (Patient patient : patients) {

            tableModel.addRow(new Object[]{
                    patient.getId(),
                    patient.getName(),
                    patient.getAge(),
                    patient.getAddress(),
                    patient.getPayment()
            });
        }
    }

    private void updateSelectedPatient() {

        int selectedRow = patientTable.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(this, "Please select a patient from the table.");
            return;
        }

        try {

            int id = (int) tableModel.getValueAt(selectedRow, 0);

            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String address = addressField.getText();
            double payment = Double.parseDouble(paymentField.getText());

            Patient patient = new Patient(id, name, age, address, payment);

            patientDAO.updatePatient(patient);

            JOptionPane.showMessageDialog(this, "Patient updated successfully!");

            clearFields();
            loadPatients();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "Please enter valid patient information.");
        }
    }

    private void deleteSelectedPatient() {

        int selectedRow = patientTable.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(this, "Please select a patient from the table.");
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);

        patientDAO.deletePatient(id);

        JOptionPane.showMessageDialog(this, "Patient deleted successfully!");

        clearFields();
        loadPatients();
    }

    private void fillFieldsFromSelectedRow() {

        int selectedRow = patientTable.getSelectedRow();

        if (selectedRow != -1) {

            nameField.setText(tableModel.getValueAt(selectedRow, 1).toString());
            ageField.setText(tableModel.getValueAt(selectedRow, 2).toString());
            addressField.setText(tableModel.getValueAt(selectedRow, 3).toString());
            paymentField.setText(tableModel.getValueAt(selectedRow, 4).toString());
        }
    }

    private void clearFields() {

        nameField.setText("");
        ageField.setText("");
        addressField.setText("");
        paymentField.setText("");
        patientTable.clearSelection();
    }
}