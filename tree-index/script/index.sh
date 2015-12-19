#!/usr/bin/env bash

source ./export.sh


echo "upload list file to index"

echo "remove old list file"
hdfs dfs -rm /user/at15/tree-index/sorted.list

echo "upload new list file"
hdfs dfs -put sorted.list /user/at15/tree-index/
echo "index"
hadoop jar ../target/tree-index-0.0.1.jar index