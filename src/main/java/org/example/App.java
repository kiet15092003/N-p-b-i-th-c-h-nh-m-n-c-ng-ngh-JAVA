package org.example;
import java. util. *;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Hello world!
 *
 */
public class App 
{
    static private String DB_URL = "jdbc:postgresql://localhost/5432/ProductManagement";
    static private String USER = "sa";
    static private String PASS = "abc123";
    static Scanner scanner = new Scanner(System.in);
    public static void main( String[] args )
    {
        try(Connection conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
        ) {
            Statement stmt = (Statement) conn.createStatement();
            String sql = "CREATE DATABASE ProductManagement";
            String sql2 = "CREATE table Product(" +
                    "id int primary key, " +
                    "name nvarchar(30)" +
                    "price int" +
                    "amount int"+
                    ")";
            stmt.executeUpdate(sql);
            stmt.executeUpdate(sql2);
            Product p1 = new Product(1, "Iphone 1", 12000, 10);
            Product p2 = new Product(2, "Iphone 2", 13000, 20);
            Product p3 = new Product(3, "Iphone 3", 14000, 15);
            ProductDAO.getInstance().add(p1);
            ProductDAO.getInstance().add(p2);
            ProductDAO.getInstance().add(p3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        boolean flag = true;
        do{
            System.out.println("----Menu--  --");
            System.out.println("1. Read all product");
            System.out.println("2. Read detail of a product by id");
            System.out.println("3. Add a new product");
            System.out.println("4. Update a product");
            System.out.println("5. Delete a product by id");
            System.out.println("6. Exit");
            System.out.println("Nhap lua chon: ");
            int choice = scanner.nextInt();
            switch(choice){
                case 1:
                    List<Product> Allproduct = ProductDAO.getInstance().readAll();
                    for (Product product:Allproduct){
                        System.out.println(product);
                    }
                    break;
                case 2:
                    System.out.println("Nhap id: ");
                    int id = scanner.nextInt();
                    System.out.println(ProductDAO.getInstance().read(id));
                    break;
                case 3:
                    System.out.println("Nhap id: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Nhap ten: ");
                    String name = scanner.nextLine();
                    System.out.println("Nhap gia: ");
                    int price = scanner.nextInt();
                    System.out.println("Nhap so luong: ");
                    int amount = scanner.nextInt();
                    Product p = new Product(id, name, price, amount);
                    ProductDAO.getInstance().add(p);
                    break;
                case 4:
                    System.out.println("Nhap id hang muon cap nhat: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Nhap ten: ");
                    name = scanner.nextLine();
                    System.out.println("Nhap gia: ");
                    price = scanner.nextInt();
                    System.out.println("Nhap so luong: ");
                    amount = scanner.nextInt();
                    p = new Product(id, name, price, amount);
                    ProductDAO.getInstance().update(p);
                    break;
                case 5:
                    System.out.println("Nhap id mat hang muon xoa: ");
                    id = scanner.nextInt();
                    ProductDAO.getInstance().delete(id);
                    break;
                case 6:
                    flag = false;
                    break;
            }

        }while(flag);
    }
}
