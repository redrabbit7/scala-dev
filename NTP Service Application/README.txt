Author: Sahithi Reddigari

About the application
----------------------
This simple NTP service is implemented using the akka Actor-concurrency model in Scala.
Lift MVC framework was utilized in order to input the number of consumers through a portal.
The console output displays the activity between Consumer actors and Producer actor

Tools/Environment for Running
------------------------------
SBT - Version 0.13.1
Java Runtime Environment (JRE) - version 1_.7.0_51-b13

Instructions for Downloading, Building, and Running Application:
----------------------------------------------------------------

Please download the code from github repository: https://github.com/redrabbit7/scala-dev.git
Additionally, if you need to install sbt (Simple Build Tool), please obtain the latest
version from http://www.scala-sbt.org/release/docs/Getting-Started/Setup

To run the application in Unix (or Windows Commandline):
> sbt
> container:start


When finished, type exit at the SBT prompt to stop the application from running.