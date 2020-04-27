#!/bin/bash

#查找text是否存
#1 存在
## 1.1 对第一列数字排序
## 1.2 求第一列数字的和
#2 不存在 查找所有包含bin的文件
if [[ -f text1 ]] ;then
more text|awk -F \| '{print $1}'| sort -n
echo ---------------
more text|awk -F \| '{SUM+=$1} END{print "sum:"SUM}'
else
grep -r "/bin/bash" ./|grep ".sh:"|cut -d "." -f 2|awk -F // '{print $2}'
fi