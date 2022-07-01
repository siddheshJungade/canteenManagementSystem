package com.Hexaware.CMS.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.Hexaware.CMS.Model.Customer;
import com.Hexaware.CMS.Model.Menu;
import com.Hexaware.CMS.Model.OrderDetails;
import com.Hexaware.CMS.Model.Vendor;

import java.sql.PreparedStatement;

/**
 * OrderDb class used to connect to data base.
 * 
 * @author hexware
 */
public class OrderDb {
    static int i;
    static String url = "jdbc:mysql://localhost:3306/CMSDB123456?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	static String username = "root";
	static String password = "Password123";
    public static int insertDb(int fid, String fname, int fprice, int fq, int foodTotal) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password );
            PreparedStatement stmt = con.prepareStatement("insert into orderfood values(?,?,?,?,?)");
            stmt.setInt(1, fid);
            stmt.setString(2, fname);
            stmt.setInt(3, fprice);
            stmt.setInt(4, fq);
            stmt.setInt(5, foodTotal);
            i = stmt.executeUpdate();
            // System.out.println(i+" records inserted");
        } catch (Exception e) {
            System.out.println(e);
        }
        return i;
    }

    // fetchDB is menuProfileDb
    // to include vendorID.
    // fetchDb
    public static Menu[] showFoodMenu() {
        Menu m[] = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password );
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from menu");
            ArrayList<Menu> list = new ArrayList<Menu>();
            while (rs.next()) {

                Menu mnu = new Menu(rs.getInt("Food_ID"), rs.getString("Food_Name"), rs.getInt("Food_Price"),
                        rs.getInt("Vendor_id"));
                list.add(mnu);
            }
            m = new Menu[list.size()];
            m = list.toArray(m);

        } catch (Exception e) {
            System.out.println(e);
        }

        return m;
    }

    // fetch the data of customer table -> array of customer
     public static Customer[] customerProfileDb(){
        Customer[] custArray = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password );
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from Customer");
            ArrayList<Customer> list = new ArrayList<Customer>();
            while (rs.next()) {
                Customer cs = new Customer( 
                    rs.getString("Customer_id"), 
                    rs.getString("Customer_name"),
                    rs.getString("Customer_phone") , 
                    rs.getString("Customer_Email"), 
                    rs.getInt("Customer_walletbal"),
                    rs.getInt("Customer_Login_id"), 
                    rs.getString("Customer_Password")
                );
                list.add(cs);
            }
            custArray = new Customer[list.size()];
            custArray = list.toArray(custArray);
        } catch (Exception e) {
            System.out.println(e);
        }

        return custArray;

     }

    // fetch the data of vendor -> array of vendor
    public static Vendor[] vendorProfileDb() {
        Vendor[] vnArray = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password );
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from vendor");
            ArrayList<Vendor> list = new ArrayList<Vendor>();
            while (rs.next()) {
                Vendor v = new Vendor(
                    rs.getInt("Vendor_id"), 
                    rs.getString("Vendor_Name"), 
                    rs.getString("Vendor_Phone"),
                    rs.getString("Vendor_Specs"));
                list.add(v);
            }
            vnArray = new Vendor[list.size()];
            vnArray = list.toArray(vnArray);
        } catch (Exception e) {
            System.out.println(e);
        }

        return vnArray;

    }

    // Validate thVendor ID
    public static Vendor validateVendor(int venId) {
        Vendor v = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password );

            String sql = " select * from Vendor where vendor_id = ?  ";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, venId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                v = new Vendor(
                    rs.getInt("vendor_id"), 
                    rs.getString("vendor_Name"), 
                    rs.getString("vendor_phone"),
                    rs.getString("vendor_specs"));
            }
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return v;

    }
    // fetch the data from the orderDetails where customerID =xx
    public static OrderDetails[] customerOrderHistoryDb(String custId){
        OrderDetails[] odArr = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password );

            String sql = " select * from OrderDetails where Customer_id = ?  ";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, custId);
            ResultSet rs = stmt.executeQuery();
            ArrayList<OrderDetails> list = new ArrayList<OrderDetails>();
            while (rs.next()) {
                OrderDetails od = new OrderDetails(
                    rs.getInt("Order_No"), 
                    rs.getInt("Vendor_id"),
                    rs.getString("Customer_id"), 
                    rs.getInt("Food_id"), 
                    rs.getInt("Quantity"), 
                    rs.getString("DateandTime"), 
                    rs.getInt("Order_value"), rs.getString("Order_status"));
                list.add(od);
            }
            odArr = new OrderDetails[list.size()];
            odArr = list.toArray(odArr);

            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return odArr;
    }

    // fetch the data from orderDetails where vendorId =xx
    public static OrderDetails[] vendorOrderHistoryDb(int vendorId) {
        OrderDetails[] odArr = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password );

            String sql = " select * from OrderDetails where Vendor_id = ?  ";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, vendorId);
            ResultSet rs = stmt.executeQuery();
            ArrayList<OrderDetails> list = new ArrayList<OrderDetails>();
            while (rs.next()) {
                OrderDetails od = new OrderDetails(
                    rs.getInt("Order_No"), 
                    rs.getInt("Vendor_id"),
                    rs.getString("Customer_id"), 
                    rs.getInt("Food_id"), 
                    rs.getInt("Quantity"), 
                    rs.getString("DateandTime"), 
                    rs.getInt("Order_value"), rs.getString("Order_status"));
                list.add(od);
            }
            odArr = new OrderDetails[list.size()];
            odArr = list.toArray(odArr);

            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return odArr;
    }

    public static String acceptOrRejectOrder() {
        //select * from orderdetails where order_id=? and vendor_id=? and orderStatus ="ordered"

        return " Yet to be implemented ...";
    }
    public static int placeOrder(){
        System.out.println( "yet to be implemented...");
        return 0;
    }

    public static Customer validateCustomerLogin( int custLoginId, String custPassword){
        Customer cs = null;
        try{
    
          Class.forName("com.mysql.cj.jdbc.Driver"); // register driver
          Connection con=DriverManager.getConnection(  
              url,"root", "Password123"); // Get the Connection
          //select * from customer where Cust_loginId=123 and Cust_Password = 'ab12';
            String sqlStr = " select * from customer where Customer_Login_id=? and Customer_Password = ? "; 
               
            PreparedStatement stmt=
              con.prepareStatement(sqlStr);
    
            stmt.setInt(1, custLoginId);
            stmt.setString(2, custPassword);
            ResultSet rs = stmt.executeQuery();
            if ( rs.next() ){
              String custId = rs.getString("Customer_id");
              String custName = rs.getString("Customer_name");;
              String custPhone = rs.getString("Customer_phone");
              String custEmail = rs.getString("Customer_Email");
              int custWalletBal = rs.getInt("Customer_walletbal");
              int custLoginId2 = rs.getInt("Customer_Login_id");
              String custPassword2 = rs.getString("Customer_Password");
                // Creating object from single row of data of customer
              cs = new Customer(custId, custName, custPhone, 
                  custEmail, custWalletBal, custLoginId2, custPassword2);
            }
           }catch(Exception e){
          System.out.println( e.getMessage());
        }
        return cs;
      }

}
