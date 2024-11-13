# Dojo Poker

## Execution de programme 
- Le jeu se trouve dans le main() de la classe Game situer dans `/src/ps5/gameRunner/Game.java`
- Tous les tests se trouvent dans le repo `/Test`
## Etat de la livraison
On a réussi à implementer et tester toutes les fonctionnalités demandées.

- La classe `Card.java` définit une carte par une valeur et sa couleur (avec les enums `CardValue.java` et `CardColor.java`).
- La classe `Hand.java` regroupe des `Card` dans une `ArrayList` et `HashMap` pour pouvoir déterminer la plus forte règle appliquée. 
- La classe `HandScanner.java` s'occupe de remplir un `Hand` à partir de l'entrée standard.
- La classe `OutputHandler.java` permet de construire le message de victoire, celui-ci dépendant de la condition de victoire d'un `Hand` donné.
- La classe `GameEngine.java` determine le gagnant entre deux `Hand`.

___
Pour une documentation plus précise, veuillez consulter la JavaDoc situé dans `/javaDoc`
