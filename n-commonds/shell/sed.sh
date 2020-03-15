#!/bin/bash

#查
#方式一,在第一行插入字符串helloworld0。vim里，注意1i\,后边有一个空格
sed -i "" "1i\
> hellworld0
> " test.txt

#方式二,在第一行插入helloworld。注意 $'\n' 。
sed -i "" "1i\\"$'\n'"helloworld" test.txt
sed -i "" '1i\'$'\n''helloworld' test.txt

#在一行之后，追加字符串。(新增内容显示在第二行)
sed -i "" "1a\\"$'\n'"helloworld" test.txt

#删除
#直接删除test.txt的第一行(d表示delete)
sed -i "" "1d" test.txt

#直接删除2-5行的内容
sed -i "" "2,5d" test.txt

#直接删除test.txt中world8所在行
sed -i "" "/world8/d” test.txt

#直接删除test.txt中正则匹配的字符串所在行
sed -i "" "/world.*/d” test.txt


#打印
#打印第一行。p表示打印。
sed -n "1p" test.txt

#打印第一到三行。
sed -n "1,3p" test.txt

#打印包含 “beijing” 字符串所在的行。
sed -n "/beijing/p" test.txt


#查找
#把world1改成world0。s表示替换，2表示第二个world1，g表示全局。
sed -i "" "s/world1/world0/2" test.txt

#把第二行，第三行的所有hello替换为welcome
sed -i "" "2,3s/hello/welcome/" test.txt

#找到world3所在行，把所有的hello替换为welcome
sed -i "" "/world3/s/hello/welcome/g" test.txt

#正则匹配修改。把所有的 world+一个数字 改成了 beijing。
sed -i "" "s/world[0-9]/beijing/g" test.txt

#变量。\(.*\)会匹配出一个变量。\1表示第一个变量，\2表示第二个变量。
sed -i "" "s/\(.*\)-\(.*\)/\2-\1/g" test.txt

#把test.txt的2-5行替换为字符串
sed -i "" '2,5c\'$'\n''China' test.txt

#把test.txt的第一行替换为test1.txt的内容。
sed -i "" "1r test1.txt" test.txt

#把test1.txt的第三，四行数据，写入到test.txt。
sed -i "" "3,4w test.txt" test1.txt
