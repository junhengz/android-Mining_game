package com.example.assignment3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment3.model.Mines;

import java.util.Random;
import java.util.ResourceBundle;

public class game extends AppCompatActivity {

    SharedPreferences selected_option;
    private int NUM_ROWS;
    private int NUM_COLS;

    private int NUM_MINE;


    Button buttons[][];
    private int[][] Mineset;
    private int scan_used = 0;

    public static Intent makeLaunchgame (Context c)
    {
        Intent intent = new Intent(c, game.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selected_option = getSharedPreferences("preference",MODE_PRIVATE);
        NUM_ROWS = selected_option.getInt("selected_row",4);
        NUM_COLS = selected_option.getInt("selected_col",6);
        NUM_MINE = selected_option.getInt("selected_num_mine",6);
        buttons = new Button[NUM_ROWS][NUM_COLS];


        Mines mineset = Mines.getInstance();
        mineset.setMines(NUM_ROWS,NUM_COLS,NUM_MINE);
        Mineset = mineset.getMine();


        setContentView(R.layout.activity_game);
        createButtons();
        Toast.makeText(getApplicationContext(),"You need to find "+NUM_MINE+" LEGO bricks",Toast.LENGTH_SHORT).show();


    }

    private void createButtons()
    {
        //set the initial messages
        TextView remain_ini = (TextView) findViewById(R.id.found);
        TextView detected_ini = (TextView) findViewById(R.id.detected);
        remain_ini.setText("Found "+0+" of "+NUM_MINE+" LEGO bricks");
        detected_ini.setText("# Scans used: "+scan_used);
        SharedPreferences timeplayed_ini = getSharedPreferences("preference",MODE_PRIVATE);
        int timesplayed_ini = timeplayed_ini.getInt("times",0);
        TextView timesplay_ini = (TextView) findViewById(R.id.times_played);
        timesplay_ini.setText("Times Played: "+ timesplayed_ini);


        TableLayout table = (TableLayout) findViewById(R.id.gameTable);

        for (int row = 0;row < NUM_ROWS; row++)
        {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            ));
            table.addView(tableRow);
            for (int col = 0; col<NUM_COLS;col++)
            {
                final int FINAL_COL = col;
                final int FINAL_ROW = row;
                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f
                ));
                button.setPadding(0,0,0,0);
                button.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v)
                    {
                        gridButtonClicked(FINAL_COL,FINAL_ROW);

                    }


                });


                buttons[row][col] = button;
                tableRow.addView(button);







            }
        }
    }



    private void gridButtonClicked(int col, int row) {
        final Button button = buttons[row][col];
        lockButtonSizes();





        if (Mineset[row][col] == 1) {
            Mineset[row][col] = 0;
            for (int row_count = 0; row_count < NUM_ROWS; row_count++) {
                Button btn = buttons[row_count][col];
                if (btn.getText().length() != 0) {
                    int near = calculate_nearby(col, row_count);
                    btn.setText("" + near);

                }
            }
            for (int col_count = 0; col_count < NUM_COLS; col_count++) {
                Button btn2 = buttons[row][col_count];
                if (btn2.getText().length() != 0) {
                    int near2 = calculate_nearby(col_count, row);
                    btn2.setText("" + near2);

                }
            }
            int max_width = button.getWidth();
            int max_height = button.getHeight();
            Bitmap origin = BitmapFactory.decodeResource(getResources(), R.mipmap.lego);
            Bitmap scaled = Bitmap.createScaledBitmap(origin, max_width, max_height, true);
            Resources res = getResources();
            button.setBackground(new BitmapDrawable(res, scaled));

            //get the found num of mine
            int num_mine_remain = 0;
            for (int row2 = 0; row2 < NUM_ROWS; row2++) {
                for (int col2 = 0; col2 < NUM_COLS; col2++) {
                    num_mine_remain = num_mine_remain + Mineset[row2][col2];
                }
            }


            TextView remain = (TextView) findViewById(R.id.found);
            remain.setText("Found " + (NUM_MINE - num_mine_remain) + " of " + NUM_MINE + " LEGO bricks");
            if (num_mine_remain == 0)
                finishgame();
        } else {
            int nearby = calculate_nearby(col, row);
            button.setText("" + nearby);
            TextView detected = (TextView) findViewById(R.id.detected);
            scan_used++;
            detected.setText("# Scans used: " + scan_used);
        }
    }



    private int calculate_nearby(int mine_col, int mine_row)
    {
        int sum = 0;
        for(int row = 0;row<NUM_ROWS;row++)
            sum=sum+Mineset[row][mine_col];
        for(int col = 0;col<NUM_COLS;col++)
            sum=sum+Mineset[mine_row][col];
        return sum;
    }

    private void lockButtonSizes()
    {
        for (int row = 0;row<NUM_ROWS;row++)
        {
            for (int col = 0;col<NUM_COLS;col++)
            {
                Button button = buttons[row][col];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }
    private void finishgame()
    {
        SharedPreferences timeplayed = getSharedPreferences("preference",MODE_PRIVATE);
        int timesplayed = timeplayed.getInt("times",0);
        timesplayed++;
        timeplayed.edit().putInt("times",timesplayed).commit();

        TextView timesplay = (TextView) findViewById(R.id.times_played);
        timesplay.setText("Times Played: "+ timesplayed);

        ImageView Image = new ImageView(this);
        Image.setImageResource(R.mipmap.lego);

        AlertDialog.Builder message = new AlertDialog.Builder(game.this);
        message.setTitle("Congratulation!");
        message.setMessage("You have found all LEGO bricks");
        message.setView(Image);
        message.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });
        message.show();



    }

}
