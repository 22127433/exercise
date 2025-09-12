package com.example.java.exercises.task6.repository;

import com.example.java.exercises.task6.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @NonNull
    List<Category> findAll();
}

//@Repository
//public class CategoryRepository {
//    private final DatabaseConnectionConfig dbConfig;
//
//    public CategoryRepository(DatabaseConnectionConfig dbConfig){
//        this.dbConfig = dbConfig;
//    }
//
//    public List<Category> getAllCategories(){
//        List<Category> categories = new ArrayList<>();
//        Connection connection = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
//
//        try {
//            connection = dbConfig.getConnection();
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery("SELECT * FROM Category");
//            while (resultSet.next()) {
//                Category category = new Category(
//                        resultSet.getInt("category_id"),
//                        resultSet.getString("category_name"),
//                        resultSet.getString("category_description")
//                );
//
//                categories.add(category);
//            }
//        }
//        catch (Exception e){
//            System.err.println(e.getMessage());
//        }
//        finally {
//            try {
//                if (resultSet != null) {resultSet.close();}
//                if (statement != null) {statement.close();}
//                if (connection != null) {connection.close();}
//            }
//            catch (Exception e) {
//                System.err.println(e.getMessage());
//            }
//        }
//
//        return  categories;
//    }
//}
