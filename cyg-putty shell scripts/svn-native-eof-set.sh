#!/usr/bin/bash
# In order to use this client hook, you need bash env, and tortise svn installed, then add this hook by specify the path and command: bash "$path/svn-native-eof-set.sh"
dir="D:\test"
cd  "$dir" && svn propset svn:eol-style 'native' -R .