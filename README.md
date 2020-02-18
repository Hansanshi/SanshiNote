# SanshiNote
# Introduction
**SanshiNote** is a simple, cross-platform, self-hosted and easy-to-migrate notebook software. It's based on git and markdown.

Git component is embbed in SanshiNote to control versions of note，which means all version history of your note will be saved. You can backup your notes by pushing to remote repository (e.g. a repo on github).

Makrdown is a lightweight markup language, and it's utilized as the format of our notes. Markdown is an open standard and supported by many other editors, so you can easily transfer notes between SanshiNote and many other notebook softwares.

All your notes are stored as a file entitled `notes/<your_username>/<notebook_name>/<title>.md`.


# Quick start
SanshiNote can be deployed on where **jdk8** has been installed, and it's the only precondition.

Download the [jar file](https://seafile.hansanshi.ink/f/6a318fb94c294bb0bd9f/?dl=1), run command
`java -jar note-<version>.jar --username=<your_username> --password=<your_password>`.

Access `<host>:8080` via your brower, and start the use. Firefox or Chrome recommonded.

The password and username of default admin user is `admin` and `admin`, separately.

You can block registartion via argumant `--"register-forbidden"=true`.

