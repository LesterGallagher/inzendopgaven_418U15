# Rekentrainer voor jenaplanbasisschool De Fuglen.

Download het exe bestand: [FuglenRekentrainer.exe](https://esstudio.site/inzendopgaven_418U15/out/exe/FuglenRekentrainer.exe)

Download het exe bestand als een zip: [FuglenRekentrainer.zip](https://esstudio.site/inzendopgaven_418U15/out/exe/FuglenRekentrainer.zip)

Download de source: [Source](https://github.com/LesterGallagher/inzendopgaven_418U15/zipball/master)

## Vereisten

- java versie "1.8" of later
- Java(TM) SE Runtime Environment "1.8" of later

## Installatie instructies

```bash
git clone https://github.com/LesterGallagher/inzendopgaven_418U15.git
```
Nadat dit klaar is, open de folder en open het in uw IDE naar keus (getest in IntelliJ IDEA).

## Documentatie

De documentatie voor dit project is [hier](https://esstudio.site/inzendopgaven_418U15/java-doc) te vinden.

## Applicatie vrijgeven

### Vereisten

- Apache Ant
- launch4j
- jsign 

### Stappen

1. Installeer Apache Ant ([instructies](https://www.mkyong.com/ant/how-to-install-apache-ant-on-windows/))
2. Installeer launch4j en voeg het toe aan het PATH omgevingsvariabel ([download](https://sourceforge.net/projects/launch4j/files/launch4j-3/3.12))
3. Installeer jsign-2.1.jar en voeg het toe aan het PATH omgevingsvariabel ([download](https://ebourg.github.io/jsign))
4. **Run de "deploy" Ant task.**

Documentatie voor launch4j: [http://launch4j.sourceforge.net](http://launch4j.sourceforge.net)

Documentatie voor jsign: [https://ebourg.github.io/jsign](http://launch4j.sourceforge.net)

## Foutopsporing

#### error: Could not find or load main class com.company.Main:
Probeer het project opnieuw te bouwen. In intelliJ: Build -> Rebuild Project en Build -> Build Artifacts.

Als dat niet werkt: [stackoverflow](https://stackoverflow.com/questions/10654120/error-could-not-find-or-load-main-class-in-intellij-ide)


