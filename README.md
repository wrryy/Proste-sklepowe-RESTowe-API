Simple shop REST API. Few functionalities. Using Hibernate with embedded HSQL db and Spring.
<strong> URLs: </strong>
<ul>
	<li> GET /products  	- returns list of all shop products
	<li> GET /items		 	- returns list of items in the basket
	<li> POST /items/{id} 	- put 1 product with {id} in the basket
	<li> PUT /items/{id} 	- removes 1 product with {id} from the basket
	<li> GET /baskets		- returns new user basket
	<li> PUT /baskets 		- returns total price of items in the basket
</ul>
<p> <strong> How to run </strong>
	<br>mvn clean package
	<br>java -jar sklep-0.0.1-SNAPSHOT.jar
</p>

Assumptions: 
<ol>
	<li> User is already logged in and his ID stored within the session. </li>
	<li> Had issues with importing prepared sql data so made a workaround - user and products are now being persisted on application startup </li>
	<li>Made few integration tests, if had more time code covered would increase </li>
</ol>
	
