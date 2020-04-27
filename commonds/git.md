
## Git常用命令

```shell
— 1 代码操作
— 1.1 克隆代码
git clone *******.git
— 1.2 更新代码
git pull
```

```shell
— 2 提交代码三步骤
- 2.1 查看状态
git status -s
- 2.2 提交到本地仓库并添加注释
git commit -am "modify test case"
- 2.3 提交至远程仓库
git push
```
```shell
— 3 分支操作
— 3.1 创建分支
git branch branchName
— 3.2 删除远程仓库分支
git push origin --delete branchName
— 3.3 删除本地仓库分支
git branch -d branchName
— 3.4 查看所有分支
git branch -a
— 3.5 切换分支
git checkout evan
— 3.6 将当前代码提交到一个新分支上（即:以当前代码创建新分支）
git push origin branchOld:branchNew
— 3.7 下载指定分支
git clone xxxxxxxxxxxxx.git -b branchName
```

```shell
# 创建新项目后提交至远程仓库
## 1 在github上创建reactor3-core
## 2 在本地仓库提交并推至远程仓库
echo "# reactor3-core" >> README.md
git init
git add README.md
git commit -m "first commit"
git remote add origin git@github.com:WeisonWei/reactor3-core.git
git push -u origin master

```

```shell
# 将本地新项目代码提交至远程仓库
git remote add origin git@github.com:WeisonWei/reactor3-core.git
git push -u origin master
```


```shell
# 此命令会列出所有GIT当时能找到的配置
git config --list    
# 修改git提交的email
git config --global user.email test@test.com 
# 修改提交的git的user.name  
git config --global user.name test
```

(1)#此命令会列出所有GIT当时能找到的配置
git config --list    
(2)#修改git提交的email
git config --global user.email test@test.com 
(3)#修改提交的git的user.name  
git config --global user.name test


git submodule init
git submodule update
git submodule sync
git submodule add URL


```shell script
D:\code\app-service\app-service>git submodule init
Submodule 'app-base' (https://newsgitlab.st.com/st-ai/app-base.git) registered for path 'app-base'
Submodule 'app-service-account' (https://newsgitlab.st.com/st-ai/app-service-account.git) registered for path 'app-service-account'
Submodule 'app-service-mod' (https://newsgitlab.st.com/st-ai/app-service-mod.git) registered for path 'app-service-mod'
Submodule 'app-service-order' (https://newsgitlab.st.com/st-ai/app-service-order.git) registered for path 'app-service-order'
Submodule 'app-service-parent' (https://newsgitlab.st.com/st-ai/app-service-parent.git) registered for path 'app-service-parent'
Submodule 'app-service-product' (https://newsgitlab.st.com/st-ai/app-service-product.git) registered for path 'app-service-product'
Submodule 'app-service-sales' (https://newsgitlab.st.com/st-ai/app-service-sales.git) registered for path 'app-service-sales'
Submodule 'app-service-user' (https://newsgitlab.st.com/st-ai/app-service-user.git) registered for path 'app-service-user'
Submodule 'app-service-weixin' (https://newsgitlab.st.com/st-ai/app-service-weixin.git) registered for path 'app-service-weixin'

D:\code\app-service\app-service>git submodule update
Cloning into 'D:/code/app-service/app-service/app-base'...
Cloning into 'D:/code/app-service/app-service/app-service-account'...
Cloning into 'D:/code/app-service/app-service/app-service-mod'...
Cloning into 'D:/code/app-service/app-service/app-service-order'...
Cloning into 'D:/code/app-service/app-service/app-service-parent'...
Cloning into 'D:/code/app-service/app-service/app-service-product'...
Cloning into 'D:/code/app-service/app-service/app-service-sales'...
Cloning into 'D:/code/app-service/app-service/app-service-user'...
Cloning into 'D:/code/app-service/app-service/app-service-weixin'...
Submodule path 'app-base': checked out 'b90767e3c4eaf0723e93cdc9cd71f9926745226b'
Submodule path 'app-service-account': checked out '5f27744aab3219ff5e07ff05ecfd0d7954c77a5c'
Submodule path 'app-service-mod': checked out '67723d272e26d8854ad1a44d78be85cbe42f94dd'
Submodule path 'app-service-order': checked out '8337e4544e82cd6b7fa5e8ab41d78f2726c81db4'
Submodule path 'app-service-parent': checked out '3d42af5335622b30d3cf770aba3778bebc991cc6'
Submodule path 'app-service-product': checked out 'b74a4812b045fa8c312b219277c33b84b099dbf9'
Submodule path 'app-service-sales': checked out 'e9d5e562b70611c4eee9cdbe1468b1c8ffc4b3c3'
Submodule path 'app-service-user': checked out '10be61d77f39fc46e025faaae1cbca1e03c3fcb6'
Submodule path 'app-service-weixin': checked out '3251ca8a5fa3232d37d188b456d58c6ae07ccd8d'

D:\code\app-service\app-service>git submodule sync
Synchronizing submodule url for 'app-base'
Synchronizing submodule url for 'app-service-account'
Synchronizing submodule url for 'app-service-mod'
Synchronizing submodule url for 'app-service-order'
Synchronizing submodule url for 'app-service-parent'
Synchronizing submodule url for 'app-service-product'
Synchronizing submodule url for 'app-service-sales'
Synchronizing submodule url for 'app-service-user'
Synchronizing submodule url for 'app-service-weixin'

```