package com.Hexaware.CMS;

import static org.junit.Assert.assertEquals;

import com.Hexaware.CMS.Model.Menu;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

/**
 * Unit test for Menu class.
 */
public class MenuTest 
{
    static Menu menu;
    @BeforeClass
    public static void beforeClass(){
        menu=new Menu(201,"Veggie Farmhouse Pizza",320,001);
    }
   
    @Test
    public  void testGet(){
       assertEquals(201,menu.getFoodId());
       assertEquals("Veggie Farmhouse Pizza",menu.getFoodName());
       assertEquals(320,menu.getFoodPrice()); 
    }

    @Test
    public void testToString(){
        String str=menu.toString();
        String expected = 
            "food id:"+menu.getFoodId()+
            "food Name:"+menu.getFoodName()+
            "food Price"+menu.getFoodPrice() + 
            "Vendor id :"+menu.getVendorId();
        assertEquals(expected,str);
    }
    @AfterClass
    public static void afterClass(){
        menu=null;
    }
}
