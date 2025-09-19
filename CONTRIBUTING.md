# CONTRIBUTING.md

> [!TIP]
> Hey! Are you looking to translate? Check [this MD out!](TRANSLATE.md)


To contribute, you must have Android Studio installed, aswell as Git.

## Download part
1. Download the Android Studio IDE.
2. Download Git for your operating system.
3. Open Terminal and put in the following command in the directory you wanna clone the repository into (cd to it).

`git clone https://github.com/acer51-doctom/TheZodiacApp.git; cd TheZodiacApp`

## Coding part
1. Open the IDE and locate the repo you just cloned.
2. In the terminal again, type in the following:

`git switch -c <username>/<feature_name>`

3. Then, you can start doing the changes you want.
4. After, after you commited the changes, type in the following:

`git remote add origin-personal https://github/<your-user-name>/TheZodiacApp.git && git push -u origin-personal <branch_name>`

It will publish your changes to your fork-repo.

> [!WARNING]
> Any PRs that only modified the languages will automatically be closed. Redo your work while following [TRANSLATE.md](TRANSLATE.md).