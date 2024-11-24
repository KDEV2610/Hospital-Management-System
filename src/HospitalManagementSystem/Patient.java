package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
    private Connection connection;
    private Scanner scanner;

//  Contructor creation
    public Patient(Connection connection,Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }

//    Methords

    public void addPatient() {
        System.out.print("Enter Patient Name :");
        String name = scanner.next();

        System.out.print("Enter Patient Age :");
        int age = scanner.nextInt();

        System.out.print("Enter Patient Gender :");
        String gender = scanner.next();


        try {
//            ? are three palceholders
            String query = "INSERT INTO patients(name, age, gender) VALUES(?, ?, ?)";

//            connection interface ke instance ke pass PreparedStatement naam ka methord hota hai
//            jiske help se hum SQL query ko as an Argument pass kerte hue usse run ker sakten hain.

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Patient Added Successfully");
            } else {
                System.out.println("Failed to Add Patient");
            }


        } catch (SQLException e) {
//            SQLException is a method which is used to check the sql error and.... e is instance.
            e.printStackTrace();
        }

    }

    public void viewPatients(){
        String query = "select * from patients";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
//ResultSet hold the data of DATABASE (TABLE) then vo ek next naam ke METHOD se pointer set kerta h and usse print krta h
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Patients :");
            System.out.println("+------------+------------------------+-----------+-------------+");
            System.out.println("| Patient Id | Name                   | Age       | Gender      |");
            System.out.println("+------------+------------------------+-----------+-------------+");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
//printf use for formatting.
                System.out.printf("|%-12s|%-25s|%-11s|%-13s|\n",id,name,age,gender);
                System.out.println("+------------+------------------------+-----------+-------------+");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public boolean getPatientById(int id){
        String query = "SELECT * FROM patients WHERE id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else{
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}
