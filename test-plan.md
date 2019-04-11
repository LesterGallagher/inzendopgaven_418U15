# Fuglen Rekentrainer Test Plan Inhoud

1. Aanduiding

# Fuglen Rekentrainer Test Plan

## Aanduiding

Testplan versie 0.1.0 (11-3-2019)
VCS: Git (https://github.com/LesterGallagher/inzendopgaven_418U15.git)
Naam: Sem Postma
Telefoon: ***
Email: ***
Organisatie: ***

## Introductie

De strekking van dit test plan omvangt alle tests omtrent deze applicatie.
Het doel van dit test plan is om alle functies van de applicatie grondig te testen.

## Test Onderdelen

De onderdelen die worden getest moeten worden zijn:

- Dynamische testen
    - Het testen van het eindproduct aan de hand van het functioneel ontwerp.
    - Testen dat het eindresultaat exe kan runnen op elke windows pc platforms.
    - Testen van de ontwikkelomgeving
    - Testen van de documentatie
    - Testen van de installatie instructies
- Statische testen
    - Regressie testen
    - Functionaliteittest
    - Middelengebruik
    
## Resultaten

- Code testdekking

## Softwarerisico's

Complexiteit bij het bundelen van de applicatie in één ".exe" bestand. 
 
## Functies die moeten worden getest

Risco's: Hoog (H), Middelmatig (M), Laag (L).

- Start scherm (L)
- Keuze scherm (H)
- Oefening schermen
    - Moeilijkheidsgraad (H)
    - Optelsommen (L)
    - Minsommen (L)
    - Vermenigvuldigen (L)
    - Delen (M)
    - Willekeurig (M)
    - Voortgang scoreverloop (M)
    - Eindresultaat (score) (H)
    - Het opslaan van rekentrainsessies. (L)
- Logboek (L)
- De applicatie moet portable zijn en via een USB stick uitgevoert kunnen worden. (M)
   
## Functies die niet moeten worden getest

Alle tests, op de regressie test na, moeten worden uitegevoerd na een grote verandering 
of wanneer de hele applicatie compleet getest moet worden.

## Aanpak

Bij het doen van een complete functionaliteits test moeten eerst de unit tests worden uitgevoerd.
Wanneer er een nieuwe release wordt gemaakt aangemaakt, moet de regressie test worden uitgevoerd

### Hulpmiddelen

Voor het uitvoeren van unit tests wordt het hulpmiddel "JUnit" gebruikt.
Om. Er is geen extra training nodig om deze tool te gebruiken. 

### Informatie verzameling

De informatie die verzamelt wordt zijn "code coverage", middelengebruik en test resultaten.

### Hardware en software

De tests moeten worden uitgevoerd op een windows pc met windows 10. 
Deze pc moet java versie "1.8" of later hebben. 
Java(TM) SE Runtime Environment "1.8" of later hebben.
JAVA JDK versie 11 of later hebben.

### Regressie tests

Als er een kleine verandering heeft plaatsgevonden in een specifieke functie, 
moet de regressie test uitgevoerd te worden. 
Ook moet het testen van het eindproduct aan de hand van het functioneel ontwerp en
het testen van de documentatie gedeeltelijk getest te worden. 
Alleen de onderdelen die betrekking hebben op de verandering dienen getest te worden. 
Bijvoorbeeld na het veranderen van start scherm hoeft, bij het testen van de documentatie, 
alleen de documentatie van het start scherm getest te worden. 
Het testen van de installatie instructies hoeft alleen wanneer er aanpassingen gemaakt 
zijn in de installatie procedure of de installatie instructies. 
Testen van de ontwikkelomgeving hoeft alleen wanneer er aanpassingen zijn gemaakt 
met betrekking op de ontwikkelomgeving.

## Criteria

- Unit tests
    - Alle tests zijn compleet zonder fouten
    - Code coverage tool geeft aan dat meer dan 80% van de methoden covered is.
- Middelengebruik
    - Carbage collection moet minder dan 100 milliseconden duren.
    - Jar bestand grootte moet minder dan 5 MB zijn.
- Testen dat het eindresultaat exe kan runnen op elke windows pc platforms.
    - Het eindresultaat moet op iniedergeval 3 verschillende windows pc's getest worden.
- Testen van de ontwikkelomgeving
    - Binnen de ontwikkelomgeving moeten Java programma's ontwikkelt kunnen wordne
    - Binnen de ontwikkelomgeving moeten ant tasks uitgevoerd kunnen worden.
    - Binnen de ontwikkelomgeving moeten unit tests uitgevoerd kunnen worden.
- Testen van de documentatie
    - De documentatie moeten up-to-date en correct zijn.
- Testen van de installatie instructies
    - De installatie instructies moeten up-to-date en correct zijn.

