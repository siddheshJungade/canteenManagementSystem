package com.Hexaware.CMS.Model;

/**
 * food class used to display food information.
 * @author hexware
 */
public class Menu {
    private String foodId;
    private String foodName;
    private float foodPrice;
    private String VendorId;

    public Menu(){}

    public Menu(String foodId,String foodName, float foodPrice,String VendorId){
        this.foodId=foodId;
        this.foodName=foodName;
        this.foodPrice=foodPrice;
        this.VendorId = VendorId;

    }
   

	public void setFoodId(String foodId){
        this.foodId=foodId;
    }

    public String getFoodId(){
        return foodId;
    }

    public void setFoodName(String foodName){
        this.foodName=foodName;
    }
    public void setVendorId(String VendorId){
        this.VendorId=VendorId;
    }
    public String getVendorId(){
        return this.VendorId;
    }

    public String getFoodName(){
        return foodName;
    }

    public void setFoodPrice(float foodPrice){
        this.foodPrice=foodPrice;
    }

    public float getFoodPrice(){
        return foodPrice;
    }
    
    public String toString(){
        return "food id:"+foodId+"food Name:"+foodName+"food Price"+foodPrice;
    }
}
