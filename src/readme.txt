# CS611-Legends

Name
-------------------------------------------------------------------------------------------------
Name: Xingkun Yin
Email: yinxingk@bu.edu
BU ID: U44255956


Files
-------------------------------------------------------------------------------------------------

Armory.java
- implements Purchasable
- the armory that can be purchase and wear by hero

Battle.java
- the battle system

Board.java
- board containing cells

Cell.java
- abstract class
- a Cell of the Board

Dragon.java
- extends MonsterModel
- a dragon monster model

Exoskeleton.java
- extends MonsterModel
- an exoskeleton monster model

FireSpell.java
- extends Spell
- a fire spell spell type

GameRunner.java
- the runner for the game

HeroGallaryControl.java
- controller for HeroGallary MVC
- contain all hero model

HeroGalleryModel.java
- model for HeroGallery MVC

HeroGalleryView.java
- view for HeroGallery MVC

HeroModel.java
- abstract class
- hero model

HeroObject.java
- the actual object for a hero

HeroObjectView.java
- the view for HeroObject

PlainCell.java
- implements Placeable
- the wild part for the map that the player can meet monster

IceSpell.java
- extends Spell
- a ice spell spell type

LegendsOfValor.java
- child class of RPGGame
- the actual game logic contain that use a lot of logic from RPGGame

IceSpell.java
- extends Spell
- a lightning spell spell type

Main.java
- the main class of the program that lunch the game runner

MarketCell.java
- implements Placeable
- the market where player can buy stuff

MarketController.java
- the controller of market MVC

MarketModel.java
- the model of market MVC

MarketView.java
- the view of market MVC

MonsterGallary.java
- contain all monster model

MonsterModel.java
- abstract monster model

MonsterObject.java
- the actual object for monster

Movable.java
- interface for Movable like the player

NonAccessibleCell.java
- the cell for cell that is no accessible

Paladin.java
- model for paladin

Placable
- the interface for all placeable object, like market cell, non-accessible cell and hostile empty cell

PlaceableFactory
- the factory for placeable

Player
- player class

PlayView
- the view for player class

Potion
- the potion class

Purchasable
- the interface for purchasable items

RPGGame
- the rpg game class which contain

Sorcerer
- the model for Sorcer

Spell
- the abstract class for spell

Spirit.java
- the spirit class

Team.java
- team class that contain players

Utils
- utilities

Warrior
- the warrior hero type model class

Weapon
- the weapon class

Notes:
-------------------------------------------------------------------------------------------------
1. Factory design pattern is use for generating placeable
2. MVC for market, HeroGallary
3. the board is able to be scalable to different width length
4. this project enable add different number of players into teams
5. it is able to add different numbers of teams


How to run:
-------------------------------------------------------------------------------------------------
1. Navigate to the directory after downloading the project
2. Run the following instructions on command line in the src folder:
   javac *.java
   java Main.java

Code tested on Windows PowerShell locally.
