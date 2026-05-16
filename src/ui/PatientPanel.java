package ui;

import dao.PatientDAO;
import model.Patient;
import util.LanguageManager;

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

        JLabel titleLabel = new JLabel(LanguageManager.get("patient_management"), JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        topPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new FlowLayout());

        searchPanel.add(new JLabel(LanguageManager.get("search")));
        searchField = new JTextField(20);
        searchPanel.add(searchField);

        JButton searchButton = new JButton(LanguageManager.get("search"));
        JButton showAllButton = new JButton(LanguageManager.get("show_all"));

        searchPanel.add(searchButton);
        searchPanel.add(showAllButton);

        topPanel.add(searchPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder(LanguageManager.get("patient_form")));

        formPanel.add(new JLabel(LanguageManager.get("name")));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel(LanguageManager.get("age")));
        ageField = new JTextField();
        formPanel.add(ageField);

        formPanel.add(new JLabel(LanguageManager.get("address")));
        addressField = new JTextField();
        formPanel.add(addressField);

        formPanel.add(new JLabel(LanguageManager.get("payment")));
        paymentField = new JTextField();
        formPanel.add(paymentField);

        JButton addButton = new JButton(LanguageManager.get("add_patient"));
        JButton clearButton = new JButton(LanguageManager.get("clear"));

        formPanel.add(addButton);
        formPanel.add(clearButton);

        add(formPanel, BorderLayout.WEST);

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{
                "ID",
                LanguageManager.get("name").replace(":", ""),
                LanguageManager.get("age").replace(":", ""),
                LanguageManager.get("address").replace(":", ""),
                LanguageManager.get("payment").replace(":", "")
        });

        patientTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(patientTable);

        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton refreshButton = new JButton(LanguageManager.get("refresh"));
        JButton updateButton = new JButton(LanguageManager.get("update_selected"));
        JButton deleteButton = new JButton(LanguageManager.get("delete_selected"));

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

            JOptionPane.showMessageDialog(this, LanguageManager.get("patient_added"));

            clearFields();
            loadPatients();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, LanguageManager.get("invalid_patient"));
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

            JOptionPane.showMessageDialog(this, LanguageManager.get("select_patient"));
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

            JOptionPane.showMessageDialog(this, LanguageManager.get("patient_updated"));

            clearFields();
            loadPatients();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, LanguageManager.get("invalid_patient"));
        }
    }

    private void deleteSelectedPatient() {

        int selectedRow = patientTable.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(this, LanguageManager.get("select_patient"));
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);

        patientDAO.deletePatient(id);

        JOptionPane.showMessageDialog(this, LanguageManager.get("patient_deleted"));

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