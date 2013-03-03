#!/bin/bash
# This script is used for svn project openproperty codes back-up into cvs repossitory
echo "Initializing..."
echo ""
project="$1"
svn_path="$2"
cvs_path="$3"
svn_dir=/home/svn/back-up/"$project"
cvs_dir=/home/svn/cvs/"$project"
svnURL="file:///home/svn/repos/$project"/"$svn_path"
cvsURL="$project"/sourcecode
bleumcvsroot=":pserver:jenkins:cvs,123456@192.168.2.200:/1fb"
echo "Reseting cvs repossitory..."
echo ""
if [ ! -d "$cvs_dir" ]
  then
	mkdir -p "$cvs_dir"
	source cd "$cvs_dir"
	cvs -d $bleumcvsroot co -P -r "$cvs_path" "$cvsURL"
else
	source cd "$cvs_dir"
	cvs -d $bleumcvsroot up -r  up -d -P -r "$cvs_path" "$cvsURL"
fi
echo "Getting latest svn codes..."
echo ""
if [ ! -d "$svn_dir" ]
  then
	mkdir -p "$svn_dir"
	source cd "$svn_dir"
	svn co -r HEAD "$svnURL" .
else
	source cd "$svn_dir"
	svn up -r HEAD "$svnURL" .
fi
echo "Coping svn2cvs..."
echo ""
cp -ru *.* "$cvs_dir"
cvs -d $bleumcvsroot importxasqxasq
