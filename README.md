# UCO Summer Camp Manager
This program has been created with the intention of managing the different summer camps that are offered by the University of Córdoba. This is a project of the Web Programming subject.

## Preinstallation requirements
- Working MySQL (or MariaDB) install
- MySQL connector (provided with the repository)
- Java 1.8.0 

## How to install
1. Create a database in your MySQL install
2. Create a new user, assgin a password to it
3. Grant all the privileges to that user for the database
4. Install the provided SQL file bundled into the scripts folder

Once you have a working database, you may now proceed to set up the program to make use of that database. In the config.properties file, you have to define the following lines in order to set up the database as follows:

```
db_url=jdbc:mysql://<server_url>:<sql_server_port>/<database_name>
db_user=<database_user>
db_pass=<user_password>
```

Make sure you replace the information with the information corresponding to your MySQL instance.

## Before running
Make sure to place the `mysql-connector.jar` in a `libs/` folder in the same folder of the program. Make sure also you have the sql.properties file and the config.properties file alongside the program. These are important files needed for the executiton of the program that provide the functionality for it to work.

## How to use
Once the database is set up, you have to run the provided jar file with the following command. Important, before running, make sure you have a Java 1.8 install in order to guarantee compatibility.

The command to run is the following:

```
$ java -jar <file_name>.jar
```

Once open, you will be greeted with a terminal interface that shows the functionality of the service. You may move around using the number keys, being 0 the key used to return to the previous menu. 

