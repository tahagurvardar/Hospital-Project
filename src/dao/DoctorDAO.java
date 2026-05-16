package dao;

import database.DatabaseConnection;
import model.Doctor;

import java.sql.*;
import java.util.ArrayList;

public class DoctorDAO {

    public void addDoctor(Doctor doctor) {

        String sql = "INSERT INTO doctors(name, specialization, salary) VALUES(?, ?, ?)";

        try {

            Connection conn = DatabaseConnection.connect();

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, doctor.getName());
            pstmt.setString(2, doctor.getSpecialization());
            pstmt.setDouble(3, doctor.getSalary());

            pstmt.executeUpdate();

            conn.close();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Doctor> getDoctors() {

        ArrayList<Doctor> doctors = new ArrayList<>();

        String sql = "SELECT * FROM doctors";

        try {

            Connection conn = DatabaseConnection.connect();

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                Doctor doctor = new Doctor(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("specialization"),
                        rs.getDouble("salary")
                );

                doctors.add(doctor);
            }

            conn.close();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        return doctors;
    }

    public void updateDoctor(Doctor doctor) {

        String sql = "UPDATE doctors SET name = ?, specialization = ?, salary = ? WHERE id = ?";

        try {

            Connection conn = DatabaseConnection.connect();

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, doctor.getName());
            pstmt.setString(2, doctor.getSpecialization());
            pstmt.setDouble(3, doctor.getSalary());
            pstmt.setInt(4, doctor.getId());

            pstmt.executeUpdate();

            conn.close();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public void deleteDoctor(int id) {

        String sql = "DELETE FROM doctors WHERE id = ?";

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
    public int getDoctorCount() {

        String sql = "SELECT COUNT(*) FROM doctors";

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