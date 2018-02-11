/**
 * Java 1. Home work 4 part_2
 *
 *@author Olesya Dinmukhametova
 *@version dated Feb 09, 2018
 */
import java.util.Random;
import java.util.Scanner;

class Java01HW04_part2 {

    //final int SIZE = 3;
    final char DOT_X = 'x';
    final char DOT_O = 'o';
    final char DOT_EMPTY = '.';
    Scanner sc = new Scanner(System.in);
    Random rand = new Random();

    public static void main(String[] args) {
        new Java01HW04_part2();
    }

    Java01HW04_part2() {
        System.out.print("Enter SIZE: ");
        int SIZE = sc.nextInt();                         //задаем размер поля
        System.out.println();
        System.out.print("Enter number of chips: ");
        int f = sc.nextInt();                            //задаем кол-во фишек
        System.out.println();
        char[][] map = new char[SIZE][SIZE];
        initMap(map, SIZE);
        
        //считаем кол-во выйгрышных вариантов q
        int q = (SIZE - f + 1) * (SIZE * 2 + 2);
        int n = SIZE - 1;
        if (n >= f) {
            for (n = SIZE - 1; n >= f; n-= 1) {
                q += (n - f + 1) * 4;
            }
        }
        int aiFound = 0;
        int aiNotFound = 0;
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                for (int z = 1; z <= 4; z++) {
                    map[i][j] = DOT_X;
                    //System.out.println("Zmejka: " + z);
                    switch (z) {
                        case 1: //создаем горизонтальную змейку
                            for (int x = 1; x < f; x++) {
                                if ((j+x) < SIZE)
                                    map[i][j+x] = DOT_X;
                            }
                            break;
                        case 2://создаем диагональную вниз и вперед змейку
                            for (int x = 1; x < f; x++) {
                                if ((i+x) < SIZE && (j+x) < SIZE)
                                    map[i+x][j+x] = DOT_X;
                            }
                            break;
                        case 3://создаем вертикальную змейку
                            for (int x = 1; x < f; x++) {
                                if ((i+x) < SIZE)
                                    map[i+x][j] = DOT_X;
                            }
                            break;
                        case 4://создаем диагональную вниз и назад змейку
                            for (int x = 1; x < f; x++) {
                                if ((i+x) < SIZE && (j-x) >= 0)
                                    map[i+x][j-x] = DOT_X;
                            }
                            break;
                    }
                    //расчитаем aiFound - сколько checkWin2 найдет выйгрышных вариантов
                    if (checkWin2(map, SIZE, f, DOT_X)) aiFound += 1;
                    initMap(map, SIZE);
                }
            }
        }
        System.out.println("Quintuty of wins: " + q);
        System.out.println("aiFound              : " + aiFound);
        System.out.println();
        if (q == aiFound)
            System.out.println("Test passed!");
        else
            System.out.println("Test faild!");
    }

    boolean checkWin2(char[][] map, int SIZE, int f, char dot) {
        int a = SIZE - f + 1;
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                int[] z = new int[4];
                if (map[i][j] == dot){
                    for (byte l = 0; l < 4; l++) z[l] = 1;
                }
                for (int x = 1; x < f; x++) {
                    //анализируем горизонтальную змейку
                    if ((j+x) < SIZE) {
                        if (map[i][j+x] == dot && z[0] != 0) {
                            z[0] += 1;
                            if (z[0] == f) return true;
                        } else z[0] = 0;
                    }
                    //анализируем диагональную вниз и вперед змейку
                    if ((i+x) < SIZE && (j+x) < SIZE) {
                        if (map[i+x][j+x] == dot && z[1] != 0) {
                            z[1] += 1;
                            if (z[1] == f) return true;
                        } else z[1] = 0;
                    }
                    //анализируем вертикальную змейку
                    if ((i+x) < SIZE) {
                        if (map[i+x][j] == dot && z[2] != 0) {
                            z[2] += 1;
                            if (z[2] == f) return true;
                        } else z[2] = 0;
                    }
                    //анализируем диагональную вниз и назад змейку
                    if ((i+x) < SIZE && (j-x) >= 0) {
                        if (map[i+x][j-x] == dot && z[3] != 0) {
                            z[3] += 1;
                            if (z[3] == f) return true;
                        } else z[3] = 0;
                    }
                }
            }
        }
        return false;
    }

    void initMap(char[][] map, int SIZE) {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                map[i][j] = DOT_EMPTY;
    }
}