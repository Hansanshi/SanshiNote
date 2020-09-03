# Warn!
This project has been migrated to [markidea](https://github.com/Hansanshi/mark-idea).
该项目已迁移至[markidea](https://github.com/Hansanshi/mark-idea)。
# SanshiNote
![Example1](https://raw.githubusercontent.com/Hansanshi/Image/master/Home.png)
[Demo Website](http://note.sanshicloud.cn/), username is `admin` and password is `admin`.

# Next plan
2020.05.24
Change editor, support more modes (WYSIWYG and Real Time Rendering) and other functions.
It will be completed in few months.
更换前端使用的编辑器，因为原来使用的编辑器只支持分屏预览模式，后续将支持所见即所得和及时渲染的模式。
页面的样式也将进行一定的调整。争取最近几个月有空更新下。

# Introduction
**SanshiNote** is a simple, cross-platform, self-hosted and easy-to-migrate notebook software. It's based on git and markdown.

Git component is embbed in SanshiNote to control versions of note，which means all version history of your note will be saved. You can backup your notes by pushing to remote repository (e.g. a repo on github).

Makrdown is a lightweight markup language, and it's utilized as the format of our notes. Markdown is an open standard and supported by many other editors, so you can easily transfer notes between SanshiNote and many other notebook softwares.

All your notes are stored as a file entitled `notes/<your_username>/<notebook_name>/<title>.md`.


# Quick start
SanshiNote can be deployed on where **jdk8** has been installed, and it's the only precondition.

Download the [jar file](https://drive.google.com/file/d/1EJaE-EMBK6Pq_KQLueodhfLZWvoxT4OX/view), run command

```shell
java -jar note-<version>.jar --username=<your_username> --password=<your_password>
```

Access `<host>:8080` via your brower, and start the use. Firefox or Chrome recommonded.

The password and username of default admin user is `admin` and `admin`, separately.

You can block registartion via argumant `--"register-forbidden"=true`.

# Developer guide
The whole project is devided into two parts, back-end and front-end. Back-end is based on Spring Boot, Spring Data Jpa, JGit and SQLite(you can easily change database from SQLite to MySQL, MariaDB or other).
Front-end is based Vue, ElmentUI and mavonEditor. And its repo is [here](https://github.com/Hansanshi/SanshiNote-front).
