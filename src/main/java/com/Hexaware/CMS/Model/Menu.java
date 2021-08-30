package com.Hexaware.CMS.Model;

/**
 * food class used to display food information.
 * @author hexware
 */
public class Menu {
    private int foodId;
    private String foodName;
    private int foodPrice;

    public Menu(){}

    public Menu(int foodId,String foodName, int foodPrice){
        this.foodId=foodId;
        this.foodName=foodName;
        this.foodPrice=foodPrice;

    }
    public void setFoodId(int foodId){
        this.foodId=foodId;
    }

    public int getFoodId(){
        return foodId;
    }

    public void setFoodName(String foodName){
        this.foodName=foodName;
    }

    public String getFoodName(){
        return foodName;
    }

    public void setFoodPrice(int foodPrice){
        this.foodPrice=foodPrice;
    }

    public int getFoodPrice(){
        return foodPrice;
    }
    public String toString(){
        return "food id:"+foodId+"food Name:"+foodName+"food Price"+foodPrice;
    }
}
