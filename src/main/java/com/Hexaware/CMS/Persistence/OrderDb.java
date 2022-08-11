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
    static String url = "jdbc:mysql://localhost:3306/CMSDB75791?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    static String username = "root";
    static String password = "password";

    public static int insertDb(int fid, String fname, int fprice, int fq, int foodTotal) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
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
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from Menu");
            ArrayList<Menu> list = new ArrayList<Menu>();
            while (rs.next()) {

                Menu mnu = new Menu(rs.getInt("Food_ID"), rs.getString("Food_Name"), rs.getInt("Food_Price"),
                        rs.getInt("Vendor_id"));
                list.add(mnu);
            }
            m = new Menu[list.size()];
            m = list.toArray(m);
            stmt.close();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return m;
    }

    // fetch the data of customer table -> array of customer
    public static Customer[] customerProfileDb() {
        Customer[] custArray = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from Customer");
            ArrayList<Customer> list = new ArrayList<Customer>();
            while (rs.next()) {
                Customer cs = new Customer(
                        rs.getString("Customer_id"),
                        rs.getString("Customer_name"),
                        rs.getString("Customer_phone"),
                        rs.getString("Customer_Email"),
                        rs.getInt("Customer_walletbal"),
                        rs.getInt("Customer_Login_id"),
                        rs.getString("Customer_Password"));
                list.add(cs);
            }
            custArray = new Customer[list.size()];
            custArray = list.toArray(custArray);
            stmt.close();
            con.close();
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
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from Vendor");
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
            Connection con = DriverManager.getConnection(url, username, password);

            String sql = " select * from Vendor where Vendor_id = ?  ";
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
    public static OrderDetails[] customerOrderHistoryDb(String custId) {
        OrderDetails[] odArr = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);

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
            Connection con = DriverManager.getConnection(url, username, password);

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

    // acceptOrReactOrder to impliment
    public static int acceptOrRejectOrder(String status, int orderId) {
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            String sql = "update OrderDetails set Order_status = ? where Order_No = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, status);
            stm.setInt(2, orderId);
            stm.executeUpdate();
            stm.close();
            con.close();
        } catch (Exception e) {
            System.out.print(e);
        }
        return i;
    }

    public static int placeOrder(int vendor_id, String customer_id, int food_id, int qtn, String date_time,
            int ord_value, String ord_status) {
        try {

            Connection con = DriverManager.getConnection(url, username, password);
            String sqlStr = "insert into OrderDetails (Vendor_id,Customer_id,Food_id,Quantity,DateandTime,Order_value,Order_status) values(?,?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sqlStr);
            stmt.setInt(1, vendor_id);
            stmt.setString(2, customer_id);
            stmt.setInt(3, food_id);
            stmt.setInt(4, qtn);
            stmt.setString(5, date_time);
            stmt.setInt(6, ord_value);
            stmt.setString(7, ord_status);
            i = stmt.executeUpdate();
            stmt.close();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return i;
    }

    public static Customer validateCustomerLogin(int custLoginId, String custPassword) {
        Customer cs = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver"); // register driver
            Connection con = DriverManager.getConnection(url, username, password); // Get the Connection
            // select * from customer where Cust_loginId=123 and Cust_Password = 'ab12';
            String sqlStr = " select * from Customer where Customer_Login_id=? and Customer_Password = ? ";

            PreparedStatement stmt = con.prepareStatement(sqlStr);

            stmt.setInt(1, custLoginId);
            stmt.setString(2, custPassword);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String custId = rs.getString("Customer_id");
                String custName = rs.getString("Customer_name");
                ;
                String custPhone = rs.getString("Customer_phone");
                String custEmail = rs.getString("Customer_Email");
                int custWalletBal = rs.getInt("Customer_walletbal");
                int custLoginId2 = rs.getInt("Customer_Login_id");
                String custPassword2 = rs.getString("Customer_Password");
                // Creating object from single row of data of customer
                cs = new Customer(custId, custName, custPhone,
                        custEmail, custWalletBal, custLoginId2, custPassword2);
                stmt.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cs;
    }

    public static int customerWalletBalance(String customerId) {
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            String sqlStr = "select * from Customer where Customer_id=?";
            PreparedStatement stmt = con.prepareStatement(sqlStr);
            stmt.setString(1, customerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                i = rs.getInt("Customer_walletbal");
            }
            stmt.close();
            con.close();

        } catch (Exception e) {
            System.out.print(e);
        }
        return i;
    }

    public static void updateCustomerWallet(String customerId, int walletBalance) {
        try {

            Connection con = DriverManager.getConnection(url, username, password);
            String updateCostumerSql = "update Customer set Customer_walletbal = ? where Customer_id=?";
            PreparedStatement stmt = con.prepareStatement(updateCostumerSql);
            stmt.setInt(1, walletBalance);
            stmt.setString(2, customerId);
            i = stmt.executeUpdate();
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static OrderDetails[] vendorOrdersPending(int vendorId) {
        OrderDetails[] odArr = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            String sql = " select * from OrderDetails where Vendor_id = ? and Order_status='ordered'";
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

    public static OrderDetails validateOrder(int orderId) {
        OrderDetails ordObj = null;
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            String sql = "select * from OrderDetails where Order_No=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ordObj = new OrderDetails(
                        rs.getInt("Order_No"),
                        rs.getInt("Vendor_id"),
                        rs.getString("Customer_id"),
                        rs.getInt("Food_id"),
                        rs.getInt("Quantity"),
                        rs.getString("DateandTime"),
                        rs.getInt("Order_value"), rs.getString("Order_status"));
            }
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return ordObj;

    }

    public static Customer validCustomer(Customer cs) {
        Customer cuObj = null;
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            String sql = "select * from Customer having Customer_id =? or Customer_Login_id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, cs.getCustId());
            stmt.setInt(1, cs.getCustLoginId());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String custId = rs.getString("Customer_id");
                String custName = rs.getString("Customer_name");
                String custPhone = rs.getString("Customer_phone");
                String custEmail = rs.getString("Customer_Email");
                int custWalletBal = rs.getInt("Customer_walletbal");
                int custLoginId2 = rs.getInt("Customer_Login_id");
                String custPassword2 = rs.getString("Customer_Password");
                // Creating object from single row of data of customer
                cuObj = new Customer(custId, custName, custPhone,
                        custEmail, custWalletBal, custLoginId2, custPassword2);
                stmt.close();
                con.close();
            }
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return cuObj;
    }

    public static int insertNewCustomer(Customer newCustomer) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = con.prepareStatement("insert into Customer values(?,?,?,?,?,?,?)");
            stmt.setString(1, newCustomer.getCustId());
            stmt.setString(2, newCustomer.getCustName());
            stmt.setString(3, newCustomer.getCustPhone());
            stmt.setString(4, newCustomer.getCustEmail());
            stmt.setInt(5, newCustomer.getCustWalletBalance());
            stmt.setInt(6, newCustomer.getCustLoginId());
            stmt.setString(7, newCustomer.getCustPassword());
            i = stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return i;
    }

    public static void updatePassword(int loingId,String pass) {

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            String updateCostumerSql = "update Customer set Customer_Password = ? where Customer_Login_id=?";
            PreparedStatement stmt = con.prepareStatement(updateCostumerSql);
            stmt.setString(1, pass);
            stmt.setInt(2, loingId);
            i = stmt.executeUpdate();
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static OrderDetails[] pendingOrderDetails(String customerId){
        OrderDetails[] odArr = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            String sql = " select * from OrderDetails where Customer_id = ? and Order_status='ordered'";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, customerId);
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

    public static OrderDetails validatePendingOrder(int orderId) {
        OrderDetails ordObj = null;
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            String sql = "select * from OrderDetails where Order_No=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ordObj = new OrderDetails(
                        rs.getInt("Order_No"),
                        rs.getInt("Vendor_id"),
                        rs.getString("Customer_id"),
                        rs.getInt("Food_id"),
                        rs.getInt("Quantity"),
                        rs.getString("DateandTime"),
                        rs.getInt("Order_value"), rs.getString("Order_status"));
            }
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return ordObj;

    }

    public static void cancelOrder(int orderId) {
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            String updateCostumerSql = "delete OrderDeatils where Order_id=?";
            PreparedStatement stmt = con.prepareStatement(updateCostumerSql);
            stmt.setInt(1, orderId);
            i = stmt.executeUpdate();
        } catch (Exception e) {
            System.out.print(e);
        }
    }

}
