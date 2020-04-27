## 同时配置gitHub和gitLab
## 生成公私密钥
执行下面命令生成密钥：

    ssh-keygen -t rsa -C "注册 gitlab 账户的邮箱"
    提示要输入名称时输入 id_rsa_gitlab

    ssh-keygen -t rsa -C "注册 github 账户的邮箱"
    提示要输入名称时输入 id_rsa_github

## 提供公钥给服务器

    复制 ~/.ssh/id_rsa_gitlab.pub文件内容，进入gitlab / profile / SSH Keys，将公钥内容添加至 gitlab 。
    复制 ~/.ssh/id_rsa_github.pub文件内容，进入github / setting / SSH and GPG keys / New SSH key 将公钥内容添加至 github 。

## 更新SSH配置

SSH 配置信息加载顺序如下：

    命令行配置参数
    用户级别的配置文件~/.ssh/config
    系统级别的配置文件 /etc/ssh/ssh_config

根据我们实际情况，更新用户级别配置信息即可，打开 SSH 客户端配置文件 ~/.ssh/config 增加配置项，如果没有就创建一个（是文本文件）。

> SSH配置项有很多，详见：https://man.openbsd.org/ssh_config 或r https://www.ssh.com/ssh/config/

在配置文件中加入以下内容
```bash
Host github.com
    HostName github.com
    User githubuser@xyz.com
    IdentityFile ~/.ssh/id_rsa_github

Host gitlab.com
    HostName gitlab.com
    User gitlabuser@xyz.com
    IdentityFile ~/.ssh/id_rsa_gitlab
```

## 配置仓库用户信息
Git 配置信息也有三个地方可以存储，根据加载顺序依次为：

    /etc/gitconfig 文件: 包含系统上每一个用户及他们仓库的通用配置。 如果使用带有 --system 选项的 git config 时，它会从此文件读写配置变量。
    ~/.gitconfig 或 ~/.config/git/config 文件只针对当前用户。 可以传递 --global 选项让 Git 读写此文件。
    当前使用仓库的 Git 目录中的 config 文件（就是 .git/config）

不同仓库链接不同的服务器，所用的git用户信息也不同。可以把常用的git用户信息配置到 ~/.gitconfig 中，不常用的我们在仓库中单独配置。以常用 gitlab 为例：

    git config --global user.name "githubuser"
    git config --global user.email "githubuser@xyz.com"

进入本地 github 仓库配置 git 用户信息

    git config --local user.name WeisonWei
    git config --local user.email weixx3@126.com
