#!/bin/bash
# This script is used for svn project openproperty codes back-up into cvs repossitory
echo "Initializing..."
echo ""
project="$1"
path="$2"
backup_dir=/home/svn/back-up/"$project"
svnURL="file:///home/svn/repos/$project"/"$path"
bleumcvsroot=":pserver:jenkins:cvs,123456@192.168.2.200:/1fb"
echo "Getting latest svn codes..."
echo ""
if [ ! -d "$backup_dir" ]
  then
	mkdir -p "$backup_dir"
	source cd "$backup_dir"
	svn co -r HEAD "$svnURL" .
else
	source cd "$backup_dir"
	svn up -r HEAD "$svnURL" .
fi
echo "C latest svn codes..."
echo ""
cvs -d $bleumcvsroot import
