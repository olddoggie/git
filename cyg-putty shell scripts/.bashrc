# use alias command by edit .bashhr
# /home/jack.wang/.bashrc
# source /home/jack.wang/.bashhr

# local alias
alias rs="vi /home/jack.wang/.bashrc"
alias ss="source /home/jack.wang/.bashrc"
alias nn="catg /home/jack.wang/.bashrc"
alias sync="catg ~/itosync-daily-check.txt"

# basic command
alias ip="ipconfig"
alias net="netstat"
alias ls="ls -hF --color=tty"
alias la="ls -atlr"
alias ll="ls -l"
alias lr="ls -Rl| less"
alias dir="ls --color=auto --format=vertical"
alias rm="rm -r -i"
alias cp="cp -r -i"
alias mv="mv -i"
alias z="find ./ -maxdepth 1 -type f|wc -l"
alias zz="find ./ -type f|wc -l"
alias f="find ./ -type f"
alias fn="find ./ -type f -name"
alias fd="find ./ -type d -name"
alias fx="find ./ -maxdepth 1 -newer"
alias fj="find ./ -maxdepth 1 ! -newer"
alias ft="find ./ -maxdepth 1 -mtime" #e.g. find ./ -mtime -1---- find files modified whith in 1 day.
alias grep="grep --color"
alias egrep="egrep --color=auto"
alias lg="ls -l |grep -i"
alias h="history"

# Log-in by SSH
alias mac="echo 'aaaaa';echo 'cd /Users/bleum/Public/jk';ssh bleum@192.168.2.123"
alias lcl="echo 'admin,1234';ssh oracle@192.168.2.100"
alias ibu="echo 'build';ssh build@itobuild.1fb.net"
alias ioas="oas"
alias icvs="echo '35\$Ticket!';ssh oracle@ito-as1.1fb.net"
alias ibbu="echo 'th80mon';ssh bbu@ito-as1.1fb.net"
alias itib="echo '35\$Ticket!';ssh tibco@ditoapv2.1fb.net"

# cd path
alias cdd="cd d:"
alias cdc="cd c:"
alias cdcvs="cd ~/cvsroot"
alias cdbcvs="cdd;cd BleumCVS"
alias cdbcvs-cs="cdbcvs;cd csmaintenance/3.50/SourceCodeUBS"
alias cdbcvs-af="cdbcvs;cd affinity/sourcecode"
alias cdicvs="cdd;cd ITOCVS"
alias cdk="cdc;cd Documents\ and\ Settings/jack.wang/Desktop"
alias cdp="cdk;cd jar\ files/FS\ Porting"
alias cdp-fs="cdp;cd oc4j10g-afcsragent_fs_porting"
alias cdp-coll="cdp;cd afcollagent"
alias cdp-csr="cdp;cd afcsragent"
alias cdsync="cdd;cd sync"
alias cdrls="cdd;cd release"
alias hb="cd;cd bin;la"
alias cd-lrn="cdk;cd learn"
alias cd-cm="cdk;cd CM"
alias cd-ci="cdk;cd CI"
alias domain="cdk;cd DOMAIN"
alias cdgit="cd;cd git"
alias cdsvn="cdd;cd SVN\ Repository"

# file list
alias v1="vi bleumModule1.txt"
alias v2="vi bleumModule2.txt"
alias v3="vi bleumModule3.txt"
alias v4="vi bleumModule4.txt"
alias v5="vi bleumModule5.txt"
alias v6="vi bleumModule6.txt"
alias v7="vi bleumModule7.txt"
alias c1="cat bleumModule1.txt"
alias c2="cat bleumModule2.txt"
alias c3="cat bleumModule3.txt"
alias c4="cat bleumModule4.txt"
alias c5="cat bleumModule5.txt"
alias c6="cat bleumModule6.txt"
alias c7="cat bleumModule7.txt"

# cvs command option
alias cvsd="cvs -d :sspi:jack.wang@192.168.2.200:/1fb"
alias cvsdi="cvs -d :ext:bbu@ito-as1.1fb.net:/vb/cvs"
alias cvsup-all="cvsd update -A -P -d"
alias cvsfile-list="cvs-local-check-update"
alias cvsfile-list-ito="cvs-ito-check-update"

# svn command option
alias svnjk="echo 'svn://192.168.2.110'"

# git command option
alias gitsync="echo 'enter path and range';~/bin/git-pull-and-push"

# check deploy note
alias dn="build-deploy-ito"
alias dl="catg  /home/jack.wang/instance-list.txt"

# substitute a certain char in vi
alias s="substitute"

# WordNet check
alias c="ruby ~/bin/Check.rb"

# Look up dictionary
alias d="dictionary" 
alias d-ox="cp ~/dictionary/oxford-dict.txt temp.txt;vi temp.txt;rm -f temp.txt"
alias d-cm="less ~/dictionary/common2000-words.txt"
alias d-new="less ~/dictionary/new-Oriental-dict.txt"
# translation -- baidu API
# alias t="translate en cn" -- already invoked in dictionary function

# Go over the New-Word Memo
alias m="Word-Memo ~/Word-Memo.txt"
alias mm="vi ~/Word-Memo.txt"
