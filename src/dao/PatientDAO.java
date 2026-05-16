package dao;

import database.DatabaseConnection;
import model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PatientDAO {

    public void addPatient(Patient patient) {

        String sql = "INSERT INTO patients(name, age, address, payment) VALUES(?, ?, ?, ?)";

        try {

            Connection conn = DatabaseConnection.connect();

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, patient.getName());
            pstmt.setInt(2, patient.getAge());
            pstmt.setString(3, patient.getAddress());
            pstmt.setDouble(4, patient.getPayment());

            pstmt.executeUpdate();

            conn.close();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Patient> getPatientsList() {

        ArrayList<Patient> patients = new ArrayList<>();

        String sql = "SELECT * FROM patients";

        try {

            Connection conn = DatabaseConnection.connect();

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                Patient patient = new Patient(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("address"),
                        rs.getDouble("payment")
                );

                patients.add(patient);
            }

            conn.close();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        return patients;
    }

    public void updatePatient(Patient patient) {

        String sql = "UPDATE patients SET name=?, age=?, address=?, payment=? WHERE id=?";

        try {

            Connection conn = DatabaseConnection.connect();

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, patient.getName());
            pstmt.setInt(2, patient.getAge());
            pstmt.setString(3, patient.getAddress());
            pstmt.setDouble(4, patient.getPayment());
            pstmt.setInt(5, patient.getId());

            pstmt.executeUpdate();

            conn.close();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public void deletePatient(int id) {

        String sql = "DELETE FROM patients WHERE id=?";

        try {

            Connection conn = DatabaseConnection.connect();

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);

            pstmt.executeUpdate();

            conn.close();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Patient> searchPatients(String keyword) {

        ArrayList<Patient> patients = new ArrayList<>();

        String sql = "SELECT * FROM patients WHERE name LIKE ? OR address LIKE ?";

        try {

            Connection conn = DatabaseConnection.connect();

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Patient patient = new Patient(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("address"),
                        rs.getDouble("payment")
                );

                patients.add(patient);
            }

            conn.close();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        return patients;
    }

    public int getPatientCount() {

        String sql = "SELECT COUNT(*) FROM patients";

        try {

            Connection conn = DatabaseConnection.connect();

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            int count = rs.getInt(1);

            conn.close();

            return count;

        } catch (Exception e) {

            System.out.println(e.getMessage());

            return 0;
        }
    }
}