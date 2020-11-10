import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;

/*
1. Полностью разобраться с кодом, попробовать переписать с нуля, стараясь не подглядывать в методичку;
 */


public class GameLogics {

    protected static char[][] map;
    protected static final int SIZE = 3;
    protected static final int DOTS_TO_WIN = 3;

    protected static final char DOT_EMPTY = '•';
    protected static final char DOT_X = 'X';
    protected static final char DOT_O = 'O';


  //  private static GameWindow gameWindow = new GameWindow();

    private static GameWindow gameWindow;

    protected static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }


    protected static void initMap() {
        gameWindow = new GameWindow();
        gameWindow.openGameWindow();
        gameWindow.textArea.setText("Игра");
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
                gameWindow.getButtons().get(fromIJToButtons(i,  j)).setText("•");
            }
        }
    }

    protected static void initMapAgain() {
        gameWindow = new GameWindow();
        gameWindow.openGameWindow();
        gameWindow.textArea.setText("Игра");
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
                gameWindow.getButtons().get(fromIJToButtons(i,  j)).setText("•");
            }
        }
    }

    private static int fromIJToButtons(int i, int j)
    {
        if (j == 0)
        {
            if (i == 0){return 0;}
            if (i == 1){return 1;}
            if (i == 2){return 2;}
        }
        else if (j == 1)
        {
            if (i == 0){return 3;}
            if (i == 1){return 4;}
            if (i == 2){return 5;}

        }
        else if (j == 2)
        {
            if (i == 0){return 6;}
            if (i == 1){return 7;}
            if (i == 2){return 8;}
        }
        return -1;
    }


    private static int fromButtonsToX(int index)
    {
        if (index == 0 || index == 3 || index == 6)
        {
           return 0;
        }
        else if (index == 1 || index == 4 || index == 7)
        {
            return 1;
        }
        else if (index == 2 || index == 5 || index == 8)
        {
            return 2;
        }
        return -1;
    }


    private static int fromButtonsToY(int index)
    {
        if (index == 0 || index == 1 || index == 2)
        {
            return 0;
        }
        else if (index == 3 || index == 4 || index == 5)
        {
            return 1;
        }
        else if (index == 6 || index == 7 || index == 8)
        {
            return 2;
        }
        return -1;
    }




    private static void printMap() {

        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

    }


    protected static Scanner sc = new Scanner(System.in);

    private static void humanTurn() {

        Coordinates c = new Coordinates();

        int x, y;
        do {
        //    System.out.println("Введите координаты в формате X Y");

            for (JButton button: gameWindow.getButtons() )
            {
                button.addActionListener ( new ActionListener() {
                    @Override
                    public void actionPerformed ( ActionEvent e ) {

                    //    System.out.println(" button.getX() = " + button.getX() );
                    //    System.out.println(" button.getY() = " + button.getY() );

                        c.setX(fromCoordinateXToX(button.getX()));
                        c.setY(fromCoordinateYToY(button.getY()));
                    }
                });
            }


            if (c.getX() <= 3 && c.getX() > -1 && c.getY() <= 3 && c.getY() > -1)
            {
                x = c.getX() - 1;
                y = c.getY() - 1;
            }
            else
                {
                    System.out.println("c.getX() =  " + c.getX());
                    System.out.println("c.getY() =  " + c.getY());
                    return;
                }

        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
        gameWindow.getButtons().get(fromIJToButtons(x,  y)).setText("X");
    }

    private static int fromCoordinatesToIndex(int getX, int getY)
    {
        if (getY == 0)
        {
            if (getX == 0){return 0;}
            if (getX == 94){return 1;}
            if (getX == 188){return 2;}
        }
        else if (getY == 124)
        {
            if (getX == 0){return 3;}
            if (getX == 94){return 4;}
            if (getX == 188){return 5;}

        }
        else if (getY == 248)
        {
            if (getX == 0){return 6;}
            if (getX == 94){return 7;}
            if (getX == 188){return 8;}
        }
        return -1;
    }


    private static int fromCoordinateXToX(int index)
    {
        if (index == 0)
        {
            return 1;
        }
        else if (index == 94)
        {
            return 2;
        }
        else if (index == 188 )
        {
            return 3;
        }
        return -1;
    }


    private static int fromCoordinateYToY(int index)
    {
        if (index == 0 )
        {
            return 1;
        }
        else if (index == 124 )
        {
            return 2;
        }
        else if (index == 248 )
        {
            return 3;
        }
        return -1;
    }



    private static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        if (map[y][x] == DOT_EMPTY) return true;
        return false;
    }

    private static Random rand = new Random();

    private static void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
        gameWindow.getButtons().get(fromIJToButtons(x,  y)).setText("0");
    }

    private static boolean findWinner(char symb) {
        if (map[0][0] == symb && map[0][1] == symb && map[0][2] == symb) return true;
        if (map[1][0] == symb && map[1][1] == symb && map[1][2] == symb) return true;
        if (map[2][0] == symb && map[2][1] == symb && map[2][2] == symb) return true;
        if (map[0][0] == symb && map[1][0] == symb && map[2][0] == symb) return true;
        if (map[0][1] == symb && map[1][1] == symb && map[2][1] == symb) return true;
        if (map[0][2] == symb && map[1][2] == symb && map[2][2] == symb) return true;
        if (map[0][0] == symb && map[1][1] == symb && map[2][2] == symb) return true;
        if (map[2][0] == symb && map[1][1] == symb && map[0][2] == symb) return true;
        return false;
    }

    public static void main(String[] args) {

       gameProcess();

    }


    private static void gameProcess()
    {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();

            if (findWinner(DOT_X)) {
                System.out.println("Вы победили");
                gameWindow.textArea.setText("Вы победили");
                startAgain();
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                gameWindow.textArea.setText("Ничья");
                startAgain();
                break;
            }
            aiTurn();
            printMap();
            if (findWinner(DOT_O)) {
                System.out.println("Победил AI");
                gameWindow.textArea.setText("Победил AI");
                startAgain();
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                gameWindow.textArea.setText("Ничья");
                startAgain();
                break;
            }

        }
        System.out.println("Конец игры");
    }

    private static void gameProcessAgain()
    {
            gameWindow = null;


            initMapAgain();
            printMap();
            while (true) {
                humanTurn();
                printMap();

                if (findWinner(DOT_X)) {
                    System.out.println("Вы победили");
                    gameWindow.textArea.setText("Вы победили");
                    startAgain();
                    break;
                }
                if (isMapFull()) {
                    System.out.println("Ничья");
                    gameWindow.textArea.setText("Ничья");
                    startAgain();
                    break;
                }
                aiTurn();
                printMap();
                if (findWinner(DOT_O)) {
                    System.out.println("Победил AI");
                    gameWindow.textArea.setText("Победил AI");
                    startAgain();
                    break;
                }
                if (isMapFull()) {
                    System.out.println("Ничья");
                    gameWindow.textArea.setText("Ничья");
                    startAgain();
                    break;
                }

            }
            System.out.println("Конец игры");
        }



    private static void startAgain()
    {

        gameWindow.getStartAgain().setVisible(true);
        gameWindow.getStartAgain().addActionListener ( new ActionListener() {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                gameProcessAgain();
            }
        });

    }


}