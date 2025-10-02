package com.example.java.exercises.task1.repository;

import com.example.java.config.DatabaseConnectionConfig;
import com.example.java.exercises.task1.entity.Student;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {
    private final DatabaseConnectionConfig dbConfig;

    public StudentRepository(DatabaseConnectionConfig dbConfig) {
        this.dbConfig = dbConfig;
    }

    public Student getStudent(int id) {
        try {
            Connection connection = dbConfig.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM STUDENT WHERE id = " + id);
            Student student = new Student();
            if (rs.next()) {
                student.setId(id);
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setGpa(rs.getFloat("gpa"));
            }
            else student = null;

            rs.close();
            statement.close();
            connection.close();

            return student;
        }
        catch (Exception e) {
            return null;
        }
    }

    public List<Student> getStudentList() {
        try {
            Connection connection = dbConfig.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from STUDENT");
            List<Student> studentList = new ArrayList<>();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("ID"));
                student.setName(rs.getString("NAME"));
                student.setAge(rs.getInt("AGE"));
                student.setGpa(rs.getInt("GPA"));
                studentList.add(student);
            }

            rs.close();
            statement.close();
            connection.close();

            return studentList;
        }
        catch (Exception e) {
            return null;
        }
    }

    public void addStudent(Student student) {
        try {
            Connection connection = dbConfig.getConnection();
            String sql = "INSERT INTO STUDENT (name, age, gpa) VALUES (?, ?, ?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, student.getName());
                preparedStatement.setInt(2, student.getAge());
                preparedStatement.setFloat(3, student.getGpa());

                connection.setAutoCommit(false);
                preparedStatement.execute();
                connection.commit();

                preparedStatement.close();
            }
            catch (Exception e) {
                connection.rollback();
            }
            connection.close();
        }
        catch (Exception e) {
        }
    }

    public void deleteStudent(int id){
        try {
            Connection connection = dbConfig.getConnection();
            try {
                Statement statement = connection.createStatement();

                connection.setAutoCommit(false);
                statement.executeUpdate(
                        "DELETE FROM STUDENT WHERE ID = " + id
                );
                connection.commit();
                statement.close();
            }
            catch (Exception e) {
                connection.rollback();
            }
            connection.close();
        }
        catch (Exception e) {
        }
    }

    public void updateStudent(Student student){
        try {
            Connection connection = dbConfig.getConnection();
            String sql = "UPDATE STUDENT SET name = ?, age = ?, gpa = ? WHERE ID = " + student.getId();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, student.getName());
                preparedStatement.setInt(2, student.getAge());
                preparedStatement.setFloat(3, student.getGpa());

                connection.setAutoCommit(false);
                preparedStatement.execute();
                connection.commit();
                preparedStatement.close();
            }
            catch (Exception e) {
                connection.rollback();
            }
            connection.close();
        }
        catch (Exception e) {
        }
    }
}
