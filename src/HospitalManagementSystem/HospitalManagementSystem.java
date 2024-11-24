package HospitalManagementSystem;

import java.sql.*;
import java.util.Scanner;


public class HospitalManagementSystem {
//  private for security, static for no need to create obj, static same value
    private static final String url = "jdbc:mysql://localhost:3306/hospital";
    private static final String username = "root";
    private static final String password = "261004";

    public static void main(String[] args) {
        try{
//            for passing string "com.mysql.cj.jdbc.Driver" TO CONN WITH DATABASE
//            Class.forName gave us exception "ClassNotFoundException", so to catch it we use catch
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);


//for connection with database
        try{
//            DriverManger have a method "get.connection" we pass credential
            Connection connection = DriverManager.getConnection(url, username, password);
            Patient patient = new Patient(connection,scanner);
            Doctor doctor = new Doctor(connection);

            while(true){
                System.out.println("HOSPITAL MANAGEMENT SYSTEM");
                System.out.println("1. Add Patient");
                System.out.println("2. View Patient");
                System.out.println("3. View Doctor");
                System.out.println("4. Book Appointment");
                System.out.println("5. Exit");

                System.out.println("Enter what you want: ");
                int choice = scanner.nextInt();

                switch (choice){
                    case 1:
//                     Add patient
                        patient.addPatient();
                        System.out.println();
                        break;
                    case 2:
//                     View Patient
                        patient.viewPatients();
                        System.out.println();
                        break;
                    case 3:
//                     View Doctor
                        doctor.viewDoctors();
                        System.out.println();
                        break;
                    case 4:
//                     Book Appointment
//  argument taken by this are-
                        bookAppointment(patient, doctor, connection, scanner);
                        System.out.println();
                        break;
                    case 5:
//                     Exit
                        return;
                    default:
                        System.out.println("Enter Valid choice!");
                }
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }


    public static void bookAppointment(Patient patient, Doctor doctor, Connection connection, Scanner scanner){
        System.out.println("Enter Patient ID: ");
        int patientID = scanner.nextInt();

        System.out.println("Enter Doctor ID: ");
        int doctorID = scanner.nextInt();

        System.out.println("Enter appointment date (YYYY-MM-DD): ");
        String appointmentDate;
        appointmentDate = scanner.next();

//        Boolean value
        if(patient.getPatientById(patientID) && doctor.getDoctorById(doctorID)){
            if(checkDoctorAvailability(doctorID, appointmentDate, connection)){
               String appointmentQuery = "INSERT INTO appointments(patient_ID, doctor_ID, appointment_date) VALUES (?, ?, ?)";
               try{
                   PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);
                   preparedStatement.setInt(1,patientID);
                   preparedStatement.setInt(2,doctorID);
                   preparedStatement.setString(3,appointmentDate);
//          To check id any ROW is affected in database.So there is a method name executeUpdate in an interface preparedStatement.
                   int rowsAffected = preparedStatement.executeUpdate();
                   if(rowsAffected > 0){
                       System.out.println("Appointment Booked!");
                   }else{
                       System.out.println("Failed to Book Appointment :( ");
                   }
               }catch(SQLException e){
                   e.printStackTrace();
                }

            }else{
                System.out.println("Doctor is not available on this date");
            }
        }else{
            System.out.println("Either doctor or patient are not available ");
        }
    }

//    Method for DOCTOR AVAILABILITY
//    As it impt to use Connection interface instance as we need to check in database.

    public static boolean checkDoctorAvailability(int doctorID , String appointmentDate, Connection connection){
//        Query to check the rows in database by (*) as if rows are > 1 that mean doctor is not available but if not that mean doctor is avl.
//        Here we left ? (placeholder)
        String query = "SELECT COUNT(*) FROM appointments WHERE doctor_ID = ? AND appointment_date = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,doctorID);
            preparedStatement.setString(2,appointmentDate);

//        For execution we use ResultSet.
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
//        Here we use INT instead of STRING cause e need
                int count = resultSet.getInt(1);
//        if there is 0 row that mean doctor is available.
                if(count==0){
                    return true;
                }else {
                    return false;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
}
