package com.Hexaware.CMS.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.Hexaware.CMS.Model.Menu;

import java.sql.PreparedStatement;

/**
 * OrderDb class used to connect to data base.
 * @author hexware
 */
public class OrderDb {
   static int i;
   
    public static int insertDb(int fid,String fname,int fprice,int fq,int foodTotal){        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");  
           Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/CMSDB","root","Password123"); 
            PreparedStatement stmt=con.prepareStatement("insert into orderfood values(?,?,?,?,?)");  
        stmt.setInt(1,fid);  
        stmt.setString(2,fname);  
        stmt.setInt(3,fprice);
        stmt.setInt(4,fq);   
        stmt.setInt(5,foodTotal);
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
            list.add(new Menu(rs.getInt(1),rs.getString(2),rs.getInt(3)));
                m=new Menu[list.size()];
                m= list.toArray(m);
                  } 
        }catch(Exception e){ System.out.println(e);}  
            
        return m;      
        }

      //  public static Customer customerProfileDb(){}
      // public static Vendor vendorProfileDb(){}
      // public static order[] customerOderHistoryDb(){}
      // public static Vendor[] vendorOderHistoryDb(){}
      // public static String acceptRejectDb(){}
    }

