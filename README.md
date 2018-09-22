# Robot Project Readme

## Introduction

The main purpose of this project is to simply direct a robot throughout a pre-defined maze.
The robot is at the starting position in the beginning, and must either drive to the end position
or return back to the starting position in case the passage is obstructed by a dead end.

## Map Details

With this source code there is a map.txt file. This is the representation of the map in which the robot
moves by specific characters. They are as follows:

"*" = Walls
"." = Floors
"S" = Starting Position
"E" = End Position

When creating different maps, they need to utilize these specific symbols, otherwise the program
can't recognize the map.

## How to run

1. Use the .sql script provided with the source code to create the scheme that will contain the "addr" table with all the necessary fields
2. Configure the username and password fields to match the username and password of your MySQL connection (default values are "username" for username and "password" for password).

###### By .jar
1. Download the .jar file located in the out/artifacts/robotproject_jar folder
2. Run it through command line interface in the following format:

	java -jar robotproject.jar filepath
	
	Filepath indicates the location of the map file that has to be processed through the program
	in order to move the robot. Examples of usage are here:
	
	- java -jar robotproject.jar map.txt
	- java -jar robotproject.jar map/map.txt
	- java -jar robotproject.jar C:\Users\User\Map\map.txt
###### By Maven
1. Clone the repository
2. Run the command line interface and change directory to the project folder (robotproject)
3. The project needs to be built by using the following command: mvn package
4. Run the project with mvn exec:java -Dexec.args="filepath"
   Expected output should be similar to this example:
	![output example](https://i.imgur.com/zdhLpRE.png)
5. To run tests use:
	- mvn test to perform all of the tests
	- mvn test -Dtest=RobotMethodsTest to perform tests regarding RobotMethods class
	- mvn test -Dtest=RobotInitializeTest to perform tests regarding RobotInitialize class

## Notes

This project was developed using IntelliJ. Maven and JUnit were used for testing. The project was tested on Windows 8.1
with Java version 1.8.
