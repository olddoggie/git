#!/bin/bash

#########################################################
# this script is used for svn repository backup
# written: jack.wang
# date: 2013/3/8
#########################################################

echo "Initializing..."
echo ""
project="$1"
CAT=$2
svn_path="$3"
cvs_path="$4"
bk_dir=/home/svn/back-up/svn2cvs
TXT_FILE_TYPES=$bk_dir/txt_file_type
# TXT_FILE_TYPES = [".java",".jsp", ".txt", ".jhtml", ".html", ".xsd",".xml", ".properties", ".htm",".dtd",".tld",".css",".js"]
total=$bk_dir/total.log
total_file=$bk_dir/total_file.log
temp=temp_$total_file
total_dir=$bk_dir/total_dir.log
add_dir=$bk_dir/add_dir.log
add_kb_file=$bk_dir/add_kb_file.log
add_kv_file=$bk_dir/add_kv_file.log
del_file=$bk_dir/del_file.log
cvs_dir=/home/svn/cvs/"$project"
svnURL="file:///home/svn/repos/$project"/"$svn_path"
cvsURL="$project"/sourcecode
bleumcvsroot=":pserver:jenkins:cvs,123456@192.168.2.200:/1fb"
function grep-v ()
{
while read LINE
do
cat $2 | grep -v "$LINE" > $2
done < $1
}
function add-d ()
{
while read LINE
do
ls "$LINE"/.cvs || cvs -d $bleumcvsroot add "$LINE"
done < $1
}
function add-kb ()
{
while read LINE
do
cvs -d $bleumcvsroot add -kb "$LINE"
done < $1
}
function add-kv ()
{
while read LINE
do
cvs -d $bleumcvsroot add "$LINE"
done < $1
}
function rm-f ()
{
while read LINE
do
cvs -d $bleumcvsroot rm -f "$LINE"
done < $1
}
function save-log ()
{
ls $bk_dir/logs || mkdir $bk_dir/logs
ls $bk_dir/*.log > $1
while read LINE
do
cp $LINE $bk_dir/logs/$LINE.`date +%Y-%m-%d-%H-%M-%S`
done < $1
}
echo "Reseting bk log files..."
echo ""
rm -f *.log
echo "Reseting cvs repossitory..."
echo ""
rm -rf "$cvs_dir" && mkdir "$cvs_dir"
source cd "$cvs_dir" 
cvs -d $bleumcvsroot co -P -r "$cvs_path" "$cvsURL"
echo "Preparing total changlist..."
echo ""
svn diff -c --summarize "$svnURL" > $total
cat $total | grep "\." > $total_file
cat $total | grep -v "\." > $total_dir
echo "Preparing newly added dirs..."
echo ""
cat $total_dir | grep "A    " > $add_dir
echo "Preparing newly added binary files..."
echo ""
cp $total_file $temp
grep-v TXT_FILE_TYPES $temp
cat $temp | grep "A    " > $add_kb_file
echo "Preparing newly added txt files..."
echo ""
cp $total_file $temp
grep-v $add_kb_file $temp
cat $temp | grep "A    " > $add_kv_file
echo "Preparing removed files..."
echo ""
cat $total_file | grep "D    " > $del_file
echo "Getting latest svn codes..."
echo ""
svn export -r HEAD "$svnURL" "$cvs_dir" 
echo "Add-in newly added dirs from svn2cvs..."
echo ""
add-d $add_dir
echo "Add-in newly added binary files from svn2cvs..."
echo ""
add-kb $add_kb_file
echo "Add-in newly added txt files from svn2cvs..."
echo ""
add-kv $add_kv_file
echo "Remove deleted files from svn2cvs..."
echo ""
rm-f $del_file
echo "OK. Check-in codes from svn2cvs..."
echo ""
cvs -d $bleumcvsroot commit -R -m "$project $CAT:daily update from svn2cvs." "$cvs_dir" 
echo "Finished svn repository back-up job, please check the latest codes in ‘$cvsURL’..."
echo ""
save-log $temp
echo "back-up logs has been recorded into $bk_dir/logs. [ALL!]"