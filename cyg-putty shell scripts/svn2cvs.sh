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
project="$1"
CAT=$2
svn_path="$3"
cvs_path="$4"
bk_dir=/home/svn/back-up/svn2cvs
log_file=$bk_dir/svn2cvs.logs
DATE=`date +%Y-%m-%d-%H-%M-%S`
total=$bk_dir/total.log
total_file=$bk_dir/total_file.log
temp=$bk_dir/temp.log
total_dir=$bk_dir/total_dir.log
add_dir=$bk_dir/add_dir.log
add_kb_file=$bk_dir/add_kb_file.log
add_kv_file=$bk_dir/add_kv_file.log
del_file=$bk_dir/del_file.log
cvs_repo=/home/svn/cvs
cvs_dir=/home/svn/cvs/"$project"
svnDir=/home/svn/repos
svnURL="file://$svnDir/$project"/"$svn_path"
cvsURL="$project"/sourcecode
bleumcvsroot=":pserver:jenkins:cvs,123456@192.168.2.200:/1fb"
# grep-v cat $2 | grep -v "$LINE" > $2 has no use on RedHat Linux bash shell,change to use awk '/[]$/ {print $0}' to filter the txt/binary files
# BINARY_FILE_TYPES = [".jpe",".jpg",".pdf",".jpeg",".png",".bmp",".gif",".ttf",".jar",".war",".ear"]
# TXT_FILE_TYPES = [".java",".jsp", ".txt", ".jhtml", ".html", ".xsd",".xml", ".properties", ".htm",".dtd",".tld",".css",".js"]
# function grep-v ()
# {
# while read LINE
# do
# cat $2 | grep -v "$LINE" > $2
# done < $1
# }
function check_status
{
			if [ $STAT -eq 1 ]
				then
				echo "Ooops...." | tee -a $log_file
				echo "Command aborted, find the error msg above and do it again!" | tee -a $log_file
				exit 1
			fi
}

function add-d ()
{
cat $1 | sed 's/A       //g' | sort -u > $1
while read LINE
do
if [ ! -d $LINE/CVS ]
 then
	cvs -d $bleumcvsroot add "$LINE"
	echo "directory '$LINE' added, go to next line..."
else
	echo "go to next line..."
fi
done < $1
STAT=$?
check_status
}

function add-kb ()
{
cat $1 | sed 's/A       //g' | sort -u > $1
while read LINE
do
cvs -d $bleumcvsroot add -kb "$LINE"
done < $1
STAT=$?
check_status
}

function add-kv ()
{
cat $1 | sed 's/A       //g' | sort -u > $1
while read LINE
do
cvs -d $bleumcvsroot add "$LINE"
done < $1
STAT=$?
check_status
}

function rm-f ()
{
cat $1 | sed 's/D       //g' | sort -u > $1
while read LINE
do
cvs -d $bleumcvsroot rm -f "$LINE"
done < $1
STAT=$?
check_status
}

function check-updated ()
{
CUR=`svnlook youngest "$svnDir/$project"`
echo $CUR > $bk_dir/HEAD_VERSION
echo "HEAD version is: $CUR" | tee -a $log_file
if [ ! -f $bk_dir/PRE_VERSION ]
 then	
	echo "This is the first back up time!" | tee -a $log_file
	mv $bk_dir/HEAD_VERSION $bk_dir/PRE_VERSION
	PRE=0
else
	echo "Check sourcecode updated or not..." | tee -a $log_file
	PRE=`cat $bk_dir/PRE_VERSION`
	if [ $PRE -eq $CUR ]
	 then
		echo "No sourcecode updated!" | tee -a $log_file
	exit 1
	fi
	if [ $PRE -gt $CUR ]
	 then
		echo "Script failed! Some thing wrong with the PRE_VERSION num: $PRE,please check!" | tee -a $log_file
	exit 1
	fi
	if [ $PRE -lt $CUR ]
	 then
		echo "Sourcecodes updated!Previous version was: $PRE, now updating to $CUR"... | tee -a $log_file
		mv $bk_dir/HEAD_VERSION $bk_dir/PRE_VERSION # update the version num as to be checked the tomorrow
	fi
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
mv $LINE $bk_dir/logs/$LINE.$DATE
done < $temp
STAT=$?
check_status
}

echo "Starting back up from svn to cvs repo..."
echo ""
echo " " | tee -a $log_file
echo " " | tee -a $log_file
echo "###########################" | tee -a $log_file
echo "$DATE" | tee -a $log_file
echo "###########################" | tee -a $log_file
echo ""
source ~/bin/go $bk_dir
rm -f *.log
echo "Reseting cvs repossitory..." | tee -a $log_file
echo "" | tee -a $log_file
rm -rf "$cvs_dir" && mkdir "$cvs_dir"
source ~/bin/go $cvs_repo
if [ "$cvs_path" = "HEAD" ]
 then
	cvs -d $bleumcvsroot co "$cvsURL" # do not use `cvs checkout -P`, beacuse it will ignore empty folder in cvs repo
else
	cvs -d $bleumcvsroot co -r "$cvs_path" "$cvsURL"
fi
STAT=$?
check_status
echo "Preparing total changlist..." | tee -a $log_file
echo "" | tee -a $log_file
check-updated
svn diff -r $PRE:HEAD --summarize "$svnURL" > $total
STAT=$?
check_status
#M       file:///home/svn/repos/openproperty/trunk/OpenProperty/src/main/java/com/onefb/op/controllers/controllers/auth/LoginAjaxController.java
#-->
#M       src/main/java/com/onefb/op/controllers/controllers/auth/LoginAjaxController.java
cat $total | grep "\." | sed 's#'"$svnURL"'/##g' > $total_file
cat $total | grep -v "\." | sed 's#'"$svnURL"'#'"$cvs_dir"'#g' > $total_dir
echo "Preparing newly added dirs..." | tee -a $log_file
echo "" | tee -a $log_file
cat $total_dir | grep "A       " > $add_dir
echo "Preparing newly added binary files..." | tee -a $log_file
echo "" | tee -a $log_file
cat $total_file | grep "A       "  | awk '/(jpe|jpg|pdf|jpeg|png|bmp|gif|ttf|jar|war|ear)$/ {print $0}' > $add_kb_file
echo "Preparing newly added txt files..." | tee -a $log_file
echo "" | tee -a $log_file
cat $total_file | grep "A       " | awk '/(java|jsp|txt|jhtml|html|xsd|xml|properties|htm|dtd|tld|css|js)$/ {print $0}' > $add_kv_file
echo "Preparing removed files..." | tee -a $log_file
echo "" | tee -a $log_file
cat $total_file | grep "D       " > $del_file
echo "Getting latest svn codes..." | tee -a $log_file
echo "" | tee -a $log_file
svn export --force -r HEAD "$svnURL" "$cvs_repo/$cvsURL"
STAT=$?
check_status
echo "Go to cvs project sourcecode folder '$cvs_repo/$cvsURL', prepare code check-in..." | tee -a $log_file
echo "" | tee -a $log_file
source ~/bin/go "$cvs_repo/$cvsURL"
echo ""
echo "Add-in newly added dirs from svn2cvs..." | tee -a $log_file
echo "" | tee -a $log_file
add-d $add_dir
echo "Add-in newly added binary files from svn2cvs..." | tee -a $log_file
echo "" | tee -a $log_file
add-kb $add_kb_file
echo "Add-in newly added txt files from svn2cvs..." | tee -a $log_file
echo "" | tee -a $log_file
add-kv $add_kv_file
echo "Remove deleted files from svn2cvs..." | tee -a $log_file
echo "" | tee -a $log_file
rm-f $del_file
echo "OK. Check-in codes from svn2cvs..." | tee -a $log_file
echo "" | tee -a $log_file
cvs -d $bleumcvsroot commit -R -m "$project $CAT:daily update from svn2cvs."
echo "Finished svn repository back-up job, please check the latest codes in ‘$cvsURL’..." | tee -a $log_file
echo "" | tee -a $log_file
save-log
echo "back-up logs has been recorded into $bk_dir/logs. [ALL!]" | tee -a $log_file