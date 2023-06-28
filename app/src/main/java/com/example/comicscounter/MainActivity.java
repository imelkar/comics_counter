package com.example.comicscounter;

import static java.time.MonthDay.now;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

//    SharedPreferences sharedPref = getSharedPreferences("mypref",0);

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("date", (now().getDayOfMonth()));
        editor.putInt("month", (now().getMonthValue()));
        editor.putInt("today", 0);
        editor.putInt("total", 0);
        editor.putInt("min", 0);
        editor.putInt("max", 0);
        editor.putStringSet("array", Collections.emptySet());

        editor.apply();

        printEverything();

//        Plus_Button plus_button = new Plus_Button();
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
    }

    protected void printText(TextView tv, int corx, int cory) {
        tv.setX(corx);
        tv.setY(cory);
        tv.setVisibility(View.VISIBLE);
    }

    protected void printDate() {
        TextView date_text = this.findViewById(R.id.date);
        printText(date_text, 20, 20);

        Date date = new Date();
        int number = date.getDate();
        int month = date.getMonth() + 1;
        date_text.setText("Дата: " + number + "/" + month);
    }

    protected void printToday() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int today = sharedPreferences.getInt("today", 0);
        TextView today_text = this.findViewById(R.id.today);
        printText(today_text, 20, 80);
        today_text.setText("Сегодня: " + today);
    }

    protected void printTotal() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        TextView total_text = this.findViewById(R.id.total);

        printText(total_text, 20, 140);
        int total = sharedPreferences.getInt("total", 0);
//        SharedPreferences.Editor editor= sharedPreferences.edit();
//        editor.putInt("total", total+1);
//        editor.apply();
        total_text.setText("Всего: " + total);
    }

    protected void printMin() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        TextView min_text = this.findViewById(R.id.min);
        printText(min_text, 20, 200);
        int min = sharedPreferences.getInt("min", 0);
        min_text.setText("Минимум: " + min);
    }

    protected void printMax() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        TextView max_text = this.findViewById(R.id.max);
        printText(max_text, 20, 260);
        int max = sharedPreferences.getInt("max", 0);
        max_text.setText("Максимум: " + max);
    }

    protected void printArray() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        TextView array_text = this.findViewById(R.id.array);
        Set<String> set = sharedPreferences.getStringSet("array", new HashSet<String>());
//        ArrayList<Integer> array_nums = new ArrayList<>();
        Object[] array = set.toArray();
        printText(array_text, 20, 380 + set.size() * 60);
        array_text.setText("Список:\n");
        try {
            array_text.append(String.valueOf(array[0]));
        }
        catch (ArrayIndexOutOfBoundsException ignored) {}
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