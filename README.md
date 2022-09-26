# canteenManagementSystem

Java Cli project build using mvn,JDBC & Mysql database.

## Purpose
Parpose of the project is to build CLI for Vender & Customer where customer can buy the food item and at any quentity if food item.

## As a customer,
   * can view personal and wallet details 
   * can order food items
	* can check the history of  ordered food items 

## As a Food Vendor,
   * can view and update (accept/deny) the ordered food items status 

## Customer Login:
   * Shows list of available food items (menu) details.
   * Shows personal and Wallet details.
   * Customer can order food.
   * The order will be accepted only,when the customer had enough money for the ordered food in
	  His/her wallet.
   * can check the status of their delivery.
   * can check their order history.  
   
## Food Vendor :
   * Customer can view his personal detail and order history.
	* can view the order request from customers.
	* can accept or deny the entire order.
 * customer details (custId, custName, etc..) are stored along with their wallet balance.
 * Customer can place their order in this portal. These orders go to the food vendor.
 * Food Vendor can accept/deny the order with proper comments.
 * Wallet balance should not go negative.
 * Customer can view their order status.

# Application flow
The skeletal application contains a simple screen with list of food items (Menu).
  * The "place order" button is the default button (i.e., pressing "Place order" invokes the login function).
  * Customer should enter his customer id will take the customer to "place the order screen" along with the food id he selected.
  * Customer should enter the item id and quantity and "Place Order" button to place the order 
  * On existing balance amount in customer Wallet, the order should be accepted or rejected with proper error message.
  * If the order is accepted, the token number should be generate using Random functionality and the order detail should stored 
    in order table along with token number.
  * The token number displayed as confirmation of the order should be fetched from the database.
  * "Back to Menu" takes the customer back to the Menu list screen from all the screen.
  * Entering the "Vendor Admin Login” button in the Home screen will take to the Food Vendor login. 


What I learn... 
- Java mvc pattern
- mvn package manaager
- JDBC driver 
- Mysql DataBase
