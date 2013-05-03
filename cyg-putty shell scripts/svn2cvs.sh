#!/bin/bash

#########################################################
# this script is used for svn repository backup
# written: jack.wang
# date: 2013/3/8
#########################################################
echo "Rationale is svn diff -c --summarize,svn export,get \$line function and cvs basic commands...."
echo ""
echo "first param is svn project name (e.g.openproperty ...)"
echo "second param is the CAT name which will be used when draft commit comment (e.g. CAT1.... )"
echo "third param is the target path of the sourcecodes subfolder in SVN project folder (e.g. trunk,trunk/OpenProperty.... )"
echo "forth param is the CVS HEAD/branch/tag name which you check codes into in CVS project folder (e.g. HEAD.... )"
echo "Initializing..."
echo ""
export PATH=$PATH:/usr/local/bin
project="$1"
CAT=$2
svn_path="$3"
cvs_path="$4"
bk_dir=/home/svn/back-up/svn2cvs
log_file=$bk_dir/svn2cvs.logs
cron_file=$bk_dir/cron.logs
DATE=`date +%Y-%m-%d-%H-%M-%S`
total=$bk_dir/total.log
total_file=$bk_dir/total_file.log
temp=$bk_dir/temp.log
total_dir=$bk_dir/total_dir.log
add_dir=$bk_dir/add_dir.log
add_kb_file=$bk_dir/add_kb_file.log
add_kv_file=$bk_dir/add_kv_file.log
del_file=$bk_dir/del_file.log
missing_file=$bk_dir/missing.log
cvs_repo=/home/svn/cvs
cvs_dir=/home/svn/cvs/"$project"
svnDir=/home/svn/repos
svnURL="file://$svnDir/$project"/"$svn_path"
cvsURL="$project"/Sourcecode
bleumcvsroot=":pserver:jenkins:cvs,123456@192.168.2.200:/1fb"
BINARY_FILE_TYPES="jpe|jpg|pdf|jpeg|png|bmp|gif|ttf|jar|war|ear|avi|mp3|mpg|doc|docx|xls|xlsx|mpp|ppt|pptx|dot|tif|swf|bmp|exe|tgz|gz|o"
# grep-v cat $2 | grep -v "$LINE" > $2 has no use on RedHat Linux bash shell,change to use awk '/[]$/ {print $0}' to filter the txt/binary files
# BINARY_FILE_TYPES = [".jpe",".jpg",".pdf",".jpeg",".png",".bmp",".gif",".ttf",".jar",".war",".ear",etc.]
# TXT_FILE_TYPES = [".java",".jsp", ".txt", ".jhtml", ".html", ".xsd",".xml", ".properties", ".htm",".dtd",".tld",".css",".js",".tags",".ftl"]

# function readline ()
# {
# while read LINE
# do
# cmd.
# done < $1
# }

function blank
{
echo "" | tee -a $log_file
}

function check_status
{
	if [ $STAT -eq 1 ]
		then
		echo "Ooops...." | tee -a $log_file
		echo "Command aborted, find the error msg above and do it again!" | tee -a $log_file
		exit 1
	fi
}

function confirm-d ()
{
declare -i k=`echo "$1" | awk -F '/' '{print NF}'`-1
for (( i=1; i<=k; i++ )); do
	cvsdir="`echo "$1" | awk -F '/' '{ {for(j=1; j<='$i'; j++) printf "%s/",$j} print "CVS"}'`"
	namedir="`echo "$cvsdir" | sed 's#/CVS$##g'`"
	if [ ! -d "$cvsdir" ]
	 then
		cvs -d $bleumcvsroot add "$namedir"
		echo "directory '$namedir' added..."
	fi
done
STAT=$?
check_status
}

function add-d ()
{
while read LINE
do
confirm-d "$LINE"
done < $1
STAT=$?
check_status
}

function add-kb ()
{
while read LINE
do
confirm-d "$LINE"
cvs -d $bleumcvsroot add -kb "$LINE"
done < $1
STAT=$?
check_status
}

function add-kv ()
{
while read LINE
do
confirm-d "$LINE"
cvs -d $bleumcvsroot add "$LINE"
done < $1
STAT=$?
check_status
}

function rm-f ()
{
while read LINE
do
cvs -d $bleumcvsroot rm -f "$LINE"
done < $1
STAT=$?
check_status
}

function double-chk ()
{
source ~/bin/go $cvs_dir
cvs st | grep "?" | grep "\." > $missing_file
if [ ! -s $missing_file ]
 then
	blank
	echo "Perfect! No missing files." | tee -a $log_file
else
	blank
	echo "Adding missing files..." | tee -a $log_file
	cat $missing_file | sed 's/? /A       /g' > $missing_file
	cat $missing_file | tee -a $log_file
	blank
	echo "..."	
	cat $missing_file | awk '/('"$BINARY_FILE_TYPES"')$/ {print $0}' | sed 's/A       //g' | sort -u >  $temp
	echo "==================================================="
	add-kb $temp
	echo "==================================================="
	echo "..."
	cat $missing_file | awk '!/('"$BINARY_FILE_TYPES"')$/ {print $0}' | sed 's/A       //g' | sort -u >  $temp
	echo "==================================================="
	add-kv $temp
	echo "==================================================="
	blank
	comments="`svnlook log -r $PRE "$svnDir/$project"`" # the Missing files should have the previous missing comment.
	comments=`echo "$comments" | tr "\n" " " | tr \" \'`
	echo "==================================================="
	cvs -d $bleumcvsroot commit -R -m "$project $CAT: daily update from svn2cvs: $comments"
	echo "==================================================="
	blank
	echo "Added missing files, for the details, please check $missing_file." | tee -a $log_file
fi
STAT=$?
check_status
}

function save-log ()
{
if [ ! -d $bk_dir/logs ]
 then
 mkdir $bk_dir/logs
fi
ls $bk_dir/*.log | awk -F '/' '{print $NF}' > $temp
source ~/bin/go $bk_dir
while read LINE
do
if [ -s $LINE ]
 then
	mv $LINE $bk_dir/logs/$LINE.$DATE
fi
done < $temp
rm -f *.log
STAT=$?
check_status
}

echo "Starting back up from svn to cvs repo..."
echo ""
blank
blank
echo "##################################################################################################################" | tee -a $log_file
echo "$DATE" | tee -a $log_file
echo "for more details, please check $cron_file and $bk_dir/logs." | tee -a $log_file
echo "##################################################################################################################" | tee -a $log_file
source ~/bin/go $bk_dir
rm -f *.log
echo "Reseting cvs repossitory..." | tee -a $log_file
blank
rm -rf "$cvs_dir" && mkdir "$cvs_dir"
source ~/bin/go $cvs_repo
if [ "$cvs_path" = "HEAD" ]
 then
	cvs -d $bleumcvsroot co -d "$project" "$cvsURL" # do not use `cvs checkout -P`, beacuse it will ignore empty folder in cvs repo
else
	cvs -d $bleumcvsroot co -d "$project" -r "$cvs_path" "$cvsURL"
fi
STAT=$?
check_status
echo "Preparing total changlist..." | tee -a $log_file
blank
declare -i CUR=`svnlook youngest "$svnDir/$project"`
echo $CUR > $bk_dir/HEAD_VERSION
echo "HEAD version is: $CUR" | tee -a $log_file
if [ ! -f $bk_dir/PRE_VERSION ]
 then	
	echo "This is the first back up time!" | tee -a $log_file
	blank
	declare -i PRE=0
else
	echo "Check sourcecode updated or not..." | tee -a $log_file
	declare -i PRE=`cat $bk_dir/PRE_VERSION`
	if [ $PRE -eq $CUR ]
	 then
		echo "No sourcecode updated!" | tee -a $log_file
		mv $bk_dir/HEAD_VERSION $bk_dir/PRE_VERSION
	exit 1
	fi
	if [ $PRE -gt $CUR ]
	 then
		echo "Script failed! Some thing wrong with the PRE_VERSION num: $PRE,please check!" | tee -a $log_file
	exit 1
	fi
	if [ $PRE -lt $CUR ]
	 then
		echo "Sourcecodes updated!Previous version was: $PRE,while latest vers is: $CUR"... | tee -a $log_file
		blank
	fi
fi
STAT=$?
check_status
svn diff -r $PRE:HEAD --summarize "$svnURL" | sed 's#%20# #g' > $total # replace %20 with space, because svn always display %20 to represent a space.
STAT=$?
check_status
#M       file:///home/svn/repos/openproperty/trunk/OpenProperty/src/main/java/com/onefb/op/controllers/controllers/auth/LoginAjaxController.java
#-->
#M       src/main/java/com/onefb/op/controllers/controllers/auth/LoginAjaxController.java
cat $total | grep "\." | sed 's#'"$svnURL"'/##g' > $total_file
cat $total | grep -v "\." | sed 's#'"$svnURL"'/##g' > $total_dir
echo "Preparing newly added dirs..." | tee -a $log_file
cat $total_dir | grep "A       " > $add_dir
cat $add_dir | tee -a $log_file
blank
echo "Preparing newly added binary files..." | tee -a $log_file
cat $total_file | grep "A       "  | awk '/('"$BINARY_FILE_TYPES"')$/ {print $0}' > $add_kb_file
cat $add_kb_file | tee -a $log_file
blank
echo "Preparing newly added txt files..." | tee -a $log_file
cat $total_file | grep "A       " | awk '!/('"$BINARY_FILE_TYPES"')$/ {print $0}' > $add_kv_file
cat $add_kv_file | tee -a $log_file
blank
echo "Preparing removed files..." | tee -a $log_file
cat $total_file | grep "D       " > $del_file
cat $del_file | tee -a $log_file
blank
echo "Getting latest svn codes..." | tee -a $log_file
blank
svn export --force -r HEAD "$svnURL" "$cvs_dir"
STAT=$?
check_status
blank
echo "Go to cvs project sourcecode folder '$cvs_dir', prepare code check-in..." | tee -a $log_file
source ~/bin/go "$cvs_dir"
blank
echo "1. Remove deleted files from svn2cvs..." | tee -a $log_file
blank
echo "..."
cat $del_file | sed 's/D       //g' | sort -u > $del_file
echo "==================================================="
rm-f $del_file
echo "==================================================="
blank
echo "2. Add-in newly added dirs from svn2cvs..." | tee -a $log_file
blank
echo "..."
cat $add_dir | sed 's/A       //g' | sort -u | awk '{print $0"/"}' > $add_dir
echo "==================================================="
add-d $add_dir
echo "==================================================="
blank
echo "3. Convert eof-style from CRLF to LF in case the linux server will get dos file to be checked in, which may cause wincvs wrongly check out file from CRLF to CRCRLF..." | tee -a $log_file
blank
find ./ -type f | awk '!/('"$BINARY_FILE_TYPES"')$/ {print $0}' | xargs -i dos2unix -k {}
STAT=$?
check_status
echo "Convertion finished." | tee -a $log_file
blank
echo "4.1 Add-in newly added binary files from svn2cvs..." | tee -a $log_file
blank
echo "..."
cat $add_kb_file | sed 's/A       //g' | sort -u > $add_kb_file
echo "==================================================="
add-kb $add_kb_file
echo "==================================================="
blank
echo "4.2 Add-in newly added txt files from svn2cvs..." | tee -a $log_file
blank
echo "..."
cat $add_kv_file | sed 's/A       //g' | sort -u > $add_kv_file
echo "==================================================="
add-kv $add_kv_file
echo "==================================================="
blank
echo "5. Getting comments:" | tee -a $log_file
comments="`svnlook log "$svnDir/$project"`" # defult is the latest log of SVN HEAD
comments=`echo "$comments" | tr "\n" " " | tr \" \'`
echo "$comments" | tee -a $log_file
blank
echo "6. Check-in codes from svn2cvs..." | tee -a $log_file
blank
echo "==================================================="
cvs -d $bleumcvsroot commit -R -m "$project $CAT: daily update from svn2cvs: $comments"
echo "==================================================="
blank
echo "OK. Double Check whether there exist still any missing files..." | tee -a $log_file
double-chk
blank
echo "Finished svn repository back-up job, please check the latest codes in '$cvsURL' on 192.168.2.200.." | tee -a $log_file
blank
save-log
echo "back-up logs has been recorded into $bk_dir/logs. [ALL!]" | tee -a $log_file
blank
echo "finally,update the PRE_VERSION to '$CUR'. [OK.]" | tee -a $log_file
mv $bk_dir/HEAD_VERSION $bk_dir/PRE_VERSION
blank
echo "Finished." | tee -a $log_file
cp $cron_file $bk_dir/logs/cron.log.$DATE
echo "see you tomorrow!" 