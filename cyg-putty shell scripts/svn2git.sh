#!/bin/bash
# ===========FYI,initial convert from svn=========================
# vi ~/.ssh/config --> gitolite
# svn log --xml | grep author | sort -u | perl -pe 's/.>(.?)<./$1 = /' > users.txt
# git svn clone -s -r 0:HEAD --authors-file=users.txt file:///home/svn/repos/openproperty/ openproperty
# cp -Rf .git/refs/remotes/tags/* .git/refs/tags/
# rm -Rf .git/refs/remotes/tags
# cp -Rf .git/refs/remotes/* .git/refs/heads/
# rm -Rf .git/refs/remotes
# git svn show-ignore > .git/info/exclude
# cat users.txr >> .git/info/exclude
# git svn fetch --authors-file=users.txt && git svn rebase (optional)
# BINARY_FILE_TYPES="jpe|jpg|pdf|jpeg|png|bmp|gif|ttf|jar|war|ear|avi|mp3|mpg|doc|docx|xls|xlsx|mpp|ppt|pptx|dot|tif|swf|bmp|exe|tgz|gz|o" && find ./OpenProperty -type f | awk '!/('"$BINARY_FILE_TYPES"')$/ {print $0}' | xargs -i dos2unix -k {}
# git add -A
# git commit -C HEAD --author="$author <$author@bleum.com>"  --amend
# git remote add origin gitolite:OpenProperty
# git push -f -u --all origin master
# git config --global core.autocrlf input
# git config user.name "git-svn"
# git config user.email "git-svn@bleum.com"
# =================================================================

# Asumptuion
# 0. git version -- 1.7.12.4; svn version -- 1.7.8 (r1419691) ; server: 192.168.2.100
# 1. source code of this project is located under: /home/svn/repos/openproperty/trunk/OpenProperty
# 3. corresponding git repo is located under: /home/svn/git-svn/openproperty/OpenProperty
# 4. EOL in svn repo is dos, but in order to get rid of unrecognized /r displayed in gitweb, auto transferred to unix into gir repo for each svn commit

#################################################################
# this script is used for svn code checkin sync to git repository
# written: jack.wang
# date: 2013/4/23
#################################################################
export PATH=$PATH:/usr/local/bin
author="$1 <$1@bleum.com>"
comment="$2"
BINARY_FILE_TYPES="jpe|jpg|pdf|jpeg|png|bmp|gif|ttf|jar|war|ear|avi|mp3|mpg|doc|docx|xls|xlsx|mpp|ppt|pptx|dot|tif|swf|bmp|exe|tgz|gz|o|zip"
svn_home=/home/svn/repos/openproperty
svn_repo="file:///home/svn/repos/openproperty/trunk/OP"
git_repo=/home/svn/git-svn/openproperty

svn export --force -r HEAD $svn_repo $git_repo/OP

source ~/bin/go $git_repo
svnlook changed $svn_home | grep "D   " |  sed 's#D.*   trunk/##g' | xargs -i git rm -rf --ignore-unmatch {}
find ./OP -type f | awk '!/('"$BINARY_FILE_TYPES"')$/ {print $0}' | xargs -i dos2unix -k {}

git add -A && git commit -m "$comment" --author="$author" && git push origin master 
