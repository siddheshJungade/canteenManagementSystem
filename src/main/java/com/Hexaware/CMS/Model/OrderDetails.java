package com.Hexaware.CMS.Model;

 

public class OrderDetails {
   private int orderNo;
   private int venderId;
   private String customerId;
   private int foodId;
   private int quantity;   private String datetime;
   private int  orderValue;
   private String orderStatus;

   public OrderDetails(int orderNo, int venderId, String customerId, 
        int foodId, int quantity, String  datetime,
       int orderValue, String orderStatus) {
     this.orderNo = orderNo;
     this.venderId = venderId;
     this.customerId = customerId;
     this.foodId = foodId;
     this.quantity = quantity;
     this.datetime = datetime;
     this.orderValue = orderValue;
     this.orderStatus = orderStatus;
   }

   @Override
   public String toString() {
     return "OrderDetails [customerId=" + customerId + ", datetime=" + datetime +  ", foodId=" + foodId
         + ", orderNo=" + orderNo + ", orderStatus=" + orderStatus + ", orderValue=" + orderValue + ", quantity="
         + quantity + ", venderId=" + venderId + "]";
   }

   

  // generate the getter and setters for this
  // default constructor, all argument constructor
  // toString - do not show password
}