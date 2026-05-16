package dao;

import database.DatabaseConnection;
import model.Appointment;

import java.sql.*;
import java.util.ArrayList;

public class AppointmentDAO {

    public void addAppointment(Appointment appointment) {

        String sql = "INSERT INTO appointments(patientName, doctorName, date, time, status) VALUES(?, ?, ?, ?, ?)";

        try {

            Connection conn = DatabaseConnection.connect();

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, appointment.getPatientName().trim());
            pstmt.setString(2, appointment.getDoctorName().trim());
            pstmt.setString(3, appointment.getDate().trim());
            pstmt.setString(4, appointment.getTime().trim());
            pstmt.setString(5, appointment.getStatus().trim());

            pstmt.executeUpdate();

            conn.close();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Appointment> getAppointments() {

        ArrayList<Appointment> appointments = new ArrayList<>();

        String sql = "SELECT * FROM appointments";

        try {

            Connection conn = DatabaseConnection.connect();

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                Appointment appointment = new Appointment(
                        rs.getInt("id"),
                        rs.getString("patientName"),
                        rs.getString("doctorName"),
                        rs.getString("date"),
                        rs.getString("time"),
                        rs.getString("status")
                );

                appointments.add(appointment);
            }

            conn.close();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        return appointments;
    }

    public void deleteAppointment(int id) {

        String sql = "DELETE FROM appointments WHERE id = ?";

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

    public boolean isAppointmentConflict(String doctorName, String date, String time) {

        String sql = "SELECT COUNT(*) FROM appointments WHERE TRIM(doctorName) = ? AND TRIM(date) = ? AND TRIM(time) = ?";

        try {

            Connection conn = DatabaseConnection.connect();

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, doctorName.trim());
            pstmt.setString(2, date.trim());
            pstmt.setString(3, time.trim());

            ResultSet rs = pstmt.executeQuery();

            int count = rs.getInt(1);

            conn.close();

            return count > 0;

        } catch (Exception e) {

            System.out.println(e.getMessage());

            return false;
        }
    }

    public int getAppointmentCount() {

        String sql = "SELECT COUNT(*) FROM appointments";

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