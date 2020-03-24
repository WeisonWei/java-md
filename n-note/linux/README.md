## Linux中的输入文件、输出文件、错误输出
标准输入 --> 0 (默认是键盘)
标准输出 --> 1 (默认是屏幕)
标准错误 --> 2 (默认是屏幕)


## 输出重定向
    Linux中&表示后台运行，>表示输出重定向
command > file --> 标准输出重定向到文件中，文件不存在时会自动创建再写入，文件存在时会先删除文件中的内容再写入
command >> file --> 标准输出重定向到文件中，文件不存在时会自动创建再写入命令产生的标准输出，文件存在时不改变原文件内容写入再写入命令产生的标准输出
command > file 2>&1 --> 标准输出和错误输出重定向到文件中，若文件不存在则会新建文件再写入，若文件存在会先删除文件中的原本内容再写入(2>&1表示标准错误以后台模式重新向到标准输出中)
command >> file 2>&1 --> 标准输出和错误输出重定向到文件中，若文件不存在，则会新建文件再写入；若文件存在，则会在不改变文件原本内容的情况下再写入文件
command 2 > file --> 标准错误重定向到文件中，若文件不存在则会新建文件再写入，若文件存在会先删除文件中的原本内容再写入
command 2 >> file --> 标准错误重定向到文件中，若文件不存在，则会新建文件再写入；若文件存在，则会在不改变文件原本内容的情况下再写入文件

## 输入重定向
    <表示输入重定向
command < file1 > file2 --> command命令以file1为标准输入，以file2为标准输出
command < file --> command命令以file为标准输入
command << del --> command以键盘为标准输入，直到遇到del结束

## /dev/null
    /dev/null在Linux中充当黑洞的作用，任何写入到/dev/null中的内容都会被自动删除
```bash
# 将命令产生的错误重定向到/dev/null，即命令执行不会在屏幕上显示任何错误信息
command 2 > /dev/null

# 将命产生的日志重定向到文件中，将命令产生的错误信息写入到/dev/null，以此达到日志写入文件，错误直接忽略，屏幕不会显示任何信息
command > file 2>&1
nohup command > file 2>&1 &

```
## nohup
    nohup表示不挂断地执行命令

通常我们在执行Linux命令时会使用&使命令在后台运行而不影响terminal的正常使用；
而&存在一个问题：关闭terminal会导致使用&的命令中断，这时就需要nohup command &，最直接简单的命令就是：
```bash
nohup command > nohub.out &
```
命令执行，正常日志会自定写入到file中，但如果有错误信息，则错误信息会直接打印到屏幕上
```bash
nohup command > nohub.out 2>&1 &
```
命令执行，生成的正常日志和错误信息会记录在file中：
```bash
jobs
```
查看使用nohup命令提交的job：
```bash
fg %n
```
关闭某个job





> https://www.cnblogs.com/jerrylocker/p/10824686.html