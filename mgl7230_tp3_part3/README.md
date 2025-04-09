# MGL7230 Énoncé du TP 3
### Hiver 2025
### Version 0.1

Dans ce suit, le genre masculin sera utilisé pour alléger le texte.

## Contexte
Je suis une entreprise specialisé sur test et j'ai reçu un contrat pour creé le Test Plan du service Booking Flight Example  

## Objectif technique de l’exercice
Fonctionnement actuel du code :
1)	Le service (part1) expose un endpoint POST qui utilise un contrat en JSON comme l'exemple suivant :
      [
            {
                  "flightNumber": "numéro du vol",
                  "passengerPassport": "passport du passager",
                  "passengerName": "nom du passager",
                  "passengerAge": age numérique du passager,
                  "passengerType": "classe du passager"
            }
      ]
2)	À la reception de ce payload, le code va valider les champs et mapper vers une classe du contrat dans le module resource
3)	Pour la création du contrat de backend, un service va valider la quantité de place disponible par type de passager et va proposer une logique d’ajustement de type de passager selon les disponibilités du vol, alors :
      a.	S’il s’agit d’un passager du type First Class, mais qu’il n’y a plus de place First Class, la logique va déplacer ce passager en Business Class
      b.	De même, s’il n’y a plus de place Business Class dans ce vol, la logique va déplacer le passager en Economy Class
      c.	Le moment que le vol est complet, un message avisera les passagers
4) Une fois le contrat de backend défini, un appel vers le service de backend (part2) va se faire
5) Le service backend (part2) reçoit le payload, calcule la distance du vol et sauvegarde dans un fichier passagerData avec un unique ID
6) Cet unique ID généré lors de la sauvegarde est après rétourné vers le service appelant (part1) qui est aprés retourné vers le client final en cas de succès.

### Exemple pour aider à la création des codes d'identification de Test Case :
#### Pour la construction du code d'identification de chaque Test Case, comme exemple :
1) pour l'entité principal, comme par exemple passager : pass
2) pour succès ou erreur : suc ou err
3) pour la sub-entité, exemple : age ou type
4) pour finalizer, ajouter un ID numérique, exemple : 01

Ces étapes expliquent le fonctionnement du code pour vous permettre de créer votre Test Plan 
En cas de doute, vous pouvez aussi utiliser des outils comme Postman, Insomnia ou Bruno (entre autres) pour effectuer des appels du type Test Exploratoire (test manuel). 

Pour cloner le code ouvrez votre terminal et exécutez le command suivant :
##  # git clone https://gitlab.info.uqam.ca/castells_f/mgl7230_tp3_part1.git
##  # git clone https://gitlab.info.uqam.ca/castells_f/mgl7230_tp3_part2.git
##  # git clone https://gitlab.info.uqam.ca/castells_f/mgl7230_tp3_part3.git

## Objectifs pédagogiques
Nos testeurs ont déjà preparé un premier draft du Test Plan avec un example de Test Suite pour les Passagers (PassengerTestSuite.feature)
L'éxercice sert à:

1)	Valider votre connaissance à créer un Test Plan
2)	Valider votre compétence pour identifier les Tests Suites à tester (Passager, Flight, Type, etc.)
3)	Valider votre capacité à créer les Tests Cases de succès et erreur dans les Tests Suites

## Vos tâches
Si vous les acceptez, consisteront en :
1)	Créer au maximum d'autres fichiers de Tests Suites features (ex.: 5)
2)  Créer des Tests Cases par Test Suite fichier de features 
3)	Implementer les nouveaux scenarios et les exécuter pour que toute passe
4)  Créer un document qui suit la structure de Test Plan comme dans l'exemple présenté en classe, c'est-à-dire à partir du diapo 28 jusqu'au diapo 33 de ce document: https://ena01.uqam.ca/mod/resource/view.php?id=4580722
5)	Une fois terminé, vous allez créer le projet dans votre gitlab d'UQAM et m'ajouter comme membre (developer).

## Barème
      Critère	                                                    |   Poids
      Création d'autres fichiers de Test Suite                      |   15%
      Création de Test Cases dans les Test Suites                   |   35%
      Implementation et exécution des tests                         |   35%
      Documentatio du Test Plan                                     |   15%
