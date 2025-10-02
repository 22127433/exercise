package com.example.java.repository;
import com.example.java.config.DatabaseConnectionConfig;
import com.example.java.entity.UserTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserTableRepository {
    private static final Logger log = LogManager.getLogger(UserTableRepository.class);

    private final DatabaseConnectionConfig dbConfig;

    public UserTableRepository(DatabaseConnectionConfig dbConfig) {
        this.dbConfig = dbConfig;
    }

    public List<UserTable> getAllUserTables() {
        try {
            Connection connection = dbConfig.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM USER_TABLE");
            List<UserTable> userTables = new ArrayList<>();
            while (rs.next()) {
                UserTable userTable = new UserTable();
                userTable.setId(rs.getLong("id"));
                userTable.setHoten(rs.getString("hoten"));
                userTable.setNgaysinh(rs.getDate("ngaysinh"));
                userTable.setDiachi(rs.getString("diachi"));
                userTable.setGioitinh(rs.getString("gioitinh"));
                userTables.add(userTable);
            }
            return  userTables;
        }
        catch (Exception e) {
            log.error("e: ", e);
        }

        return null;
    }

}