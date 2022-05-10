package com.Hexaware.CMS.Cli;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import com.Hexaware.CMS.Factory.CustomerFactory;
import com.Hexaware.CMS.Factory.LoginFactory;
import com.Hexaware.CMS.Factory.OrderFactory;
import com.Hexaware.CMS.Factory.VendorFactory;
import com.Hexaware.CMS.Model.*;
import com.Hexaware.CMS.Persistence.OrderDb;

/**
 * CliMain used as Client interface for java coading.
 * @author hexware
 */
public class CliMain {
	  public static float WALLET_BALANCE;
    static Scanner sc=new Scanner(System.in);
/**
 * main method  used to display the option we had in the application.
 */
    public static void main( String[] args )
    {    /*  
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
        }*/
    	loginMenu();
    }
    
    
    /**
     * this method  is to display login menu.
     */
    public static void loginMenu()
    {
    	System.out.println( "--------------------------------" );
    	System.out.println( "Canteen Management System" );   
    	System.out.println( "--------------------------------" );
        System.out.println("1.For User Login");
        System.out.println("2.For Registering New User");
        System.out.println("3.Change Password");
        System.out.println("4.Exit");
        System.out.println("Enter your choice :");
        int choice=sc.nextInt();
        switch(choice){
        case 1:
            userLogin();
            break;
        case 2:
            newUserMenu();  
            loginMenu();
        case 3:
          changePassword();
        case 4:
        	System.out.println("Exiting...");
        	  Runtime.getRuntime().halt(0);
        default:
            System.out.println("Choose option 1,2,3,4");
        }

    }
    
    /**
     * this method  is to user login .
     */
    public static void userLogin()
    {
    	 System.out.println("Please Enter user id & password");
    	 System.out.println("Enter Username :");
    	 String username = sc.next();
    	 System.out.println("Enter Password :");
    	 String password = sc.next();
    	 Login l[]= LoginFactory.doLogin(username, password);
    	 if(l!=null)
    	 {
    		 System.out.println(l[0].getType());
    		 if(l[0].getType().equals("customer")) {
    		 displayCustomerMenu(username);
    		 }
    		 else if(l[0].getType().equals("vendor"))
    		 {
    			 displayVendorMenu(username);
    		 }
    	 }
    	 else {
    		
    		 System.out.println("Invalid user id & password!!");
    		 loginMenu();
    	 }
    }
    
    
    
    
    /**
     * this method  is to Show Vendor Menu .
     */
    public static void  displayVendorMenu(String username)
    {
	Vendor c[]=VendorFactory.doLogin(username);
    	
    	System.out.println( "--------------------------------" );
    	System.out.println( "Canteen Management System" );   
    	System.out.println( "--------------------------------" );
    	System.out.println( "Vendor :"+username );   
    	System.out.println( "--------------------------------" );
        System.out.println("1.Show Menu");
        System.out.println("2.Accept & Reject");
        System.out.println("3.Order History");
        System.out.println("4.Edit Menu");
        System.out.println("5.Profile");
        System.out.println("6.Previous Menu");
        System.out.println("Enter your choice :");
        int choice = sc.nextInt();
        switch(choice){
        case 1:
        	VendormenuList(username);
        	displayVendorMenu(username);
            break;
        case 2:
           
        case 3:
         
        case 4:
        case 5: displayVendorProfile(username);
        		displayVendorMenu(username);
        		break;
        case 6:loginMenu();break;
        default:
            System.out.println("Choose option 1,2,3,4");
        }
    }
    
    
    
    
    
    
    
  
    /**
     * this method  is to Show CustomerMenu .
     */
    public static void  displayCustomerMenu(String username)
    {
    	
    	
    	Customer c[]=CustomerFactory.doLogin(username);
    	  WALLET_BALANCE=c[0].getWalletBalance();
    	System.out.println( "--------------------------------" );
    	System.out.println( "Canteen Management System" );   
    	System.out.println( "--------------------------------" );
    	System.out.println( "Customer :"+username );   
    	System.out.println( "--------------------------------" );
        System.out.println("1.Show Menu");
        System.out.println("2.Placing Order");
        System.out.println("3.Order History");
        System.out.println("4.Profile");
        System.out.println("5.Wallet Balance");
        System.out.println("6.Previous Menu");
        System.out.println("Enter your choice :");
        int choice = sc.nextInt();
        switch(choice){
        case 1:
        	 menuList();
        	 displayCustomerMenu(username);
            break;
        case 2:
        		placeOrder(username);displayCustomerMenu(username);break;
        case 3:
        		showOrderHistory(username);displayCustomerMenu(username);break;
         
        case 4:
        	  	displayCustomerProfile(username);
        	  	displayCustomerMenu(username);
        	  	break;
        case 5: walletMenu(username,c);break;
        case 6: loginMenu();break;
            
        default:
            System.out.println("Choose option 1,2,3,4");
        }
    }
    
    
    
    
    
    
    private static void showOrderHistory(String username) {
    	
Order_ m[] = OrderFactory.fetcOrderHistoryByCustomer(username);
//System.out.print(m.length);
String OrderIdP=m[0].getORDER_ID();
    	 System.out.println("-----------------------------------------------------------------------------------------------------");
         System.out.println("----------------------------------------      Order History       ------------------------------------");
         System.out.println("------------------------------------------------------------------------------------------------------");
         System.out.println("Date"+"             "+"Food Id"+"      "+"Food Name"+"      "+"Food Price"+"      "+"Quantity"+"     "+"Value"); 
         for(int i=0;i< m.length;i++)
    	 {
        	 System.out.println(m[i].getDATE_TIME()+"         "+m[i].getFOOD_ID()+"         "+m[i].getFOOD_NAME()+"          "+m[i].getAMOUNT()+"          "+m[i].getQUANTITY()+"          "+m[i].getORDER_VALUE());
        	 
        	 if(!m[i].getORDER_ID().equals(OrderIdP))
        	 {
        		 System.out.println("------------------------------------------------------------------------------------------------------");
        		 System.out.println("------------------------------------------------------------------------------------------------------");
        	       
        	 }
        	
        	 OrderIdP=m[i].getORDER_ID();}
         System.out.println("------------------------------------------------------------------------------------------------------");
         System.out.println("Press Enter to Previous Menu");
          try{System. in. read();}
          catch(Exception e){}
	}


	private static void walletMenu(String username,Customer[] c) {
    	 System.out.println("------------------------------------------------------------------------------------------------------");
    	 System.out.println("Your Wallet Balance is");
    	 System.out.println("User Id"+"    "+"Customer Name"+"       "+"Wallet Balance"); 
    	 System.out.println(c[0].getUserName()+"         "+c[0].getName()+"       "+c[0].getWalletBalance());
    	 System.out.println("------------------------------------------------------------------------------------------------------");
    	 System.out.println("1. Add Money");
    	 System.out.println("2. Cancel");
    	 System.out.println("Select 1 Or 2 :");
    	 int choice = sc.nextInt();
         switch(choice){
         case 1: System.out.println("------------------------------------------------------------------------------------------------------");
         		System.out.println("Enter Amount:");
         		float amt = sc.nextFloat();
         		WALLET_BALANCE+= amt;
         		 int s = CustomerFactory.updateWallet(WALLET_BALANCE, username);
         		 c[0].SetWalletBalance(WALLET_BALANCE);
         		 walletMenu(username,c);
             break;
         case 2:
        	 displayCustomerMenu(username);break;
         default:
             System.out.println("Choose option 1,2,3,4");
         }
	}


	private static void displayCustomerProfile(String username) {
    	 Customer m[]=CustomerFactory.doLogin(username);
         System.out.println("------------------------------------------------------------------------------------------------------");
         System.out.println("--------------------------------         Customer Profile                -----------------------------");
         System.out.println("------------------------------------------------------------------------------------------------------");
         System.out.println("User Id"+"    "+"Customer Name"+"        "+"Phone"+"       "+"Wallet Balance"); 
       
               System.out.println(m[0].getUserName()+"         "+m[0].getName()+"          "+m[0].getPhoneNumber()+"          "+m[0].getWalletBalance());
            
         System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("Press Enter to Previous Menu");
         try{System. in. read();}
         catch(Exception e){}
		
	}
	private static void displayVendorProfile(String username) {
   	 Vendor m[]=VendorFactory.doLogin(username);
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("------------------------------         Vendor Profile                ---------------------------------");
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("User Id"+"    "+"Vendor Name"+"        "+"Phone"+"       "+"Address"); 
      
              System.out.println(m[0].getUserName()+"         "+m[0].getName()+"          "+m[0].getPhoneNumber()+"          "+m[0].getAddress());
           
        System.out.println("------------------------------------------------------------------------------------------------------");
       System.out.println("Press Enter to Previous Menu");
        try{System. in. read();}
        catch(Exception e){}
		
	}

	/**
     * this method  is to Create user .
     */
    public static void  newUserMenu()
    {
    	System.out.println( "--------------------------------" );
    	System.out.println( "-----------New User-------------" );   
    	System.out.println( "--------------------------------" );
        System.out.println("1. Customer");
        System.out.println("2. Vendor");
        System.out.println("3. Cancel");
        int choice=sc.nextInt();
        switch(choice){
        case 1:
             newCustomer();
            break;
        case 2:
            newVendor();    
            break;
        case 3:
            loginMenu();    
            break;
        default:
            System.out.println("Choose option 1,2");
            newUserMenu();
           
        }
        
        
    }
    
    private static void newVendor() {
    	Vendor v =new Vendor();
		Login l = new Login();
		 System.out.println("Enter Vendor Id:");
		 v.SetId(sc.next());
		 System.out.println("Enter Vendor Name:");
		 v.SetName(sc.next());
		 System.out.println("Enter Phone Number:");
		 v.SetPhoneNumber(sc.next());
		 System.out.println("Enter Address:");
		 v.SetAddress(sc.next());
		 System.out.println("Enter UserName:");
		 v.setUserName(sc.next());
		 System.out.println("Enter Password:");
		 l.SetPassword(sc.next());
		 l.setUserName(v.getUserName());
		 l.SetType("vendor");
		 try {
		int r= VendorFactory.insertVendor(v.getId(),v.getName(),v.getUserName(),v.getPhoneNumber(),v.getAddress());
		 int lr= LoginFactory.insertLogin(l.getUserName(), l.getPassword(), l.getType());
		 System.out.println("Success..");
		 }
		 catch(Exception e)
		 {
			 System.out.println(e.toString());
		 }
	}


	private static void newCustomer() {
		try {
		Customer c =new Customer();
		Login l = new Login();
		 System.out.println("Enter Customer Id:");
		 c.SetId(sc.next());
		 System.out.println("Enter Customer Name:");
		 c.SetName(sc.next());
		 System.out.println("Enter Phone Number:");
		 c.SetPhoneNumber(sc.next());
		 System.out.println("Enter Wallet Balance:");
		 c.SetWalletBalance(sc.nextInt());
		 System.out.println("Enter Address:");
		 c.SetAddress(sc.next());
		 System.out.println("Enter UserName:");
		 c.setUserName(sc.next());
		 System.out.println("Enter Password:");
		 l.SetPassword(sc.next());
		 l.setUserName(c.getUserName());
		 l.SetType("customer");
		 
		int r= CustomerFactory.insertCustomer(c.getId(),c.getName(),c.getUserName(),c.getPhoneNumber(),c.getWalletBalance(),c.getAddress());
		 int lr= LoginFactory.insertLogin(l.getUserName(), l.getPassword(), l.getType());
		 System.out.println("Success..");
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
	}


	/**
     * this method  is to Change Password
     */
    public static void  changePassword()
    {
    	
    }
    
  
    public static float TOTAL=0;
    public static float TOTAL_ITEM=0;
    public static String VENDOR_ID;
    public static ArrayList<Order> FOOD_ITEM=new ArrayList<Order>();
    
    public static void addItem()
    {
    	 System.out.println("Enter the Food id");
         String fid=sc.next();
         System.out.println("Enter the Food Quantity");
         int  fquan=sc.nextInt();
         Menu m[] = OrderDb.fetchDb();
         if(m != null) {
        	 for(int i=0;i<m.length;i++){
             	if(m[i].getFoodId().equals(fid))
     	        	{
     	        		CliMain.TOTAL+= (m[i].getFoodPrice() * fquan);
     	        		TOTAL_ITEM += fquan;
     	        		FOOD_ITEM.add(new Order(fid,m[i].getFoodName(),fquan,m[i].getFoodPrice()));
     	        		VENDOR_ID = m[i].getVendorId();
     	        	}
             	}
         }
    }
    
    /**
     * this method  is to place food order.
     */
        public static void placeOrder(String username){
        	
        	menuList();
        	addItem();
        	 System.out.println("------------------------------------------------------------------------------------------------------");
             System.out.println("----------------------------------------      Order Summary       ------------------------------------");
             System.out.println("------------------------------------------------------------------------------------------------------");
             System.out.println("Food Id"+"    "+"Food Name"+"         "+"Food Price"+"         "+"Quantity"); 
             if(FOOD_ITEM != null)
             {
            	 for(int i=0;i< FOOD_ITEM.size();i++)
            	 {
            		  System.out.println(FOOD_ITEM.get(i).getFoodId()+"         "+FOOD_ITEM.get(i).getfoodName()+"          "+FOOD_ITEM.get(i).getfoodPrice()+"          "+FOOD_ITEM.get(i).getQuantity());
            		     
            	 }
             }
             System.out.println("------------------------------------------------------------------------------------------------------");
             System.out.println("---------------------------------------------------------------------TOTAL : "+ TOTAL +"--------------");
             System.out.println("------------------------------------------------------------------------------------------------------");
             System.out.println("1. Add Item");
        	 System.out.println("2. Place Order");
        	 System.out.println("Select 1 Or 2 :");
        	 int choice = sc.nextInt();
             switch(choice){
             case 1:
            	 
            	 placeOrder(username);
                 break;
             case 2:
            	 String OrderId = createOrderNumber();
                 
                 if(FOOD_ITEM != null)
                 {
                	 if(WALLET_BALANCE < TOTAL)
                	 {
                		 System.out.println("Your Wallet Balance is low..Sorry!!");
                	 }
                	 else {
	                	 for(int i=0;i< FOOD_ITEM.size();i++)
	                	 {
	                		//  System.out.println(FOOD_ITEM.get(i).getFoodId()+"         "+FOOD_ITEM.get(i).getfoodName()+"          "+FOOD_ITEM.get(i).getfoodPrice()+"          "+FOOD_ITEM.get(i).getQuantity());
	                		int r= OrderFactory.OrderFood(OrderId,VENDOR_ID,username,FOOD_ITEM.get(i).getFoodId(),FOOD_ITEM.get(i).getQuantity(),FOOD_ITEM.get(i).getfoodPrice());
	                	 }
	                	 int s = CustomerFactory.updateWallet(WALLET_BALANCE-TOTAL, username);
	                	 
	                	  System.out.println("Order Placed.....");
                	 }
                 }break;
             default:
                 System.out.println("Choose option 1,2");
             }
       
        //Add other attributes to complete the functionality
        
      
        }
        
        
        public static String createOrderNumber() {
            String ts = String.valueOf(System.currentTimeMillis());
            String rand = UUID.randomUUID().toString();
            return rand;
        }
/**
 * this method is to fetch Menu list.
 */
        public static void menuList(){
        Menu m[]=OrderFactory.fetchMenu();
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("----------------------------------------         MENU                ---------------------------------");
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("Food Id"+"    "+"Food Name"+"    "+"Food Price"); 
        for(int i=0;i<m.length;i++){
              System.out.println(m[i].getFoodId()+"         "+m[i].getFoodName()+"          "+m[i].getFoodPrice());
           }
        System.out.println("------------------------------------------------------------------------------------------------------");
       System.out.println("Press Enter to Continue");
        try{System. in. read();}
        catch(Exception e){}
        
    }
        
        
        
        /**
         * this method is to fetch Menu list.
         */
                public static void VendormenuList(String username){
                Menu m[]=VendorFactory.fetchVendorMenu(username);
                System.out.println("------------------------------------------------------------------------------------------------------");
                System.out.println("----------------------------------------         MENU                ---------------------------------");
                System.out.println("------------------------------------------------------------------------------------------------------");
                System.out.println("Food Id"+"    "+"Food Name"+"    "+"Food Price"); 
                if(m != null)
                {
                for(int i=0;i<m.length;i++){
                      System.out.println(m[i].getFoodId()+"         "+m[i].getFoodName()+"          "+m[i].getFoodPrice());
                   }
                }
                System.out.println("------------------------------------------------------------------------------------------------------");
                
                System.out.println("Press Enter to Continue");
                try{System. in. read();}
                catch(Exception e){}
                
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
