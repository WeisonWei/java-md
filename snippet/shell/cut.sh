#!/bin/bash
#剪切
# -d 提取分隔符
# -f 提取第几列
STR=`cut -d "|" -f 1,2 ./text`
echo $STR
