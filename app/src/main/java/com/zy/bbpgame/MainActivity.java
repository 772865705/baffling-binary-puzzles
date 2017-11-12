package com.zy.bbpgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridLayout;

import com.zy.bbpgame.view.NumberGridLayout;
import com.zy.bbpgame.view.OneNumberView;

/**
 * baffling binary puzzlgit remote add origin git@github.com:772865705/baffling-binary-puzzles.gites
 * 一个类似数独的游戏
 */
public class MainActivity extends AppCompatActivity {

    OneNumberView nbview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final NumberGridLayout gridLayout = findViewById(R.id.grid);
        gridLayout.setColumnCount(10);
        gridLayout.setRowCount(10);
        gridLayout.fillAll();
//        gridLayout.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                gridLayout.fillAll();
//            }
//        },120);

//        nbview = findViewById(R.id.nbview);

    }
}
