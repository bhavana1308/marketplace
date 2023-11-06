# Marketplace Application

The Marketplace application is a simple web-based platform that seamlessly integrates SQL queries executed through MyBatis and allows Users can log in, explore product listings, add items to their cart, remove items from their cart, calculate loyalty points, generate purchase orders, manage buyer information, and delete buyers.User logins are validated against a "buyers" table in a MySQL database.



- Java
- JavaScript
- React
- Spring Boot
- MyBatis
- Thymeleaf
- MySQL


## Usage
### Log In
Launch the application in your web browser.
Access your account by entering the login credentials stored in the "buyers" table of the database.
### Manage Buyers
Within the "buyers" table, you have the capability to add new buyers ,update the details of existing buyers and delete the buyers as required.
### Explore Product Listings
You can choose to view the entire product catalog or refine your selection by filtering products based on their categories or product names.
### Add Items to Your Cart
After logging in, simply pick a product from the filtered list to include it in your shopping cart. The total cost of the items in your cart will be caluclated automatically.
### Remove Items from Your Cart
If you wish to remove a product from your cart, you can do so by clicking the delete button associated with that item.
### Generate Purchase Orders
Purchase orders are generated related to Buyer.
### Calculate Loyalty Points
The application calculates loyalty points based on a buyer's purchase amount.
### Finalize Your Order
To complete your purchase, click on the "Checkout" button. You'll then receive a confirmation of your successful order with Order Number.

