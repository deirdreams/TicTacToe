

# TicTacToe
Coding challenge for HolidayCheck

An implementation of Tic Tac Toe on the terminal, where you play against an AI that uses the minimax algorithm. My use of this algorithm was inspired by [this blog post](https://www.neverstopbuilding.com/blog/2013/12/13/tic-tac-toe-understanding-the-minimax-algorithm13) discussing how the minimax algorithm can be used efficiently for this game.

## Gameplay

After choosing a difficulty, the player can choose from one the the 9 available cells using the number corresponding to the cell.

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
 - Hard: 0.1

The downside of this randomness in terms of the computer is that the player can almost have a match (i.e. two in one row/col/diagonal) and the computer, even on hard mode, can randomly choose a spot that doesn't stop that match, allowing the player to win. However, this also gives the player a chance to win on hard mode.

## Notes

 - The player is always 'X' and the computer is always 'O'
 - The player always chooses first