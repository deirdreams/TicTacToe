# TicTacToe
Coding challenge for HolidayCheck

An implementation of Tic Tac Toe on the terminal, where you play against an AI that uses the [minimax algorithm](https://www.neverstopbuilding.com/blog/2013/12/13/tic-tac-toe-understanding-the-minimax-algorithm13).

## Gameplay

Run:

    javac Main.java Board.java Computer.java
    java Main

Player will choose from one the the 9 available cells using the number corresponding to the cell.

    +---+---+---+
    | 0 | 1 | 2 |
    +---+---+---+
    | 3 | 4 | 5 |
    +---+---+---+
    | 6 | 7 | 8 |
    +---+---+---+

## The Computer

The computer comes in three different difficulty levels. The difficulty is characterised by the probability of making a random move **not** according to the minimax algorithm.

 - Easy: 0.8
 - Med: 0.4
 - Hard: 0.2

## Notes

 - The player is always 'X' and the computer is always 'O'
 - The player always chooses first