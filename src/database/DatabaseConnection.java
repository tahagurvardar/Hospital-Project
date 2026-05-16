package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnection {

    private static final String URL = "jdbc:sqlite:hospital.db";

    public static Connection connect() {

        try {

            Connection conn = DriverManager.getConnection(URL);

            createTables(conn);
            insertDefaultUsers(conn);

            return conn;

        } catch (Exception e) {

            System.out.println(e.getMessage());

            return null;
        }
    }

    private static void createTables(Connection conn) {

        try {

            Statement stmt = conn.createStatement();

            String patientTable =
                    "CREATE TABLE IF NOT EXISTS patients(" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "name TEXT NOT NULL," +
                            "age INTEGER," +
                            "address TEXT," +
                            "payment REAL" +
                            ");";

            String doctorTable =
                    "CREATE TABLE IF NOT EXISTS doctors(" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "name TEXT NOT NULL," +
                            "specialization TEXT," +
                            "salary REAL" +
                            ");";

            String appointmentTable =
                    "CREATE TABLE IF NOT EXISTS appointments(" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "patientName TEXT," +
                            "doctorName TEXT," +
                            "date TEXT," +
                            "time TEXT," +
                            "status TEXT" +
                            ");";

            String userTable =
                    "CREATE TABLE IF NOT EXISTS users(" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "username TEXT UNIQUE NOT NULL," +
                            "password TEXT NOT NULL," +
                            "role TEXT NOT NULL" +
                            ");";

            stmt.execute(patientTable);
            stmt.execute(doctorTable);
            stmt.execute(appointmentTable);
            stmt.execute(userTable);

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    private static void insertDefaultUsers(Connection conn) {

        try {

            Statement stmt = conn.createStatement();

            stmt.execute(
                    "INSERT OR IGNORE INTO users(username, password, role) VALUES" +
                            "('admin', 'admin123', 'Admin')," +
                            "('doctor', 'doctor123', 'Doctor')," +
                            "('reception', 'reception123', 'Receptionist');"
            );

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}