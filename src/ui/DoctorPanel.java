package ui;

import dao.DoctorDAO;
import model.Doctor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class DoctorPanel extends JPanel {

    private JTextField nameField;
    private JTextField specializationField;
    private JTextField salaryField;

    private JTable doctorTable;
    private DefaultTableModel tableModel;

    private DoctorDAO doctorDAO = new DoctorDAO();

    public DoctorPanel() {

        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Doctor Form"));

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Specialization:"));
        specializationField = new JTextField();
        formPanel.add(specializationField);

        formPanel.add(new JLabel("Salary:"));
        salaryField = new JTextField();
        formPanel.add(salaryField);

        JButton addButton = new JButton("Add Doctor");
        JButton clearButton = new JButton("Clear");

        formPanel.add(addButton);
        formPanel.add(clearButton);

        add(formPanel, BorderLayout.WEST);

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"ID", "Name", "Specialization", "Salary"});

        doctorTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(doctorTable);

        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton refreshButton = new JButton("Refresh");
        JButton updateButton = new JButton("Update Selected");
        JButton deleteButton = new JButton("Delete Selected");

        buttonPanel.add(refreshButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        loadDoctors();

        addButton.addActionListener(e -> addDoctor());
        clearButton.addActionListener(e -> clearFields());
        refreshButton.addActionListener(e -> loadDoctors());
        updateButton.addActionListener(e -> updateSelectedDoctor());
        deleteButton.addActionListener(e -> deleteSelectedDoctor());

        doctorTable.getSelectionModel().addListSelectionListener(e -> fillFieldsFromSelectedRow());
    }

    private void addDoctor() {

        try {

            String name = nameField.getText();
            String specialization = specializationField.getText();
            double salary = Double.parseDouble(salaryField.getText());

            Doctor doctor = new Doctor(name, specialization, salary);

            doctorDAO.addDoctor(doctor);

            JOptionPane.showMessageDialog(this, "Doctor added successfully!");

            clearFields();
            loadDoctors();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "Please enter valid doctor information.");
        }
    }

    private void loadDoctors() {

        tableModel.setRowCount(0);

        ArrayList<Doctor> doctors = doctorDAO.getDoctors();

        for (Doctor doctor : doctors) {

            tableModel.addRow(new Object[]{
                    doctor.getId(),
                    doctor.getName(),
                    doctor.getSpecialization(),
                    doctor.getSalary()
            });
        }
    }

    private void updateSelectedDoctor() {

        int selectedRow = doctorTable.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(this, "Please select a doctor from the table.");
            return;
        }

        try {

            int id = (int) tableModel.getValueAt(selectedRow, 0);

            String name = nameField.getText();
            String specialization = specializationField.getText();
            double salary = Double.parseDouble(salaryField.getText());

            Doctor doctor = new Doctor(id, name, specialization, salary);

            doctorDAO.updateDoctor(doctor);

            JOptionPane.showMessageDialog(this, "Doctor updated successfully!");

            clearFields();
            loadDoctors();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "Please enter valid doctor information.");
        }
    }

    private void deleteSelectedDoctor() {

        int selectedRow = doctorTable.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(this, "Please select a doctor from the table.");
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);

        doctorDAO.deleteDoctor(id);

        JOptionPane.showMessageDialog(this, "Doctor deleted successfully!");

        clearFields();
        loadDoctors();
    }

    private void fillFieldsFromSelectedRow() {

        int selectedRow = doctorTable.getSelectedRow();

        if (selectedRow != -1) {

            nameField.setText(tableModel.getValueAt(selectedRow, 1).toString());
            specializationField.setText(tableModel.getValueAt(selectedRow, 2).toString());
            salaryField.setText(tableModel.getValueAt(selectedRow, 3).toString());
        }
    }

    private void clearFields() {

        nameField.setText("");
        specializationField.setText("");
        salaryField.setText("");
        doctorTable.clearSelection();
    }
}