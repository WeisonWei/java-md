#!/bin/bash
#数据准备
# sudo cp /etc/passwd ./
# sudo chown admin passwd

#awk -F : '{print $7}' passwd
echo ------------
#awk -F : '{print $1","$7}' passwd
echo ------------
#awk -F : '/^root/ {print $1","$7}' passwd
echo ------------
#awk -F : 'BEGIN{print "user,shell"} {print $1","$7} END{print "weison"}' passwd
echo ------------
#awk -F : -v i=1 '{print $3+i}' passwd
#awk 内置变量
#文件名 $FILENAME
#已读记录数 $NR
#切割后，域的个数 $NF
#awk -F : '{print "fileName:" FILENAME ",lineNumber:" NR ",columns:" NF}' passwd
#查找空行所在行号
awk '/^$/ {print NR}' text

#其他练习
#ifconfig |grep eth|awk -F : '{print $2,$3,$4,$5}'
