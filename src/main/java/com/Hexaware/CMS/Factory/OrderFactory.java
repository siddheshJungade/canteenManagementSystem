package com.Hexaware.CMS.Factory;

import com.Hexaware.CMS.Model.Customer;
import com.Hexaware.CMS.Model.Menu;
import com.Hexaware.CMS.Model.OrderDetails;
import com.Hexaware.CMS.Model.Vendor;
import com.Hexaware.CMS.Persistence.OrderDb;

/**
 * OrderFactory class used to fetch and insert data to database. something
 * 
 * @author hexware
 */
public class OrderFactory {

    public static int OrderFood(int fid, String fname, int fprice, int fquan) {
        int foodTotal = fquan * fprice;
        int result = OrderDb.insertDb(fid, fname, fprice, fquan, foodTotal);
        return result;
    }

    public static Menu[] showFoodMenu() {
        Menu menu[] = OrderDb.showFoodMenu();
        return menu;
    }

    public static Customer[] customerProfile() {
        return OrderDb.customerProfileDb();
    }

    public static Vendor[] vendorProfile() {
        return OrderDb.vendorProfileDb();
    }

    public static Vendor validateVendor(int venId) {
        return OrderDb.validateVendor(venId);
    }

    public static OrderDetails validateOrder(int orderId){
        return OrderDb.validateOrder(orderId);
    }
    
    public static OrderDetails[] customerOrderHistory(String custId) {
        return OrderDb.customerOrderHistoryDb(custId);
    }

    public static OrderDetails[] vendorOrderHistory(int vendorId) {
        return OrderDb.vendorOrderHistoryDb(vendorId);
    }

    public static int acceptRejectOrder(String status,int orderId) {
        return OrderDb.acceptOrRejectOrder(status, orderId);
    }

    public static Customer validateCustomerLogin(int custLoginId, String custPassword) {
        return OrderDb.validateCustomerLogin(custLoginId, custPassword);
    }

    public static int placeOrder(int vendor_id, String customer_id, int food_id, int qtn, String date_time,
            int ord_value, String ord_status) {
        return OrderDb.placeOrder(vendor_id, customer_id, food_id, qtn, date_time, ord_value, ord_status);
    }
    
    public static void updateCustomerWallet(String customerId,int walletBal){
        OrderDb.updateCustomerWallet(customerId, walletBal);
    }

    public static OrderDetails[] vendorOrdersPending(int venderId){
        return OrderDb.vendorOrdersPending(venderId);
    }
    
    public static int customerWalletBalance(String customerId) {
        return OrderDb.customerWalletBalance(customerId);
    }

    public static int newCustomer(Customer newCustomer) {
        return OrderDb.insertNewCustomer(newCustomer);
    }

    public static Customer validCustomer(Customer cs) {
        return OrderDb.validCustomer(cs);
    }

    public static void updatePassword(int lid,String cnpwd){
        OrderDb.updatePassword(lid,cnpwd);
    }

    public static OrderDetails[] pendingOrderDetails(String customerId){
        return OrderDb.pendingOrderDetails(customerId);
    }

    public static OrderDetails validatePendingOrder(int orderId) {
        return OrderDb.validatePendingOrder(orderId);

    }

    public static void cancelOrder(int orderId){
        OrderDb.cancelOrder(orderId);
    }

}
