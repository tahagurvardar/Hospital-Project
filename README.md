# Hospital Management System

A desktop-based Hospital Management System developed with Java Swing and SQLite.

## Features

- Dashboard with total records
- Patient management
    - Add patient
    - Update patient
    - Delete patient
    - Search patient
    - List patients
- Doctor management
    - Add doctor
    - Update doctor
    - Delete doctor
    - List doctors
- Appointment management
    - Add appointment
    - Delete appointment
    - List appointments
    - Appointment conflict control
- SQLite database integration
- Java Swing graphical user interface
- Persistent data storage

## Technologies Used

- Java
- Java Swing
- SQLite
- JDBC
- Maven
- IntelliJ IDEA

## Project Structure

```txt
src/
├── dao/
│   ├── AppointmentDAO.java
│   ├── DoctorDAO.java
│   └── PatientDAO.java
│
├── database/
│   └── DatabaseConnection.java
│
├── model/
│   ├── Appointment.java
│   ├── Doctor.java
│   └── Patient.java
│
├── ui/
│   ├── AppointmentPanel.java
│   ├── DashboardPanel.java
│   ├── DoctorPanel.java
│   ├── MainFrame.java
│   └── PatientPanel.java
│
└── Main.java

How to Run
Clone the repository:
git clone https://github.com/tahagurvardar/Hospital-Project.git
Open the project with IntelliJ IDEA.
Make sure JDK 17 or higher is installed.
Run:
Main.java
Database

The project uses SQLite.
The database file is created automatically as:

hospital.db

Tables are created automatically when the program starts.

Screenshots

Add screenshots here:

Dashboard screenshot
Patients screenshot
Doctors screenshot
Appointments screenshot
Future Improvements
Login system
Admin, doctor and receptionist roles
Payment history
Appointment update feature
Better modern UI design
Export reports as PDF
Advanced search and filtering
Author

Developed by Taha Gürvardar