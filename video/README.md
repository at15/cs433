# Video stream

This project stream a video file and the client receive and save it to hdfs. 

## Requirement

- JDK7 installed, JAVA_HOME set
- maven (if you want to repackage)

- MUST have vlc installed
- MUST have same arch for JDK and vlc,  both x64 or both x86
- Set the native path in code. `StreamClient.java` and `StreamServer.java`. TODO: support pass it from cli
- windows need to use old version vlc https://github.com/at15/cs433/issues/3

- Have a hdfs you can visit. NOTE: using linux virtual machine and port mapping will get the `There are 1 datanode(s) running and 1 node(s) are excluded in this operation`. see https://github.com/at15/cs433/issues/5

## Stream server 

using `vlcj`, 

server can stream only one file at a time.

## Stream client

using `vlcj`. You need to start the server first, otherwise there would be unexpected behaviour for the client, ie: quite 
right after start.

client can receive only one file at a time

## HDFS client

upload one file at a time

- CAN NOT upload to remote hdfs, don't know how to solve it. https://github.com/at15/cs433/issues/5