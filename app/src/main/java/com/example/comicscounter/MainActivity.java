package com.example.comicscounter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import androidx.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {

    Context cc = this;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("date", Integer.parseInt((LocalDate.now()).toString().substring(8)));
        editor.putInt("month", Integer.parseInt((LocalDate.now()).toString().substring(5, 7)));
        editor.putInt("today", 0);
        editor.putInt("total", 0);
        editor.putInt("min", 0);
        editor.putInt("max", 0);
        editor.putStringSet("array", Collections.emptySet());

        editor.apply();

        printEverything();

        Button plus_button = new Button(this);

        plus_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(cc);
                int today = sharedPreferences1.getInt("today", 0);

                SharedPreferences.Editor editor1 = sharedPreferences1.edit();

                editor1.putInt("today", today + 1);
                editor1.apply();
                System.out.println("Button clicked");

                printToday();
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        printEverything();
    }



    protected void printEverything() {
        printDate();
        printToday();
        printTotal();
        printMin();
        printMax();
        printArray();
        printButton();
    }

    protected void printText(TextView tv) {
        tv.setVisibility(View.VISIBLE);
    }

    @SuppressLint("SetTextI18n")
    protected void printDate() {
        TextView date_text = this.findViewById(R.id.date);
        printText(date_text);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            date_text.setText((LocalDate.now()).toString());
        }
        else {
            date_text.setText("SDK error getting date");
        }
    }

    @SuppressLint("SetTextI18n")
    protected void printToday() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int today = sharedPreferences.getInt("today", 0);
        System.out.println(today);
        TextView today_text = this.findViewById(R.id.today);
        printText(today_text);
        today_text.setText("Сегодня: " + today);
    }


    @SuppressLint("SetTextI18n")
    protected void printTotal() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        TextView total_text = this.findViewById(R.id.total);

        printText(total_text);
        int total = sharedPreferences.getInt("total", 0);
//        SharedPreferences.Editor editor= sharedPreferences.edit();
//        editor.putInt("total", total+1);
//        editor.apply();
        total_text.setText("Всего: " + total);
    }

    @SuppressLint("SetTextI18n")
    protected void printMin() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        TextView min_text = this.findViewById(R.id.min);
        printText(min_text);
        int min = sharedPreferences.getInt("min", 0);
        min_text.setText("Минимум: " + min);
    }

    @SuppressLint("SetTextI18n")
    protected void printMax() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        TextView max_text = this.findViewById(R.id.max);
        printText(max_text);
        int max = sharedPreferences.getInt("max", 0);
        max_text.setText("Максимум: " + max);
    }

    protected void printArray() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        TextView array_text = this.findViewById(R.id.array);
        Set<String> set = sharedPreferences.getStringSet("array", new HashSet<>());
//        ArrayList<Integer> array_nums = new ArrayList<>();
        Object[] array = set.toArray();
        printText(array_text);
        array_text.setText("Список:\n");
        try {
            array_text.append(String.valueOf(array[0]));
        }
        catch (ArrayIndexOutOfBoundsException ignored) {}
    }

    protected void printButton(){
        //
    }

    protected void add1ToToday(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("today", sharedPreferences.getInt("today", 0) + 1);
        editor.apply();
    }

    protected void addElementToArray() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }
}