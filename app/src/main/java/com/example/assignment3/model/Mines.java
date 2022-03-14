package com.example.assignment3.model;

import java.util.Random;


//the class Mines is to set up the random mine selection for the game
public class Mines {
    private int[][] mine;

    private static Mines instance;

    public static Mines getInstance()
    {
        if(instance == null) {
            instance = new Mines();
        }
        return instance;

    }

    public void setMines(int num_row,int num_col,int num_mine)
    {
        mine = new int[num_row][num_col];

        for (int row = 0;row < num_row; row++)
            for (int col = 0; col<num_col;col++)
                    mine[row][col] = 0;
            Random rand = new Random();

            int mine_index = 0;
            while(mine_index<num_mine)
            {
                int mine_row = rand.nextInt(num_row);
                int mine_col = rand.nextInt(num_col);

                if (mine[mine_row][mine_col] != 1) {
                    mine[mine_row][mine_col] = 1;
                    mine_index++;
                }
            }


    }

    public int[][] getMine()
    {
        return mine;
    }


}
