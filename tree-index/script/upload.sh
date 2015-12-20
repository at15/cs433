#!/usr/bin/env bash

source ./export.sh

echo "remove old"
hdfs dfs -rm /user/at15/tree-index/input/*
hdfs dfs -rm /user/at15/tree-index/output/*
echo "upload new"
#hdfs dfs -put ../data/duplicate/u.data /user/at15/tree-index/input
hdfs dfs -put ../data/duplicate/u.user /user/at15/tree-index/input
