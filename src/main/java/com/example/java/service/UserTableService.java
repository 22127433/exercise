package com.example.java.service;

import com.example.java.entity.UserTable;
import com.example.java.repository.UserTableRepository;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

@Service
public class UserTableService {
    private final UserTableRepository userTableRepository;
    Logger log = LogManager.getLogger(UserTableService.class);

    public UserTableService(UserTableRepository userTableRepository) {
        this.userTableRepository = userTableRepository;
    }

    private void writeToFile(FileWriter fw,  UserTable userTable) throws IOException {
        fw.write(userTable.getId().toString() + ' ');
        fw.write(userTable.getHoten() + ' ');
        fw.write(userTable.getNgaysinh().toString() + ' ');
        fw.write(userTable.getDiachi() + ' ');
        fw.write(userTable.getGioitinh() + ' ');
        fw.write('\n');
    }

    public List<UserTable> getAllUserTables() {
        List<UserTable> userTables = userTableRepository.getAllUserTables();

        try {
            FileWriter fw = new FileWriter("output.txt");
            for (UserTable userTable : userTables) {
                writeToFile(fw, userTable);
            }
            fw.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            log.error("e: ", e);
        }

        return userTables;
    }
}
