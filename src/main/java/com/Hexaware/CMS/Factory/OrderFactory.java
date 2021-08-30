package com.Hexaware.CMS.Factory;

import com.Hexaware.CMS.Model.Menu;
import com.Hexaware.CMS.Persistence.OrderDb;

/**
 * OrderFactory class used to fetch and insert data to database.
 * @author hexware
 */
public class OrderFactory {
    
    public static int OrderFood(int fid,String fname,int fprice,int fquan){
        int foodTotal=fquan*fprice;
       int result= OrderDb.insertDb(fid,fname,fprice,fquan,foodTotal);
       return result;
    }

    public static Menu[] fetchMenu(){
        Menu menu[]=OrderDb.fetchDb();
        return menu;
    }

    // public static Customer customerProfile(){}
    // public static Vendor vendorProfile(){}
    // public static Order[] customerOrderHistory(){}
    // public static Order[] vendorOrderHistory(){}
    // public static String acceptRejectOrder(){}
}
