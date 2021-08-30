package com.Hexaware.CMS.Cli;

import java.util.Scanner;

import com.Hexaware.CMS.Factory.OrderFactory;
import com.Hexaware.CMS.Model.Menu;

/**
 * CliMain used as Client interface for java coading.
 * @author hexware
 */
public class CliMain {
    
    static Scanner sc=new Scanner(System.in);
/**
 * main method  used to display the option we had in the application.
 */
    public static void main( String[] args )
    {      
        System.out.println( "Canteen Management System" );      
        System.out.println("Enter your choice....");
        System.out.println("1.Show Menu");
        System.out.println("2.Placing Order");
        System.out.println("3.Exit");

        
        int choice=sc.nextInt();
        switch(choice){
            case 1:
                menuList();
                break;
            case 2:
                placeOrder();    
                break;
            case 3:
                Runtime.getRuntime().halt(0);
            default:
                System.out.println("Choose option 1 or 2");
        }
    }
    /**
     * this method  is to place food order.
     */
        public static void placeOrder(){
        System.out.println("Enter the Food id");
        int fid=sc.nextInt();
        System.out.println("Enter the Food Name");
        String fname=sc.next();
        System.out.println("Enter the Food Price");
        int fprice=sc.nextInt();
        System.out.println("Enter the Food Quantity");
        int  fquan=sc.nextInt();

        //Add other attributes to complete the functionality
        int r= OrderFactory.OrderFood(fid,fname,fprice,fquan);
        System.out.println(r+"   is inserted.....");
        }
/**
 * this method is to fetch Menu list.
 */
        public static void menuList(){
        Menu m[]=OrderFactory.fetchMenu();
        System.out.println("Food Id"+"    "+"Food Name"+"    "+"Food Price"); 
        for(int i=0;i<m.length;i++){
              System.out.println(m[i].getFoodId()+"       "+m[i].getFoodName()+"       "+m[i].getFoodPrice());
           }
    }
/**
 * this method is to acceptRejectOrder.
 */
// public static String acceptRejectOrder(){}

 /**
 * this method is for customerProfile.
 */
// public static Customer customerProfile(){}

/**
 * this method is for VendorProfile.
 */
// public static Vendor vendorProfile(){}

/**
 * this method is for VendorOderHistory.
 */
// public static Vendor[] VendorOderHistory(){}

/**
 * this method is for CustomerOrderHistory.
 */
// public static Customer[] CustomerOrderHistory(){}

}
