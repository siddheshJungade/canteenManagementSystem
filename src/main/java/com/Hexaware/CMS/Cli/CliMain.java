package com.Hexaware.CMS.Cli;

import java.util.Scanner;
import java.text.SimpleDateFormat;  
import java.util.Date;  

import com.Hexaware.CMS.Factory.OrderFactory;
import com.Hexaware.CMS.Model.Customer;
import com.Hexaware.CMS.Model.Menu;
import com.Hexaware.CMS.Model.OrderDetails;
import com.Hexaware.CMS.Model.Vendor;

/**
 * CliMain used as Client interface for java coading.
 * 
 * @author hexware
 */
//mvn exec:java -Dexec.mainClass=com.Hexaware.CMS.Cli.CliMain
public class CliMain {

    static Scanner sc = new Scanner(System.in);
    /**
     * main method used to display the option we had in the application.
     */
    public static void main(String[] args) {
        boolean inloop = true;
        while ( inloop ){
            System.out.println("--------------------------");
            System.out.println("Canteen Management System");
            System.out.println("--------------------------");
            System.out.println("1. For User Login ");
            System.out.println("2. For Vendor Login ");  
            System.out.println("3. For Registering new user");
            System.out.println("4. Change Password");
            System.out.println("5. For Logging Out");
            System.out.println("               ");
            System.out.print("Enter Your Choice : ");

        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                customerProfile();
                break;
            case 2:
                vendorProfile();
                break;
            case 3:
                newCustomer();
                break;
            case 4:
                changeCustomerPassword();
            case 5:
                continue;
            default:
                System.out.println("Choose option 1 , 2, 3 , 4" );
        }
        }
    }



    public static void menuList() {
        Menu m[] = OrderFactory.showFoodMenu();
        System.out.println("Food Id" + "    " + "Food Name" + "               "  + "Food Price" + "      " + " VendorId");
        for (int i = 0; i < m.length; i++) {
            System.out.println(m[i].getFoodId() + "    " + m[i].getFoodName() + "  " + m[i].getFoodPrice()
                    + " " + m[i].getVendorId());
        }
    }
 

    public static void vendorProfile() {
        // call vendorProfileDb() -> returns Vendor[] via OrderFactory

        Vendor[] venArray = OrderFactory.vendorProfile();
        // display all vendors from Vendor[]
        for (Vendor v : venArray)
            System.out.println(v);

        // prompt the user -> Enter Vendor ID:
        System.out.print(" Enter Vendor Id: ");
        int venId = sc.nextInt();
        // fetch vendorObject for this VendorId
        Vendor ven = OrderFactory.validateVendor(venId);
        // if valid vendorObject
        if (ven == null) {
            System.out.println(" Invalid Vendor id");
        } else {
            System.out.println(" Press 1 - AcceptOrRejectorder \r\n " + " Press 2 - See Vendor Order history \n"
                    + " Press any key to exit ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    acceptRejectOrder(ven);
                    break;
                case 2:
                    OrderDetails[] od = OrderFactory.vendorOrderHistory(ven.getVenId());
                    for (OrderDetails o : od)
                        System.out.println(o);
                    break;
                default:
                    System.out.println(" Invalid choice ");
            }
        }
    }

    /**
     * this method is to acceptRejectOrder. the order
     * will print all pending order of vender
     * vender can chose wether to accept orde or not 
     * vender reject order money get beack to customer account
    */  
    public static void acceptRejectOrder(Vendor vendorObject){
        OrderDetails[] ord = OrderFactory.vendorOrdersPending(vendorObject.getVenId());
        for(OrderDetails a : ord){
            System.out.println(a);
        }
        // choose one order
        System.out.print("choss order number:");
        int orderId = sc.nextInt();
        OrderDetails validOrder = OrderFactory.validateOrder(orderId);
        // validate it as valid order from orderdetailstable
        System.out.println(validOrder);
        if(validOrder != null){
            System.out.print("'1' to Accept order  ||  '0' to rejeact Order :");
            int result = sc.nextInt();
            switch(result){
                case 0:
                OrderFactory.acceptRejectOrder("Reject",orderId);
                String customerId = validOrder.getCustomerId();
                System.out.println(customerId);
                int walletBal= OrderFactory.customerWalletBalance(customerId);
                System.out.println(walletBal);
                walletBal += validOrder.getOrderValue();
                System.out.println(walletBal);
                OrderFactory.updateCustomerWallet(customerId,walletBal);
                    break;
                case 1:                    
                        OrderFactory.acceptRejectOrder("Accept",orderId);
                        break;
                default:
                    System.out.println("Invalid Choice");
                    acceptRejectOrder(vendorObject);
            }
        }
     }

     /**
     * this method is for customerProfile.
     */
    public static void customerProfile() {
        Customer[] cArray = OrderFactory.customerProfile();
        for (Customer cs : cArray) {
            System.out.println(cs);
        }
        System.out.println(" ------------------------Login as Customer-----------------------------  ");

        System.out.print("Login Id:");
        int custLoginId = sc.nextInt();
        sc.nextLine();
        System.out.print("Password:");
        String custPassword = sc.nextLine();
        Customer cs = OrderFactory.validateCustomerLogin(custLoginId, custPassword);
        if ( cs != null ){
            System.out.println( "Successfully logined");
            System.out.println("--------------------------");
            System.out.println("Canteen Management System");
            System.out.println("--------------------------");
            System.out.println("Customer Id : " + custLoginId);
            System.out.println("--------------------------");
            System.out.println(
                "1. Placing Order \r\n" +
                "2. Order Histoy \r\n" +
                "3. Profile \r\n" +
                "4. Wallet Balance \r\n" +
                "5. Cancel Order \r\n");
            System.out.println("--------------------------");
            System.out.println("Canteen Management System");
            System.out.println("--------------------------");
            System.out.println("Customer Id : " + custLoginId);
            System.out.println("--------------------------");
            System.out.println("Enter Your Choice : ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:                     
                     placeOrder(cs);                     
                    break;
                case 2:
                    OrderDetails[] od = OrderFactory.customerOrderHistory(cs.getCustId());
                    for (OrderDetails o : od)
                        System.out.println(o);
                    break;
                case 3:
                    System.out.println(cs);
                case 4:
                    int wb = OrderFactory.customerWalletBalance(cs.getCustId());
                    System.out.print("Your Wallet Balance is " + wb +" Rs");
                case 5:
                    OrderDetails[] pendingOrdersArr = OrderFactory.pendingOrderDetails(cs.getCustId());
                    for(OrderDetails o : pendingOrdersArr){
                        System.out.println(o);
                    } 
                    System.out.print("Choss the Order Id of Food You want to Cancel : ");
                    int orderId = sc.nextInt();
                    OrderDetails ordD = OrderFactory.validatePendingOrder(orderId);
                    if(ordD != null){
                        OrderFactory.cancelOrder(orderId);
                    } else {
                        System.out.println("Not Valaid Order Id");
                    }
                default:
                    System.out.println(" Invalid choice ");
                    break;
            }
      
        } else {
        System.out.println("Invalid login id or password....");
        }
    }

    /**
     * this method is to place food order.
     */
    public static void placeOrder(Customer cs) {
        

        menuList();
        boolean mFlag = false;
        System.out.println("Enter Food id");
        int foodId = sc.nextInt();
        Menu[] mArray = OrderFactory.showFoodMenu();
        Menu mObj=null;
        for ( Menu m : mArray){
            if ( m.getFoodId() == foodId ){
                mFlag = true;
                mObj = m;
                break;
            }
        }
        if( mFlag == false ){
            System.out.println( "Invalid food id");
            return;
        }
        
        
        
        System.out.println("Enter Food Qty");
        int qty = sc.nextInt();
        int totalCost = qty * mObj.getFoodPrice();
        int wB = cs.getCustWalletBalance();
        if ( wB > totalCost) {
            int venderId = mObj.getVendorId();
            String customerId = cs.getCustId();
            String status = "ordered";
            SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
            Date now = new Date();
            String curDateTime = dtf.format(now);
            int i = OrderFactory.placeOrder( venderId,customerId, foodId, qty,curDateTime, totalCost,status);
            System.out.println("Thanks For Ordaring Your order has been Send to Vender. " + i );
            int walletBal = wB - totalCost;
            OrderFactory.updateCustomerWallet(customerId, walletBal);
            System.out.println(" Wallet Balance Updated ");

        } else {
            System.out.println( "Low wallet balance..");
        }
    }


    public static void newCustomer(){
        System.out.print("Enter the Unique customer Id:");
        String cid = sc.next();
        System.out.print("Enter your name:");
        String cname = sc.next();
        System.out.print("Enter your phone number:");
        String phno = sc.next();
        System.out.println("Enter your email Id:");
        String email = sc.next();
        System.out.println("Enter the amount credit to walletbalance:");
        int wb = sc.nextInt();
        System.out.println("Enter Login Id:");
        int Lid = sc.nextInt();
        System.out.println("Enter your password:");
        String pw = sc.next(); 
        Customer newCustomer = new Customer(cid, cname, phno, email, wb, Lid, pw);  
        Customer existCs = OrderFactory.validCustomer(newCustomer);
        if(existCs == null) {
            int i = OrderFactory.newCustomer(newCustomer);
        } else {
            System.out.println("Customer Exist ");
        }
    }

    public static void changeCustomerPassword(){
        System.out.println("Enter the  customer Login id :");
        int lid = sc.nextInt();
        System.out.println("Enter the current password :");
        String cpwd = sc.next();
        System.out.println("Enter the new password :");
        String cnpwd = sc.next();
        Customer cs = OrderFactory.validateCustomerLogin(lid, cpwd);
        if(cs == null) {
            System.out.println("Login Id or Password is Worng");
        } else {
            OrderFactory.updatePassword(lid,cnpwd);
        }
    }
}
