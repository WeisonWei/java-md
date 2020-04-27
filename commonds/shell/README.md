# shell

shell --> 命令行解释器，用于接收应用程序命令操作Linux内核

application --> shell --> Linux内核 --> 硬件

## 1.命令行解析器

查看命令行解析器 --> cat /ect/shells

查看默认命令行解析器 --> echo $SHELL

执行命令 --> 
./ --> 需要具有执行权限
sh
bash

## 2.变量
系统变量 --> $HOME $PWD $SHELL $USER

自定义变量 --> A=1  =号两边不能有空格 默认hi字符串格式 无法进行运算 如有空格 用 ""

定义静态变量 --> readonly A=1

提升变量为全局变量 --> export A 所有进程共享

系统特有变量 --> 
$n --> $1 $2 $3 ... $9 ${10} ${11}
$# --> 输入参数个数
$* --> 输入的所有参数(一个整体)
$@ --> 输入的所有参数(分别对待)
$? --> 最后一个命令的执行状态 0 成功 非0 失败

## 3.运算符
$[空格 运算 空格] --> $[ 2+3 ]
expr + - \* / % --> expr 2+3
## 4.条件判断
### 4.1 if的基本语法:
```bash
if [ command ];then
   符合该条件执行的语句
elif [ command ];then
   符合该条件执行的语句
else
   符合该条件执行的语句
fi
```
### 4.2 文件/文件夹(目录)判断
[ -b FILE ] 如果 FILE 存在且是一个块特殊文件则为真。  
[ -c FILE ] 如果 FILE 存在且是一个字特殊文件则为真。  
[ -d DIR ] 如果 FILE 存在且是一个目录则为真。  
[ -e FILE ] 如果 FILE 存在则为真。  
[ -f FILE ] 如果 FILE 存在且是一个普通文件则为真。  
[ -g FILE ] 如果 FILE 存在且已经设置了SGID则为真。  
[ -k FILE ] 如果 FILE 存在且已经设置了粘制位则为真。  
[ -p FILE ] 如果 FILE 存在且是一个名字管道(F如果O)则为真。  
[ -r FILE ] 如果 FILE 存在且是可读的则为真。  
[ -s FILE ] 如果 FILE 存在且大小不为0则为真。  
[ -t FD ] 如果文件描述符 FD 打开且指向一个终端则为真。  
[ -u FILE ] 如果 FILE 存在且设置了SUID (set user ID)则为真。  
[ -w FILE ] 如果 FILE存在且是可写的则为真。  
[ -x FILE ] 如果 FILE 存在且是可执行的则为真。  
[ -O FILE ] 如果 FILE 存在且属有效用户ID则为真。  
[ -G FILE ] 如果 FILE 存在且属有效用户组则为真。  
[ -L FILE ] 如果 FILE 存在且是一个符号连接则为真。  
[ -N FILE ] 如果 FILE 存在 and has been mod如果ied since it was last read则为真。  
[ -S FILE ] 如果 FILE 存在且是一个套接字则为真。  
[ FILE1 -nt FILE2 ] 如果 FILE1 has been changed more recently than FILE2, or 如果 FILE1 exists and FILE2 does not则为真。  
[ FILE1 -ot FILE2 ] 如果 FILE1 比 FILE2 要老, 或者 FILE2 存在且 FILE1 不存在则为真。  
[ FILE1 -ef FILE2 ] 如果 FILE1 和 FILE2 指向相同的设备和节点号则为真。  


### 4.3 字符串判断
[ -z STRING ] 如果STRING的长度为零则为真 ，即判断是否为空，空即是真；  
[ -n STRING ] 如果STRING的长度非零则为真 ，即判断是否为非空，非空即是真；  
[ STRING1 = STRING2 ] 如果两个字符串相同则为真 ；  
[ STRING1 != STRING2 ] 如果字符串不相同则为真 ；  
[ STRING1 ]　 如果字符串不为空则为真,与-n类似 ;   
### 4.4 数值判断

INT1 -eq INT2           INT1和INT2两数相等为真 ,=  
INT1 -ne INT2           INT1和INT2两数不等为真 ,<>  
INT1 -gt INT2            INT1大于INT1为真 ,>  
INT1 -ge INT2           INT1大于等于INT2为真,>=  
INT1 -lt INT2             INT1小于INT2为真 ,<</div>  
INT1 -le INT2             INT1小于等于INT2为真,<=  

### 4.5 复杂逻辑判断
> -a 与
> -o 或
> ! 非

```bash
exp1: 如果a>b且a
if (( a > b )) && (( a < c ))
或者
if [[ $a > $b ]] && [[ $a < $c ]]
或者
if [ $a -gt $b -a $a -lt $c ]

exp2:如果a>b或a
if (( a > b )) || (( a < c ))
或者
if [[ $a > $b ]] || [[ $a < $c ]]
或者
if [ $a -gt $b -o $a -lt $c ]

"||"和"&&"在SHELL里可以用，也就是第一个写成if [ a>b && a ]
```

## 5.流程控制
```bash
#!/bin/bash
if [ $1 -eq 1 ];then
echo this is 1
elif [ $1 -eq 2 ];then
echo this is 2
else
echo nothing
fi
```
## 6.流读取
read -p 提示语 -t 读取等待时间
## 7.函数

系统函数 --> 
basename [string/pathname] [suffix] 获取路径下文件名
dirname [string/pathname] 获取文件绝对路径

自定义函数 --> 
```bash
function funcname()
{ 
action
} 
```
## 8.shell工具
cut -f 提取第几列 -d 指定提取分隔符  --> 剪切

sed "命令" 文件  --> 流编辑器 
-a 增加
-d 删除
-s 查找并替换

awk -F 分隔符 -v 自定义变量赋值 'pattern1{action1} pattern2{action2}...' 文件  --> 文本分析


sort  --> 排序
-n 按照自然顺序排列
-r 倒序
-t 排序分隔符
-k 排序的列

## 9.练习
见practise.sh
