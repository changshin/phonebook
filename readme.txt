========== It is tested: ===========

On Mac Sierra version 10.12.5
Java version: 1.7.0_80
Tomcat 7
Spring boot 1.5.2 and MVC
MySQL: 5.6.25 MySQL Community Server (GPL)
node version :v6.9.1
npm version : 4.0.5
Yeoman
Grunt

Please check Yeoman_setup.txt in the ng-phone-ui folder, if you have an issue


========= How to create MySQL Database ===========

In terminal
Go to the folder you downloaded.
I used it:
cd /Users/chang/projects/ng-phone/docs

mysql -uroot -p < phonebook.sql

or 

mysql -uroot -p 
type [your_own_password]
create database phonebook
exit
mysql -uroot -p  phonebook< phonebook.sql

If you has your own mysql root password, 
Please change the password in the application.properties in the ng-phone-api/src/main/resources folder


=========== Back end API: ===============

Java version: 1.7.0_80
MySQL: 5.6.25 MySQL Community Server (GPL)
Spring boot 1.5.2 and MVC

Cross-Origin Resource Sharing (CORS) option is used for demo task
log4j, phonebook.log is in /var/log/tomcat7/ folder

Next version: 
1. Login and Security
2. Avatar upload for user and groups
3. Thumbnail for performance
4. Add userreCAPTCHA for UI security
5. Dashboard
6. Schedulers to create useful report data
7. Need db cache
8. Download excel or pdf report from db
9. Email Notification



============ Front end (UI) =================

Node.js
NPM
AngualrJS 1.6, Local language support sample
Grunt
Use bootstrap css
Less
CSS Responsive
Local language support files are in common/json folder

There are four UIs

1. FAQ
2. Login UI is  simple UI to show login. Just type any word and click "submit"
3. User UI:  add new user, table sorting, multi selection to delete users
3. Groups UI: pie chart, view users from clicking number or click the group part of pie chart, multi selection to delete user on modal window
   You can not delete the groups with users. without users, you can delete the groups.

Next Version: 

1. Inline css should be changed to css file.
2. International phone format.
3. User or group avatar image cropping.
4. Add more the detail for local language support.
5. Welcome information after logged in with email message.



=========== How to test it ================

Go to the folder in terminal
cd /[downaloaded folder/ng-phone

1. Create war file
   mvn clean install -DskipTests=true: When it is done, you can the below messages:
..
..
[INFO] Reactor Summary:
[INFO] 
[INFO] Phonebook web demo application .................... SUCCESS [0.363s]
[INFO] ng-phone api ...................................... SUCCESS [5.672s]
[INFO] ng-phone UI ....................................... SUCCESS [1:46.336s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 1:52.760s
[INFO] Finished at: Thu Jun 01 15:14:43 PDT 2017
[INFO] Final Memory: 37M/459M

2. Start maven command to start tomcat 7

   mvn tomcat7:run

..
 .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.5.2.RELEASE)

3. Test it on browser
   localhost:8080
