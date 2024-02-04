
# Launcher BYOD

Automation program for installing and running a professional work environment on a personal computer. Supinfo project for the Lyon metropolitan area.


## Requirements

To operate, this project requires the following elements :
- Use Intellij IDEA or an equivalent IDE supporting Maven projects
- You must have Java JRE 16 or higher installed
- Your workstation or those of your users must have at least `8 GB` of RAM


## Installation

To install this project on your work environment, simply run :

```bash
  git clone <project_url>
```
```bash
  cd <project_folder>
```
## Configuration

You need to tell the launcher the URL to which it should download resources during installation

To do this, modify the `Constants.java` file in the `utils` package and adjust the `installPack` variable accordingly.

## Launch

The launcher can be launched from your IDE's runtime function or with the following command after building the `.jar` :

```bash
  java -jar launcher.java
```

## Deployment

When you want to distribute the launcher to the user, first build the `.jar` with Maven, then create the Windows executable with the :

```bash
  jpackage --type exe --input <runtime_folder> --name "Launcher BYOD - Metropole de Lyon" --main-jar Launcher-BYOD-Metropole-de-Lyon-1.0-SNAPSHOT-jar-with-dependencies.jar --main-class org.supinfo.Main --vendor "Métropole de Lyon" --icon favicon.ico --win-shortcut --win-menu
```
## Authors

Project by Supinfo Nantes students for the Lyon metropolis.

- [Supinfo](https://www.supinfo.com/)
- [Grand Lyon](https://www.grandlyon.com/)
