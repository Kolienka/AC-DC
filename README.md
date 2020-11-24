# AC-DC
Projet AC/DC Smart Contracts 2020 dans le cadre de la formation FIL d'IMT Atlantique

## Lancer le projet:

Depuis linux: 

`./start`

Depuis Windows:

`./gradlew --console=plain clean build run `

## Fonctionalités

+ Récupérer le nom de tous les scripts:
`localhost:1234/scripts`
+ Savoir si oui ou nom un script est inclut dans le projet
`localhost:1234/scripts/:nomDuScript`
+ Récupérer la liste du coût en gas du rang 1 à n d'une fonction d'un contrat 
`localhost:1234/scripts/:nomDuScript/result/:rang`


## Structure:

* Smart.Contracts.Romain
  * api
    * controllers
    * routes
    * services
      * gestion Contracts
   * generaters
   
Les contrats sont dans `solidity`

### api
Contient les éléments necessaires au fonctionnement de l'API Rest: les routes, controllers et les services, les routes communique de manière unidirectionelle aux controllers les services appelés, les controlleurs et les services communiquent entre eux de manière bidirectionelle
### generaters
Contient tous les générateurs permettant de générer de manière aléatoire des données ou tableaux de données

