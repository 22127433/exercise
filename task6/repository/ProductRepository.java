package com.example.java.exercises.task6.repository;

import com.example.java.exercises.task6.entity.Category;
import com.example.java.exercises.task6.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
    List<Product> findProductsByCategory(Category category);
}

//@Repository
//public class ProductRepository {
//    private final DatabaseConnectionConfig dbConfig;
//
//    public ProductRepository(DatabaseConnectionConfig dbConfig) {
//        this.dbConfig = dbConfig;
//    }
//
//    public List<Product> getProducts(){
//        List<Product> products = new ArrayList<>();
//        Connection connection = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
//        try {
//            connection = dbConfig.getConnection();
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery("SELECT * FROM PRODUCT");
//            while (resultSet.next()) {
//                Product product = new Product(
//                        resultSet.getInt("product_id"),
//                        resultSet.getString("product_name"),
//                        resultSet.getString("product_description"),
//                        resultSet.getInt("product_price"),
//                        resultSet.getInt("category_id")
//
//                );
//                products.add(product);
//            }
//        }
//        catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        finally {
//            try {
//                if(resultSet != null) resultSet.close();
//                if(statement != null) statement.close();
//                if(connection != null) connection.close();
//            }
//            catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
//        return products;
//    }
//
//    public List<Product> getProductsByCategoryId(int category_id){
//        List<Product> products = new ArrayList<>();
//        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//        try {
//            connection = dbConfig.getConnection();
//            String sql = "SELECT * FROM PRODUCT WHERE category_id = ?";
//            statement = connection.prepareStatement(sql);
//            statement.setInt(1, category_id);
//            resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                Product product = new Product(
//                        resultSet.getInt("product_id"),
//                        resultSet.getString("product_name"),
//                        resultSet.getString("product_description"),
//                        resultSet.getInt("product_price"),
//                        resultSet.getInt("category_id")
//
//                );
//                products.add(product);
//            }
//        }
//        catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        finally {
//            try {
//                if(resultSet != null) resultSet.close();
//                if(statement != null) statement.close();
//                if(connection != null) connection.close();
//            }
//            catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
//        return products;
//    }
//
//    public List<Product> getProductsByCategoryName(String category_name){
//        List<Product> products = new ArrayList<>();
//        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//        try {
//            connection = dbConfig.getConnection();
//            String sql = "SELECT * FROM PRODUCT P JOIN CATEGORY C ON P.category_id = C.category_id WHERE C.category_name = ?";
//            statement = connection.prepareStatement(sql);
//            statement.setString(1, category_name);
//            resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                Product product = new Product(
//                        resultSet.getInt("product_id"),
//                        resultSet.getString("product_name"),
//                        resultSet.getString("product_description"),
//                        resultSet.getInt("product_price"),
//                        resultSet.getInt("category_id")
//
//                );
//                products.add(product);
//            }
//        }
//        catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        finally {
//            try {
//                if(resultSet != null) resultSet.close();
//                if(statement != null) statement.close();
//                if(connection != null) connection.close();
//            }
//            catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
//        return products;
//    }
//}
