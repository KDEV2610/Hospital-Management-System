package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {
    private Connection connection;
    private Scanner scanner;

    //  Contructor creation
    public Doctor(Connection connection){
        this.connection = connection;
    }

    public void viewDoctors(){
        String query = "select * from doctors";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
//ResultSet hold the data of DATABASE (TABLE) then vo ek next naam ke METHOD se pointer set kerta h and usse print krta h
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Doctor :");
            System.out.println("+------------+------------------------+-------------------+");
            System.out.println("| Doctor Id  | Name                   | Specialization    |");
            System.out.println("+------------+------------------------+-------------------+");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
//printf use for formatting.
                System.out.printf("|%-12s|%-24s|%-19s|\n",id,name,specialization);
                System.out.println("+------------+------------------------+-------------------+");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public boolean getDoctorById(int id){
        String query = "SELECT * FROM doctors WHERE id = ?";
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
