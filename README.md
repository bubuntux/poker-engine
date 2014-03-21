Java Poker Engine
===================


Introduction
------------

This project has been created as a platform to experiment with a Texas Hold'em poker game in Java.

Its primary goal is to offer a clean, open source Texas Hold'em poker engine in Java. The game
engine strives to follow the famous Robert's Rules of Poker as close as possible. In order to test
the game engine, the project also offers a Swing GUI and bots.

This project is provided under the Apache License 2.0.


Status [![Build Status](https://travis-ci.org/bubuntux/poker-engine.png?branch=master)](https://travis-ci.org/bubuntux/poker-engine)
------

The current implementation support the Fixed-Limit and No-Limit variants of the game with a Swing
GUI, with the human player playing against 3 bots.

Usage
-----

```
<repositories>
  <repository>
     <id>bubuntux-repo</id>
     <url>https://raw.github.com/bubuntux/mvn/repo/</url>
  </repository>
</repositories>
```
```
<dependency>
    <groupId>org.dsaw</groupId>
    <artifactId>poker-engine</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```
TODO....

Building and running
--------------------

This Java project has a Maven structure. Build the project with "mvn clean install" and find the
binary in the 'target' directory. Run the game with "java -jar texasholdem.jar".

Origin
------

This project is fork of [texasholdem-java](https://code.google.com/p/texasholdem-java/) by Oscar Stigter
