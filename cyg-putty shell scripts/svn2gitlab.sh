#!/bin/bash

export PATH=$PATH:/usr/local/bin

DATE=`date +%Y-%m-%d-%H-%M-%S`
author="$1 <$1@bleum.com>"
comment="$2"
tag="$3"

svn_home=/home/svn/repos/openproperty
svn_repo="file:///home/svn/repos/openproperty/tags/Pre-Release/$tag"
git_repo=/home/svn/gitlab-svn/openproperty

project=op
svnURL="file:///home/svn/repos/openproperty/trunk/OpenProperty" # used by svn diff --summerize in order to fetch the deleted files since last tag
bk_dir=/home/svn/gitlab-svn/backlog/$project
log_file=/home/svn/gitlab-svn/backlog/$project/$project.log

if [[ ! -d $bk_dir ]]
 then
 mkdir $bk_dir
fi

echo "##################################" | tee -a $log_file
echo "$DATE" | tee -a $log_file
echo "This log is used to record the version info of svn repo between tags." | tee -a $log_file
echo "##################################" | tee -a $log_file

declare -i CUR=`svnlook youngest "$svn_home"`
echo $CUR > $bk_dir/HEAD_VERSION
echo "HEAD version is: $CUR" | tee -a $log_file

if [[ ! -f $bk_dir/PRE_VERSION ]]
 then	
	echo "This is the first back up time!" | tee -a $log_file
	declare -i PRE=0
else
	echo "Check sourcecode updated or not..." | tee -a $log_file
	declare -i PRE=`cat $bk_dir/PRE_VERSION`
	if [[ $PRE -eq $CUR ]]
	 then
		echo "No sourcecode updated!" | tee -a $log_file
		mv $bk_dir/HEAD_VERSION $bk_dir/PRE_VERSION
	exit 1
	fi
	if [[ $PRE -gt $CUR ]]
	 then
		echo "Script failed! Some thing wrong with the PRE_VERSION num: $PRE,please check!" | tee -a $log_file
	exit 1
	fi
	if [[ $PRE -lt $CUR ]]
	 then
		echo "Sourcecodes updated!Previous version was: $PRE,while latest vers is: $CUR"... | tee -a $log_file
	fi
fi

source ~/bin/go $git_repo
bra=`git branch | grep "*" | awk '{print $2}'`
if [[ ! $bra = develop ]]
 then
	git checkout develop
fi
svn export --force -r HEAD $svn_repo $git_repo/OpenProperty # attention, svn export to subfolder Openproperty
svn diff -r $PRE:HEAD --summarize "$svnURL" | sed 's#%20# #g' | grep "\." | sed 's#'"$svnURL"'/##g' | grep "D       " | sed 's#D       #OpenProperty/#g' | sort -u | xargs -i git rm -rf --ignore-unmatch {}
git add -A && git commit -m "$comment" --author="$author" && git push origin develop # now, the Pre-Release version of gitlab sync is on branch develop 

echo "Finished." | tee -a $log_file
echo "finally,update the PRE_VERSION to '$CUR'. [OK.]" | tee -a $log_file
mv $bk_dir/HEAD_VERSION $bk_dir/PRE_VERSION