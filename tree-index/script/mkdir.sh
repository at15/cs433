#!/usr/bin/env bash

source ./export.sh

echo "mkdir"

hdfs dfs -mkdir /user
hdfs dfs -mkdir /user/at15
hdfs dfs -mkdir /user/at15/tree-index
hdfs dfs -mkdir /user/at15/tree-index/input
# TODO: don't know if partition require create folder
hdfs dfs -mkdir /user/at15/tree-index/part
