package org.example;


import java.io.Flushable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ProductDAO {
    static private String url = "jdbc:postgresql://localhost/5432/ProductManagement";
    static private String user = "sa";
    static private String pwd = "abc123";
    public static ProductDAO getInstance(){
        return new ProductDAO();
    }

    public List<Product> readAll() {
        List<Product> products = new ArrayList<Product>();
        try {
            Connection conn = this.GetConnection();
            String sql = "select * from Product";
            Statement stm = conn.createStatement();

            ResultSet data = stm.executeQuery(sql);
            while(data.next()){
                Product p = new Product(data.getInt(1), data.getString(2), data.getInt(3), data.getInt(4));
                products.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
    public Integer add(Product item){
        try{
            Connection conn = this.GetConnection();
            String sql = "insert into Product values(?,?,?,?)";
            PreparedStatement stm =  conn.prepareStatement(sql);
            stm.setInt(1, item.getId());
            stm.setString(2, item.getName());
            stm.setInt(3, item.getPrice());
            stm.setInt(4, item.getAmount());
            int result = stm.executeUpdate();
            System.out.println("Rows added: " + result);
            this.closeConnection(conn);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public Product read(Integer id) {
        Product p = null;
        try {
            Connection conn = this.GetConnection();
            String sql = "select * from Product where id = ?";
            PreparedStatement stm = conn.prepareStatement(sql);

            stm.setInt(1, id);

            ResultSet data = stm.executeQuery();
            while(data.next()){
                p = new Product(data.getInt(1), data.getString(2), data.getInt(3), data.getInt(4));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return p;
    }

    public boolean update(Product item) {
        try{
            Connection conn = this.GetConnection();
            String sql = "update Product set name = ?, price = ?, amount = ? where id = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, item.getName());
            stm.setInt(2, item.getPrice());
            stm.setInt(3, item.getAmount());
            stm.setInt(4, item.getId());

            int result = stm.executeUpdate();
            if(result == 1){
                System.out.println("Updated rows: " + result);
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Integer id) {
        try{
            Connection conn = this.GetConnection();
            String sql = "delete from Product where id = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            int result = stm.executeUpdate();
            if(result == 1){
                System.out.println("Deleted rows: " + result);
                return true;
            }


        }catch(SQLException e){
            e.printStackTrace();
        }


        return false;
    }


    public static Connection GetConnection() throws SQLException {
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url, user, pwd);
            System.out.println("Success");
        }catch(SQLException e){
            System.out.println("Error" + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(Connection conn){
        try{
            if(conn != null){
                conn.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
