# Flippy-Four

A Connect Four that rotates!


## Instructions

First, clone this repository.

```
git clone https://github.com/JasonLin43212/Flippy-Four.git
```

Next, go inside of the directory.

```
cd Flippy-Four
```

Compile the code.

```
javac FlippyFour.java
```

Finally, run the code, follow the instructions and have fun!

```
java FlippyFour
```

## Development Log

### Accomplished Features

* Displaying board in terminal

* Displaying the board in GUI

* Inserting pieces into the board in GUI

* Declare win/draw

* Restart game in GUI

* Choose board dimensions

* Choose piece colors

* Rotation of board in GUI

* Start screen to initialize game

* Arrow that indicates the next move

* Animation of pieces dropping

* Start new game

* Disable user input except to start a new game after a game ends

#### Features Added After Demo

* Different rotation modes

* GUI start menu

* Colorful start screen

* Start Screen with all features implemented

* Allowing option to have a Connect Five

* A singleplayer mode

* Navigation between start screen and game

* Having instructions on playing in gui

### Planned Features
It's done!!!
#### Remove/Unadded Features

* A way to save and load games
> We believe that this feature is unnecessary since most games do not last that long

* Bombs
> Deviates too much from original game

### Milestone

* Game is playable on Gui (1/12/18)

* Game has win messages (1/14/18)

* More COLORS (1/16/18)

* Colorful start screen (1/20/18)

* A Computer to play against (but not a very skillful computer) (1/20/18)

* Finished all of the planned features (1/20/18)

### Bugs

####Fixed

* GUI rotation being weird

* Rotating makes the game a draw

* When in 'Set Interval' mode, after fulfilling the amount of pieces to start the rotation, the number of pieces to insert jumps to 6. This doesn't affect the gameplay, however, because it goes back to 5 after rotation is done.

* In random rotation, if the player rotates the board, they get an extra turn.

## Playing the game

### About the settings

**Color-** You can choose the color of the pieces for both colors. The options for color are red, orange, yellow, green, blue, cyan, magenta, purple, pink, and brown.

**Height and Width-** You can select the size of your board. The minimum width/height is 5 cells and the maximum is 11 cells.

**Rotation Type-** There are different rotation modes that you can choose.
* Player Only- In this mode, the board will not rotate by itself. The only way that you can get the board to rotate is if the player chooses to do so.
* Random Rotation- In this mode, the board will rotate randomly to the left or right. When it rotates, it doesn't count as a turn.
* Set Interval- In this mode, the board will rotate for every 5 pieces that are placed on the board. Each of these rotations do not count as a turn.

**Winning Number-** This number is the number of pieces in a row you need to have to win.

**Player Rotation-** When this is checked, then the player will be able to rotate the board. If it is unchecked, then the player won't be able to rotate the baord.

**Singleplayer Mode-** When this is checked, you can play against a computer. This computer is not skillful, cannot think properly, and makes random moves. When this is unchecked, you can either play against yourself or with another entity.

### Playing

These instructions are displayed in the game but here they are if you want to look at them.

* ⬅️ Moves the pointer left by one cell.
* ➡️ Moves the pointer right by one cell.
* [Space] Drops a piece whereever the point is located
* [Q] Rotates the board to the left ↪️
* [E] Rotates the board to the right ↩️

### Different Modes to Try out

Name|Width|Height|Rotation Type|Winning Number|Allow Player Rotation|Singleplayer
----|----|----|----|----|----|----

## Contributors
**Team JacKeL**
* Jason Lin
* Karen Li



![alt text](http://www.krugerpark.co.za/images/1jackal-gc590a.jpg "A Jackel")
