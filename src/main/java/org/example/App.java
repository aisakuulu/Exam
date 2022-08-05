package org.example;

import org.example.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) throws SQLException {
        connection();
        getAllStudents();

    }

    public static Connection connection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "postgres"
        );
    }

    public static List<Student> getAllStudents() {
        String QUERY = "SELECT * FROM student";
        List<Student> allStudents = new ArrayList<>();

        try (Statement statement = connection().createStatement();
             ResultSet resultSet = statement.executeQuery(QUERY)){
            while (resultSet.next()){
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                student.setEmail(resultSet.getString("email"));
                allStudents.add(student);

                System.out.println("Student ID: " + student.getId());
                System.out.println("Student name: " + student.getName());
                System.out.println("Student age: " + student.getAge());
                System.out.println("Student email: " + student.getEmail());
                System.out.println();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allStudents;
    }
}
