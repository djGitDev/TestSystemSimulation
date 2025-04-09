# Prè-requis pour exécuter le TP3

## Pour que le TP fonctionne, vous dévez:
1) Sauvegarder les trois parties (part1, part2, part3) dans le même directory
2) rouler mvn clean install pour creer les classes d'openapi
3) Lancer les deux Applications de la part1 et de la part2 
4) Lancer l'exécution du RunCucumber trouvé dans system-tests

## Troubleshooting
1) Si jamais vous avez des erreurs comme ça "Address already in use":
   a) allez dans la classe HttpServerStarter et changer la valeur du port pour quelque chose d'autre (ex.: 7777)
   b) sachez que vous devez aussi ajuster le port dans les autres classes utilisées pour l'appel, à savoir SavePassengersRestCall ou dans la part3 SystemCall
2) Pour tout autre problème, contactez-moi et je peux vous aider
3) Si jamais vous avez un erreur type I/O unexpected... allez-y dans la classe FlightTestDefinition, dans la ligne 59 et decommente la ligne et comment la ligne 60

## Notes
1) Malheureusement j'ai pas eu le temps de finaliser tous les tests unitaires de chaque part (part1 et part2), 
mais le but c'est de rouler les tests système alors j'ai du laisser ça de coter.
2) Théoriquement vous avez pas besoin de toucher les codes de la part1 et de la part2, mais si jamais ça arrive il n'y a pas de problème.