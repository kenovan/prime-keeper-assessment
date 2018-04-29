# prime-keeper-assessment #
Prime Keeper Techinical Assessment

## Specification ##

* Spring Boot 2.0.1 RELEASE
* JAVA 8
* MySQL
* Gradle
* LiquidBase
* Embedded Tomcat 8.5

## Configuration properties in application.properties ##

*Data Source Configuration (Change to point to own mysql database and user login)*

    spring.datasource.url=jdbc:mysql://localhost:3306/application?createDatabaseIfNotExist=true&useSSL=false
    spring.datasource.hikari.jdbc-url=jdbc:mysql://localhost:3306/application?createDatabaseIfNotExist=true&useSSL=false
    spring.datasource.hikari.username=kenovan
    spring.datasource.hikari.password=kenovan

*LiquidBase Configuration (Change to point to own mysql database and user login)*

    spring.liquibase.url=jdbc:mysql://localhost:3306/application?createDatabaseIfNotExist=true&useSSL=false
    spring.liquibase.user=kenovan
    spring.liquibase.password=kenovan

## Gradle build and run ##
1. Ensure your enviroment have gradle install in your machine.
2. Go to checkout/download project directory, execute `gradle clean build`.
3. After build, execute `gradlew bootJar` which will packing the application as Jar file.
4. Under project directory, under this folder `build\libs` will consist of `PrimeKeeperAssessment-0.0.1-SNAPSHOT.jar`.
5. Based on jar file path, use `java -jar PrimeKeeperAssessment-0.0.1-SNAPSHOT.jar` to startup the application. (Please make sure use `java -version` to verify java 8 and above are install in machine.

## API List ##
1. **User Registration API** `[POST]` `http://localhost:8080/api/register?username={input username}&password={input password}&role={input user role}` 
	- input username: user name to be register and use for login
	- password: user password for login purpose
	- role: user role, you may refer back to database table application.app_role. You also can create a new role.
	- Sample cURL:
		- Request: `curl -X POST "http://localhost:8080/api/register" -d "username=test123&password=12345678&role=merchant"`
		- Response: `{"code":"000","data":{"id":4,"userName":"test123","createDate":"29-04-2018 09:20:29"}}`
2. **User Login API** `[POST]` `http://localhost:8080/api/login?username={input username}&password={password}`
	- input username: user name to be register and use for login
	- password: user password for login purpose
	- Sample cURL:
		- Request: `curl -X POST "http://localhost:8080/api/login" -d "username=test123&password=12345678"`
		- Response: `{"id":11,"userId":4,"userSession":"3660ED666BA4CF6B5A422004DD7D4833","userToken":"14bK1npMnBJKApkI70xOyaw12Ek=","tokenExpired":"29-04-2018 10:24:26","createDate":"29-04-2018 09:24:26"}`
		- **Noted that response value "userToken" will be use for action that need authentication (together with session id), add header name "auth-token" and header value as {userToken value}**
3.  **User Name Lookup Information API** `[GET]` `http://localhost:8080/api/user/lookup?username={input username}`
	- input username: user name to be lookup information
	- Request: Suggest use Postman to simulate due to need to depend on session and auth-token for authentication
	- Sample Response: `{"code":"000","data":{"id":1,"userName":"kenovan","createDate":"29-04-2018 05:42:04","appUserRoles":[{"userId":1,"roleId":1,"appRole":{"id":1,"roleName":"customer","roleDescription":"Customers","createDate":"29-04-2018 05:42:04"}}],"appUserAccounts":[{"id":1,"userId":1,"createDate":"29-04-2018 05:42:04"}]}}`
4.  **Own Account Information API** `[GET]` `http://localhost:8080/api/user/info`
	- Need to be after login(depend on session and auth-token), use Postman to simulate
	- Sample Response: `{"code":"000","data":{"id":1,"userName":"kenovan","createDate":"29-04-2018 05:42:04","appUserRoles":[{"userId":1,"roleId":1,"appRole":{"id":1,"roleName":"customer","roleDescription":"Customers","createDate":"29-04-2018 05:42:04"}}],"appUserAccounts":[{"id":1,"userId":1,"balanceAmount":990,"createDate":"29-04-2018 05:42:04"}]}}` 
5. **Transfer amount by user name lookup API** `[POST]` `http://localhost:8080/api/transaction/send-using-recipient-name?username={recipient name}&amount={amount}`
	- Need to be after login(depend on session and auth-token), use Postman to simulate
	- recipient name: receiver username
	- amount: total amount to be transfer to recipient (in cent unit)
	-  Sample Response: `{"id":1,"userId":1,"balanceAmount":890,"createDate":"29-04-2018 05:42:04"}`
6. **Transfer amount by recipient account ID API** `[POST]` `http://localhost:8080/api/transaction/send-using-recipient-account-id?account-id={recipient account id}&amount={amount}`
	-  Need to be after login(depend on session and auth-token), use Postman to simulate
	-  recipient account id: Can use User Name Lookup Information API to get recipient account number
	-  amount: total amount to be transfer to recipient (in cent unit)
	-  Sample Response: `{"id":1,"userId":1,"balanceAmount":890,"createDate":"29-04-2018 05:42:04"}`
7. **User Logout API** `[POST]` `http://localhost:8080/api/logout`
	- Need to be after login(depend on session and auth-token), use Postman to simulate
	- Update token to be expired.


