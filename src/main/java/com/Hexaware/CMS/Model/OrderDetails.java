package com.Hexaware.CMS.Model;

public class OrderDetails {
  private int orderNo;
  private int venderId;
  private String customerId;
  private int foodId;
  private int quantity;
  private String datetime;
  private int orderValue;
  private String orderStatus;

  public OrderDetails(int orderNo, int venderId, String customerId,
      int foodId, int quantity, String datetime,
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
    return "OrderDetails [customerId=" + customerId + ", datetime=" + datetime + ", foodId=" + foodId
        + ", orderNo=" + orderNo + ", orderStatus=" + orderStatus + ", orderValue=" + orderValue + ", quantity="
        + quantity + ", venderId=" + venderId + "]";
  }

  public int getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(int orderNo) {
    this.orderNo = orderNo;
  }

  public int getVenderId() {
    return venderId;
  }

  public void setVenderId(int venderId) {
    this.venderId = venderId;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public int getFoodId() {
    return foodId;
  }

  public void setFoodId(int foodId) {
    this.foodId = foodId;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public String getDatetime() {
    return datetime;
  }

  public void setDatetime(String datetime) {
    this.datetime = datetime;
  }

  public int getOrderValue() {
    return orderValue;
  }

  public void setOrderValue(int orderValue) {
    this.orderValue = orderValue;
  }

  public String getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(String orderStatus) {
    this.orderStatus = orderStatus;
  }

  
  // generate the getter and setters for this
  // default constructor, all argument constructor
  // toString - do not show password
}