package com.Hexaware.CMS.Model;

public class Vendor {
  private int venId;  // Vendor.vendor_id  rs.getInt("vendor_id")
  private String venName;   // rs.getString("vendor_name")
  private String venPhone;  // rs.getString("vendor_phone")
  private String venSpecs;  // rs.getString("vendor_specs")

  public Vendor(int venId, String venName, 
        String venPhone, String venSpecs) {
    this.venId = venId;
    this.venName = venName;
    this.venPhone = venPhone;
    this.venSpecs = venSpecs;
  }

  public int getVenId() {
    return venId;
  }

  public void setVenId(int venId) {
    this.venId = venId;
  }

  public String getVenName() {
    return venName;
  }

  public void setVenName(String venName) {
    this.venName = venName;
  }

  public String getVenPhone() {
    return venPhone;
  }

  public void setVenPhone(String venPhone) {
    this.venPhone = venPhone;
  }

  public String getVenSpecs() {
    return venSpecs;
  }

  public void setVenSpecs(String venSpecs) {
    this.venSpecs = venSpecs;
  }

  @Override
  public String toString() {
    return "Vendor [venId=" + venId + ", venName=" + venName 
            + ", venPhone=" + venPhone + ", venSpecs=" + venSpecs
            + "]";
  }

  // generate the getter and setters for this
  // default constructor, all argument constructor
  // toString - do not show password

}