User Can Create/Login using Contact Number.
User can view items, add/remove items to card and place the order.
Only written the api using spring boot to support above functionalities. 


Create User - http://localhost:8081/login

Login User -  http://localhost:8081/login - Fetch JWT Token From Header

Get All Available Items  - http://localhost:8081/api/item/

GetItemById- http://localhost:8081/api/item/1

AddItemsToCart - http://localhost:8081/api/cart/addToCart - Add Token in Header

PlaceOrderFromCart - http://localhost:8081/api/order/submit/9876543210 - Add Token in Header

