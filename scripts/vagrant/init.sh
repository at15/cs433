#!/usr/bin/env bash

echo "let's do some warm up"

sudo apt-get update
echo "install tools curl, git, vim"
sudo apt-get install -y curl git vim

echo "hadop require ssh, rsync"
sudo apt-get install -y ssh rsync

echo "hadoop need to ssh to loaclhost without password"
ssh-keygen -t dsa -P '' -f ~/.ssh/id_dsa
cat ~/.ssh/id_dsa.pub >> ~/.ssh/authorized_keys
# export HADOOP\_PREFIX=/usr/local/hadoop # TODO: Never tried this, don't know why it is in the doc

echo "time to config java and hadoop"