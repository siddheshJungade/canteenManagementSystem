package com.Hexaware.CMS.Persistence;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.Hexaware.CMS.Model.Menu;
import com.Hexaware.CMS.Model.Order_;

import java.sql.PreparedStatement;

/**
 * OrderDb class used to connect to data base.
 * @author hexware
 */
public class OrderDb {
   static int i;
   
    public static int insertDb(String Order_Id,String VENDOR_ID,String Username,String Food_Id,int Quantity,Date ETA,Date DateTime,float Order_Value,String OrderStatus,String Reason){        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");  
           Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/CMSDB","root","Password123"); 
            PreparedStatement stmt=con.prepareStatement("insert into order_(ORDER_ID,VENDOR_ID,USERNAME,FOOD_ID,QUANTITY,ESTIMATED_TIME,DATE_TIME,ORDER_VALUE,ORDER_STATUS,REASON) values(?,?,?,?,?,?,?,?,?,?)");  
        stmt.setString(1,Order_Id);  
        stmt.setString(2,VENDOR_ID);  
        stmt.setString(3,Username);
        stmt.setString(4,Food_Id);   
        stmt.setInt(5,Quantity);
        stmt.setObject(6,ETA);
        stmt.setObject(7,DateTime);   
        stmt.setFloat(8,Order_Value);
        stmt.setString(9,OrderStatus);   
        stmt.setString(10,Reason);
        i=stmt.executeUpdate();  
        //System.out.println(i+" records inserted");  
        }catch(Exception e){ System.out.println(e);}  
                return i;
            }  

    public static Menu[] fetchDb(){
        Menu m[]=null;
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
           Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/CMSDB","root","Password123");
            Statement stmt=con.createStatement();  
                    
            ResultSet rs=stmt.executeQuery("select * from menu");  
            ArrayList<Menu> list=new ArrayList<Menu>();          
            while(rs.next()) { 
            list.add(new Menu(rs.getString(1),rs.getString(2),rs.getFloat(3),rs.getString(4)));
                
                  } 
            m=new Menu[list.size()];
            m= list.toArray(m);
        }catch(Exception e){ System.out.println(e);}  
            
        return m;      
        }

    
    
    public static Order_[] fetchOrderHistoryByCustomer(String username){
        Order_ m[]=null;
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
           Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/CMSDB","root","Password123");
            Statement stmt=con.createStatement();  
                    
            ResultSet rs=stmt.executeQuery("select * from order_ where username='"+username+"'");  
            ArrayList<Order_> list=new ArrayList<Order_>();      
            //System.out.println(rs.getFetchSize());
            while(rs.next()) { 
            	//System.out.println("hi");
            	Order_ obj = new Order_();
            	//System.out.println("hi3");
            	obj.setO_ID(rs.getInt(11));
            	//System.out.println("hi4");
            	obj.setORDER_ID(rs.getString(1));
            	//System.out.println("hi5");
            	obj.setVENDOR_ID(rs.getString(2));
            	//System.out.println("hi6");
            	obj.setUSERNAME(rs.getString(3));
            	//System.out.println("hi7");
            	obj.setFOOD_ID(rs.getString(4));
            	//System.out.println("hi8");
            	obj.SetQUANTITY(rs.getInt(5));
            	//System.out.println("hi9");
            	obj.setESTIMATED_TIME(rs.getDate(6));
            	//System.out.println("hi10");
            	obj.setDATE_TIME(rs.getDate(7));
            	//System.out.println("hi11");
            	obj.SetORDER_VALUE(rs.getFloat(8));
            	//System.out.println("hi12");
            	obj.setORDER_STATUS(rs.getString(9));
            	//System.out.println("hi13");
            	obj.setREASON(rs.getString(10));
            	//System.out.println("hi14");
            	//System.out.println(obj.getFOOD_NAME());
            	list.add(obj);
                m=new Order_[list.size()];
                m= list.toArray(m);
                  } 
        }catch(Exception e){ System.out.println("Error2:"+e);}  
            
        return m;      
        }
      //  public static Customer customerProfileDb(){}
      // public static Vendor vendorProfileDb(){}
      // public static order[] customerOderHistoryDb(){}
      // public static Vendor[] vendorOderHistoryDb(){}
      // public static String acceptRejectDb(){}
    }

