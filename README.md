# GameOfLife

A program presenting four different types of game of life (with different rules.)

![image](https://github.com/0Hubert0/GameOfLife/assets/95587852/951d2d6f-2df2-4c80-aea5-95355c4cbeca)

Rules:
Top left (default):
- a cell dies if number of neighbours is different than 2 or 3
- a cell is created if number of neighoubrs is equal to 3

Bottom left:
- a cell dies if number of neighbours equals to 2 or 3
- a cell is created if number of neighoubrs is equal to 3

Cells on the right side of the screen calculate their neighbours in the 5x5 square.

Top right:
- a cell dies if number of neighbours is bigger than 5
- a cell is created if number of neighoubrs is equal to 7

Bottom right:
- a cell dies if number of neighbours is different than 5 or 14
- a cell is created if number of neighoubrs is bigger than 5 and lower than 10
