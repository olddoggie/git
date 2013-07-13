#!/bin/sh

##############################################################################
# this script is used for svn repository backup
# written: jack.wang
# date: 2013/3/8
# ! Please update $BACKUPDIR/projectlist.txt to customize your back up plan !
##############################################################################

###################################
# mail address for status updates
#  - This is used to email you a status report
###################################
MAILADDR=jack.wang@bleum.com

###################################
# backup directory,please change it
# - This is the path to the backup directory
###################################
BACKUPDIR=/home/svn/back-up/svn2dump

###################################
# svn repository directory, please change it.
# - This is the path to the directory you want to archive
###################################
SVNDIR=/home/svn/repos

###################################
# HOSTNAME and project list
#  - This is also used for reporting
###################################
ProjectLst=$BACKUPDIR/projectlist.txt

###################################
# svn backup log path and date
#  - This is used to log result
###################################
LogFile=$BACKUPDIR/svnback.log
DATE=`date +%Y%m%d-%T`
###################################
# svn command path
# - This is the path to the directory you want to archive
###################################
export PATH=$PATH:/bin:/usr/bin:/usr/local/bin

# our actual rsyncing function
do_accounting()
{
        echo " " | tee -a $LogFile
		echo " " | tee -a $LogFile
        echo "###########################" | tee -a $LogFile
        echo "$DATE" | tee -a $LogFile
        echo "###########################" | tee -a $LogFile
        source ~/bin/go $BackDir
}

do_svndump()
{
   PROJECTLIST=`cat $ProjectLst`
   source ~/bin/go $SVNDIR
   for project in $PROJECTLIST
          do
          echo "begin to dump $project databases" | tee -a $LogFile
                if [ ! -f $BACKUPDIR/$project.dump ]
                then
                YOUNGEST=`svnlook youngest $project`
                svnadmin dump $project > $BACKUPDIR/$project.dump
		cp $BACKUPDIR/$project.dump $BACKUPDIR/incremental-dump/$project-inital.$DATE.dump
                echo "OK,dump file successfully!!" | tee -a $LogFile
                echo "$YOUNGEST" > $BACKUPDIR/$project.youngest
                else
                        echo "$project.dump existed,will do increatment job" | tee -a $LogFile
                        if [ ! -f $BACKUPDIR/$project.youngest ]
                        then
                        echo "error, no youngest check!" | tee -a $LogFile
                        else
                                PREVYOUNGEST=`cat $BACKUPDIR/$project.youngest`
                                NEWYOUNGEST=`svnlook youngest $project`
                                if [ $PREVYOUNGEST -eq $NEWYOUNGEST ]
                                then
                                        echo " no database updated!" | tee -a $LogFile
                                else
                                        LASTYOUNGEST=`expr $PREVYOUNGEST + 1`
                                        echo "last youngest is $LASTYOUNGEST" | tee -a $LogFile
					svnadmin dump $project > $BACKUPDIR/$project.dump
                                        svnadmin dump $project --revision $LASTYOUNGEST:$NEWYOUNGEST --incremental > $BACKUPDIR/incremental-dump/$project-$LASTYOUNGEST-$NEWYOUNGEST.$DATE.dump
                                        echo "$NEWYOUNGEST" > $BACKUPDIR/$project.youngest
                                fi
                        fi
  fi
        done
}

# our post rsync accounting function
do_mail()
{
#   mail $MAILADDR -s svn-back_log < $LogFile
source ~/bin/call-ant back-mail
}
# some error handling and/or run our backup and accounting
do_accounting && do_svndump 
