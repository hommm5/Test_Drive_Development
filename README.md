# Test_Drive_Development
Lab – Test Driven Development

This document defines the lab for "Java OOP" course @ Software University.

INStock

Pesho has been struggling lately. He is a major shareholder at one of the largest product manufacturers in the world. As such he is always looking for new ways to improve his game and stay on the top. He has hired you (because you are from SoftUni, therefore you are a grandmaster programmer) to create a product tracking system for him. He has a lot of products in stock so you have to make the system really fast.

•	add(Product) – Add the new manufactured Product in stock. You will need to implement the contains() method as well.

•	contains(Product) – Checks if a particular product is in stock. *Keep in mind that only labels are unique.

•	count – Returns the number of products currently in stock.

•	find(int) – Return the N-th product that was added in stock. The index is based on insertion order in the data structure. If such index is not present, throw IndexOutOfBoundsException.

•	changeQuantity(String,int) –Changes the quantity of a given product by n-amount. If the particular product is not in stock, throw IllegalArgumentException.

•	findByLabel(String) – Returns the product with a given label, throws IllegalArgumentException if no such product is in stock.

•	findFirstByAlphabeticalOrder(int) – Returns the first N-th products in stock ordered by label in alphabetical order or returns an empty collection if the passed argument is out of range.

•	findAllInPriceRange(double,double) – Returns all products within given price range (lower end is exclusive, higher end is inclusive). Keep in mind that they should be returned in descending order. If there are no such products, return empty collection.

•	findAllByPrice(double) – Returns all products in stock with given price or empty collection if none were found.

•	findFirstMostExpensiveProducts(int) – Returns the first N products with highest price in stock or throw IllegalArgumentException if less than counts exist.

•	findAllByQuantity(int) – Returns all products in stock with given remaining quantity. If there is no product with identical quantity, return empty collection.

•	getIterable<Product>() – Returns all products in stock.
