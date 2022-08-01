package com.Hexaware.CMS.Model;

public class Customer {

  private String custId;
  private String custName;
  private String custPhone;
  private String custEmail;
  private int custWalletBalance;
  private int custLoginId;
  private String custPassword;
  // generate the getter and setters for this
  // default constructor, all argument constructor
  // toString - do not show password
  
  
  public String getCustId() {
    return custId;
  }

  public void setCustId(String custId) {
    this.custId = custId;
  }

  public String getCustName() {
    return custName;
  }

  public void setCustName(String custName) {
    this.custName = custName;
  }

  public String getCustPhone() {
    return custPhone;
  }

  public void setCustPhone(String custPhone) {
    this.custPhone = custPhone;
  }

  public String getCustEmail() {
    return custEmail;
  }

  public void setCustEmail(String custEmail) {
    this.custEmail = custEmail;
  }


  public int getCustWalletBalance() {
    return custWalletBalance;
  }

  public void setCustWalletBalance(int custWalletBalance) {
    this.custWalletBalance = custWalletBalance;
  }

  public int getCustLoginId() {
    return custLoginId;
  }

  public void setCustLoginId(int custLoginId) {
    this.custLoginId = custLoginId;
  }

  public String getCustPassword() {
    return custPassword;
  }

  public void setCustPassword(String custPassword) {
    this.custPassword = custPassword;
  }

  public Customer(String custId, String custName, 
                  String custPhone, String custEmail, 
                  int custWalletBalance,
                  int custLoginId, String custPassword) {
    this.custId = custId;
    this.custName = custName;
    this.custPhone = custPhone;
    this.custEmail = custEmail;
    this.custWalletBalance = custWalletBalance;
    this.custLoginId = custLoginId;
    this.custPassword = custPassword;
  }

  @Override
  public String toString() {
    return "Customer [custEmail=" + custEmail + ", custId=" + custId + ", custLoginId=" + custLoginId + ", custName="
        + custName +  ", custPhone=" + custPhone + ", custWalletBalance="
        + custWalletBalance + "]";
  }
  

}