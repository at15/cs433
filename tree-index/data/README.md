# Data

## ml-100k

### Hive

https://cwiki.apache.org/confluence/display/Hive/GettingStarted

create the table

````
CREATE TABLE u_data (
  userid INT,
  movieid INT,
  rating INT,
  unixtime STRING)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\t'
STORED AS TEXTFILE;
````

load data

````
LOAD DATA LOCAL INPATH '/home/at15/workspace/src/github.com/at15/cs433/tree-index/data/ml-100k/u.data'
OVERWRITE INTO TABLE u_data;
````# Data

## ml-100k

### Hive

https://cwiki.apache.org/confluence/display/Hive/GettingStarted

create the table

````
CREATE TABLE u_data (
  userid INT,
  movieid INT,
  rating INT,
  unixtime STRING)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\t'
STORED AS TEXTFILE;
````

load data

````
LOAD DATA LOCAL INPATH '/home/at15/workspace/src/github.com/at15/cs433/tree-index/data/ml-100k/u.data'
OVERWRITE INTO TABLE u_data;
````# Data

## ml-100k

### Hive

https://cwiki.apache.org/confluence/display/Hive/GettingStarted

create the table

````
CREATE TABLE u_data (
  userid INT,
  movieid INT,
  rating INT,
  unixtime STRING)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\t'
STORED AS TEXTFILE;
````

load data

````
LOAD DATA LOCAL INPATH '/home/at15/workspace/src/github.com/at15/cs433/tree-index/data/ml-100k/u.data'
OVERWRITE INTO TABLE u_data;
````

````
LOAD DATA LOCAL INPATH '/home/at15/workspace/src/github.com/at15/cs433/tree-index/data/duplicate/u.data'
OVERWRITE INTO TABLE u_data;
````