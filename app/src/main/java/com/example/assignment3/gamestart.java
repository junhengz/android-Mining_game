package com.example.assignment3;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

public class gamestart extends AppCompatActivity {

    public static Intent makeLaunchgamestart (Context c)
    {
        Intent intent = new Intent(c, gamestart.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamestart);


        Button start = (Button) findViewById(R.id.startgame);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = game.makeLaunchgame(gamestart.this);
                startActivity(i);
            }
        });

        Button option = (Button) findViewById(R.id.option);
        option.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = options.makeLaunchoptions(gamestart.this);
                startActivity(i);
            }
        });

        Button helps = (Button) findViewById(R.id.help);
        helps.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = Help.makeLaunchHelp(gamestart.this);
                startActivity(i);
            }
        });

    }
}
