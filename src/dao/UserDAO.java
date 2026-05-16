package dao;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public boolean login(String username, String password, String role) {

        String sql = "SELECT * FROM users WHERE username = ? AND password = ? AND role = ?";

        try {

            Connection conn = DatabaseConnection.connect();

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, role);

            ResultSet rs = pstmt.executeQuery();

            boolean isValid = rs.next();

            conn.close();

            return isValid;

        } catch (Exception e) {

            System.out.println(e.getMessage());

            return false;
        }
    }
}