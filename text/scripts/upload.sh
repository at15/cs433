#!/usr/bin/env bash
source common.sh
echo "upload fixture to hdfs"
${DFS} -put ../fixtures/* /user/at15/input
