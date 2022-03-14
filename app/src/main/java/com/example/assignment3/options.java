package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class options extends AppCompatActivity {

    public static Intent makeLaunchoptions (Context c)
    {
        Intent intent = new Intent(c, options.class);
        return intent;
    }

    private static int selected_row_size;
    private static int selected_col_size;
    private static int selected_num_mine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        final SharedPreferences selected_option = getSharedPreferences("preference",MODE_PRIVATE);

        final Spinner broadsize = findViewById(R.id.boradsize);
        String[] sizes = new String[]{"4 x 6", "5 x 10", "6 x 15", "8 x 18"};
        ArrayAdapter<String> size_arr = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sizes);
        broadsize.setAdapter(size_arr);

        int saved_row = selected_option.getInt("selected_row",4);
        int saved_col = selected_option.getInt("selected_col",6);
        int saved_mine = selected_option.getInt("selected_num_mine",6);

        if(saved_row==5&&saved_col==10)
            broadsize.setSelection(1);
        if(saved_row==6&&saved_col==15)
            broadsize.setSelection(2);
        if(saved_row==8&&saved_col==18)
            broadsize.setSelection(3);


        broadsize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int selected_size = broadsize.getSelectedItemPosition();
                if (selected_size == 0)
                {
                    selected_row_size = 4;
                    selected_col_size = 6;
                }

                if (selected_size == 1)
                {
                    selected_row_size = 5;
                    selected_col_size = 10;
                }

                if (selected_size == 2)
                {
                    selected_row_size = 6;
                    selected_col_size = 15;
                }

                if (selected_size == 3)
                {
                    selected_row_size = 8;
                    selected_col_size = 18;
                }


                selected_option.edit().putInt("selected_row",selected_row_size).commit();
                selected_option.edit().putInt("selected_col",selected_col_size).commit();


            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );


        final Spinner num_Mine = findViewById(R.id.num_mine);
        String[] num_m = new String[]{"6", "10", "15", "20"};
        ArrayAdapter<String> num_m_arr = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, num_m);
        num_Mine.setAdapter(num_m_arr);

        if(saved_mine==10)
            num_Mine.setSelection(1);
        if(saved_mine==15)
            num_Mine.setSelection(2);
        if(saved_mine==20)
            num_Mine.setSelection(3);

        num_Mine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int selected_size = num_Mine.getSelectedItemPosition();
                if (selected_size == 0)
                {
                    selected_num_mine = 6;

                }

                if (selected_size == 1)
                {
                    selected_num_mine = 10;
                }

                if (selected_size == 2)
                {
                        selected_num_mine = 15;
                }

                if (selected_size == 3)
                {
                    selected_num_mine = 20;
                }


                selected_option.edit().putInt("selected_num_mine",selected_num_mine).commit();



            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );

        Button erases = (Button) findViewById(R.id.erase);
        erases.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selected_option.edit().putInt("times",0).commit();

            }
        });




    }
}
