package com.Hexaware.CMS.Factory;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import com.Hexaware.CMS.Model.Menu;
import com.Hexaware.CMS.Model.Order_;
import com.Hexaware.CMS.Persistence.OrderDb;

/**
 * OrderFactory class used to fetch and insert data to database.
 * @author hexware
 */
public class OrderFactory {
    
    public static int OrderFood(String OrderId,String VendorID,String Username,String FoodId,int Quantity,float FoodPrice ){
        float foodTotal=Quantity*FoodPrice;
       int result= OrderDb.insertDb(OrderId,VendorID,Username,FoodId,Quantity,new Date(),new Date(),foodTotal,"Open","");
       return result;
    }

    public static Menu[] fetchMenu(){
        Menu menu[]=OrderDb.fetchDb();
        return menu;
    }
    public static Order_[] fetcOrderHistoryByCustomer(String username){
    	Order_ oh[]=OrderDb.fetchOrderHistoryByCustomer(username);
    	//System.out.println("Rows:"+oh.length);
        return oh;
    }
    // public static Customer customerProfile(){}
    // public static Vendor vendorProfile(){}
    // public static Order[] customerOrderHistory(){}
    // public static Order[] vendorOrderHistory(){}
    // public static String acceptRejectOrder(){}
}
