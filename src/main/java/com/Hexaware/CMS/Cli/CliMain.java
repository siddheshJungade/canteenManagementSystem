package com.Hexaware.CMS.Cli;

import java.util.Scanner;

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
        System.out.println("Canteen Management System");
        System.out.println("Enter your choice....");
        System.out.println("1. Show Menu");
        System.out.println("2. View as Vendor");
        System.out.println("3. Login as Customer");
        System.out.println("4. Exit");

        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                menuList();
                break;
            case 2:
                vendorProfile();
                break;
            case 3:
                customerProfile();
                break;
            case 4:
                inloop = false;
                Runtime.getRuntime().halt(0);
            default:
                System.out.println("Choose option 1 , 2, 3 , 4" );
        }
        }
    }

    /**
     * this method is to place food order.
     */
    public static void placeOrder(Customer cs) {
        Menu[] mArray = OrderFactory.showFoodMenu();
        for ( Menu m : mArray)
            System.out.println(m);
        boolean mFlag = false;
        System.out.println("Enter Food id");
        int foodId = sc.nextInt();
        Menu mObj=null;
        for ( Menu m : mArray){
            if ( m.getFoodId() == foodId ){
                mFlag = true;
                mObj = m;
                break;
            }
        }
        if( mFlag == false ){
            System.out.println( " Invalid food id");
            return;
        }
        System.out.println("Enter Food Qty");
        int qty = sc.nextInt();
        int totalCost = qty * mObj.getFoodPrice();
        int wB = cs.getCustWalletBalance();
        if ( wB > totalCost) {
            System.out.println( "proceed");
            System.out.println("To be implemented by you...");
            int i = OrderFactory.placeOrder();
            System.out.println(" records inserted.. " + i );
        } else {
            System.out.println( "invalid wallet balance..");
        }
        

        /*
         * step 1: show all food items 
         * step 2 User chooses one food id, and ordered_qty
         *      2a, validating whether it is valid foodid or not you will be getting a food
         * object 
         * Step 3: display the total cost. also validate whether there is
         * sufficient walletbalance available for order. 
         * if yes - processorder.
         * Functionality for processorder: inputs foodObject, ordered_qty, customer
         * object a: totalcost = fooobject.getcost() *ordered_qty; 
         * b: if ( totalcost <=
         * customer.walletbalance) 
         * I) insert a record with all relevant details and
         * orderstatus as 'ordered' in the ordertable 
         * 2) Update the customer record by
         * reducing the walletbalance. if insertion and updation success return 1 else
         * return 0;
         * 
         * if no - display the message 'insufficient walletbalance'
         * 
         */
    }

    /**
     * this method is to fetch Menu list.
     */
    public static void menuList() {
        Menu m[] = OrderFactory.showFoodMenu();
        System.out.println("Food Id" + "    " + "Food Name" + "               "  + "Food Price" + "      " + " VendorId");
        for (int i = 0; i < m.length; i++) {
            System.out.println(m[i].getFoodId() + "    " + m[i].getFoodName() + "  " + m[i].getFoodPrice()
                    + " " + m[i].getVendorId());
        }
    }
    /**
     * this method is to acceptRejectOrder.
     */
     public static void acceptRejectOrder(Vendor vendorObject){
        System.out.println("To be implemented by you... based on orderid, vendorid");
        String str = OrderFactory.acceptRejectOrder();
        System.out.println(str);
     }

    /**
     * this method is for customerProfile.
     */
    public static void customerProfile() {
        Customer[] cArray = OrderFactory.customerProfile();
        for (Customer cs : cArray) {
            System.out.println(cs);
        }
        System.out.println(" Login - Customer  ");

        System.out.println("Login Id:");
        int custLoginId = sc.nextInt();
        sc.nextLine();

        System.out.println("Password:");
        String custPassword = sc.nextLine();
        Customer cs = OrderFactory.validateCustomerLogin(
                        custLoginId, custPassword);
        if ( cs != null ){
            System.out.println( " Successfully logined,,,");
            System.out.println(cs);
            System.out.println(
                    " Press 1 - Place Order \r\n " + 
                    "Press 2 - See Your Order history \r\n" +
                    " Press any key to exit ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:                     
                     placeOrder(cs);                     
                    break;
                case 2:
                    OrderDetails[] od = 
                        OrderFactory.customerOrderHistory(cs.getCustId());
                    for (OrderDetails o : od)
                        System.out.println(o);
                    break;
                default:
                    System.out.println(" Invalid choice ");
                    break;
            }
      
        } else {
        System.out.println( "Invalid login id or password....");
        }
    }

    /**
     * this method is for VendorProfile.
     */
    public static void vendorProfile() {
        // call vendorProfileDb() -> returns Vendor[] via OrderFactory

        Vendor[] venArray = OrderFactory.vendorProfile();
        // display all vendors from Vendor[]
        for (Vendor v : venArray)
            System.out.println(v);

        // prompt the user -> Enter Vendor ID:
        System.out.println(" Enter Vendor Id: ");
        int venId = sc.nextInt();

        // fetch vendorObject for this VendorId
        Vendor ven = OrderFactory.validateVendor(venId);
        // if valid vendorObject
        if (ven == null) {
            System.out.println(" Invalid Vendor id");
        } else {

            System.out.println(" Press 1 - AcceptOrRejectorder \r\n " + "Press 2 - See Vendor Order history \n"
                    + " Press any key to exit ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    // show all orders for this venid where orderstatus is 'ordered'
                    // choose one order
                    // validate it as valid order from orderdetailstable
                    // get from user to either accept or reject order
                    // now pass the arguments orderid, orderstatus received from user
                    // the order status should be either 'Accepted' or 'Rejected'
                    // if rejected - give the totprice back to customerid.
                     
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
}
