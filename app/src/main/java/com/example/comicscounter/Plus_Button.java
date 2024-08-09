package com.example.comicscounter;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Plus_Button extends AppCompatActivity {
    Button plus_button;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        plus_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
            }
        });
    }

//    public void setX(int corx) {
//        plus_button.setX(corx);
//    }
//    public void setY(int cory) {
//        plus_button.setY(cory);
//    }
}
