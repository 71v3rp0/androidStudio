package com.example.androidproj1;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class JC1 extends Activity {
    int countLeft = 0;
    int countRight = 0;
    long startTime;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helloact);

        Button button1 = findViewById(R.id.button1);
        TextView textview1 = findViewById(R.id.textview1);


        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                button1.setText("меня нажжжали!");
                countLeft++;
                textview1.setText(String.valueOf(countLeft));
            }
        });

        Button button2 = findViewById(R.id.button2);
        TextView textview2 = findViewById(R.id.textview2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button2.setText("НАЖАТО!");
                countRight++;
                textview2.setText(String.valueOf(countRight));
            }
        });

        Button button3 = findViewById(R.id.button3);
        TextView textview3 = findViewById(R.id.textview3);
        TextView textview4 = findViewById(R.id.textview4);

        button3.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View V, MotionEvent event)
            {
                Date currentDate = new Date();
                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                String dateText = dateFormat.format(currentDate);
                DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                String timeText = timeFormat.format(currentDate);
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        startTime = System.currentTimeMillis();
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        long totalTime = System.currentTimeMillis() - startTime;
                        long totalSeconds = totalTime / 1000;
                        textview3.setText(String.valueOf(totalSeconds));
                        textview4.setText(String.valueOf(timeText));
                }
                return true;
            }


        });
    }
}
