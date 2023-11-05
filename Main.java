package com.example;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;


public class Main extends Application {

    static final int WINDOW_SIZE=800;
    static final int TILES_NUMBER=200;
    static Rectangle[][] board;
    static boolean isBlue = false, isRed = false, isGreen = false, isBrown = false;
    static Random rand = new Random();

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane root = new GridPane();

        Scene scene = new Scene(root, WINDOW_SIZE+5, WINDOW_SIZE+5);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Game of life");
        primaryStage.show();

        initialization(root);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(90), event -> step()));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public static void step()
    {
        boolean[][] black = new boolean[TILES_NUMBER+2][TILES_NUMBER+2];
        boolean[][] black2 = new boolean[TILES_NUMBER+2][TILES_NUMBER+2];
        boolean[][] black3 = new boolean[TILES_NUMBER+2][TILES_NUMBER+2];
        boolean[][] black4 = new boolean[TILES_NUMBER+2][TILES_NUMBER+2];

        for (int row = 2; row <board.length-2; row++) {
            for (int column = 2; column <board.length-2; column++) {
                int number = howManyNeighbours(row, column);
                int number2 = howManyNeighbours3(row, column);
                int number3 = howManyNeighbours2(row, column);
                int number4 = howManyNeighbours4(row, column);
                isBlue = false; isRed = false; isGreen = false; isBrown = false;
                if((number == 2 || number==3) && board[row][column].getFill()==Color.BLUE)
                {
                    black[row][column]=true;
                }
                if((number==3 || number == 6)&& board[row][column].getFill()!=Color.BLUE)
                {
                    isBlue=true;
                }
                if((number2 < 2 || number2>3) && board[row][column].getFill()==Color.RED)
                {
                    black2[row][column]=true;
                }
                if(number2==3 && board[row][column].getFill()!=Color.RED)
                {
                    isRed=true;
                }
                if((number3 == 5 || number3 == 14) && board[row][column].getFill()==Color.GREEN)
                {
                    black3[row][column]=true;
                }
                if((number3>5 && number3<10) && board[row][column].getFill()!=Color.GREEN)
                {
                    isGreen=true;
                }
                if(number4 > 5 && board[row][column].getFill()==Color.BROWN)
                {
                    black4[row][column]=true;
                }
                if(number4==7 && board[row][column].getFill()!=Color.BROWN)
                {
                    isBrown=true;
                }

                if(isBlue && isRed)
                {
                    if(rand.nextInt(3)%3==0)
                    {
                        black2[row][column]=true;
                    }
                    else
                    {
                        black[row][column]=true;
                    }
                }
                else if(isBlue && isGreen)
                {
                    if(rand.nextInt(4)%4==0)
                    {
                        black3[row][column]=true;
                    }
                    else
                    {
                        black[row][column]=true;
                    }
                }
                else if(isBlue && isBrown)
                {
                    if(rand.nextInt(5)%5==0)
                    {
                        black4[row][column]=true;
                    }
                    else
                    {
                        black[row][column]=true;
                    }
                }
                else if(isGreen && isBrown)
                {
                    if(rand.nextInt(4)%4==0)
                    {
                        black3[row][column]=true;
                    }
                    else
                    {
                        black4[row][column]=true;
                    }
                }
                else if(isRed && isGreen)
                {
                    if(rand.nextInt(2)%2==0)
                    {
                        black2[row][column]=true;
                    }
                    else
                    {
                        black3[row][column]=true;
                    }
                }
                else if(isRed && isBrown)
                {
                    if(rand.nextInt(4)%4==0)
                    {
                        black2[row][column]=true;
                    }
                    else
                    {
                        black4[row][column]=true;
                    }
                }
                else {
                    if (isBlue) {
                        black[row][column] = true;
                    }
                    if (isRed) {
                        black2[row][column] = true;
                    }
                    if (isGreen) {
                        black3[row][column] = true;
                    }
                    if (isBrown) {
                        black4[row][column] = true;
                    }
                }
            }
        }
        for (int row = 2; row < board.length-2; row++) {
            for (int column = 2; column < board.length-2; column++) {
                if(black[row][column])
                {
                    board[row][column].setFill(Color.BLUE);
                }
                else if(black2[row][column])
                {
                    board[row][column].setFill(Color.RED);
                }
                else if(black3[row][column])
                {
                    board[row][column].setFill(Color.GREEN);
                }
                else if(black4[row][column])
                {
                    board[row][column].setFill(Color.BROWN);
                }
                else{
                    board[row][column].setFill(Color.BEIGE);
                }
            }
        }
    }

    public static void initialization(GridPane root)
    {
        board = new Rectangle[TILES_NUMBER+2][TILES_NUMBER+2];

        for (int row = 0; row <board.length; row++) {
            for (int column = 0; column <board.length; column++) {
                Rectangle rec = new Rectangle(WINDOW_SIZE/TILES_NUMBER, WINDOW_SIZE/TILES_NUMBER, Color.BEIGE);
                if(Math.random()<0.2 && row!=0 && column!=0 && row!=board.length-1 && column != board.length-1
                  && row<TILES_NUMBER/2 && column<TILES_NUMBER/2)
                {
                    rec.setFill(Color.BLUE);
                }
                else if(Math.random()<0.2 && column!=0 && row!=board.length-1 && column != board.length-1
                        && row>TILES_NUMBER/2 && column<TILES_NUMBER/2)
                {
                    rec.setFill(Color.RED);
                }
                else if(Math.random()<0.2 && row!=0 && row!=board.length-1 && column != board.length-1
                        && row<TILES_NUMBER/2 && column>TILES_NUMBER/2)
                {
                    rec.setFill(Color.GREEN);
                }
                else if(Math.random()<0.2 && row!=board.length-1 && column != board.length-1
                        && row>TILES_NUMBER/2 && column>TILES_NUMBER/2)
                {
                    rec.setFill(Color.BROWN);
                }
                board[row][column] = rec;
                root.add(rec, column, row);
            }
        }
    }

    public static int howManyNeighbours(int row, int column)
    {
        int counter=0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j <2; j++) {
                if(!(i==0 && j==0) && board[row+i][column+j].getFill()==Color.BLUE)
                {
                    counter++;
                }
            }
        }
        return counter;
    }

    public static int howManyNeighbours2(int row, int column)
    {
        int counter=0;
        for (int i = -2; i < 3; i++) {
            for (int j = -2; j <3; j++) {
                if(!(i==0 && j==0) && board[row+i][column+j].getFill()==Color.GREEN)
                {
                    counter++;
                }
            }
        }
        return counter;
    }

    public static int howManyNeighbours3(int row, int column)
    {
        int counter=0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j <2; j++) {
                if(!(i==0 && j==0) && board[row+i][column+j].getFill()==Color.RED)
                {
                    counter++;
                }
            }
        }
        return counter;
    }

    public static int howManyNeighbours4(int row, int column)
    {
        int counter=0;
        for (int i = -2; i < 3; i++) {
            for (int j = -2; j <3; j++) {
                if(!(i==0 && j==0) && board[row+i][column+j].getFill()==Color.BROWN)
                {
                    counter++;
                }
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        launch(args);
    }
}